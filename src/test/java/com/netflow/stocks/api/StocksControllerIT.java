package com.netflow.stocks.api;

import java.net.URL;
import java.nio.charset.Charset;

import com.google.common.io.Files;
import com.netflow.stocks.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class StocksControllerIT {

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/stocks/PHAG.L");
        template = new TestRestTemplate();
    }

    @Test
    public void getStock() throws Exception {

        Resource expectedResponse = new ClassPathResource("responses/appleStock01.json");
        String expectedResponseString = Files.toString(expectedResponse.getFile(), Charset.defaultCharset());

        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        String responseString = response.getBody();

        JSONAssert.assertEquals(expectedResponseString, responseString, true);

    }
}