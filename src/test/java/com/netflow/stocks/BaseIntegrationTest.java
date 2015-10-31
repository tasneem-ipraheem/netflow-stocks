package com.netflow.stocks;

import com.netflow.stocks.service.retrieval.util.DateUtils;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;

public class BaseIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Configuration
    public static class TestConfiguration {


        @Bean
        @Primary
        public RestTemplate getYahooRestTemplate(){
            return mock(RestTemplate.class);
        }

        @Bean
        @Primary
        public DateUtils getDateUtils() {
            return mock(DateUtils.class);
        }

    }

    /**
     * Method can be invoked while in debugging session. It will start H2 GUI to check DB state while on breakpoint.
     * @throws SQLException
     */
    public void startH2GUI() throws SQLException {
        Server.startWebServer(dataSource.getConnection());
    }

}
