package com.minhnln.challenge03.cli;

import com.minhnln.challenge03.PhoneNumberConverterApplication;
import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.rules.*;
import com.minhnln.challenge03.commands.unknown.UnknownCommand;
import com.minhnln.challenge03.commands.unknown.UnknownExecute;

import java.util.Arrays;

public class CommandLineParser {

    private static final String DELIMITER = " ";

    private static String[] splitCommandLine;

    public static Command parse (String commandLine) {
        splitCommandLine = commandLine.split(DELIMITER);

        // parse rule commands
        if (splitCommandLine[0].contains(PhoneNumberConverterApplication.RULE_CMD)) {
            if (splitCommandLine.length > 2) {
                String number = splitCommandLine[1];
                String[] digits = Arrays.copyOfRange(splitCommandLine, 2, splitCommandLine.length);
                return new RuleCreateNew(new RuleCommands(number, digits));
            }
            else {
                if (splitCommandLine[1].equals("default")) {
                    return new RuleCreateDefault(new RuleCommands());
                }
                else if (splitCommandLine[1].equals("view")) {
                    return new RuleView(new RuleCommands());
                }
            }
        }
        // parse dictionary commands
        else if (splitCommandLine[0].contains(PhoneNumberConverterApplication.DICTIONARY_CMD)) {

        }
        // parse execute commands
        else if (splitCommandLine[0].contains(PhoneNumberConverterApplication.EXECUTE_CMD)) {

        }
        // unknown command
        else {
            return new UnknownExecute(new UnknownCommand());
        }

        return null;
    }
}
