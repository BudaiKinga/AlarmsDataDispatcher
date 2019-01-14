package com.stocks.scheduler.provider.alphavantage;

public class TimeSeries {
    private static final String EXCEEDED_STR = "{    \"Note\": \"Thank you for using Alpha Vantage! Our standard API call frequency is 5 calls per minute and 500 calls per day. Please visit https://www.alphavantage.co/premium/ if you would like to target a higher API call frequency.\"}";
    private static final String TEST_EX = Example.STRING;
    private final ApiConnector apiConnector;

    public TimeSeries(ApiConnector apiConnector) {
        this.apiConnector = apiConnector;
    }


    public IntraDay intraDay(String symbol, Interval interval, OutputSize outputSize) {
        String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_INTRADAY, interval, outputSize);
        if (json.equals(EXCEEDED_STR)) {
            System.out.println("no. of calls exceeded!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;

        }
        if (json == null) {
            System.out.println("something wrong: " + symbol);
        }
        return IntraDay.from(interval, json);
    }

    public IntraDay intraDay(String symbol, Interval interval) {
        String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_INTRADAY, interval);
        return IntraDay.from(interval, json);
    }
}