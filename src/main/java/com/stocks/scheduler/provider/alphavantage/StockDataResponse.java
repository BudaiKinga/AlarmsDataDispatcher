package com.stocks.scheduler.provider.alphavantage;


import com.stocks.models.stocks.Code;
import com.stocks.models.stocks.PriceType;
import com.stocks.models.stocks.StockPriceData;
import com.stocks.scheduler.provider.alphavantage.queryparams.Interval;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StockDataResponse {
    private final Map<String, String> metaData;
    private final List<StockPriceData> stockData;

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public List<StockPriceData> getStockData() {
        return stockData;
    }

    private StockDataResponse(final Map<String, String> metaData, final List<StockPriceData> stocks) {
        this.stockData = stocks;
        this.metaData = metaData;
    }


    public static StockDataResponse from(Interval interval, String json) {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    public StockPriceData getCurrentStockData() {
        return getStockData().get(0);
    }


    private static class Parser extends TimeSeriesParser<StockDataResponse> {
        private final Interval interval;

        Parser(Interval interval) {
            this.interval = interval;
        }

        @Override
        String getStockDataKey() {
            return "Time Series (" + interval.getValue() + ")";
        }

        @Override
        StockDataResponse resolve(Map<String, String> metaData, Map<String, Map<String, String>> stockData) {
            List<StockPriceData> stocks = new ArrayList<>();
            try {
                Code symbol = Code.valueOf(metaData.get("2. Symbol"));
                stockData.forEach((key, values) -> {
                    HashMap<PriceType, Double> priceData = new HashMap<>();
                    priceData.put(PriceType.OPEN_PRICE, Double.parseDouble(values.get(PriceType.OPEN_PRICE.getResponseTag())));
                    priceData.put(PriceType.HIGH_PRICE, Double.parseDouble(values.get(PriceType.HIGH_PRICE.getResponseTag())));
                    priceData.put(PriceType.LOW_PRICE, Double.parseDouble(values.get(PriceType.LOW_PRICE.getResponseTag())));
                    priceData.put(PriceType.CLOSE_PRICE, Double.parseDouble(values.get(PriceType.CLOSE_PRICE.getResponseTag())));
                    priceData.put(PriceType.VOLUME, Double.parseDouble(values.get(PriceType.CLOSE_PRICE.getResponseTag())));
                    stocks.add(new StockPriceData(symbol, LocalDateTime.parse(key, DATE_WITH_TIME_FORMAT), priceData));
                });
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return new StockDataResponse(metaData, stocks);
        }
    }

}
