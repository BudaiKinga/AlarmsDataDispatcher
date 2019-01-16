package com.stocks.scheduler.provider.alphavantage;


import com.stocks.scheduler.provider.alphavantage.queryparams.ApiParameter;

public class ApiParameterBuilder {
    private StringBuilder urlBuilder;

    public ApiParameterBuilder() {
        this.urlBuilder = new StringBuilder();
    }

    public void add(ApiParameter apiParameter) {
        add(apiParameter.getKey(), apiParameter.getValue());
    }

    public void add(String key, String value) {
        String parameter = "&" + key + "=" + value;
        this.urlBuilder.append(parameter);
    }

    public String build() {
        return this.urlBuilder.toString();
    }
}
