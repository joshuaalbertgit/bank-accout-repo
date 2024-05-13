package com.techbank.account.cmd.api.commands;

import com.techbank.account.common.dto.AccountType;
import com.techbank.cqrs.core.commands.BaseCommand;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Adding the Open Account command input error messages
 */
@Data
public class OpenAccountCommand extends BaseCommand {
    @NotBlank(message = "Account holder name cannot be empty")
    private String accountHolder;
    private AccountType accountType;
    @Positive(message = "Initial balance must be a positive number")
    private double openingBalance;
}