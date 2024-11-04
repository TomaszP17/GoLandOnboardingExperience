import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tomaszp17.com.indexer.Indexer;
import tomaszp17.com.tokenizer.Tokenizer;
import tomaszp17.com.tokenizer.TokenizerImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndexerTest {
    private Indexer indexer;
    private Path path;
    @BeforeEach
    void setUp() throws IOException {

        Tokenizer tokenizer = new TokenizerImpl();
        indexer = new Indexer(tokenizer);

        path = Files.createTempDirectory("testsDir");

        Path testFile = path.resolve("a1.txt");
        Path secondTestFile = path.resolve("a2.txt");

        Files.writeString(testFile, "My name is Tom");
        Files.writeString(secondTestFile, "What is your name my friend?");

        indexer.indexPath(path.toString());
    }
    @Test
    public void testSearchReturnsCorrectResult() {
        Set<Path> tomPaths = indexer.search("Tom");
        assertEquals(1, tomPaths.size(), "Should return 'Tom' in 1 file");
        assertTrue(tomPaths.contains(path.resolve("a1.txt")));

        Set<Path> friendPaths = indexer.search("Friend");
        assertEquals(1, friendPaths.size(), "Should return 'friend' in 1 file");
        assertTrue(friendPaths.contains(path.resolve("a2.txt")));
    }
    @Test
    public void testSearchIsCaseInsensitive() {
        Set<Path> tomLowerCase = indexer.search("tom");
        Set<Path> tomUpperCase = indexer.search("TOM");
        assertEquals(tomLowerCase, tomUpperCase, "Search should be case-insensitive");
    }
}
