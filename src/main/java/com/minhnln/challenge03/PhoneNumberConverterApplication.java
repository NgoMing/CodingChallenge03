package com.minhnln.challenge03;

import com.minhnln.challenge03.cli.CommandLineParser;
import com.minhnln.challenge03.commands.invoker.UserCommand;
import com.minhnln.challenge03.utils.ConsoleSignal;
import com.minhnln.challenge03.utils.StringAsker;

import java.io.PrintStream;

/**
 * The application of Phone Number Converter
 */
public class PhoneNumberConverterApplication {

    public static final String RULE_CMD = "-rules";
    public static final String DICTIONARY_CMD = "-dict";
    public static final String EXECUTE_CMD = "-exe";

    /**
     * enum for user commands
     */
    private enum COMMAND {
        CREATE_NEW_RULE(RULE_CMD + " [digit] [letter1] [letter2] ..."),
        CREATE_DEFAULT_RULE(RULE_CMD + " default"),
        SET_RULES(RULE_CMD + " [rulesFileName.txt]"),
        VERIFY_RULES(RULE_CMD + " verify"),
        SAVE_RULES(RULE_CMD + " save [ruleFileName.txt]"),
        SHOW_CURRENT_RULES(RULE_CMD + " view"),
        SHOW_LIST_EXIST_RULES(RULE_CMD + " list"),
        SET_DEFAULT_DICTIONARY(DICTIONARY_CMD + " default"),
        SET_DICTIONARY(DICTIONARY_CMD + " [dictionaryFileName.txt]"),
        SHOW_CURRENT_DICTIONARY(DICTIONARY_CMD + " view"),
        SHOW_LIST_EXIST_DICTIONARIES(DICTIONARY_CMD + " list"),
        CONVERT_SINGLE_PHONE_NUMBER(EXECUTE_CMD + " [phone_number]"),
        CONVERT_MULTIPLE_PHONE_NUMBER(EXECUTE_CMD + " [phoneNumberFileName.txt]"),
        HELP("-help"),
        QUIT("-quit");

        private String content;

        COMMAND(String content) { this.content = content; }

        @Override
        public String toString() {
            return content;
        }
    }


    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {

        execute(new StringAsker(System.in, System.out));
    }

    /**
     * execute method
     * support simulate input and output in console
     *
     * @param asker
     */
    public static void execute(StringAsker asker) {
        ConsoleSignal consoleSignal = new ConsoleSignal();
        UserCommand userCommand = new UserCommand();
        boolean done = false;

        String commandLine = asker.ask(consoleSignal.execute());

        while(!done) {
            // -help
            if (commandLine.equals(COMMAND.HELP.toString())) {
                showCommandList();
                commandLine = asker.ask(consoleSignal.execute());
            }

            // quit command
            else if (commandLine.equals(COMMAND.QUIT.toString())) {
                done = true;
            }

            // others commands
            else {
                try {
                    userCommand.setCommand(CommandLineParser.parse(commandLine));
                }
                catch (UnsupportedOperationException uoe) {
                    if (uoe.toString().contains(CommandLineParser.RULES_COMMAND_NOT_FOUND)) {
                        System.out.println(uoe);
                        showRulesCommand();
                    }
                    commandLine = asker.ask(consoleSignal.execute());
                    continue;
                }

                try {
                    userCommand.execute();
                }
                catch (NullPointerException npe) {
                    System.out.println(npe);
                    if (npe.toString().contains("Rules"))
                        showRulesCommand();
                    else if (npe.toString().contains(("Dictionary")))
                        showDictionaryCommand();
                }

                commandLine = asker.ask(consoleSignal.execute());
            }
        }
    }

    /**
     * print the instruction of program
     */
    public static void printInstruction() {
        final PrintStream out = System.out;

        out.println("===================== PHONE NUMBER CONVERTER - CODING CHALLENGE ======================");
        out.println();
        out.println("Step 1: create rules");
        showRulesCommand();
        out.println("Step 2: set dictionary");
        showDictionaryCommand();
        out.println("Step 3: convert single or multiple phone numbers");
        showExecuteCommand();
        out.println("If you want to list all the commands, type " + COMMAND.HELP);
        out.println("If you want to quit the program, type " + COMMAND.QUIT);
    }

    /**
     * print the list of supported commands
     */
    public static void showCommandList() {
        final PrintStream out = System.out;

        out.println("Rules commands:");
        showRulesCommand();
        out.println("Dictionary commands:");
        showDictionaryCommand();
        out.println("Execute commands:");
        showExecuteCommand();
    }

    /**
     * print the list of commands related to rules configuration
     */
    private static void showRulesCommand() {
        final PrintStream out = System.out;

        out.println("\tCreate default rules:               " + COMMAND.CREATE_DEFAULT_RULE);
        out.println("\tSet rules in exist file:            " + COMMAND.SET_RULES);
        out.println("\tCreate a new rule:                  " + COMMAND.CREATE_NEW_RULE);
        out.println("\tSave rules in file(Optional):       " + COMMAND.SAVE_RULES);
        out.println("\tVerify current rules(Optional):     " + COMMAND.VERIFY_RULES);
        out.println("\tShow current rules(Optional):       " + COMMAND.SHOW_CURRENT_RULES);
        out.println("\tShow list of exist rules(Optional): " + COMMAND.SHOW_LIST_EXIST_RULES);
    }

    /**
     * print the list of commands related to dictionary configuration
     */
    private static void showDictionaryCommand() {
        final PrintStream out = System.out;

        out.println("\tSet default dictionary:                    " + COMMAND.SET_DEFAULT_DICTIONARY);
        out.println("\tSet dictionary:                            " + COMMAND.SET_DICTIONARY);
        out.println("\tShow current dictionary(Optional):         " + COMMAND.SHOW_CURRENT_DICTIONARY);
        out.println("\tShow list of exist dictionaries(Optional): " + COMMAND.SHOW_LIST_EXIST_DICTIONARIES);
    }

    /**
     * print the list of execute commands
     */
    private static void showExecuteCommand() {
        final PrintStream out = System.out;

        out.println("\tConvert single phone number:   " + COMMAND.CONVERT_SINGLE_PHONE_NUMBER);
        out.println("\tConvert multiple phone number: " + COMMAND.CONVERT_MULTIPLE_PHONE_NUMBER);
    }
}
