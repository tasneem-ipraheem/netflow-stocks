package com.netflow.stocks.service.load;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.service.load.yahoo.YahooStockTransformer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import yahoofinance.Stock;

import static org.fest.assertions.api.Assertions.*;

@Ignore
public class YahooStockDtoTransformerTest {

    @InjectMocks
    private YahooStockTransformer transformer;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApply(){
//        StockDto stockDto = transformer.apply(stubYahooStock());
//        assertThat(stockDto.getSymbol()).isEqualTo("AAPL");
//        assertThat(stockDto.getName()).isEqualTo("Apple Inc.");
    }

    private Stock stubYahooStock(){
        Stock yahooStock = new Stock("AAPL");
        yahooStock.setName("Apple Inc.");
        return yahooStock;
    }


}