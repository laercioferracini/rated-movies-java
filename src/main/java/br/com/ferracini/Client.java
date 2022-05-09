package br.com.ferracini;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.text.MessageFormat.format;

public class Client {

    private static final String API_KEY = System.getenv("API_KEY");
    private static final String URL = "https://imdb-api.com/en/API/Top250Movies/";

    private Client() {
    }

    public static String getResponse250Movies() throws IOException, URISyntaxException {

        var client = HttpClient.newBuilder().build();

        var request = HttpRequest.newBuilder()
                .header("THEAD", "AUTOMATO")
                .uri(new URL(format(URL.concat("{0}"), API_KEY)).toURI())
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

    }

}
