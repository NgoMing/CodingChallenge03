package com.minhnln.challenge03.commands.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordToNumberRules {
    private static WordToNumberRules uniqueWordToNumberRules;

    Map<String, ArrayList<String>> dictionaryMap;

    private WordToNumberRules() {
        dictionaryMap = new HashMap<>();
    }

    public static WordToNumberRules getInstance() {
        if (uniqueWordToNumberRules == null) {
            uniqueWordToNumberRules = new WordToNumberRules();
        }

        return uniqueWordToNumberRules;
    }

    /*
     * create default rules to convert from letter to digit in char type
     */
    public void createDefault() {
        createNew("2", "A", "B", "C");
        createNew("3", "D", "E", "F");
        createNew("4", "G", "H", "I");
        createNew("5", "J", "K", "L");
        createNew("6", "M", "N", "O");
        createNew("7", "P", "Q", "R", "S");
        createNew("8", "T", "U", "V");
        createNew("9", "W", "X", "Y", "Z");
    }

    /*
     * create single rule to convert from a letter to a digit in char type
     * which allow users to set their own dictionary.
     *
     * @param letter without case sensitive
     * @param digits which is converted from the letter
     */
    public void createNew(String digit, String ... letters) {
        dictionaryMap.put(digit.toUpperCase(), new ArrayList<>());

        for (String letter : letters) {
            dictionaryMap.get(digit).add(letter.toUpperCase());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        for (String digit : dictionaryMap.keySet()) {
            sb.append("Digit ").append(digit).append(": ");
            for (String letter : dictionaryMap.get(digit)) {
                sb.append(letter).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(System.getProperty("line.separator"));
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
