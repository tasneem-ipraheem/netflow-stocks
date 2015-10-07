package com.netflow.stocks;

import com.netflow.stocks.service.load.yahoo.YahooStocksWrapper;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
        public YahooStocksWrapper getYahooStocksWrapper() {
            return mock(YahooStocksWrapper.class);
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
