package org.example;

import org.example.analyzer.normalizer.Normalizer;
import org.example.analyzer.tokenizer.StandardTokenizer;
import org.example.analyzer.tokenizer.Tokenizer;
import org.example.filereader.Document;

import java.util.*;
import java.util.stream.Collectors;

public class Index {

    private final String name;
    private final List<Normalizer> normalizers;
    private final Tokenizer tokenizer;
    private final Tokenizer searchTokenizer;

    private final Map<String, Set<String>> invertedIndex = new HashMap<>();
    private final HashSet<String> docs = new HashSet<>();

    public Index(String name, Tokenizer tokenizer, Tokenizer searchTokenizer, List<Normalizer> normalizers) {
        this.name = name;
        this.normalizers = normalizers;
        this.tokenizer = tokenizer;
        this.searchTokenizer = searchTokenizer;
    }

    /**
     * By default, the {@link StandardTokenizer} is used as search tokenizer.
     */
    public Index(String name, Tokenizer tokenizer, List<Normalizer> normalizers) {
        this(name, tokenizer, StandardTokenizer.INSTANCE, normalizers);
    }

    public Set<String> search(String query) {
        return searchTokenizer.tokenize(query).stream()
                .map(q -> invertedIndex.get(applyNormalizers(q)))
                .reduce((l1, l2) -> l1.stream()
                        .filter(l2::contains)
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    public void indexDocuments(List<Document> documents) {
        documents.forEach(this::indexDocument);
    }

    public void indexDocument(Document document) {
        var tokens = tokenizer.tokenize(document.content());
        for (String token : tokens) {
            if (token != null && !token.isBlank())
                addToInvertedIndex(document.name(), applyNormalizers(token));
        }
        docs.add(document.name());
    }

    private String applyNormalizers(String inputString) {
        for (Normalizer normalizer : normalizers) {
            inputString = normalizer.normalize(inputString);
        }
        return inputString;
    }

    private void addToInvertedIndex(String documentId, String token) {
        if (invertedIndex.containsKey(token))
            invertedIndex.get(token).add(documentId);
        else invertedIndex.put(token, new HashSet<>() {{
            add(documentId);
        }});
    }
}