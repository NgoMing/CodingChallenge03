package com.minhnln.challenge03.commands.receiver.rules.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.rules.RuleCommands;

public class RuleCreateDefault implements Command {

    RuleCommands ruleCommands;

    public RuleCreateDefault(RuleCommands ruleCommands) {
        this.ruleCommands = ruleCommands;
    }

    @Override
    public void execute() {
        ruleCommands.createDefault();
    }
}
