package com.stocks.jms;

import com.stocks.models.stocks.Code;
import com.stocks.models.stocks.StockPriceData;
import com.stocks.scheduler.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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
