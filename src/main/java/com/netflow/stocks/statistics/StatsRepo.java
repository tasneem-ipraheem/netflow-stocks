package com.netflow.stocks.statistics;

import com.google.common.collect.Lists;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.apache.http.annotation.ThreadSafe;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ThreadSafe
public class StatsRepo {

    private ConcurrentHashMap<Provider, ProviderStats> statsMap = new ConcurrentHashMap<>();

    public void addSuccess(Provider provider, int executionTimeMilliseconds) {
        statsMap.compute(provider, (key, value) -> addSuccess(key, value, executionTimeMilliseconds));
    }

    public void addFailure(Provider provider) {
        statsMap.compute(provider, (key, value) -> addFailure(key, value));
    }

    public synchronized ProviderStats getProviderStats(Provider provider) {
        ProviderStats providerStats = statsMap.getOrDefault(provider, new ProviderStats(provider));
        ProviderStats result = getDefensiveCopy(providerStats);
        return result;
    }

    public synchronized Collection<ProviderStats> getStatsSnapshot() {
        Collection<ProviderStats> defensiveStatsCollectionCopy = Lists.newArrayList();
        Collection<ProviderStats> statsCollection = statsMap.values();
        for (ProviderStats providerStats : statsCollection) {
            defensiveStatsCollectionCopy.add(getDefensiveCopy(providerStats));
        }
        return ImmutableList.copyOf(defensiveStatsCollectionCopy);
    }

    public synchronized void clear() {
        statsMap.clear();
    }

    private ProviderStats addSuccess(Provider provider, ProviderStats stats, int executionTimeMilliseconds) {

        if (stats == null) {

            stats = new ProviderStats(provider);
            stats.setSuccessNumber(1);
            stats.setExecutionTimeMilliseconds(executionTimeMilliseconds);

        } else {

            int newSuccessNumber = stats.getSuccessNumber() + 1;
            int newAverage = getApproximateAverageTime(stats, executionTimeMilliseconds);
            stats.setExecutionTimeMilliseconds(newAverage);
            stats.setSuccessNumber(newSuccessNumber);

        }

        return stats;
    }

    private ProviderStats addFailure(Provider provider, ProviderStats stats) {

        if (stats == null) {

            stats = new ProviderStats(provider);
            stats.setFailureNumber(1);

        } else {

            int newFailureNumber = stats.getFailureNumber() + 1;
            stats.setFailureNumber(newFailureNumber);

        }

        return stats;

    }

    private int getApproximateAverageTime(ProviderStats stats, int executionTimeMilliseconds) {
        int approximateAverage = stats.getExecutionTimeMilliseconds();
        int successNumber = stats.getSuccessNumber();
        int newApproximateAverage = ((approximateAverage * successNumber) + executionTimeMilliseconds) / (successNumber + 1);
        return newApproximateAverage;
    }

    private ProviderStats getDefensiveCopy(ProviderStats providerStats) {
        ProviderStats result = new ProviderStats(providerStats.getProvider());
        result.setSuccessNumber(providerStats.getSuccessNumber());
        result.setFailureNumber(providerStats.getFailureNumber());
        result.setExecutionTimeMilliseconds(providerStats.getExecutionTimeMilliseconds());
        return result;
    }

}
