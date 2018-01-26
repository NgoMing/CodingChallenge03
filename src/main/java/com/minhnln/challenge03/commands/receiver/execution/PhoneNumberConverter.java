package com.minhnln.challenge03.commands.receiver.execution;

import com.minhnln.challenge03.commands.receiver.dictionary.WordToNumberMaps;
import com.minhnln.challenge03.utils.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PhoneNumberConverter {

    /**
     * Static string for test empty result
     */
    public static final String EMPTY_RESULT = "There is not any result";

    static final String DELIMITER = "-";
    static final int NUMBER_OF_DIGIT_SKIPPED = 1;

    private static PhoneNumberConverter uniquePhoneNumberConverter;
    private WordToNumberMaps wordToNumberMaps;
    private Map<String, ArrayList<String>> possibleMatches;

    private PhoneNumberConverter() {
        wordToNumberMaps = WordToNumberMaps.getInstance();
        possibleMatches = new HashMap<>();
    }

    private void clearPossibleMatches() {
        possibleMatches = new HashMap<>();
    }

    public static PhoneNumberConverter getInstance() {
        if(null == uniquePhoneNumberConverter) {
            uniquePhoneNumberConverter = new PhoneNumberConverter();
        }

        return uniquePhoneNumberConverter;
    }

    public void convertMultiplePhoneNumber(String fileName) {
        clearPossibleMatches();
        Stack<String> phoneNumber = new FileUtil().readResourceFile(fileName);
        while (!phoneNumber.empty()) {
            separate(new StringBuilder(phoneNumber.pop()), 0, new StringBuilder(""), 0);
        }
    }

    public void convertSinglePhoneNumber(String phoneNumber) {
        clearPossibleMatches();
        separate(new StringBuilder(phoneNumber), 0, new StringBuilder(""), 0);
    }

    @Override
    public String toString() {
        if ((null == possibleMatches) || (possibleMatches.size() == 0)) {
            return EMPTY_RESULT;
        }

        StringBuilder sb = new StringBuilder("");

        for (String phoneNumber : possibleMatches.keySet()) {
            sb.append(phoneNumber).append(": ");
            for (String word : possibleMatches.get(phoneNumber)) {
                sb.append(word).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    void separate(StringBuilder sb, int position, StringBuilder sbTemp, int numberOfDigitSkipped) {
        if (sb.length() == position) {
            sbTemp.setLength(sbTemp.length() - 1);
            if (!possibleMatches.containsKey(sb.toString()))
                possibleMatches.put(sb.toString(), new ArrayList<>());
            possibleMatches.get(sb.toString()).add(sbTemp.toString());
            sbTemp.setLength(0);
            return;
        }

        boolean isContained = false;

        for (String word : wordToNumberMaps.findConvertedWordByNumberStartingIn(sb.substring(position))) {
            numberOfDigitSkipped = 0;
            isContained = true;
            sbTemp.append(word.toUpperCase()).append(DELIMITER);
            separate(sb, position + word.length(), sbTemp, numberOfDigitSkipped);
        }

        if (!isContained) {
            if (numberOfDigitSkipped < NUMBER_OF_DIGIT_SKIPPED) {
                numberOfDigitSkipped ++;
                sbTemp.append(sb.substring(position, position + 1)).append(DELIMITER);
                separate(sb, position + 1, sbTemp, numberOfDigitSkipped);
            }
            else {
                sbTemp.setLength(0);
                return;
            }
        }
    }
}
