package com.stocks.scheduler.provider.alphavantage.queryparams;

import com.stocks.scheduler.provider.alphavantage.queryparams.ApiParameter;

public enum Function implements ApiParameter {

    TIME_SERIES_INTRADAY("TIME_SERIES_INTRADAY");

    private final String urlParameter;

    Function(String urlParameter) {
        this.urlParameter = urlParameter;
    }

    @Override
    public String getKey() {
        return "function";
    }

    @Override
    public String getValue() {
        return urlParameter;
    }
}
