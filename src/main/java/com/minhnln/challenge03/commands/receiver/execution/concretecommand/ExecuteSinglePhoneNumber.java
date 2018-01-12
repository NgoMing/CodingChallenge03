package com.minhnln.challenge03.commands.receiver.execution.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.execution.ExecutionCommands;

public class ExecuteSinglePhoneNumber implements Command {

    private ExecutionCommands executionCommands;

    public ExecuteSinglePhoneNumber(ExecutionCommands executionCommands) {
        this.executionCommands = executionCommands;
    }

    @Override
    public void execute() {
        executionCommands.convertSinglePhoneNumber();
    }
}
