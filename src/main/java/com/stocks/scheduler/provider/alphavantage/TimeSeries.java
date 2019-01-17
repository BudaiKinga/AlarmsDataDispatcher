package com.stocks.scheduler.provider.alphavantage;

import com.stocks.scheduler.provider.alphavantage.queryparams.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class TimeSeries {
    private static final String EXCEEDED_STR = "{    \"Note\": \"Thank you for using Alpha Vantage! Our standard API call frequency is 5 calls per minute and 500 calls per day. Please visit https://www.alphavantage.co/premium/ if you would like to target a higher API call frequency.\"}";
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeSeries.class);
    private final AlphaVantageConnector apiConnector;

    public TimeSeries(AlphaVantageConnector apiConnector) {
        this.apiConnector = apiConnector;
    }

    public StockDataResponse intraDay(String symbol) {
        String json = apiConnector.getRequest(symbol);
        if (json.equals(EXCEEDED_STR)) {
            LOGGER.warn("no. of calls exceeded!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        if (json == null) {
            LOGGER.warn("No response for: " + symbol);
        }
        return StockDataResponse.from(Interval.ONE_MIN, json);
    }
}