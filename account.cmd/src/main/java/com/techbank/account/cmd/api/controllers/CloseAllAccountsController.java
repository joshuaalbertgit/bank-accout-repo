package com.techbank.account.cmd.api.controllers;

import com.techbank.account.cmd.api.commands.CloseAccountCommand;
import com.techbank.account.cmd.api.commands.CloseAllAccountsCommand;
import com.techbank.account.common.dto.BaseResponse;
import com.techbank.cqrs.core.exceptions.AggragateNotFoundException;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path="/api/v1/closeAllBankAccounts")
public class CloseAllAccountsController {
    private final Logger logger = Logger.getLogger(CloseAllAccountsController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @DeleteMapping
    public ResponseEntity<BaseResponse> closeAccount() {

        try {
            commandDispatcher.send(new CloseAllAccountsCommand());
            return new ResponseEntity<>(new BaseResponse("All Bank Accounts closure been successfully completed!"), HttpStatus.OK);
        }
        catch (IllegalStateException | AggragateNotFoundException exp) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request {0}", exp.toString()));
            return new ResponseEntity<>(new BaseResponse(exp.toString()), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exp) {
            var safeErrorMessage = "Error, while processing request to close bank account";
            logger.log(Level.SEVERE, safeErrorMessage);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
