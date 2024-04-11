package org.example;


import org.example.analyzer.normalizer.MarkRemoverNormalizer;
import org.example.analyzer.normalizer.UppercaseNormalizer;
import org.example.analyzer.tokenizer.EdgeNGramTokenizer;
import org.example.analyzer.tokenizer.StandardTokenizer;
import org.example.filereader.FileReader;

import java.util.Collection;
import java.util.List;

public class Main {

    private static Index bookIndex;
    private static Index userIndex;

    public static void main(String[] args) {

        var bookDocuments = FileReader.getDirectoryFilesAsDocument("/home/sajad/IdeaProjects/SearchEngine_AP/src/main/resources/Books", "txt");
        var userDocuments = FileReader.getDirectoryFilesAsDocument("/home/sajad/IdeaProjects/SearchEngine_AP/src/main/resources/Users", "txt");

        bookIndex = new Index("books", StandardTokenizer.INSTANCE, List.of(MarkRemoverNormalizer.INSTANCE, UppercaseNormalizer.INSTANCE));
        userIndex = new Index("users", EdgeNGramTokenizer.INSTANCE, List.of(MarkRemoverNormalizer.INSTANCE, UppercaseNormalizer.INSTANCE));

        bookIndex.indexDocuments(bookDocuments);
        userIndex.indexDocuments(userDocuments);

        printList(bookIndex.search("software testing"));
        printList(userIndex.search("saja solta"));
    }

    private static void printList(Collection<String> items) {
        items.forEach(System.out::println);
    }
}

//var usernames = List.of("s_soltanian", "sajad soltanian", "alizadeh", "alireza", "soltani", "milad sajadi", "sajed molaei", "alimardani", "Alishah", "Reza Soltanian", "Soltani1432", "Rezaei", "Rezaeian", "Ali Majidi", "Majid Tavakoli", "Mohammad Mohammadi", "Mohammad Keymaram", "Mohammad Alizadeh", "zahra mohammadi", "zahra soltankhah", "zahra majidi", "maryam mohammadzadeh", "Mohammad maryamzadeh", "mahdi mohammadi", "mohammad mahdi mohammadi", "fateme sadat rezavi", "fateme safaei");
//        usernames.forEach(Main::createFiles);
//private static void createFiles(String username) {
//    try {
//        FileWriter myWriter = new FileWriter("/home/sajad/IdeaProjects/SearchEngine_AP/src/main/resources/Users/" + username + ".txt");
//        myWriter.write(username);
//        myWriter.close();
//        System.out.println("Successfully wrote to the file: " + username);
//    } catch (IOException e) {
//        System.out.println("An error occurred.");
//        e.printStackTrace();
//    }
//}