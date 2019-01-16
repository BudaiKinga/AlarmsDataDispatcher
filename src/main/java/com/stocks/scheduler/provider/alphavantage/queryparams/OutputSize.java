package com.stocks.scheduler.provider.alphavantage.queryparams;

import com.stocks.scheduler.provider.alphavantage.queryparams.ApiParameter;

public enum OutputSize implements ApiParameter {
    COMPACT("compact");

    private final String outputSize;

    OutputSize(String outputSize) {
        this.outputSize = outputSize;
    }

    @Override
    public String getKey() {
        return "outputsize";
    }

    @Override
    public String getValue() {
        return outputSize;
    }
}
