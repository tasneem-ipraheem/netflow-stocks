package com.netflow.stocks.service.load.yahoo;

import com.netflow.stocks.data.NetflowStock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;

import static org.fest.assertions.api.Assertions.assertThat;

public class YahooStockDtoTransformerTest {

    @InjectMocks
    private YahooStockTransformer transformer;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApply(){
        NetflowStock netflowStock = transformer.apply(stubYahooStock());
        assertThat(netflowStock.getSymbol()).isEqualTo("AAPL");
        assertThat(netflowStock.getName()).isEqualTo("Apple Inc.");
        assertThat(netflowStock.getPrice()).isEqualTo(new BigDecimal("100.02"));
    }

    private Stock stubYahooStock(){
        Stock yahooStock = new Stock("AAPL");
        yahooStock.setName("Apple Inc.");
        StockQuote stockQuote = new StockQuote("AAPL");
        stockQuote.setPrice(new BigDecimal("100.02"));
        yahooStock.setQuote(stockQuote);
        return yahooStock;
    }


}