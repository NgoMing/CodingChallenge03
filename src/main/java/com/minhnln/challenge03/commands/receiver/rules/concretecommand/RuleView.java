package com.minhnln.challenge03.commands.receiver.rules.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.rules.RuleCommands;

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
