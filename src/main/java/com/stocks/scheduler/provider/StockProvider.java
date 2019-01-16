package com.stocks.scheduler.provider;

import com.stocks.messaging.StockDataSubscribeHandler;
import com.stocks.models.stocks.Code;
import com.stocks.models.stocks.StockPriceData;
import com.stocks.scheduler.provider.alphavantage.AlphaVantageConnector;
import com.stocks.scheduler.provider.alphavantage.StockDataResponse;
import com.stocks.scheduler.provider.alphavantage.TimeSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class StockProvider {

    private static final int TIME_OUT = 3000;
    private Set<Code> symbols = new HashSet<>();
    private TimeSeries stockTimeSeries = new TimeSeries(new AlphaVantageConnector(TIME_OUT));
    private static Logger LOGGER = LoggerFactory.getLogger(StockProvider.class);
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
                StockDataResponse response = stockTimeSeries.intraDay(c.name());
                if (response == null) {
                    continue;
                }
                Map<String, String> metaData = response.getMetaData();
                LOGGER.info("Information: " + metaData.get("1. Information"));
                LOGGER.info("StockPriceData: " + metaData.get("2. Symbol"));
                result.add(response.getCurrentStockData());
            }
        } catch (Exception e) {
            LOGGER.error("Exception during requesting", e);
        }
        return result;
    }

    public void updateSymbols(Set<Code> symbols) {
        this.symbols.addAll(symbols);
    }

    public StockPriceData getPriceDataForSymbol(String symbol) {
        try {

            StockDataResponse response = null;
            while (response == null) {
                response = stockTimeSeries.intraDay(symbol);
            }
            Map<String, String> metaData = response.getMetaData();
            LOGGER.info("Information: " + metaData.get("1. Information"));
            LOGGER.info("StockPriceData: " + metaData.get("2. Symbol"));
            return response.getCurrentStockData();
        } catch (Exception e) {
            LOGGER.error("Exception during retrieval of price data", e);
        }
        return new StockPriceData(Code.valueOf(symbol), LocalDateTime.now(), new HashMap<>());
    }
}
