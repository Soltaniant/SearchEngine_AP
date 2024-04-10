package org.example.filereader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Document {

    private final String name;
    private final String content;
}