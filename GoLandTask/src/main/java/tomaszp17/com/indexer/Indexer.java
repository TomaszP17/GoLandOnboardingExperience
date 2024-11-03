package tomaszp17.com.indexer;

import tomaszp17.com.tokenizer.Tokenizer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Indexer {

    private Tokenizer tokenizer;
    private Map<String, Set<File>> index;

    public Indexer(Map<String, Set<File>> index, Tokenizer tokenizer) {
        this.index = new HashMap<>();
        this.tokenizer = tokenizer;
    }

    
}
