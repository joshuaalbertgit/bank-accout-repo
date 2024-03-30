package com.techbank.account.cmd.api.controllers;

import com.techbank.account.cmd.api.commands.OpenAccountCommand;
import com.techbank.account.cmd.api.dto.OpenAccountResponse;
import com.techbank.account.common.dto.BaseResponse;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path="/api/v1/openBankAccount")
public class OpenAccountController {
    private final Logger logger = Logger.getLogger(OpenAccountController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand accountCommand) {
        var id = UUID.randomUUID().toString();

        try {
            accountCommand.setId(id);
            commandDispatcher.send(accountCommand);
            return new ResponseEntity<>(new OpenAccountResponse("account created successfully!", id), HttpStatus.CREATED);
        }
        catch (IllegalStateException exp) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request {0}", exp.toString()));
            return new ResponseEntity<>(new BaseResponse(exp.toString()), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exp) {
            var safeErrorMessage = MessageFormat.format("Error, while processing the client request for bank account id - {0}", id);
            logger.log(Level.SEVERE, safeErrorMessage);
            return new ResponseEntity<>(new OpenAccountResponse(safeErrorMessage,id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
