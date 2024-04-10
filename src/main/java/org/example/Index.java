package org.example;

import lombok.Builder;
import lombok.Getter;
import org.example.analyzer.normalizer.Normalizer;
import org.example.analyzer.tokenizer.Tokenizer;
import org.example.filereader.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Builder
public class Index {

    private final String name;
    private final List<Normalizer> normalizers;
    private final Tokenizer tokenizer;
    private int totalDocs;

    private Map<String, Set<String>> invertedIndex;

    public void indexDocuments(List<Document> documents) {
        documents.forEach(this::indexDocument);
    }

    public void indexDocument(Document document) {
        var tokens = tokenizer.tokenize(document.getContent());
        var isNewDocument = true;
        for (String token: tokens) {
            if (token != null && !token.isBlank())
                isNewDocument &= addToInvertedIndex(document.getName(), applyNormalizers(token));
        }

        totalDocs += isNewDocument ? 1 : 0;
    }

    private String applyNormalizers(String token) {
        for (Normalizer normalizer : normalizers) {
            token = normalizer.normalize(token);
        }
        return token;
    }

    private boolean addToInvertedIndex(String documentId, String token) {
        if (invertedIndex.containsKey(token))
            return invertedIndex.get(token).add(documentId);

        invertedIndex.put(token, Set.of(documentId));
        return true;
    }
}