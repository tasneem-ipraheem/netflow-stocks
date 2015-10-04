package com.netflow.stocks.api;

import com.netflow.stocks.service.StocksService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

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
        StockDto stockDto = stocksService.getStockBySymbol("AAPL");
        assertThat(stockDto).isNotNull();
    }

}