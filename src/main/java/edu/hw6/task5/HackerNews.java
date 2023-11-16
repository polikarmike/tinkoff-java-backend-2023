package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_URL_TEMPLATE = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final int HTTP_OK_STATUS_CODE = 200;

    public long[] hackerNewsTopStories() {
        try {
            String responseBody = sendGetRequest(TOP_STORIES_URL);
            String[] ids = responseBody.replaceAll("\\[|\\]", "").split(",");
            return convertToLongArray(ids);
        } catch (Exception e) {
            return new long[0];
        }
    }

    public String news(long id) {
        try {
            String itemUrl = String.format(ITEM_URL_TEMPLATE, id);
            String responseBody = sendGetRequest(itemUrl);

            Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(responseBody);

            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "Title not found";
    }

    private static String sendGetRequest(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == HTTP_OK_STATUS_CODE) {
            return response.body();
        } else {
            throw new RuntimeException("HTTP request failed");
        }
    }

    private long[] convertToLongArray(String[] ids) {
        long[] result = new long[ids.length];
        for (int i = 0; i < ids.length; i++) {
            result[i] = Long.parseLong(ids[i]);
        }
        return result;
    }

}

