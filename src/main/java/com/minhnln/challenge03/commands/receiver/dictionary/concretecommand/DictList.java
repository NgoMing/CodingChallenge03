package com.minhnln.challenge03.commands.receiver.dictionary.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.dictionary.DictionaryCommands;

public class DictList implements Command {

    private DictionaryCommands dictionaryCommands;

    public DictList(DictionaryCommands dictionaryCommands) {
        this.dictionaryCommands = dictionaryCommands;
    }

    @Override
    public void execute() {
        dictionaryCommands.list();
    }
}
