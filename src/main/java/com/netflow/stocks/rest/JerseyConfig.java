package com.netflow.stocks.rest;

import com.netflow.stocks.service.StocksService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/stocks")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(StocksService.class);
    }

}