package org.example.analyzer.tokenizer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StandardTokenizer implements Tokenizer{

    public static Tokenizer INSTANCE = new StandardTokenizer();

    @Override
    public String separator() {
        return "\\s+";
    }

    /**
     * Splits the input by the separator string.
     */
    public List<String> tokenize(String inputString) {
        return Stream.of(inputString.split(separator())).filter(Objects::nonNull).toList();
    }
}