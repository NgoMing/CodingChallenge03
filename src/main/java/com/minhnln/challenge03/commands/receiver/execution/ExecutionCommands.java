package com.minhnln.challenge03.commands.receiver.execution;

public class ExecutionCommands {

    private PhoneNumberConverter phoneNumberConverter;

    // phone number or file name
    private String inputString;

    public ExecutionCommands(String inputString) {
        phoneNumberConverter = new PhoneNumberConverter();
        this.inputString = inputString;
    }

    public ExecutionCommands() {
        phoneNumberConverter = new PhoneNumberConverter();
    }

    public void convertMultiplePhoneNumber() {
        phoneNumberConverter.convertMultiplePhoneNumber(inputString);
    }

    public void convertSinglePhoneNumber() {
        phoneNumberConverter.convertSinglePhoneNumber(inputString);
    }

    public void view() {
        if (phoneNumberConverter.isRulesEmpty()) {
            throw new NullPointerException("Rules have not been set up yet");
        }
        else if (phoneNumberConverter.isDictionaryEmpty()) {
            throw new NullPointerException("Dictionary has not been ready yet");
        }

        System.out.println(phoneNumberConverter);
    }
}
