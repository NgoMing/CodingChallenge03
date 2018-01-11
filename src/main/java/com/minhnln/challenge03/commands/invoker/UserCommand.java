package com.minhnln.challenge03.commands.invoker;

import com.minhnln.challenge03.commands.Command;

public class UserCommand {
    Command command;

    public UserCommand() {}

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }
}
