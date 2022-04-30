package br.com.ferracini;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.text.MessageFormat.format;

public class Gate {
    private static final String API_KEY = System.getenv("API_KEY");
    private static Logger logger = Logger.getLogger(Gate.class.getName());
    private String URL = "https://imdb-api.com/en/API/Top250Movies/";

    public String getMovies() throws IOException, URISyntaxException {

        var client = HttpClient.newBuilder().build();

        var request = HttpRequest.newBuilder()
                .header("THEAD", "AUTOMATO")
                .uri(new URL(format(URL.concat("{0}"), API_KEY)).toURI())
                .GET()
                .build();

        return client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

    }

    public static void main(String[] args) {
        Gate gate = new Gate();
        try {
            logger.log(Level.INFO, gate.getMovies());
        } catch (IOException | URISyntaxException e) {

            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
