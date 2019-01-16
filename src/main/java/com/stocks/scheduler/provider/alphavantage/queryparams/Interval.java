package com.stocks.scheduler.provider.alphavantage.queryparams;

import com.stocks.scheduler.provider.alphavantage.queryparams.ApiParameter;

public enum Interval implements ApiParameter {
    ONE_MIN("1min");

    private final String interval;

    Interval(String interval) {
        this.interval = interval;
    }

    @Override
    public String getKey() {
        return "interval";
    }

    @Override
    public String getValue() {
        return interval;
    }
}
