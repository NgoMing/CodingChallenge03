package com.minhnln.challenge03.commands.receiver.rules.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.rules.RuleCommands;

public class RuleSave implements Command {

    private RuleCommands ruleCommands;

    public RuleSave(RuleCommands ruleCommands) {
        this.ruleCommands = ruleCommands;
    }

    @Override
    public void execute() {
        ruleCommands.save();
    }
}
