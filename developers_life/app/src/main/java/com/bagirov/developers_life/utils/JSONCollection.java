package com.bagirov.developers_life.utils;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONCollection {
    private final JSONArray jsonArray = new JSONArray();

    public static String getLinkFromJSON(JSONObject object) {
        try {
            return object.getString("image_original_url");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getRandomGifLink() throws JSONException {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(Giphy.getURL());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONObject jObj = new JSONObject(content.toString());
        return jObj.getJSONObject("data").getString("image_original_url");

    }
}
