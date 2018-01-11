package com.minhnln.challenge03.commands.unknown;

import com.minhnln.challenge03.commands.Command;

public class UnknownExecute implements Command {

    private UnknownCommand unknownCommand;

    public UnknownExecute (UnknownCommand unknownCommand) {
        this.unknownCommand = unknownCommand;
    }

    @Override
    public void execute() {
        unknownCommand.execute();
    }
}
