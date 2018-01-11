package com.minhnln.challenge03.commands.rules;

import com.minhnln.challenge03.commands.Command;

public class RuleView implements Command {

    RuleCommands ruleCommands;

    public RuleView(RuleCommands ruleCommands) {
        this.ruleCommands = ruleCommands;
    }

    @Override
    public void execute() {
        ruleCommands.view();
    }
}
