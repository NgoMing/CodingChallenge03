package com.minhnln.challenge03.commands.receiver.dictionary.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.dictionary.DictionaryCommands;

public class DictCreateDefault implements Command {

    DictionaryCommands dictionaryCommands;

    public DictCreateDefault(DictionaryCommands dictionaryCommands) {
        this.dictionaryCommands = dictionaryCommands;
    }

    @Override
    public void execute() {
        dictionaryCommands.createDefault();
    }
}
