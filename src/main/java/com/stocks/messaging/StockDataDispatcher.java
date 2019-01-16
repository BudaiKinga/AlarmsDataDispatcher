package com.stocks.messaging;

import com.stocks.models.stocks.StockPriceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockDataDispatcher {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${messaging.stockdata.subject}")
    String topic;

    public void send(List<StockPriceData> stockPriceDataList) {
        for (StockPriceData priceData : stockPriceDataList) {
            jmsTemplate.convertAndSend(topic, priceData);
        }
    }
}
