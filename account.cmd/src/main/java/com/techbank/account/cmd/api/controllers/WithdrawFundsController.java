package com.techbank.account.cmd.api.controllers;

import com.techbank.account.cmd.api.commands.DepositFundsCommand;
import com.techbank.account.cmd.api.commands.OpenAccountCommand;
import com.techbank.account.cmd.api.commands.WithdrawFundsCommand;
import com.techbank.account.cmd.api.dto.OpenAccountResponse;
import com.techbank.account.common.dto.BaseResponse;
import com.techbank.cqrs.core.exceptions.AggragateNotFoundException;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
@RestController
@RequestMapping(path="/api/v1/withdrawFunds")
public class WithdrawFundsController {
    private final Logger logger = Logger.getLogger(WithdrawFundsController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable(value = "id") String id,
                                                      @RequestBody WithdrawFundsCommand withdrawFundsCommand) {
        try {
            withdrawFundsCommand.setId(id);
            commandDispatcher.send(withdrawFundsCommand);
            return new ResponseEntity<>(new BaseResponse("Withdraw Funds request completed successfully!"), HttpStatus.OK);
            //HttpStatus.OK for updating a record not creating one
        }
        catch (IllegalStateException | AggragateNotFoundException exp) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request {0}", exp.toString()));
            return new ResponseEntity<>(new BaseResponse(exp.toString()), HttpStatus.BAD_REQUEST);
        }
        catch (Exception exp) {
            var safeErrorMessage = MessageFormat.format("Error, while processing the client request for withdraw funds from account with id - {0}", id);
            logger.log(Level.SEVERE, safeErrorMessage);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
