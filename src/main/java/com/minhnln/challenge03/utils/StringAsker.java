package com.minhnln.challenge03.utils;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * StringAsker class
 *
 * support simulate for input and output in console
 */
public class StringAsker {
    private final Scanner scanner;
    private final PrintStream out;

    public StringAsker(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public String ask(String message) {
        out.println(message);
        return scanner.nextLine();
    }
}
