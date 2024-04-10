package org.example.filereader;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class FileReader {

    /**
     * Returns files of a directory having the specified extension as a list of {@link Document}
     */
    public static List<Document> getDirectoryFilesAsDocument(String directory, String extension) {
        return getDirectoryFiles(directory, extension)
                .map(FileReader::getFileAsDocument)
                .toList();
    }

    /**
     * reads the specified file and return its details as a {@link Document}
     */
    public static Document getFileAsDocument(File file) {
        String content = null;
        try {
            content = Files.readString(Paths.get(file.getPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Document(file.getName(), content);
    }

    /**
     * Find all files in a directory with the specified extension.
     */
    private static Stream<File> getDirectoryFiles(String directoryPath, String extension) {
        File directory = new File(directoryPath);
        if (directory.listFiles() != null)
            return Stream.of(directory.listFiles()).filter(file -> hasRequiredFileExtension(file, extension));
        return Stream.of();
    }

    private static String getFileExtension(File file) {
        return FilenameUtils.getExtension(file.getName());
    }

    private static boolean hasRequiredFileExtension(File file, String extension) {
        return getFileExtension(file).equals(extension);
    }
}