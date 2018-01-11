package com.minhnln.challenge03;

import com.minhnln.challenge03.utils.ConsoleSignal;
import com.minhnln.challenge03.utils.FileUtil;
import com.minhnln.challenge03.utils.StringAsker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PhoneNumberConverterApplicationTest {

    private static final String CHARSET_NAME = "UTF-8";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    StringAsker asker = mock(StringAsker.class);

    @Before
    public void before() throws UnsupportedEncodingException {
        System.setOut(new PrintStream(outContent, false, CHARSET_NAME));
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
        ConsoleSignal consoleSignal = new ConsoleSignal();
        when(asker.ask(consoleSignal.execute())).thenReturn("-help");
        when(asker.ask(consoleSignal.execute())).thenReturn("-quit");
        PhoneNumberConverterApplication.execute(asker);

        final String outContentString = outContent.toString(CHARSET_NAME);
        assertThat(outContentString).contains(
                new FileUtil().getContentFromClasspath("Instructions/expected_show_command_list.txt")
        );
    }
}