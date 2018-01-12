package com.minhnln.challenge03.commands.receiver.dictionary;

import com.minhnln.challenge03.commands.receiver.rules.DigitToLetterRules;
import com.minhnln.challenge03.utils.FileUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class WordToNumberMaps {

    /**
     * Static string for test incomplete methods
     */
    public static final String CREATE_IN_FILE = "Create dictionary in file ";
    public static final String LIST_DICTIONARIES = "List all the existing dictionaries:";

    private static final String DEFAULT_DICTIONARY_FILE_DIRECTORY = "Dictionaries/default_dictionary.txt";

    private static WordToNumberMaps uniqueWordToNumberMaps;
    private Map<String, String> dictionaryMap;
    private DigitToLetterRules digitToLetterRules;

    private WordToNumberMaps() {
        dictionaryMap = new HashMap<>();
        digitToLetterRules = DigitToLetterRules.getInstance();
    }

    public static WordToNumberMaps getInstance() {
        if (null == uniqueWordToNumberMaps) {
            uniqueWordToNumberMaps = new WordToNumberMaps();
        }

        return uniqueWordToNumberMaps;
    }

    public void createDefault() {
        Stack<String> words = new FileUtil().readResourceFile(DEFAULT_DICTIONARY_FILE_DIRECTORY);

        for (String word : words) {
            if (dictionaryMap.containsKey(word)) {
                //TODO
            }
            dictionaryMap.put(word.toUpperCase(), convert(word));
        }
    }

    public void createInFile(String fileName) {
        System.out.println(CREATE_IN_FILE + fileName);
    }

    public void list() {
        System.out.println(LIST_DICTIONARIES);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        for (String word : dictionaryMap.keySet()) {
            sb.append(word).append(": ").append(dictionaryMap.get(word)).append("\n");
        }
        sb.setLength((sb.length() - 1));

        return sb.toString();
    }

    /*
     * convert word to number in String type
     *
     * @param word in upper case
     *
     * @return number in String type
     * @return null if there was at least one letter which cannot be converted
     */
    public String convert(String word) {
        StringBuilder numberString = new StringBuilder("");

        if (null == word)
            return null;

        if ((null == digitToLetterRules) || (digitToLetterRules.isEmpty())) {
            throw new NullPointerException("Rules have not been set up yet");
        }

        for (char letter : word.toCharArray()) {
            if (digitToLetterRules.convert(letter) == null)
                return null;
            numberString.append(digitToLetterRules.convert(letter));
        }

        return numberString.toString();
    }

    public boolean isEmpty() {
        return dictionaryMap.isEmpty();
    }

    public boolean isRulesEmpty() {
        return digitToLetterRules.isEmpty();
    }
}
