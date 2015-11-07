package com.netflow.stocks.api;

import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

import com.google.common.io.Files;
import com.netflow.stocks.Application;
import com.netflow.stocks.BaseIntegrationTest;
import com.netflow.stocks.provider.yahoo.data.YahooFinanceResponse;
import com.netflow.stocks.provider.yahoo.lookup.YahooLookupQueryResponse;
import com.netflow.stocks.provider.yahoo.lookup.YahooLookupQueryResponseStubs;
import com.netflow.stocks.service.retrieval.util.DateUtils;
import com.netflow.stocks.provider.yahoo.data.YahooFinanceResponseStubs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@SqlGroup({
        @Sql(scripts = "file:src/main/database/mysql/drop_tables.sql"),
        @Sql(scripts = "file:src/main/database/mysql/create_tables.sql"),
        @Sql(scripts = "classpath:database/insert_test_data.sql")
})
public class StocksApiIT extends BaseIntegrationTest {

    @Autowired
    private RestTemplate yahooRestTemplate;
    @Autowired
    private RestTemplate yahooLookupRestTemplate;
    @Autowired
    private DateUtils dateUtils;

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
        when(dateUtils.now()).thenReturn(LocalDateTime.of(2015, 12, 31, 23, 55, 55));
    }

    @Test
    public void getStockFromDatabase() throws Exception {

        Resource expectedResponse = new ClassPathResource("responses/netflow/data/gold_etf_details.json");
        String expectedResponseString = Files.toString(expectedResponse.getFile(), Charset.defaultCharset());

        base = new URL("http://localhost:" + port + "/stocks/PHAG.L");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        String responseString = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONAssert.assertEquals(expectedResponseString, responseString, true);

    }


    @Test
    public void getStockFromYahooFinance() throws Exception {

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        when(yahooRestTemplate.getForObject(queryCaptor.capture(), eq(YahooFinanceResponse.class)))
                .thenReturn(YahooFinanceResponseStubs.stubYahooResponseWithQuoteKnown("MOCK"));

        Resource expectedResponse = new ClassPathResource("responses/netflow/data/mocked_stock_details.json");
        String expectedResponseString = Files.toString(expectedResponse.getFile(), Charset.defaultCharset());

        base = new URL("http://localhost:" + port + "/stocks/MOCK");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        String responseString = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONAssert.assertEquals(expectedResponseString, responseString, true);
        assertThat(queryCaptor.getValue()).contains("from yahoo.finance.quotes where symbol = \"MOCK\"");

    }

    @Test
    public void getUnknownStock() throws Exception {

        when(yahooRestTemplate.getForObject(any(String.class), eq(YahooFinanceResponse.class)))
                .thenReturn(YahooFinanceResponseStubs.stubYahooResponseWithQuoteUnknown("UNK"));

        base = new URL("http://localhost:" + port + "/stocks/UNK");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @Test
    public void lookupSymbol() throws Exception {

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        when(yahooLookupRestTemplate.getForObject(queryCaptor.capture(), eq(YahooLookupQueryResponse.class)))
                .thenReturn(YahooLookupQueryResponseStubs.stubYahooLookupResponse("ed", "Ed Inc."));

        Resource expectedResponse = new ClassPathResource("responses/netflow/lookup/lookup_ed.json");
        String expectedResponseString = Files.toString(expectedResponse.getFile(), Charset.defaultCharset());

        base = new URL("http://localhost:" + port + "/stocks/lookup?q=ed");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        String responseString = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONAssert.assertEquals(expectedResponseString, responseString, true);
        assertThat(queryCaptor.getValue()).contains("http://finance.yahoo.com/lookup?s=ed");

    }

    @Test
    public void lookupSymbolWithSpace() throws Exception {

        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);
        when(yahooLookupRestTemplate.getForObject(queryCaptor.capture(), eq(YahooLookupQueryResponse.class)))
                .thenReturn(YahooLookupQueryResponseStubs.stubYahooLookupResponseEmpty());

        Resource expectedResponse = new ClassPathResource("responses/netflow/lookup/lookup_empty.json");
        String expectedResponseString = Files.toString(expectedResponse.getFile(), Charset.defaultCharset());

        base = new URL("http://localhost:" + port + "/stocks/lookup?q=ed inc");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        String responseString = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONAssert.assertEquals(expectedResponseString, responseString, true);
        assertThat(queryCaptor.getValue()).contains("http://finance.yahoo.com/lookup?s=ed%20inc");

    }



}