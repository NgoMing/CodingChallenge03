package com.minhnln.challenge03.utils;

public class ConsoleSignal {
    private int countCommand;
    private String ConsoleCommandStr;

    public ConsoleSignal() {
        countCommand = 0;
        ConsoleCommandStr = ">>>";
    }

    public String execute() {
        countCommand ++;
        return ("" + countCommand + ConsoleCommandStr);
    }
}
