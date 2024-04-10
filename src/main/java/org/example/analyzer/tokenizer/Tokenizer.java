package org.example.analyzer.tokenizer;

import java.util.List;

public interface Tokenizer {

    /**
     * @return a separator to split the string base on it.
     */
    default String separator() {
        return "\\s+";
    }

    /**
     * Tokenizes the input string.
     * @param inputString the string to be tokenized.
     * @return list of tokenized strings.
     */
    List<String> tokenize(String inputString);

}