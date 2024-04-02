package com.techbank.account.cmd.api.controllers;

import com.techbank.account.cmd.api.commands.CloseAccountCommand;
import com.techbank.account.cmd.api.commands.OpenAccountCommand;
import com.techbank.account.cmd.api.dto.OpenAccountResponse;
import com.techbank.account.common.dto.BaseResponse;
import com.techbank.cqrs.core.exceptions.AggragateNotFoundException;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path="/api/v1/closeBankAccount")
public class CloseAccountController {
    private final Logger logger = Logger.getLogger(CloseAccountController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable(value = "id") String id) {

        try {
            commandDispatcher.send(new CloseAccountCommand(id));
            return new ResponseEntity<>(new BaseResponse("Bank Account closure successfully completed!"), HttpStatus.OK);
        }
        catch (IllegalStateException | AggragateNotFoundException exp) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request {0}", exp.toString()));
            return new ResponseEntity<>(new BaseResponse(exp.toString()), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exp) {
            var safeErrorMessage = MessageFormat.format("Error, while processing request to close bank account with id - {0}", id);
            logger.log(Level.SEVERE, safeErrorMessage);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
