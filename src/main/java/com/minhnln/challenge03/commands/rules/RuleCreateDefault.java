package com.minhnln.challenge03.commands.rules;

import com.minhnln.challenge03.commands.Command;

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
