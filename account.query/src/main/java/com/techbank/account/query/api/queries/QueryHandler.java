package com.techbank.account.query.api.queries;

import com.techbank.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    //define 4 handle methods, to handle 4 Find Queries
    List<BaseEntity> handle(FindAccountByHolderQuery query);
    List<BaseEntity> handle(FindAccountByIdQuery query);
    List<BaseEntity> handle(FindAccountsWithBalanceQuery query);
    List<BaseEntity> handle(FindAllAccountsQuery query);
}
