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
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
public class YahooStocksWrapperTest {

    @InjectMocks
    private YahooStocksWrapper yahooStocksWrapper;
    @Mock
    private YahooAsset yahooAssetMock;
    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        yahooStocksWrapper.postConstruct();
        when(yahooAssetMock.getName()).thenReturn("Apple");
    }

    @Test
    public void testGetStockBySymbol() throws Exception {
        when(restTemplate.getForObject(any(String.class), eq(YahooAsset.class))).thenReturn(yahooAssetMock);
        YahooAsset yahooAsset = yahooStocksWrapper.getStockBySymbol("AAPL");
        Assertions.assertThat(yahooAsset).isSameAs(yahooAssetMock);
    }

}