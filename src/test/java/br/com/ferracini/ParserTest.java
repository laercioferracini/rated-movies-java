package br.com.ferracini;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    static List<String> fieldsList = Arrays.asList("id", "rank", "title", "fullTitle", "year", "image", "crew", "imDbRating", "imDbRatingCount");

    public static Stream<Arguments> fieldsList() {
        return fieldsList.stream().map(Arguments::of);
    }

    @ParameterizedTest(name = "Checking list of Field: {0}")
    @MethodSource(value = "fieldsList")
    void titleListTest(String field) throws IOException {
        var file = Path.of("response.json");
        var json = Files.readAllLines(file);
        var parser = new Parser();

        var titleList = parser.extractFieldList(json.get(0), field);

        assertAll("Assering field: ".concat(field),
                () -> assertFalse(titleList.isEmpty(), "List is empty for field: ".concat(field)),
                () -> assertEquals(250, titleList.size(), "List size is incorrect for field: ".concat(field))
        );
    }

}