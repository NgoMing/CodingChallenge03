package com.minhnln.challenge03;

import com.minhnln.challenge03.commands.receiver.execution.PhoneNumberConverter;
import com.minhnln.challenge03.commands.receiver.rules.DigitToLetterRules;
import com.minhnln.challenge03.utils.ConsoleSignal;
import com.minhnln.challenge03.utils.FileUtil;
import com.minhnln.challenge03.utils.StringAsker;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhoneNumberConverterApplicationTest {

    private static final String CHARSET_NAME = "UTF-8";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    StringAsker asker = mock(StringAsker.class);
    ConsoleSignal consoleSignal;

    @Before
    public void before() throws UnsupportedEncodingException {
        System.setOut(new PrintStream(outContent, false, CHARSET_NAME));
        consoleSignal = new ConsoleSignal();
    }

    @After
    public void after() {
        System.setOut(null);
    }

    @Test
    public void printInstruction() throws Exception {
        PhoneNumberConverterApplication.printInstruction();

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Instructions/expected_instruction.txt")
        );
    }

    @Test
    public void showCommandList() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-help");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Instructions/expected_show_command_list.txt")
        );
    }


    /********************************************************************************
     *
     * Test cases related rules command
     */
    @Test
    public void createDefaultRules() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/default_rules.txt")
        );
    }

    private static final Object[] newRulesData() {
        return new Object[] {
                new Object[] {"2", "b", "d", "v"},
                new Object[] {"4", "q", "s", "t", "z"}
        };
    }

    @Test
    @Parameters(method = "newRulesData")
    public void createNewRule(String number, String ... digits) throws Exception {
        StringBuilder sb = new StringBuilder("");
        sb.append("-rules " + number + " ");
        for (String digit : digits) {
            sb.append(digit).append(" ");
        }
        sb.setLength(sb.length() - 1);
        when(asker.ask(consoleSignal.execute())).thenReturn(sb.toString());
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);

        sb = new StringBuilder("");
        sb.append("Digit " + number + ":");
        for (String digit : digits) {
            sb.append(" " + digit.toUpperCase() + ",");
        }
        sb.setLength(sb.length() - 1);
        assertThat(outContentString).contains(sb.toString());
    }

    @Test
    public void __viewRulesWithoutCreatingRules() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/error_view_without_rules.txt")
        );
    }

    @Test
    public void wrongRulesCommand() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules abc");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/error_rules_command.txt")
        );
    }

    @Test
    public void createRulesInFile() throws Exception {
        String fileName = "rules.txt";
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules " + fileName);
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(DigitToLetterRules.CREATE_IN_FILE + fileName);
    }

    @Test
    public void saveRulesInFile() throws Exception {
        String fileName = "rules.txt";
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules save " + fileName);
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(DigitToLetterRules.SAVE_IN_FILE + fileName);
    }

    @Test
    public void verifyRules() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules verify");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(DigitToLetterRules.VERIFY_RULES);
    }

    @Test
    public void listRules() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules list");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(DigitToLetterRules.LIST_RULES);
    }

    @Test
    public void errorRulesCommand() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules abc");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/error_rules_command.txt")
        );
    }

    /********************************************************************************
     *
     * Test cases related dictionary command
     */
    @Test
    public void createDefaultDictionary() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-dict default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-dict view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");

        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Dictionaries/default_converted_dictionary.txt")
        );
    }

    @Test
    public void __viewDictionaryWithoutCreatingRules() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-dict default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-dict view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/error_view_without_rules.txt")
        );
    }

    @Test
    public void _viewDictionaryWithoutCreatingDictionary() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-dict view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/error_view_without_dictionary.txt")
        );
    }

    @Test
    public void __viewDictionaryWithoutCreatingRulesAndDictionary() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-dict view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/error_view_without_rules.txt")
        );
    }

    /********************************************************************************
     *
     * Test cases related execution command
     */
    @Test
    public void convertSinglePhoneNumber() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-dict default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe 225563");
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains("CALL-ME, BALL-ME");
    }

    @Test
    public void _convertSinglePhoneNumberWithoutDictionary() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe 225563");
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/error_view_without_dictionary.txt")
        );
    }

    @Test
    public void __convertSinglePhoneNumberWithoutRules() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe 225563");
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/error_view_without_rules.txt")
        );
    }

    @Test
    public void convertMultiplePhoneNumber() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-dict default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe phone_number_default.txt");
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("ConvertedPhoneNumberResults/converted_phone_number_default.txt")
        );
    }

    @Test
    public void _convertMultiplePhoneNumberWithoutDictionary() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-rules default");
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe phone_number_default.txt");
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/error_view_without_dictionary.txt")
        );
    }

    @Test
    public void __convertMultiplePhoneNumberWithoutRules() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe phone_number_default.txt");
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Commands/error_view_without_rules.txt")
        );
    }

    @Test
    public void ___viewingPhoneNumberWithoutRulesAndDictionary() throws Exception {
        when(asker.ask(consoleSignal.execute())).thenReturn("-exe view");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(PhoneNumberConverter.EMPTY_RESULT);
    }
}