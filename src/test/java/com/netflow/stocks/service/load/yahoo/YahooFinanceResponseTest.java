package com.netflow.stocks.service.load.yahoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

import static org.fest.assertions.api.Assertions.*;

public class YahooFinanceResponseTest {

    @Test
    public void testMultipleQuotes() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Resource resource = new ClassPathResource("responses/yahoo/multiple_quotes_found.json");

        YahooFinanceResponse response = mapper.readValue(resource.getFile(), YahooFinanceResponse.class);

        YahooQuery query = response.getQuery();
        List<YahooQuote> quotes = query.getResults().getQuotes();
        assertThat(quotes).hasSize(2);
        YahooQuote quote1 = quotes.get(0);
        YahooQuote quote2 = quotes.get(1);
        assertThat(quote1.getSymbol()).isEqualTo("ed");
        assertThat(quote1.getName()).isEqualTo("Consolidated Edison, Inc. Commo");
        assertThat(quote1.getCurrency()).isEqualTo("USD");
        assertThat(quote1.getLastTradePriceOnly()).isEqualTo("65.43");
        assertThat(quote2.getSymbol()).isEqualTo("AAPL");
        assertThat(quote2.getName()).isEqualTo("Apple Inc.");
        assertThat(quote2.getCurrency()).isEqualTo("USD");
        assertThat(quote2.getLastTradePriceOnly()).isEqualTo("112.12");
    }

    @Test
    public void testMultipleQuotesUnknown() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Resource resource = new ClassPathResource("responses/yahoo/multiple_quotes_unknown.json");

        YahooFinanceResponse response = mapper.readValue(resource.getFile(), YahooFinanceResponse.class);

        YahooQuery query = response.getQuery();
        List<YahooQuote> quotes = query.getResults().getQuotes();
        assertThat(quotes).hasSize(2);
        YahooQuote quote1 = quotes.get(0);
        YahooQuote quote2 = quotes.get(1);
        assertThat(quote1.getSymbol()).isEqualTo("UNK1");
        assertThat(quote1.getName()).isNull();
        assertThat(quote2.getSymbol()).isEqualTo("UNK2");
        assertThat(quote2.getName()).isNull();
    }

}