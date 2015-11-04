package com.netflow.stocks.provider.yahoo.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;

public class YahooFinanceResponseIT {

    @Test
    public void testSingleQuote() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Resource resource = new ClassPathResource("responses/yahoo/data/single_quote_found.json");

        YahooFinanceResponse response = mapper.readValue(resource.getFile(), YahooFinanceResponse.class);

        YahooQuery query = response.getQuery();
        YahooQuote quote = query.getResults().getQuote();
        Diagnostics diagnostics = query.getDiagnostics();
        assertThat(quote.getSymbol()).isEqualTo("ed");
        assertThat(quote.getName()).isEqualTo("Consolidated Edison, Inc. Commo");
        assertThat(quote.getLastTradePriceOnly()).isEqualTo("65.75");
        assertThat(quote.getCurrency()).isEqualTo("USD");
        assertThat(diagnostics.getExecutionTime()).isEqualTo(503);
    }

    @Test
    public void testSingleQuoteUnknown() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Resource resource = new ClassPathResource("responses/yahoo/data/single_quote_unknown.json");

        YahooFinanceResponse response = mapper.readValue(resource.getFile(), YahooFinanceResponse.class);

        YahooQuery query = response.getQuery();
        Diagnostics diagnostics = query.getDiagnostics();
        YahooQuote quote = query.getResults().getQuote();
        assertThat(quote.getSymbol()).isEqualTo("UNK1");
        assertThat(quote.getLastTradePriceOnly()).isNull();
        assertThat(quote.getName()).isNull();
        assertThat(diagnostics.getExecutionTime()).isEqualTo(426);
    }

}