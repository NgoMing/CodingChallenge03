package com.minhnln.challenge03.cli;

import com.minhnln.challenge03.PhoneNumberConverterApplication;
import com.minhnln.challenge03.commands.Command;
import com.minhnln.challenge03.commands.receiver.dictionary.DictionaryCommands;
import com.minhnln.challenge03.commands.receiver.dictionary.concretecommand.DictCreateDefault;
import com.minhnln.challenge03.commands.receiver.dictionary.concretecommand.DictList;
import com.minhnln.challenge03.commands.receiver.dictionary.concretecommand.DictSetInFIle;
import com.minhnln.challenge03.commands.receiver.dictionary.concretecommand.DictView;
import com.minhnln.challenge03.commands.receiver.execution.ExecutionCommands;
import com.minhnln.challenge03.commands.receiver.execution.concretecommand.ExecuteMultiplePhoneNumber;
import com.minhnln.challenge03.commands.receiver.execution.concretecommand.ExecuteSinglePhoneNumber;
import com.minhnln.challenge03.commands.receiver.execution.concretecommand.ExecuteView;
import com.minhnln.challenge03.commands.receiver.rules.RuleCommands;
import com.minhnln.challenge03.commands.receiver.rules.concretecommand.*;

import java.util.*;

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

    /**
     * parse single user command lines
     * @param commandLine
     * @return Command Interface related to specific command line
     * @throws UnsupportedOperationException if the command line can not be recognised
     */
    public static Command parseSingleUserCommandLine(String commandLine) {
        splitCommandLine = commandLine.split(DELIMITER);

        // parse rule commands
        if (splitCommandLine[0].contains(PhoneNumberConverterApplication.RULE_CMD)) {
            return parseRuleCommandLine();
        }
        // parse dictionary commands
        else if (splitCommandLine[0].contains(PhoneNumberConverterApplication.DICTIONARY_CMD)) {
            return parseDictionaryCommandLine();
        }
        // parse execute commands
        else if (splitCommandLine[0].contains(PhoneNumberConverterApplication.EXECUTE_CMD)) {
            return parseExecuteCommandLine();
        }
        // unknown command
        else {
            throw new UnsupportedOperationException(COMMAND_NOT_FOUND);
        }
    }

    /**
     * parse multiple user command lines
     * @param commandLine
     * @return Queue of user command lines
     */
    public static Queue<String> parseMultipleUserCommandLines(String commandLine) {
        Queue<String> queueCommandLines = new LinkedList<>();

        commandLine = commandLine.replace(" -", "*-");
        String[] commandLines = commandLine.split("\\*");
        for (int i = 0; i < commandLines.length; i++)
            queueCommandLines.add(commandLines[i]);

        return queueCommandLines;
    }

    /**
     * parse rule's group command lines
     * including:
     *      -rules [digit] [letter1] [letter2] ...
     *      -rules default
     *      -rules view
     *      -rules ruleNameFile.txt
     *      -rules save (reserved)
     *      -rules verify (reserved)
     *      -rules list (reserved)
     * @return Command Interface related to specific command line
     *
     * @throws UnsupportedOperationException if the command line can not be recognised
     */
    private static Command parseRuleCommandLine() {
        // create new customer rules
        if (splitCommandLine.length > 3) {
            String number = splitCommandLine[1];
            String[] digits = Arrays.copyOfRange(splitCommandLine, 2, splitCommandLine.length);
            return new RuleCreateNew(new RuleCommands(number, digits));
        }
        else {
            // unrecognised rule command line
            if (splitCommandLine.length < 2) {
                throw new UnsupportedOperationException(RULES_COMMAND_NOT_FOUND);
            }
            // -rules default
            else if (splitCommandLine[1].equals("default")) {
                return new RuleCreateDefault(new RuleCommands());
            }
            // -rules view
            else if (splitCommandLine[1].equals("view")) {
                return new RuleView(new RuleCommands());
            }
            // -rules fileRuleName.txt
            else if (splitCommandLine[1].contains(".txt")) {
                return new RuleSetInFile(new RuleCommands(splitCommandLine[1]));
            }
            // -rules save
            else if ((splitCommandLine[1].equals("save")) && splitCommandLine[2].contains(".txt")) {
                return new RuleSave(new RuleCommands(splitCommandLine[2]));
            }
            // -rules verify
            else if (splitCommandLine[1].equals("verify")) {
                return new RuleVerify(new RuleCommands());
            }
            // -rules list
            else if (splitCommandLine[1].equals("list")) {
                return new RuleList(new RuleCommands());
            }
            // Unrecognised rule command line
            else {
                throw new UnsupportedOperationException(RULES_COMMAND_NOT_FOUND);
            }
        }
    }

    /**
     * parse dictionary's group command lines
     * including:
     *      -dict default
     *      -dict dictionaryNameFile.txt
     *      -dict view
     *      -dict list
     * @return Command Interface related to specific command line
     *
     * @throws UnsupportedOperationException if the command line can not be recognised
     */
    private static Command parseDictionaryCommandLine() {
        // Unrecognised dictionary command line
        if (splitCommandLine.length < 2) {
            throw new UnsupportedOperationException(DICTIONARY_COMMAND_NOT_FOUND);
        }
        // dict default
        else if (splitCommandLine[1].equals("default")) {
            return new DictCreateDefault(new DictionaryCommands());
        }
        // dict fileDictionaryName.txt
        else if (splitCommandLine[1].contains(".txt")) {
            return new DictSetInFIle(new DictionaryCommands(splitCommandLine[1]));
        }
        // dict view
        else if (splitCommandLine[1].equals("view")) {
            return new DictView(new DictionaryCommands());
        }
        // dict list
        else if (splitCommandLine[1].equals("list")) {
            return new DictList(new DictionaryCommands());
        }
        // Unrecognised dictionary command line
        else {
            throw new UnsupportedOperationException(DICTIONARY_COMMAND_NOT_FOUND);
        }
    }

    /**
     * parse execute's group command lines
     * including:
     *      -exe singlePhoneNumber
     *      -exe phoneNumberFile.txt
     *      -exe view
     * @return Command Interface related to specific command line
     *
     * @throws UnsupportedOperationException if the command line can not be recognised
     */
    private static Command parseExecuteCommandLine() {
        // Unrecognised execute command line
        if (splitCommandLine.length != 2) {
            throw new UnsupportedOperationException(EXECUTE_COMMAND_NOT_FOUND);
        }
        // exe phoneNumberNameFile.txt
        else if (splitCommandLine[1].contains(".txt")) {
            return new ExecuteMultiplePhoneNumber(new ExecutionCommands(splitCommandLine[1]));
        }
        // exe view
        else if (splitCommandLine[1].equals("view")) {
            return new ExecuteView(new ExecutionCommands());
        }
        // exe singlePhoneNumber
        else {
            try {
                Integer.valueOf(splitCommandLine[1]);
            }
            catch (NumberFormatException nfe) {
                System.out.println(splitCommandLine[1] + " is not an integer!");
                throw new UnsupportedOperationException(EXECUTE_COMMAND_NOT_FOUND);
            }

            return new ExecuteSinglePhoneNumber(new ExecutionCommands(splitCommandLine[1]));
        }
    }
}
