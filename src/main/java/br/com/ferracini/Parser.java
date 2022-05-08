package br.com.ferracini;

import java.util.List;
import java.util.regex.Pattern;

public class Parser {


    public List<String> extractFieldList(String json, String field) {

        String regex = "\"" + field + "\":\\s?\"([^\"]+)+\"";//"\"" + field + "\":\\s?\"(.+?)\"+"
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(json);

        return matcher.results().map(e -> e.group(1)).toList();


    }
}
