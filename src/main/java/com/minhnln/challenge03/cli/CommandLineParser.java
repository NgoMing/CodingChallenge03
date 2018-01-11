package com.minhnln.challenge03.cli;

import com.minhnln.challenge03.PhoneNumberConverterApplication;
import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.rules.RuleCommands;
import com.minhnln.challenge03.commands.receiver.rules.concretecommand.*;

import java.util.Arrays;

public class CommandLineParser {
    /**
     * Static string for test error commands
     */
    public static final String RULES_COMMAND_NOT_FOUND = "rules command not found";
    public static final String DICTIONARY_COMMAND_NOT_FOUND = "dictionary command not found";
    public static final String EXECUTE_COMMAND_NOT_FOUND = "execute command not found";
    public static final String COMMAND_NOT_FOUND = "command not found";

    private static final String DELIMITER = " ";

    private static String[] splitCommandLine;

    public static Command parse (String commandLine) {
        splitCommandLine = commandLine.split(DELIMITER);

        // parse rule commands
        if (splitCommandLine[0].contains(PhoneNumberConverterApplication.RULE_CMD)) {
            if (splitCommandLine.length > 3) {
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
                else if (splitCommandLine[1].contains(".txt")) {
                    return new RuleSetInFile(new RuleCommands(splitCommandLine[1]));
                }
                else if ((splitCommandLine[1].equals("save")) && splitCommandLine[2].contains(".txt")) {
                    return new RuleSave(new RuleCommands(splitCommandLine[2]));
                }
                else if (splitCommandLine[1].equals("verify")) {
                    return new RuleVerify(new RuleCommands());
                }
                else if (splitCommandLine[1].equals("list")) {
                    return new RuleList(new RuleCommands());
                }
                else {
                    throw new UnsupportedOperationException(RULES_COMMAND_NOT_FOUND);
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
            throw new UnsupportedOperationException(COMMAND_NOT_FOUND);
        }

        return null;
    }
}
