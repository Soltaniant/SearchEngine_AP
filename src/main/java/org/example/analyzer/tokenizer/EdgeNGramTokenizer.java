package org.example.analyzer.tokenizer;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class EdgeNGramTokenizer implements Tokenizer {

    public static Tokenizer INSTANCE = new EdgeNGramTokenizer(2, 5);

    private final int minGram;
    private final int maxGram;

    /**
     * Splits input string based on whitespace and then add substrings according to min & max gram.
     */
    @Override
    public List<String> tokenize(String inputString) {
        List<String> tokens = new ArrayList<>();
        for (int i = minGram; i <= maxGram; i++) {
            for (int j = 0; j <= inputString.length() - i; j++) {
                tokens.add(inputString.substring(j, j + i));
            }
        }
        return tokens;
    }
}