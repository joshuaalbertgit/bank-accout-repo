package com.techbank.account.query.infrastructure.handlers;

import com.techbank.account.common.events.AccountClosedEvent;
import com.techbank.account.common.events.AccountOpenedEvent;
import com.techbank.account.common.events.FundsDepositedEvent;
import com.techbank.account.common.events.FundsWithdrawnEvent;
import com.techbank.account.query.domain.AccountRepository;
import com.techbank.account.query.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountEventHandler implements EventHandler {

    @Autowired
    AccountRepository accountRepository;
    @Override
    public void on(AccountOpenedEvent event)
    {
        var bankAccount = BankAccount.builder().
                    id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreatedDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();
        //now use the accountRepository to save it to DB
        accountRepository.save(bankAccount);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        //invoke the findBy to get the bankAccount and deposit the amount, comes in the event/message
        var bankAccount = accountRepository.findByAccountHolder(event.getId());
        if(bankAccount.isEmpty()){
            return;
        }
        //get the balance
        var currentBalance = bankAccount.get().getBalance();
        var latestBalance = currentBalance + event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        //after the balance updated, save
        System.out.println("Query::FundsDepositedEvent :: currentBalance" + currentBalance);
        System.out.println("Query::FundsDepositedEvent :: latestBalance" + latestBalance);

        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(FundsWithdrawnEvent event) {
        //invoke the findBy to get the bankAccount and deposit the amount, comes in the event/message
        var bankAccount = accountRepository.findByAccountHolder(event.getId());
        if(bankAccount.isEmpty()){
            return;
        }
        //get the balance
        var currentBalance = bankAccount.get().getBalance();
        var latestBalance = currentBalance - event.getAmount(); //withdrawal amount
        bankAccount.get().setBalance(latestBalance);
        //after the balance updated, save
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
