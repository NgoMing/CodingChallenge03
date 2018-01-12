package com.minhnln.challenge03.commands.receiver.execution;

public class ExecutionCommands {

    private PhoneNumberConverter phoneNumberConverter;

    // phone number or file name
    private String inputString;

    public ExecutionCommands(String inputString) {
        phoneNumberConverter = PhoneNumberConverter.getInstance();
        this.inputString = inputString;
    }

    public ExecutionCommands() {
        phoneNumberConverter = PhoneNumberConverter.getInstance();
    }

    public void convertMultiplePhoneNumber() {
        phoneNumberConverter.convertMultiplePhoneNumber(inputString);
    }

    public void convertSinglePhoneNumber() {
        phoneNumberConverter.convertSinglePhoneNumber(inputString);
    }

    public void view() {
        System.out.println(phoneNumberConverter);
    }
}
