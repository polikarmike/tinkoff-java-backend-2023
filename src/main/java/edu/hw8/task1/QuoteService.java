package edu.hw8.task1;

import java.util.HashMap;
import java.util.Map;

public class QuoteService {
    private QuoteService() {}

    private static final Map<String, String> QUOTES = new HashMap<>();

    static {
        QUOTES.put("личности", "Не переходи на личности там, где их нет");
        QUOTES.put("оскорбления", "Если твои противники перешли на личные оскорбления, "
            + "будь уверен — твоя победа не за горами");
        QUOTES.put("глупый", "А я тебе говорил, что ты глупый? "
            + "Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
        QUOTES.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    public static String getQuoteByKeyword(String keyword) {
        String quote = QUOTES.get(keyword.toLowerCase());
        if (quote == null) {
            quote = "Не могу найти цитату по этому ключевому слову";
        }
        return quote;
    }
}
