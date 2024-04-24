package com.techbank.account.cmd.infrastructure;

import com.techbank.account.cmd.domain.AccountAggregate;
import com.techbank.cqrs.core.domain.AggregateRoot;
import com.techbank.cqrs.core.handlers.EventSourcingHandler;
import com.techbank.cqrs.core.infrastructure.EventStore;
import com.techbank.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Comparator;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    @Autowired
    private EventStore eventStore;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.getUncommittedChanges();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate = new  AccountAggregate();
        var events = eventStore.getEvents(id);
        //check the events are not null, then iterate it
        if (events != null && !events.isEmpty()){
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(x-> x.getVersion()).max(Comparator.naturalOrder());
        }
        return aggregate;
    }
    @Override
    public void delete() {
        eventStore.deleteEvents();
        //aggregate.markChangesAsCommitted();
    }

    @Override
    public void republishEvents() {
        var aggregateIds = eventStore.getAggregateIds();
        for(var aggregateId: aggregateIds){

            //getById and pass the aggregateId
            var aggregate = getById(aggregateId);

            if(aggregate == null || !aggregate.getActive())
                continue;
            //the get the Events for the aggregateId
            var events = eventStore.getEvents(aggregateId);
            for(var event: events){
                //String formattedMessage = MessageFormat.format("Name: {0}, Age: {1}, Salary: {2}", name, age, salary);
                String formattedMessage = MessageFormat.format("Event Name: {0}, aggregateId: {1}", event.getClass().getSimpleName(), aggregateId);
                System.out.println(formattedMessage + "\n");
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }
}
