package com.netflow.stocks.service.lookup;

import com.netflow.stocks.api.LookupResultDto;
import com.netflow.stocks.provider.yahoo.lookup.YahooLookupDao;
import com.netflow.stocks.provider.yahoo.lookup.YahooLookupQueryResponse;
import com.netflow.stocks.provider.yahoo.lookup.YahooLookupTransformer;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collection;

public class LookupServiceTest {

    @InjectMocks
    private LookupService lookupService;
    @Mock
    private YahooLookupDao yahooLookupDao;
    @Mock
    private YahooLookupTransformer yahooLookupTransformer;
    @Mock
    private YahooLookupQueryResponse yahooLookupQueryResponse;
    @Mock
    private Collection<LookupResultDto> lookupResultDtos;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLookupSymbolByName() throws Exception {
        Mockito.when(yahooLookupDao.lookupSymbolByName("ed")).thenReturn(yahooLookupQueryResponse);
        Mockito.when(yahooLookupTransformer.apply(yahooLookupQueryResponse)).thenReturn(lookupResultDtos);
        Collection<LookupResultDto> resultDtos = lookupService.lookupSymbolByName("ed");
        Assertions.assertThat(resultDtos).isSameAs(lookupResultDtos);
    }



}