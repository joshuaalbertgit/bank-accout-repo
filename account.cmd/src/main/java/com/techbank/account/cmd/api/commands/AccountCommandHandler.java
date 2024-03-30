package com.techbank.account.cmd.api.commands;

import com.techbank.account.cmd.domain.AccountAggregate;
import com.techbank.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandHandler implements CommandHandler{
    @Autowired
    private EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    //use save to open an account
    @Override
    public void handle(OpenAccountCommand command) {
        var aggregate = new AccountAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    //get the latest & make the deposit and save
    @Override
    public void handle(DepositFundsCommand command) {
        //get the aggregate
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.depositFunds(command.getAmount());
        //save
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(WithdrawFundsCommand command) {
        //get the aggregate
        var aggregate = eventSourcingHandler.getById(command.getId());
        //check enough funds are available
        if ((command.getAmount() > aggregate.getBalance())){
            throw new IllegalStateException("Insufficient Funds, Withdrawal declined.");
        }
        aggregate.withdrawFunds(command.getAmount());
        //save
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(CloseAccountCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.closeAccount();
        eventSourcingHandler.save(aggregate);

    }
}
