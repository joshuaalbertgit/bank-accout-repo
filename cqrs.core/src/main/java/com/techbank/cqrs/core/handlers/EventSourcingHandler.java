package com.techbank.cqrs.core.handlers;

import com.techbank.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler <T>{
    void save(AggregateRoot aggregate);
    T getById(String id);
    void delete();
    //restore read db operation handled via this method
    void republishEvents();
}
