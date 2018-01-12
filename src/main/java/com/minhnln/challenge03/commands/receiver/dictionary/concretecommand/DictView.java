package com.minhnln.challenge03.commands.receiver.dictionary.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.dictionary.DictionaryCommands;

public class DictView implements Command {

    private DictionaryCommands dictionaryCommands;

    public DictView(DictionaryCommands dictionaryCommands) {
        this.dictionaryCommands = dictionaryCommands;
    }

    @Override
    public void execute() {
        dictionaryCommands.view();
    }
}
