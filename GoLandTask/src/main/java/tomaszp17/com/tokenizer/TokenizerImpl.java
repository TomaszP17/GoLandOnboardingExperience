package tomaszp17.com.tokenizer;

public class TokenizerImpl implements Tokenizer {
    @Override
    public String[] tokenize(String text) {
        return text.split("\\W+");
    }
}
