package com.bagirov.developers_life.utils;

public class Giphy {

    final static String baseURL = "https://api.giphy.com/v1/gifs/";
    final static String apiKey = "0UTRbFtkMxAplrohufYco5IY74U8hOes";
    final static String tag = "fail";
    static String type = "random";
    final static String rating = "pg-13";

    public static String getURL() {
        StringBuilder builder = new StringBuilder();
        builder.append(baseURL);
        builder.append(type);
        builder.append("?api_key=" + apiKey);
        builder.append("&tag=" + tag);
        builder.append("&rating=" + rating);


        return builder.toString();
    }
}
