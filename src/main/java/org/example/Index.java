package org.example;

import lombok.Getter;
import org.example.analyzer.normalizer.Normalizer;
import org.example.analyzer.tokenizer.Tokenizer;
import org.example.filereader.Document;

import java.util.*;

@Getter
public class Index {

    private final String name;
    private final List<Normalizer> normalizers;
    private final Tokenizer tokenizer;

    private final Map<String, HashSet<String>> invertedIndex;

    public Index(String name, Tokenizer tokenizer, List<Normalizer> normalizers) {
        this.name = name;
        this.normalizers = normalizers;
        this.tokenizer = tokenizer;
        this.invertedIndex = new HashMap<>();
    }

    public HashSet<String> search(String query) {
        return invertedIndex.get(applyNormalizers(query));
    }

    public void indexDocuments(List<Document> documents) {
        documents.forEach(this::indexDocument);
    }

    public void indexDocument(Document document) {
        var tokens = tokenizer.tokenize(document.getContent());
        for (String token : tokens) {
            if (token != null && !token.isBlank())
                addToInvertedIndex(document.getName(), applyNormalizers(token));
        }
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