package org.example.analyzer.normalizer;

import org.example.analyzer.tokenizer.StandardTokenizer;
import org.example.analyzer.tokenizer.Tokenizer;

public class UppercaseNormalizer implements Normalizer {

    public static Normalizer INSTANCE = new UppercaseNormalizer();

    /**
     * Makes every letter in string uppercase.
     * @param inputString the string to be normalized.
     * @return the normalized string with only upper case letters.
     */
    public String normalize(String inputString){
        return inputString.toUpperCase();
    }

}
