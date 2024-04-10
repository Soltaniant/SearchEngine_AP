package org.example;

import org.example.filereader.FileReader;

public class Main {
    public static void main(String[] args) {
        var documents = FileReader.getDirectoryFilesAsDocument("C:\\Users\\brights\\Documents\\FullTextSearchSamples\\Books", "txt");
        /**
         * bookIndex = new Index("book");
         * bookIndex.indexDocument(); forloop => indexDocuments;
         */

        System.out.println("Hello world!");
    }
}