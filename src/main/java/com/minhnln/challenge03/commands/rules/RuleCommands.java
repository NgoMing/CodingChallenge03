package com.minhnln.challenge03.commands.rules;

public class RuleCommands {

    private WordToNumberRules wordToNumberRules;
    private String number;
    private String[] digits;

    public RuleCommands() {
        wordToNumberRules = WordToNumberRules.getInstance();
    }

    public RuleCommands(String number, String[] digits) {
        this.number = number;
        this.digits = digits;
    }

    public void createDefault() {
        wordToNumberRules.createDefault();
    }

    public void createNew() {
        wordToNumberRules.createNew(number, digits);
    }

    public void view() {
        System.out.println(wordToNumberRules);
    }
}
