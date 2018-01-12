package com.minhnln.challenge03.commands.receiver.execution.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.execution.ExecutionCommands;

public class ExecuteView implements Command {

    private ExecutionCommands executionCommands;

    public ExecuteView(ExecutionCommands executionCommands) {
        this.executionCommands = executionCommands;
    }

    @Override
    public void execute() {
        executionCommands.view();
    }
}
