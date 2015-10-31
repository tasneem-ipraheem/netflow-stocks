package com.netflow.stocks.yahoo.lookup;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import java.util.List;

import static org.fest.assertions.api.Assertions.*;

public class YahooLookupQueryResponseIT {

    @Test
    public void testGetLookup() throws Exception {


        ObjectMapper objectMapper = new MappingJackson2XmlHttpMessageConverter().getObjectMapper();

        Resource resource = new ClassPathResource("responses/yahoo/lookup/lookup_found.xml");

        YahooLookupQueryResponse response = objectMapper.readValue(resource.getFile(), YahooLookupQueryResponse.class);
        YahooResults yahooResults = response.getResults();
        List<YahooLookupResultRow> rows = yahooResults.getRows();

        assertThat(rows).hasSize(2);

        YahooLookupResultRow row1 = rows.get(0);
        List<YahooLookupResultColumn> columns1 = row1.getColumns();
        assertThat(columns1).hasSize(6);
        assertThat(columns1.get(0).getValue()).isEqualTo("GGQ1.DE");
        assertThat(columns1.get(1).getValue()).isEqualTo("Google Inc.");
        assertThat(columns1.get(2).getValue()).isEqualTo("564.00");
        assertThat(columns1.get(3).getValue()).isEqualTo("Stock");
        assertThat(columns1.get(4)).isNull();
        assertThat(columns1.get(5).getValue()).isEqualTo("GER");

        YahooLookupResultRow row2 = rows.get(1);
        List<YahooLookupResultColumn> columns2 = row2.getColumns();
        assertThat(columns2).hasSize(6);
        assertThat(columns2.get(0).getValue()).isEqualTo("GGQ7.BE");
        assertThat(columns2.get(1)).isNull();
        assertThat(columns2.get(2).getValue()).isEqualTo("NaN");
        assertThat(columns2.get(3).getValue()).isEqualTo("Stock");
        assertThat(columns2.get(4)).isNull();
        assertThat(columns2.get(5)).isNull();

    }


    @Test
    public void testGetLookupWithoutResults() throws Exception {

        ObjectMapper objectMapper = new MappingJackson2XmlHttpMessageConverter().getObjectMapper();

        Resource resource = new ClassPathResource("responses/yahoo/lookup/lookup_not_found.xml");

        YahooLookupQueryResponse response = objectMapper.readValue(resource.getFile(), YahooLookupQueryResponse.class);
        YahooResults yahooResults = response.getResults();

        assertThat(yahooResults).isNull();

    }

}