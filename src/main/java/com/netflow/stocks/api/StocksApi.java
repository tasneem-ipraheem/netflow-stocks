package com.netflow.stocks.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


@Path("/")
public interface StocksApi {

    @GET
    @Path("/stock/{stockId}")
    @Encoded
    @Produces(MediaType.APPLICATION_JSON)
    StockDto getStockBySymbol(@PathParam("stockId") String stockId);

    @GET
    @Path("/lookup")
    @Encoded
    @Produces(MediaType.APPLICATION_JSON)
    Collection<LookupResultDto> lookupSymbolByName(@QueryParam("q") String nameQuery);

}