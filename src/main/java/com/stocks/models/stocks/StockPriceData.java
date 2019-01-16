package com.stocks.models.stocks;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StockPriceData {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime date;
    private Map<PriceType, Double> priceData;
    private Set<PriceType> updatedPrices;
    private Code symbol;

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

    public void setSymbol(Code symbol) {
        this.symbol = symbol;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setPriceData(Map<PriceType, Double> priceData) {
        this.priceData = priceData;
    }

    public void setUpdatedPrices(Set<PriceType> updatedPrices) {
        this.updatedPrices = updatedPrices;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof StockPriceData) {
            StockPriceData o = (StockPriceData) other;
            return this.date.equals(o.date)
                    && this.symbol.equals(o.symbol)
                    && mapEquals(this.priceData, o.priceData)
                    && setEquals(this.updatedPrices, o.updatedPrices);
        }
        return false;
    }

    private boolean setEquals(Set<PriceType> set1, Set<PriceType> set2) {
        return set1.containsAll(set2) && set2.containsAll(set1);
    }

    private boolean mapEquals(Map<PriceType, Double> map1, Map<PriceType, Double> map2) {
        Set<Map.Entry<PriceType, Double>> entries1 = map1.entrySet();
        Set<Map.Entry<PriceType, Double>> entries2 = map2.entrySet();
        for (Map.Entry<PriceType, Double> e1 : entries1) {
            if (!map2.get(e1.getKey()).equals(e1.getValue())) {
                return false;
            }
        }
        for (Map.Entry<PriceType, Double> e2 : entries2) {
            if (!map1.get(e2.getKey()).equals(e2.getValue())) {
                return false;
            }
        }
        return true;
    }

    public StockPriceData() {
    }
}
