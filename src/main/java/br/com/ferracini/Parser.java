package br.com.ferracini;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {


    public List<String> extractFieldList(String json, String field) {

        String regex = "\"" + field + "\":\\s?\"([^\"]+)+\"";//"\"" + field + "\":\\s?\"(.+?)\"+"
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(json);

        return matcher.results().map(e -> e.group(1)).toList();


    }
    public List<Movie> moviesJson(String json) throws ParserRuntimeException {
       var list = new ArrayList<Movie>();
        try {
            String items =new ObjectMapper().readTree(json).get("items").toString();
            list = new ObjectMapper().readValue(items, new TypeReference<>() {});
        }catch (Exception e){
            throw new ParserRuntimeException(e.getMessage());
        }
        return list;
    }


}
