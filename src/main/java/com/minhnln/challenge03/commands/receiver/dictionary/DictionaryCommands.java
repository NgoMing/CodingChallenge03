package com.minhnln.challenge03.commands.receiver.dictionary;

public class DictionaryCommands {
    private WordToNumberMaps wordToNumberMaps;
    private String fileName;

    public DictionaryCommands() {
        wordToNumberMaps = WordToNumberMaps.getInstance();
    }

    public DictionaryCommands(String fileName) {
        wordToNumberMaps = WordToNumberMaps.getInstance();
        this.fileName = fileName;
    }

    public void createDefault() {
        wordToNumberMaps.createDefault();
    }

    public void setInFile() {
        wordToNumberMaps.createInFile(fileName);
    }

    public void list() {
        wordToNumberMaps.list();
    }

    public void view() {
        System.out.println(wordToNumberMaps);
    }
}
