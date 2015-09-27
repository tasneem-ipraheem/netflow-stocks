package com.netflow.stocks.api;

import com.netflow.stocks.rest.StocksService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import yahoofinance.YahooFinance;

import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;

public class StocksServiceTest {

    @InjectMocks
    private StocksService stocksService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getStock() throws Exception {
        Stock stock = stocksService.getStockBySymbol("AAPL");
        assertThat(stock).isNotNull();
    }

}