package tomaszp17.com.indexer;

import tomaszp17.com.tokenizer.Tokenizer;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Indexer {
    private final Tokenizer tokenizer;
    private final Map<String, Set<Path>> index;

    public Indexer(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.index = new HashMap<>();
    }
    public void indexPath(String path) throws IOException {
        Path startPath = Paths.get(path);
        if (Files.notExists(startPath)) {
            throw new IOException("this path is not exists");
        }
        Files.walkFileTree(startPath, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (Files.isRegularFile(file) && file.toString().endsWith(".txt")) {
                    indexFile(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
    private void indexFile(Path file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String word : tokenizer.tokenize(line)) {
                    word = word.toLowerCase();
                    index.computeIfAbsent(word, k -> new HashSet<>()).add(file);
                }
            }
        }
    }
    public Set<Path> search(String word) {
        return index.getOrDefault(word.toLowerCase(), Collections.emptySet());
    }
}
