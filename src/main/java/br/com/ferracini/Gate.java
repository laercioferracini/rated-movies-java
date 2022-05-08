package br.com.ferracini;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.text.MessageFormat.format;

public class Gate {

    private static final Logger logger = Logger.getLogger(Gate.class.getName());
    private static final String API_KEY = System.getenv("API_KEY");
    private static final String URL = "https://imdb-api.com/en/API/Top250Movies/";

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
            var movies = gate.getMovies();
            var parser = new Parser();

            List<String> titleList = parser.extractFieldList(movies, "title");
            List<String> idList = parser.extractFieldList(movies, "id");
            List<String> rankList = parser.extractFieldList(movies, "rank");
            List<String> fullTitleList = parser.extractFieldList(movies, "fullTitle");
            List<String> yearList = parser.extractFieldList(movies, "year");
            List<String> imageList = parser.extractFieldList(movies, "image");
            List<String> crewList = parser.extractFieldList(movies, "crew");
            List<String> imDbRatingList = parser.extractFieldList(movies, "imDbRating");
            List<String> imDbRatingCount = parser.extractFieldList(movies, "imDbRatingCount");

            titleList.forEach(logger::info);
            idList.forEach(logger::info);
            rankList.forEach(logger::info);
            fullTitleList.forEach(logger::info);
            yearList.forEach(logger::info);
            imageList.forEach(logger::info);
            crewList.forEach(logger::info);
            imDbRatingList.forEach(logger::info);
            imDbRatingCount.forEach(logger::info);


        } catch (IOException | URISyntaxException e) {

            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
