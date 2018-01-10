package com.minhnln.challenge03.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;

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
}
