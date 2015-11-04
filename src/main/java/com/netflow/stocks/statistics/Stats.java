package com.netflow.stocks.statistics;

import javax.inject.Singleton;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class Stats {

    private ConcurrentHashMap<Provider, ProviderStats> stats = new ConcurrentHashMap<>();


    private class ProviderStats {
        private AtomicInteger executionTimeMilliseconds;
        private AtomicInteger successNumber;
        private AtomicInteger failureNumber;

    }

}
