package com.netflow.stocks.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface StocksApi {

    @GET
    @Path("/{stockId}")
    @Produces(MediaType.APPLICATION_JSON)
    StockDto getStockBySymbol(@PathParam("stockId") String stockId);

}