package com.minhnln.challenge03.commands.receiver.rules.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.rules.RuleCommands;

public class RuleList implements Command {

    private RuleCommands ruleCommands;

    public RuleList(RuleCommands ruleCommands) {
        this.ruleCommands = ruleCommands;
    }

    @Override
    public void execute() {
        ruleCommands.list();
    }
}
