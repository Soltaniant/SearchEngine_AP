package org.example.analyzer.normalizer;

public class MarkRemoverNormalizer implements Normalizer {

    public static Normalizer INSTANCE = new MarkRemoverNormalizer();

    /**
     * Removes every character except letters and numbers.
     * @param inputString the string to be normalized.
     * @return the normalized string with only alphanumeric characters.
     */
    public String normalize(String inputString) {
        String regex = "[^a-zA-Z0-9]+";
        return inputString.replaceAll(regex, "");
    }
}