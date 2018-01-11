package com.minhnln.challenge03.commands.rules;

import com.minhnln.challenge03.commands.Command;

public class RuleCreateNew implements Command {

    RuleCommands ruleCommands;

    public RuleCreateNew(RuleCommands ruleCommands) {
        this.ruleCommands = ruleCommands;
    }

    @Override
    public void execute() {
        ruleCommands.createNew();
    }
}
