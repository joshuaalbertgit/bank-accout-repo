package com.techbank.cqrs.core.infrastructure;

import com.techbank.cqrs.core.domain.BaseEntity;
import com.techbank.cqrs.core.queries.BaseQuery;
import com.techbank.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    //have two methods

    // 1. registerHandler - which uses /queries/QueryHandlerMethod<T extends BaseQuery>
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);

    // 2. Send - which uses /queries/abstract class BaseQuery
    <U extends BaseEntity>List<U> send(BaseQuery query);
}
