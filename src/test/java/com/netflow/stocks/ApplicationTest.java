package com.netflow.stocks;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.fest.assertions.api.Assertions.*;

public class ApplicationTest {

    private Application application;

    @Before
    public void setUp() {
        application = new Application();
    }

    @Test
    public void testYahooRestTemplate() throws Exception {
        RestTemplate restTemplate = application.yahooLookupRestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        assertThat(messageConverters).hasSize(1);
        HttpMessageConverter messageConverter = messageConverters.get(0);
        assertThat(messageConverter instanceof MappingJackson2XmlHttpMessageConverter).isTrue();
    }

    @Test
    public void testYahooLookupRestTemplate() throws Exception {
        RestTemplate restTemplate = application.yahooLookupRestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        assertThat(messageConverters).hasSize(1);
        HttpMessageConverter messageConverter = messageConverters.get(0);
        assertThat(messageConverter instanceof MappingJackson2XmlHttpMessageConverter).isTrue();
    }
}
