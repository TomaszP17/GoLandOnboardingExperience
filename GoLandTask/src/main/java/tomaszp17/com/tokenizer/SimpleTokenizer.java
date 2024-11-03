package tomaszp17.com.tokenizer;

public class SimpleTokenizer implements Tokenizer {
    @Override
    public String[] tokenize(String text) {
        return text.split("\\W+");
    }
}
