package com.stocks.scheduler.provider.alphavantage.queryparams;

import com.stocks.scheduler.provider.alphavantage.queryparams.ApiParameter;

public class Symbol implements ApiParameter {
    private String symbol;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getKey() {
        return "symbol";
    }

    @Override
    public String getValue() {
        return symbol;
    }
}
