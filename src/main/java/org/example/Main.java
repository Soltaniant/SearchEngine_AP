package org.example;


import org.example.analyzer.normalizer.MarkRemoverNormalizer;
import org.example.analyzer.normalizer.UppercaseNormalizer;
import org.example.analyzer.tokenizer.EdgeNGramTokenizer;
import org.example.analyzer.tokenizer.StandardTokenizer;
import org.example.filereader.FileReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static Index bookIndex;
    private static Index userIndex;

    public static void main(String[] args) {

        var bookDocuments = FileReader.getDirectoryFilesAsDocument("/home/sajad/IdeaProjects/SearchEngine_AP/src/main/resources/books", "txt");
        var userDocuments = FileReader.getDirectoryFilesAsDocument("/home/sajad/IdeaProjects/SearchEngine_AP/src/main/resources/users", "txt");

        bookIndex = new Index("books", StandardTokenizer.INSTANCE, List.of(MarkRemoverNormalizer.INSTANCE, UppercaseNormalizer.INSTANCE));
        userIndex = new Index("users", EdgeNGramTokenizer.INSTANCE, List.of(MarkRemoverNormalizer.INSTANCE, UppercaseNormalizer.INSTANCE));

        bookIndex.indexDocuments(bookDocuments);
        userIndex.indexDocuments(userDocuments);

        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("serach -> ");
        while (!(input = scanner.nextLine()).isBlank()) {
            var inputParts = Arrays.stream(input.split(":")).map(String::trim).toList();
            switch (inputParts.get(0)) {
                case "users":
                    printList(userIndex.search(inputParts.get(1)));
                    break;
                case "books":
                    printList(bookIndex.search(inputParts.get(1)));
                    break;
            }
            System.out.printf("serach -> ");
        }
    }

    private static void printList(Collection<String> items) {
        var index = 0;
        for(String i : items)
            System.out.println(String.format("%s %d-%s %s", ANSI_BLUE, index++, ANSI_RESET, i));
    }
}