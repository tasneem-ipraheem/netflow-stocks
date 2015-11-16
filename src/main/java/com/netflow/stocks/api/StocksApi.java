package com.netflow.stocks.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


@Api
@Path("/")
public interface StocksApi {


    @ApiOperation(value = "aaa")
    @GET
    @Path("/stock/{stockId}")
    @Encoded
    @Produces(MediaType.APPLICATION_JSON)
    StockDto getStockBySymbol(@PathParam("stockId") String stockId);

    @ApiOperation("bbb")
    @GET
    @Path("/lookup")
    @Encoded
    @Produces(MediaType.APPLICATION_JSON)
    Collection<LookupResultDto> lookupSymbolByName(@QueryParam("q") String nameQuery);

}