package com.netflow.stocks.service;

import com.google.common.collect.Lists;
import com.netflow.stocks.api.LookupResultDto;
import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.service.lookup.LookupService;
import com.netflow.stocks.service.retrieval.StockRetrievalService;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collection;


public class StocksServiceTest {

    @InjectMocks
    private StocksService stocksService;

    @Mock
    private LookupService lookupService;
    @Mock
    private StockRetrievalService stockRetrievalService;
    @Mock
    private StockDto stockDto;
    @Mock
    private LookupResultDto lookupResultDto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStockBySymbol() throws Exception {
        Mockito.when(stockRetrievalService.getStockBySymbol("ed")).thenReturn(stockDto);
        StockDto resultStockDto = stocksService.getStockBySymbol("ed");
        Assertions.assertThat(resultStockDto).isSameAs(stockDto);
    }

    @Test
    public void testLookupSymbolByName() throws Exception {
        Mockito.when(lookupService.lookupSymbolByName("google")).thenReturn(Lists.newArrayList(lookupResultDto));
        Collection<LookupResultDto> lookupResults = stocksService.lookupSymbolByName("google");
        Assertions.assertThat(lookupResults).containsOnly(lookupResultDto);
    }
}