package com.minhnln.challenge03.commands.receiver.rules;

public class RuleCommands {

    private WordToNumberRules wordToNumberRules;
    private String number;
    private String[] digits;
    private String fileName;

    public RuleCommands() {
        wordToNumberRules = WordToNumberRules.getInstance();
    }

    public RuleCommands(String number, String[] digits) {
        wordToNumberRules = WordToNumberRules.getInstance();
        this.number = number;
        this.digits = digits;
    }

    public RuleCommands(String fileName) {
        wordToNumberRules = WordToNumberRules.getInstance();
        this.fileName = fileName;
    }

    public void createDefault() {
        wordToNumberRules.createDefault();
    }

    public void createNew() {
        wordToNumberRules.createNew(number, digits);
    }

    public void view() {
        if ((null == wordToNumberRules) || (wordToNumberRules.isEmpty())){
            throw new NullPointerException("Rules has not set up yet");
        }
        System.out.println(wordToNumberRules);
    }

    public void setInFile() {
        wordToNumberRules.createInFile(fileName);
    }

    public void save() {
        wordToNumberRules.save(fileName);
    }

    public void verify() {
        wordToNumberRules.verify();
    }

    public void list() {
        wordToNumberRules.list();
    }
}
