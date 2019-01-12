package com.stocks.scheduler.provider;

import com.stocks.jms.StockDataSubscribeHandler;
import com.stocks.models.stocks.Code;
import com.stocks.models.stocks.StockPriceData;
import com.stocks.scheduler.provider.alphavantage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class StockProvider {

    private static final String API_KEY = "P4C4FPAYBZW5IHDA";
    private static final int TIME_OUT = 3000;
    private Set<Code> symbols = new HashSet<>();
    private TimeSeries stockTimeSeries = new TimeSeries(new AlphaVantageConnector(API_KEY, TIME_OUT));
    @Autowired
    StockDataSubscribeHandler subscribeMsgHandler;

    @PostConstruct
    public void init() {
        subscribeMsgHandler.addAsListener(this);
    }

    public List<StockPriceData> getPriceData() {
        List<StockPriceData> result = new ArrayList<>();
        try {
            // API call frequency is 5 calls per minute and 500 calls per day
            for (Code c : symbols) {
                IntraDay response = stockTimeSeries.intraDay(c.name(), Interval.ONE_MIN, OutputSize.COMPACT);
                Map<String, String> metaData = response.getMetaData();
                System.out.println("Information: " + metaData.get("1. Information"));
                System.out.println("StockPriceData: " + metaData.get("2. Symbol"));

                List<StockPriceData> stockData = response.getStockData();
                return stockData;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("something went wrong");
        }
        return result;
    }

    public void updateSymbols(Set<Code> symbols) {
        this.symbols.addAll(symbols);
    }
}
