package com.stocks.scheduler.provider.alphavantage;


import com.stocks.models.stocks.Code;
import com.stocks.models.stocks.PriceType;
import com.stocks.models.stocks.StockPriceData;

import java.time.LocalDateTime;
import java.util.*;


/**
 * Representation of intra day response from api.
 *
 * @see TimeSeriesResponse
 */
public class IntraDay extends TimeSeriesResponse {

    private IntraDay(final Map<String, String> metaData, final List<StockPriceData> stocks) {
        super(metaData, stocks);
    }

    /**
     * Creates {@code IntraDay} instance from json.
     *
     * @param json string to parse
     * @return IntraDay instance
     */
    public static IntraDay from(Interval interval, String json) {
        Parser parser = new Parser(interval);
        return parser.parseJson(json);
    }

    /**
     * Helper class for parsing json to {@code IntraDay}.
     *
     * @see TimeSeriesParser
     * @see JsonParser
     */
    private static class Parser extends TimeSeriesParser<IntraDay> {
        private final Interval interval;

        Parser(Interval interval) {
            this.interval = interval;
        }

        @Override
        String getStockDataKey() {
            return "Time Series (" + interval.getValue() + ")";
        }

        @Override
        IntraDay resolve(Map<String, String> metaData, Map<String, Map<String, String>> stockData) {
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
            return new IntraDay(metaData, stocks);
        }
    }

}
