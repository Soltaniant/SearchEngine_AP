package org.example;

import org.example.analyzer.tokenizer.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class IndexTest {

    /**
     * mockito simple mock for searching in indices
     *   mock tokenizer and normalizer to index documents simply
     *   use @Before (before each)
     *   
     */

    @Test
    void test(){
        var tokenizer = mock(Tokenizer.class);
        when(tokenizer.tokenize(any())).thenReturn(List.of("a", "b"));
        verify(tokenizer).tokenize("salam");
    }
}
