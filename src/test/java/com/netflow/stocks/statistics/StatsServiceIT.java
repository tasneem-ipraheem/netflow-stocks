package com.netflow.stocks.statistics;

import com.google.common.io.Files;
import com.netflow.stocks.Application;
import com.netflow.stocks.BaseIntegrationTest;
import com.netflow.stocks.provider.yahoo.lookup.YahooLookupQueryResponse;
import com.netflow.stocks.provider.yahoo.lookup.YahooLookupQueryResponseStubs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class StatsServiceIT extends BaseIntegrationTest {

    @Autowired
    private RestTemplate yahooLookupRestTemplate;
    @Autowired
    private StatsRepo statsRepo;

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
        statsRepo.clear();
    }

    @Test
    public void getStats() throws Exception {

        simulateLookup();

        Resource expectedResponse = new ClassPathResource("responses/netflow/stats/stats_response.json");
        String expectedResponseString = Files.toString(expectedResponse.getFile(), Charset.defaultCharset());

        base = new URL("http://localhost:" + port + "/stocks/stats");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        String responseString = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONAssert.assertEquals(expectedResponseString, responseString, true);

    }

    private void simulateLookup() throws MalformedURLException {
        when(yahooLookupRestTemplate.getForObject(any(String.class), eq(YahooLookupQueryResponse.class)))
                .thenReturn(YahooLookupQueryResponseStubs.stubYahooLookupResponse("ed", "Ed Inc."));
        base = new URL("http://localhost:" + port + "/stocks/lookup/ed");
        template.getForEntity(base.toString(), String.class);
    }

}