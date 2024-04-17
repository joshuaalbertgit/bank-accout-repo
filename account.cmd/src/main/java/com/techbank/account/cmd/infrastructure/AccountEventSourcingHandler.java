package com.techbank.account.cmd.infrastructure;

import com.techbank.account.cmd.domain.AccountAggregate;
import com.techbank.cqrs.core.domain.AggregateRoot;
import com.techbank.cqrs.core.handlers.EventSourcingHandler;
import com.techbank.cqrs.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    @Autowired
    private EventStore eventStore;

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
}
