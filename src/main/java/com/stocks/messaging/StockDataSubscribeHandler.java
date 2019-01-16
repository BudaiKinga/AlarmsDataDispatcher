package com.stocks.messaging;

import com.stocks.models.stocks.Code;
import com.stocks.models.stocks.StockPriceData;
import com.stocks.scheduler.provider.StockProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StockDataSubscribeHandler {

    @Autowired
    StockProvider stockDataProvider;

    @Value("${messaging.init.subject}")
    String initialPriceSubject;

    @Autowired
    JmsTemplate jmsTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(StockDataSubscribeHandler.class);

    @JmsListener(destination = "${messaging.subscribe.subject}")
    public void receiveSubscribeMessage(Set<String> msg) {
        LOGGER.info("Received Message: " + msg);
        stockDataProvider.updateSymbols(msg.stream().map(Code::valueOf).collect(Collectors.toSet()));
    }

    public void addAsListener(StockProvider stockProvider) {
        this.stockDataProvider = stockProvider;
    }

    @JmsListener(destination = "${messaging.init.subject}")
    public void receiveRequestToInitPrice(String code) {
        LOGGER.info("Received Message to init price for : " + code);
        StockPriceData spd = stockDataProvider.getPriceDataForSymbol(code);
        sendInitialPrice(spd);
    }

    private void sendInitialPrice(StockPriceData spd) {
        jmsTemplate.convertAndSend(initialPriceSubject, spd);
    }
}
