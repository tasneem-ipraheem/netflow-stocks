package com.netflow.stocks.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


@Api(value = "financial-data", description = "Financial data retrieval", produces = "application/json")
@Path("/")
public interface StocksApi {


    @ApiOperation(value = "Get stock data")
    @GET
    @Path("/stock/{stockId}")
    @Encoded
    @Produces(MediaType.APPLICATION_JSON)
    StockDto getStockBySymbol(@ApiParam(value = "stock symbol", required = true) @PathParam("stockId") String stockId);

    @ApiOperation("Lookup stock symbol")
    @GET
    @Path("/lookup")
    @Encoded
    @Produces(MediaType.APPLICATION_JSON)
    Collection<LookupResultDto> lookupSymbolByName(@ApiParam(value = "lookup query", required = true) @QueryParam("q") String nameQuery);

}