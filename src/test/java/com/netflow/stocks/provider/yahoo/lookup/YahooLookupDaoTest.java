package com.netflow.stocks.provider.yahoo.lookup;

import com.netflow.stocks.statistics.Provider;
import com.netflow.stocks.statistics.StatsService;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class YahooLookupDaoTest {

    @InjectMocks
    private YahooLookupDao lookupDao;
    @Mock
    private RestTemplate yahooLookupRestTemplate;
    @Mock
    private StatsService statsService;
    private YahooLookupQueryResponse responseStub;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        lookupDao.postConstruct();
        lookupDao.setRetryTimes(2);
        responseStub = YahooLookupQueryResponseStubs.stubYahooLookupResponse("ed", "Consolidated Edison, Inc. Commo");
    }

    @Test
    public void testLookupSymbolByName() throws Exception {

        when(yahooLookupRestTemplate.getForObject(any(String.class), eq(YahooLookupQueryResponse.class)))
                .thenReturn(responseStub);

        YahooLookupQueryResponse response = lookupDao.lookupSymbolByName("ed");

        Assertions.assertThat(response).isSameAs(responseStub);
        verify(statsService).logSuccess(Provider.YAHOO_LOOKUP, 101);

    }

    @Test
    public void testLookupSymbolByNameAfterReadTimeoutRetry() throws Exception {

        when(yahooLookupRestTemplate.getForObject(any(String.class), eq(YahooLookupQueryResponse.class)))
                .thenThrow(new ResourceAccessException(""))
                .thenReturn(responseStub);

        YahooLookupQueryResponse response = lookupDao.lookupSymbolByName("ed");

        Assertions.assertThat(response).isSameAs(responseStub);
        verify(statsService).logSuccess(Provider.YAHOO_LOOKUP, 101);

    }

    @Test(expected = ResourceAccessException.class)
    public void testLookupSymbolByNameAfterReadTimeoutRetriesExceededMax() throws Exception {

        when(yahooLookupRestTemplate.getForObject(any(String.class), eq(YahooLookupQueryResponse.class)))
                .thenThrow(new ResourceAccessException(""))
                .thenThrow(new ResourceAccessException(""));

        lookupDao.lookupSymbolByName("ed");

        verify(statsService).logFailure(Provider.YAHOO_LOOKUP);

    }


}