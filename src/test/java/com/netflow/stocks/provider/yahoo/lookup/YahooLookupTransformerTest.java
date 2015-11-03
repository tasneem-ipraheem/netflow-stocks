package com.netflow.stocks.provider.yahoo.lookup;

import com.google.common.collect.Lists;
import com.netflow.stocks.api.LookupResultDto;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.Optional;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class YahooLookupTransformerTest {

    @InjectMocks
    private YahooLookupTransformer lookupTransformer;
    @Mock
    private YahooLookupRowTransformer rowTransformer;
    @Mock
    private LookupResultDto lookupResultDto;
    private YahooLookupQueryResponse yahooLookupQueryResponse;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        yahooLookupQueryResponse = new YahooLookupQueryResponse();
    }


    @Test
    public void testApplyWithNoResponse() throws Exception {
        yahooLookupQueryResponse.setResults(null);
        Collection<LookupResultDto> resultDtos = lookupTransformer.apply(yahooLookupQueryResponse);
        assertThat(resultDtos).isEmpty();
        verifyZeroInteractions(rowTransformer);
    }

    @Test
    public void testApplyWithEmptyResponse() throws Exception {
        yahooLookupQueryResponse.setResults(stubEmptyYahooResults());
        Collection<LookupResultDto> resultDtos = lookupTransformer.apply(yahooLookupQueryResponse);
        assertThat(resultDtos).isEmpty();
        verifyZeroInteractions(rowTransformer);
    }

    @Test
    public void testApply() throws Exception {
        Mockito.when(rowTransformer.apply(any(YahooLookupResultRow.class)))
                .thenReturn(Optional.of(lookupResultDto))
                .thenReturn(Optional.empty());
        yahooLookupQueryResponse.setResults(stubYahooResults());
        Collection<LookupResultDto> resultDtos = lookupTransformer.apply(yahooLookupQueryResponse);
        assertThat(resultDtos).containsExactly(lookupResultDto);
    }

    private YahooResults stubEmptyYahooResults() {
        YahooResults yahooResults = new YahooResults();
        yahooResults.setRows(Lists.newArrayList());
        return yahooResults;
    }

    private YahooResults stubYahooResults() {
        YahooResults yahooResults = new YahooResults();
        yahooResults.setRows(Lists.newArrayList(new YahooLookupResultRow(), new YahooLookupResultRow()));
        return yahooResults;
    }

}