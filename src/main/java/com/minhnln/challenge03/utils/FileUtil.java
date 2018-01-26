package com.minhnln.challenge03.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        URL url = getClass().getClassLoader().getResource(fileName);
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                result.push(line);
            }
            br.close();

        } catch (IOException e) {
            result = null;
        }

        return result;
    }

}
