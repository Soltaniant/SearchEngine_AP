# Unit Test
<hr>

## 👋 Hello and Welcome!
- We are going to learn: not only unit testing, but also ...
- admin to warn you
- any question will be answered by **love** ❤️ so please ask!

## 🤔 Why test?

you may already been taught what Test is and how important it is, though let's take a brief look again:

-  works correctly or not?! though, **what _correct_ means**? 
- is getting expected output enough to say a software works correctly?
- sharif.edu example

# Test types ...
- Functional
  - unit 
  - integration
  - ...
- Non-Functional (e.g. performance)
  - load test
  - stress test
  - scalability
  - ...

## Unit test
> A unit refers to the smallest testable component of an application, typically a method or a function.

Importance:
- Bug Detection
- Code Quality (enforce software design principles)
- Confident (prevent unintended side effects)
- Refactoring 
- Collaboration
- ...

## Junit 4 | 5
```xml
<dependencies>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
  </dependency>
</dependencies>
```
⚠️ **Notice maven project structure**: package naming convention

<br>
<br>
<br>

# Project
<hr>

## Full-Text Search
![google animated logo](Images/google.gif)

**Question:**
- how it works?
- how it works in such a quick way?

## Inverted Index!
🎥 [video](https://m.youtube.com/watch?v=Wf6HbY2PQDw) for more details!
### 📄 Documents
```dtd
Document 1: The quick brown fox jumped over the lazy dog.
Document 2: The lazy dog slept in the sun.
```

### Tokenize
```dtd
Document 1: [The, quick, brown, fox, jumped, over, the lazy, dog]
Document 2: [The, lazy, dog, slept, in, the, sun]
```

### Index
```dtd
The    -> Document 1, Document 2
Quick  -> Document 1
Brown  -> Document 1
Fox    -> Document 1
Jumped -> Document 1
Over   -> Document 1
Lazy   -> Document 1, Document 2
Dog    -> Document 1, Document 2
Slept  -> Document 2
In     -> Document 2
Sun    -> Document 2
```

**Q:** what if we search for `brown`?

### Normalize before indexing
```dtd
THE    -> Document 1, Document 2
QUICK  -> Document 1
BROWN  -> Document 1
FOX    -> Document 1
JUMPED -> Document 1
OVER   -> Document 1
LAZY   -> Document 1, Document 2
DOG    -> Document 1, Document 2
SLEPT  -> Document 2
IN     -> Document 2
SUN    -> Document 2
```


