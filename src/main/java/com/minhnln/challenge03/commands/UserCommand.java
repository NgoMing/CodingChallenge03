package com.minhnln.challenge03.commands;

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
