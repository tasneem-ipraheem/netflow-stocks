package com.netflow.stocks.yahoo;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.*;

public class YqlQueryTest {

    @Test
    public void testGetQuery() throws Exception {

        YqlQuery yqlQuery = new YqlQuery.YqlQueryBuilder()
                .statement("select symbol, Name, Currency, LastTradePriceOnly from yahoo.finance.quotes where symbol = AAPL")
                .build();

        String expectedQuery = "https://query.yahooapis.com/v1/public/yql?" +
                "q=select symbol, Name, Currency, LastTradePriceOnly from yahoo.finance.quotes " +
                "where symbol = AAPL&format=json&diagnostics=true&env=store://datatables.org/alltableswithkeys";

        assertThat(yqlQuery.getQuery()).isEqualTo(expectedQuery);

    }
}