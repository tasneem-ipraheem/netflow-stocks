package com.netflow.stocks.loader;

import com.netflow.stocks.api.Stock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.*;

public class YahooStockTransformerTest {

    @InjectMocks
    private YahooStockTransformer transformer;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApply(){
        Stock stock = transformer.apply(stubYahooStock());
        assertThat(stock.getSymbol()).isEqualTo("AAPL");
        assertThat(stock.getName()).isEqualTo("Apple Inc.");
    }

    private yahoofinance.Stock stubYahooStock(){
        yahoofinance.Stock yahooStock = new yahoofinance.Stock("AAPL");
        yahooStock.setName("Apple Inc.");
        return yahooStock;
    }


}