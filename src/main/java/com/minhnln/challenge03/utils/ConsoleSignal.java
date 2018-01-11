package com.minhnln.challenge03.utils;

/**
 * ConsoleSignal class
 *
 * print the beginning String of commands
 */
public class ConsoleSignal {
    private int countCommand;
    private String ConsoleCommandStr;

    public ConsoleSignal() {
        countCommand = 0;
        ConsoleCommandStr = ">>>";
    }

    public String execute() {
        countCommand ++;
        return ("(" + countCommand + ")" + ConsoleCommandStr);
    }
}
