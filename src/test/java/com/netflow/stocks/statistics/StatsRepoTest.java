package com.netflow.stocks.statistics;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class StatsRepoTest {

    private StatsRepo statsRepo;

    @Before
    public void setUp(){
        statsRepo = new StatsRepo();
    }

    @Test
    public void testAddSuccess() throws Exception {
        statsRepo.addSuccess(Provider.YAHOO_DATA, 101);
        ProviderStats providerStats = statsRepo.getProviderStats(Provider.YAHOO_DATA);
        Assertions.assertThat(providerStats.getSuccessNumber()).isEqualTo(1);
        Assertions.assertThat(providerStats.getFailureNumber()).isZero();
        Assertions.assertThat(providerStats.getExecutionTimeMilliseconds()).isEqualTo(101);
        Assertions.assertThat(providerStats.getProvider()).isSameAs(Provider.YAHOO_DATA);

    }

    @Test
    public void testAddMultipleSuccessEntries() throws Exception {
        statsRepo.addSuccess(Provider.YAHOO_DATA, 100);
        statsRepo.addSuccess(Provider.YAHOO_DATA, 200);
        ProviderStats providerStats = statsRepo.getProviderStats(Provider.YAHOO_DATA);
        Assertions.assertThat(providerStats.getSuccessNumber()).isEqualTo(2);
        Assertions.assertThat(providerStats.getFailureNumber()).isZero();
        Assertions.assertThat(providerStats.getExecutionTimeMilliseconds()).isEqualTo(150);
        Assertions.assertThat(providerStats.getProvider()).isSameAs(Provider.YAHOO_DATA);

    }

    @Test
    public void testAverageTimeCalculationForMultipleSuccessEntries() throws Exception {
        statsRepo.addSuccess(Provider.YAHOO_DATA, 5);
        statsRepo.addSuccess(Provider.YAHOO_DATA, 5000);
        statsRepo.addSuccess(Provider.YAHOO_DATA, 68);
        statsRepo.addSuccess(Provider.YAHOO_DATA, 349);
        ProviderStats providerStats = statsRepo.getProviderStats(Provider.YAHOO_DATA);
        Assertions.assertThat(providerStats.getSuccessNumber()).isEqualTo(4);
        Assertions.assertThat(providerStats.getFailureNumber()).isZero();
        Assertions.assertThat(providerStats.getExecutionTimeMilliseconds()).isEqualTo(1354);
        Assertions.assertThat(providerStats.getProvider()).isSameAs(Provider.YAHOO_DATA);
    }


    @Test
    public void testEmptyStats() throws Exception {
        ProviderStats providerStats = statsRepo.getProviderStats(Provider.YAHOO_DATA);
        Assertions.assertThat(providerStats.getSuccessNumber()).isZero();
        Assertions.assertThat(providerStats.getFailureNumber()).isZero();
        Assertions.assertThat(providerStats.getExecutionTimeMilliseconds()).isZero();
        Assertions.assertThat(providerStats.getProvider()).isSameAs(Provider.YAHOO_DATA);
    }


    @Test
    public void testAddFailure() throws Exception {
        statsRepo.addFailure(Provider.YAHOO_DATA);
        ProviderStats providerStats = statsRepo.getProviderStats(Provider.YAHOO_DATA);
        Assertions.assertThat(providerStats.getSuccessNumber()).isZero();
        Assertions.assertThat(providerStats.getFailureNumber()).isEqualTo(1);
        Assertions.assertThat(providerStats.getExecutionTimeMilliseconds()).isZero();
        Assertions.assertThat(providerStats.getProvider()).isSameAs(Provider.YAHOO_DATA);
    }


    @Test
    public void testAddMultipleFailures() throws Exception {
        statsRepo.addFailure(Provider.YAHOO_DATA);
        statsRepo.addFailure(Provider.YAHOO_DATA);
        statsRepo.addFailure(Provider.YAHOO_DATA);
        ProviderStats providerStats = statsRepo.getProviderStats(Provider.YAHOO_DATA);
        Assertions.assertThat(providerStats.getSuccessNumber()).isZero();
        Assertions.assertThat(providerStats.getFailureNumber()).isEqualTo(3);
        Assertions.assertThat(providerStats.getExecutionTimeMilliseconds()).isZero();
        Assertions.assertThat(providerStats.getProvider()).isSameAs(Provider.YAHOO_DATA);
    }

    @Test
    public void testGetStatsSnapshot() throws Exception {

        statsRepo.addSuccess(Provider.YAHOO_DATA, 101);
        Collection<ProviderStats> providerStatsCollection = statsRepo.getStatsSnapshot();
        Assertions.assertThat(providerStatsCollection).hasSize(1);

        ProviderStats providerStats = providerStatsCollection.iterator().next();
        Assertions.assertThat(providerStats.getSuccessNumber()).isEqualTo(1);
        Assertions.assertThat(providerStats.getFailureNumber()).isZero();
        Assertions.assertThat(providerStats.getExecutionTimeMilliseconds()).isEqualTo(101);
        Assertions.assertThat(providerStats.getProvider()).isSameAs(Provider.YAHOO_DATA);
    }
}