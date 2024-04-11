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

        for (int i = minGram; i <= maxGram; i++) {
            for (int j = 0; j <= inputString.length() - i; j++) {
                tokens.add(inputString.substring(j, j + i));
            }
        }
        return tokens;
    }
}