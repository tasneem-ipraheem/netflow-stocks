package com.netflow.stocks.service.load.yahoo;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(YahooFinance.class)
public class YahooStocksWrapperTest {

    @InjectMocks
    private YahooStocksWrapper yahooStocksWrapper;
    @Mock
    private Stock yahooStockMock;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(YahooFinance.class);
        Mockito.when(yahooStockMock.getName()).thenReturn("Apple");
    }

    @Test
    public void testGetStockBySymbol() throws Exception {
        Mockito.when(YahooFinance.get("AAPL")).thenReturn(yahooStockMock);
        Stock yahooStock = yahooStocksWrapper.getStockBySymbol("AAPL");
        Assertions.assertThat(yahooStock).isSameAs(yahooStockMock);
    }

    @Test(expected = YahooStocksException.class)
    public void testGetStockBySymbolWhenNameIsNotKnown() throws Exception {

        Mockito.when(YahooFinance.get("AAPL")).thenReturn(yahooStockMock);
        Mockito.when(yahooStockMock.getName()).thenReturn("N/A");

        yahooStocksWrapper.getStockBySymbol("AAPL");

    }

    @Test(expected = YahooStocksException.class)
    public void testGetStockBySymbolWhenIOExceptionIsThrown() throws Exception {
        Mockito.when(YahooFinance.get("AAPL")).thenThrow(new IOException("Could not open socket"));
        yahooStocksWrapper.getStockBySymbol("AAPL");
    }

    @Test(expected = YahooStocksException.class)
    public void testGetStockBySymbolWhenRuntimeIsThrown() throws Exception {
        Mockito.when(YahooFinance.get("AAPL")).thenThrow(new RuntimeException("Third party issue"));
        yahooStocksWrapper.getStockBySymbol("AAPL");
    }

}