package org.example;

import org.example.analyzer.normalizer.MarkRemoverNormalizer;
import org.example.analyzer.normalizer.UppercaseNormalizer;
import org.example.analyzer.tokenizer.StandardTokenizer;
import org.example.filereader.FileReader;

import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var documents = FileReader.getDirectoryFilesAsDocument("C:\\Users\\brights\\IdeaProjects\\searchEngineWorkshop\\src\\main\\resources\\Books", "txt");

        var bookIndex = new Index("books", StandardTokenizer.INSTANCE, List.of(MarkRemoverNormalizer.INSTANCE, UppercaseNormalizer.INSTANCE));
        bookIndex.indexDocuments(documents);
        printList(bookIndex.search("great"));
    }

    private static void printList(Collection<String> items) {
        items.forEach(System.out::println);
    }
}