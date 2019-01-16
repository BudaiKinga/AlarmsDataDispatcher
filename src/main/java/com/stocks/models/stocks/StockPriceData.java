package com.stocks.models.stocks;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StockPriceData {
    private Code symbol;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
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

    public StockPriceData() {
    }
}
