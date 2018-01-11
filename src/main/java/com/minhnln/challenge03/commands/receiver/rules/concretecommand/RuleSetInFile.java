package com.minhnln.challenge03.commands.receiver.rules.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.rules.RuleCommands;

public class RuleSetInFile implements Command{

    RuleCommands ruleCommands;

    public RuleSetInFile(RuleCommands ruleCommands) {
        this.ruleCommands = ruleCommands;
    }

    @Override
    public void execute() {
        ruleCommands.setInFile();
    }
}
