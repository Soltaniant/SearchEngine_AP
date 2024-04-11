package org.example.analyzer.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class EdgeNGramTokenizer implements Tokenizer {

    public static Tokenizer INSTANCE = new EdgeNGramTokenizer(4, 20);

    private final int minGram;
    private final int maxGram;

    public EdgeNGramTokenizer(int minGram, int maxGram) {
        this.minGram = minGram;
        this.maxGram = maxGram;
    }

    @Override
    public String separator() {
        return "\\s+";
    }

    /**
     * Splits input string based on whitespace and then add substrings according to min & max gram.
     */
    @Override
    public List<String> tokenize(String inputString) {
        List<String> tokens = new ArrayList<>();
        StandardTokenizer.INSTANCE.tokenize(inputString).forEach(word -> {
            for (int i = minGram; i <= maxGram; i++) {
                for (int j = 0; j <= word.length() - i; j++) {
                    tokens.add(word.substring(j, j + i));
                }
            }
        });
        return tokens;
    }
}