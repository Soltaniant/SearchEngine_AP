package org.example.analyzer.tokenizer;

import java.util.ArrayList;
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
     * TODO: rewrite this method using streams (++bonus)
     */
    public List<String> tokenize(String inputString) {
        var result = new ArrayList<String>();
        for (String token : inputString.split(separator())) {
            if (!token.isBlank())
                result.add(token);
        }
        return result;
    }
}