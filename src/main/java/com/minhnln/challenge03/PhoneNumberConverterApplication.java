package com.minhnln.challenge03;

import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;

public class PhoneNumberConverterApplication {

    private enum COMMAND {
        CREATE_NEW_RULE("-rules [digit] [letter1] [letter2] ..."),
        CREATE_DEFAULT_RULE("-rules default"),
        SET_RULES("-rules [rulesFileName.txt]"),
        VERIFY_RULES("-rules verify"),
        SAVE_RULES("-rules save [ruleFileName.txt]"),
        SHOW_CURRENT_RULES("-rules view"),
        SHOW_LIST_EXIST_RULES("-rules list"),
        SET_DEFAULT_DICTIONARY("-dict default"),
        SET_DICTIONARY("-dict [dictionaryFileName.txt]"),
        SHOW_LIST_EXIST_DICTIONARIES("-dict list"),
        CONVERT_SINGLE_PHONE_NUMBER("-pnum [phone_number]"),
        CONVERT_MULTIPLE_PHONE_NUMBER("-file {phone_number_file_name]");

        private String content;

        COMMAND(String content) { this.content = content; }

        @Override
        public String toString() {
            return content;
        }
    }

    public static void main(String[] args) {
        printInstruction();
    }

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
        out.println("If you want to list all the commands, type -help");
    }

    public static void showCommandList() {
        final PrintStream out = System.out;

        out.println("Rules commands:");
        showRulesCommand();
        out.println("Dictionary commands:");
        showDictionaryCommand();
        out.println("Execute commands:");
        showExecuteCommand();
    }

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

    private static void showDictionaryCommand() {
        final PrintStream out = System.out;

        out.println("\tSet default dictionary:                    " + COMMAND.SET_DEFAULT_DICTIONARY);
        out.println("\tSet dictionary:                            " + COMMAND.SET_DICTIONARY);
        out.println("\tShow list of exist dictionaries(Optional): " + COMMAND.SHOW_LIST_EXIST_DICTIONARIES);
    }


    private static void showExecuteCommand() {
        final PrintStream out = System.out;

        out.println("\tConvert single phone number:   " + COMMAND.CONVERT_SINGLE_PHONE_NUMBER);
        out.println("\tConvert multiple phone number: " + COMMAND.CONVERT_MULTIPLE_PHONE_NUMBER);
    }
}
