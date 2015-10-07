package com.netflow.stocks.api;

import java.net.URL;
import java.nio.charset.Charset;

import com.google.common.io.Files;
import com.netflow.stocks.Application;
import com.netflow.stocks.BaseIntegrationTest;
import com.netflow.stocks.service.load.yahoo.YahooStocksWrapper;
import com.netflow.stocks.stubs.YahooStockStubs;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@TestPropertySource(value = "classpath:config/netflow-stocks.properties")
@SqlGroup({
        @Sql(scripts = "file:src/main/database/mysql/drop_tables.sql"),
        @Sql(scripts = "file:src/main/database/mysql/create_tables.sql"),
        @Sql(scripts = "classpath:database/insert_test_data.sql")
})
public class StocksApiIT extends BaseIntegrationTest {

    @Autowired
    private YahooStocksWrapper yahooStocksWrapper;

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {

        template = new TestRestTemplate();

    }

    @Test
    public void getStockFromDatabase() throws Exception {

        Resource expectedResponse = new ClassPathResource("responses/gold_ETF_01.json");
        String expectedResponseString = Files.toString(expectedResponse.getFile(), Charset.defaultCharset());

        base = new URL("http://localhost:" + port + "/stocks/PHAG.L");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        String responseString = response.getBody();


        JSONAssert.assertEquals(expectedResponseString, responseString, true);

    }


    @Test
    public void getStockFromYahooFinance() throws Exception {

        Resource expectedResponse = new ClassPathResource("responses/mocked_01.json");
        String expectedResponseString = Files.toString(expectedResponse.getFile(), Charset.defaultCharset());
        when(yahooStocksWrapper.getStockBySymbol("MOCK")).thenReturn(YahooStockStubs.stubStock("MOCK"));


        base = new URL("http://localhost:" + port + "/stocks/MOCK");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);

        String responseString = response.getBody();

        JSONAssert.assertEquals(expectedResponseString, responseString, true);

    }


}