package com.stocks;

import com.stocks.models.stocks.Code;
import com.stocks.scheduler.RequestExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StockUpdateHandler implements Runnable {

    @Autowired
    private RequestExecutor executor;
    private Set<Code> symbols = new HashSet<>();

    public void subscribe(Code symbol) {
        this.symbols.add(symbol);
    }

    @Override
    public void run() {
        executor.startRequesting();
        System.out.println("hello");
    }
}
