package com.minhnln.challenge03.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.Stack;

/**
 * FileUtil class
 *
 * support read content of file in resource folder
 */
public class FileUtil {

    public String getContentFromClasspath(final String fileName) {
        final URL url = getClass().getClassLoader().getResource(fileName);
        assert url != null;
        final File file = new File(url.getFile());
        try {
            return new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());
        } catch (IOException e) {
            return null;
        }
    }

    /*
     * read words from file in Resource folder - 1 word per line
     *
     * @param fileName name of file in resource folder
     *
     * @return stack of words in file
     */
    public Stack<String> readResourceFile(String fileName) {
        Stack<String> result = new Stack<>();

        File dictionaryFile = new File(getClass().getClassLoader().getResource(fileName).getFile());
        if (!dictionaryFile.exists()) {
            System.out.println("Your file has the wrong name or is in the wrong directory");
            return null;
        }

        try (Scanner scanner = new Scanner(dictionaryFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.push(line);
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
