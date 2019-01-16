package com.stocks.scheduler.provider.alphavantage;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.time.format.DateTimeFormatter;

public abstract class JsonParser<Data> {

    protected final DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static com.google.gson.JsonParser PARSER = new com.google.gson.JsonParser();


    protected static Gson GSON = new Gson();

    protected abstract Data resolve(JsonObject rootObject);


    public Data parseJson(String json) {
        try {
            JsonElement jsonElement = PARSER.parse(json);
            JsonObject rootObject = jsonElement.getAsJsonObject();
            return resolve(rootObject);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
