package br.com.ferracini;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gate {

    private static final Logger logger = Logger.getLogger(Gate.class.getName());

    public static void main(String[] args) {

        try {
            var movies = Client.getResponse250Movies();
            var parser = new Parser();
            var moviesJson = parser.moviesJson(movies);

            moviesJson.forEach(m -> logger.log(Level.INFO, m.fullTitle()));

        } catch (IOException | URISyntaxException e) {

            logger.log(Level.SEVERE, e.getMessage());
        } catch (ParserRuntimeException e) {
            e.printStackTrace();
        }
    }
}
