package br.com.ferracini;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public record Movie(String id, String rank, String title, String fullTitle, String year, String image, String crew,
                    String imDbRating, String imDbRatingCount) {


    public String toJsonString() throws ParserRuntimeException {
        String value;
        try {
            value = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
           throw new ParserRuntimeException(e.getMessage());
        }
        return value;
    }

    @Override
    public String toString() {
        try {
            return toJsonString();
        } catch (ParserRuntimeException e) {
            e.printStackTrace();
            return "";
        }
    }
}
