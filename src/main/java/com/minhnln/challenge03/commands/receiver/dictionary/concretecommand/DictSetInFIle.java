package com.minhnln.challenge03.commands.receiver.dictionary.concretecommand;

import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.dictionary.DictionaryCommands;

public class DictSetInFIle implements Command {

    private DictionaryCommands dictionaryCommands;

    public DictSetInFIle(DictionaryCommands dictionaryCommands) {
        this.dictionaryCommands = dictionaryCommands;
    }

    @Override
    public void execute() {
        dictionaryCommands.setInFile();
    }
}
