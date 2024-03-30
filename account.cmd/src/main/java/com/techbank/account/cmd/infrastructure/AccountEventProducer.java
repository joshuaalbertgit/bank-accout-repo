package com.techbank.account.cmd.infrastructure;

import com.techbank.cqrs.core.domain.AggregateRoot;
import com.techbank.cqrs.core.events.BaseEvent;
import com.techbank.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AccountEventProducer implements EventProducer {

    private final Logger logger = Logger.getLogger(AccountEventProducer.class.getName());
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, BaseEvent event) {
        logger.log(Level.WARNING, MessageFormat.format("AccountEvent Producer - Posting a message - {0}", event.getClass().toString()));
        System.out.println(MessageFormat.format("AccountEvent Producer - Posting a message - {0}", event.getClass().toString()));
        kafkaTemplate.send(topic,event);
    }
}
