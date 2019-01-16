package com.stocks.scheduler.provider;

public class ResponseMock {

    public static final String MSFT_RESPONSE = "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Intraday (5min) open, high, low, close prices and volume\",\n" +
            "        \"2. Symbol\": \"MSFT\",\n" +
            "        \"3. Last Refreshed\": \"2019-01-11 16:00:00\",\n" +
            "        \"4. Interval\": \"1min\",\n" +
            "        \"5. Output Size\": \"Compact\",\n" +
            "        \"6. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Time Series (1min)\": {\n" +
            "        \"2019-01-11 16:00:00\": {\n" +
            "            \"1. open\": \"102.7700\",\n" +
            "            \"2. high\": \"103.0950\",\n" +
            "            \"3. low\": \"102.7200\",\n" +
            "            \"4. close\": \"102.7700\",\n" +
            "            \"5. volume\": \"1881938\"\n" +
            "        }" +
            "    }\n" +
            "}";

    public static final String NWL_RESPONSE = "{\n" +
            "    \"Meta Data\": {\n" +
            "        \"1. Information\": \"Intraday (5min) open, high, low, close prices and volume\",\n" +
            "        \"2. Symbol\": \"NWL\",\n" +
            "        \"3. Last Refreshed\": \"2019-01-11 16:00:00\",\n" +
            "        \"4. Interval\": \"1min\",\n" +
            "        \"5. Output Size\": \"Compact\",\n" +
            "        \"6. Time Zone\": \"US/Eastern\"\n" +
            "    },\n" +
            "    \"Time Series (1min)\": {\n" +
            "        \"2019-01-11 16:00:00\": {\n" +
            "            \"1. open\": \"89.7564\",\n" +
            "            \"2. high\": \"99.9996\",\n" +
            "            \"3. low\": \"85.0237\",\n" +
            "            \"4. close\": \"86.9720\",\n" +
            "            \"5. volume\": \"5379154\"\n" +
            "        }" +
            "    }\n" +
            "}";
}
