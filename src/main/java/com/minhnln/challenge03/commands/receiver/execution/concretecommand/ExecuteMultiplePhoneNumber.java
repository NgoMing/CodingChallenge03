package com.minhnln.challenge03.commands.receiver.execution.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.execution.ExecutionCommands;

public class ExecuteMultiplePhoneNumber implements Command {

    private ExecutionCommands executionCommands;

    public ExecuteMultiplePhoneNumber(ExecutionCommands executionCommands) {
        this.executionCommands = executionCommands;
    }

    @Override
    public void execute() {
        executionCommands.convertMultiplePhoneNumber();
    }
}
