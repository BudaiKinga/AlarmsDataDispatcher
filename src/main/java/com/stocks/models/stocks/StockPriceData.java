package com.stocks.models.stocks;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StockPriceData {
    private Code symbol;
    private LocalDateTime date;
    private Map<PriceType, Double> priceData;
    private Set<PriceType> updatedPrices;

    public StockPriceData(Code symbol, LocalDateTime date, Map<PriceType, Double> priceData) {
        this.symbol = symbol;
        this.date = date;
        this.priceData = priceData;
        updatedPrices = new HashSet<>(priceData.keySet());
    }

    public Code getSymbol() {
        return symbol;
    }

    public String getDate() {
        return date.toString();
    }

    public Map<PriceType, Double> getPriceData() {
        return priceData;
    }

    public Set<PriceType> getUpdatedPrices() {
        return updatedPrices;
    }

    public void updatePrices(Set<PriceType> updatedPrices, Map<PriceType, Double> priceData) {
        for (PriceType pt : updatedPrices) {
            this.priceData.put(pt, priceData.get(pt));
        }
    }
}
