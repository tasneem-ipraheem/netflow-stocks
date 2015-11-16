package com.netflow.stocks.rest;

import com.netflow.stocks.service.StocksService;
import com.netflow.stocks.statistics.StatsService;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/stocks")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerEndpoints();
        configureSwagger();
    }

    private void configureSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setTitle("NetFlow Stocks API");
        beanConfig.setBasePath("/stocks");
        beanConfig.setResourcePackage("com.netflow.stocks");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
    }

    private void registerEndpoints() {
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
        register(WadlResource.class);
        register(StocksService.class);
        register(StatsService.class);
    }

}