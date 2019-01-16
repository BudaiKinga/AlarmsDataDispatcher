package com.stocks.scheduler.provider.alphavantage;


import com.stocks.scheduler.provider.alphavantage.queryparams.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AlphaVantageConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlphaVantageConnector.class);
    private static final String BASE_URL = "https://www.alphavantage.co/query?";
    private static final String API_KEY = "P4C4FPAYBZW5IHDA";
    private final int timeOut;

    public AlphaVantageConnector(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getRequest(String symbol) {
        String params = getParameters(symbol);
        try {
            URL request = new URL(BASE_URL + params);
            LOGGER.info("Sending request to alpha vantage: " + request.toString());
            URLConnection connection = request.openConnection();
            connection.setConnectTimeout(timeOut);
            connection.setReadTimeout(timeOut);

            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseBuilder.append(line);
            }
            bufferedReader.close();
            return responseBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getParameters(String symbol) {
        ApiParameterBuilder urlBuilder = new ApiParameterBuilder();
        urlBuilder.add(new Symbol(symbol));
        urlBuilder.add(Function.TIME_SERIES_INTRADAY);
        urlBuilder.add(OutputSize.COMPACT);
        urlBuilder.add(Interval.ONE_MIN);
        urlBuilder.add("apikey", API_KEY);
        return urlBuilder.build();
    }
}
