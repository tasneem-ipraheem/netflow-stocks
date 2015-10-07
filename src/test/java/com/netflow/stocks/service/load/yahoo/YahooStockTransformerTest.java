package com.netflow.stocks.service.load.yahoo;

import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.fest.assertions.api.Assertions.assertThat;

public class YahooStockTransformerTest {

    @InjectMocks
    private YahooStockTransformer transformer;
    @Mock
    private DateUtils dateUtils;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(dateUtils.now()).thenReturn(LocalDateTime.of(2016, 1, 1, 1, 1, 1));
    }

    @Test
    public void testApply() {
        NetflowStock netflowStock = transformer.apply(stubYahooStock());
        assertThat(netflowStock.getSymbol()).isEqualTo("AAPL");
        assertThat(netflowStock.getName()).isEqualTo("Apple Inc.");
        assertThat(netflowStock.getPrice()).isEqualTo(new BigDecimal("100.02"));
        assertThat(netflowStock.getUpdated()).isEqualTo(LocalDateTime.of(2016, 1, 1, 1, 1, 1));
    }

    private Stock stubYahooStock() {
        Stock yahooStock = new Stock("AAPL");
        yahooStock.setName("Apple Inc.");
        StockQuote stockQuote = new StockQuote("AAPL");
        stockQuote.setPrice(new BigDecimal("100.02"));
        yahooStock.setQuote(stockQuote);
        return yahooStock;
    }


}