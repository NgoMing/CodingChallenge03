package com.minhnln.challenge03.commands.receiver.rules;

public class RuleCommands {

    private DigitToLetterRules digitToLetterRules;
    private String number;
    private String[] digits;
    private String fileName;

    public RuleCommands() {
        digitToLetterRules = DigitToLetterRules.getInstance();
    }

    public RuleCommands(String number, String[] digits) {
        digitToLetterRules = DigitToLetterRules.getInstance();
        this.number = number;
        this.digits = digits;
    }

    public RuleCommands(String fileName) {
        digitToLetterRules = DigitToLetterRules.getInstance();
        this.fileName = fileName;
    }

    public void createDefault() {
        digitToLetterRules.createDefault();
    }

    public void createNew() {
        digitToLetterRules.createNew(number, digits);
    }

    public void view() {
        if ((null == digitToLetterRules) || (digitToLetterRules.isEmpty())){
            throw new NullPointerException("Rules have not been set up yet");
        }
        System.out.println(digitToLetterRules);
    }

    public void setInFile() {
        digitToLetterRules.createInFile(fileName);
    }

    public void save() {
        digitToLetterRules.save(fileName);
    }

    public void verify() {
        digitToLetterRules.verify();
    }

    public void list() {
        digitToLetterRules.list();
    }
}
