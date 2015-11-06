package com.netflow.stocks.statistics;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collection;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StatsServiceTest {

    @InjectMocks
    private StatsService statsService;
    @Mock
    private StatsRepo statsRepo;
    @Mock
    private ProviderStats providerStats;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStatsSnapshot() throws Exception {
        Mockito.when(statsRepo.getStatsSnapshot()).thenReturn(Lists.newArrayList(providerStats));
        Collection<ProviderStats> statsCollection = statsService.getStatsSnapshot();
        assertThat(statsCollection).containsOnly(providerStats);
    }

    @Test
    public void testLogSuccess() throws Exception {
        statsService.logSuccess(Provider.YAHOO_DATA, 101);
        verify(statsRepo).addSuccess(Provider.YAHOO_DATA, 101);
    }

    @Test
    public void testLogFailure() throws Exception {
        statsService.logFailure(Provider.YAHOO_DATA);
        verify(statsRepo).addFailure(Provider.YAHOO_DATA);
    }
}