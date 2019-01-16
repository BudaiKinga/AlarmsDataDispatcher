package com.stocks.scheduler.provider;

import com.stocks.models.stocks.StockPriceData;
import com.stocks.scheduler.provider.alphavantage.AlphaVantageConnector;
import com.stocks.scheduler.provider.alphavantage.StockDataResponse;
import com.stocks.scheduler.provider.alphavantage.TimeSeries;
import com.stocks.scheduler.provider.alphavantage.queryparams.Interval;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.stocks.models.stocks.Code.MSFT;
import static com.stocks.models.stocks.Code.NWL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StockProviderTest {

    private StockProvider stockProvider;

    @Before
    public void setup() {
        AlphaVantageConnector connector = mock(AlphaVantageConnector.class);
        when(connector.getRequest(eq(MSFT.name()))).thenReturn(ResponseMock.MSFT_RESPONSE);
        when(connector.getRequest(eq(NWL.name()))).thenReturn(ResponseMock.NWL_RESPONSE);
        TimeSeries timeSeries = new TimeSeries(connector);
        stockProvider = new StockProvider();
        stockProvider.setStockTimeSeries(timeSeries);
    }

    @Test
    public void getPriceData_shouldReturnEmptyList_whenNoSymbolsSubscribed() throws Exception {
        List<StockPriceData> result = stockProvider.getPriceData();
        assertTrue(result.isEmpty());
    }

    @Test
    public void getPriceData_shouldReturnValidStockData_whenSymbolsSubscribed() throws Exception {
        stockProvider.updateSymbols(new HashSet(Arrays.asList(MSFT, NWL)));
        List<StockPriceData> result = stockProvider.getPriceData();
        assertThat(result.size(), equalTo(2));
        List<StockPriceData> expected = new ArrayList<>();
        expected.add(buildStockPriceData(ResponseMock.MSFT_RESPONSE));
        expected.add(buildStockPriceData(ResponseMock.NWL_RESPONSE));
        assertListEquals(result, expected);
    }

    private void assertListEquals(List<StockPriceData> result, List<StockPriceData> expected) {
        checkListContains(result, expected);
        checkListContains(expected, result);
    }

    private void checkListContains(List<StockPriceData> result, List<StockPriceData> expected) {
        for (StockPriceData spd : result) {
            assertTrue(expected.contains(spd));
        }
    }

    private StockPriceData buildStockPriceData(String response) {
        return StockDataResponse.from(Interval.ONE_MIN, response).getCurrentStockData();
    }


}