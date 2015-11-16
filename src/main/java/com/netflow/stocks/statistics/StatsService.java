package com.netflow.stocks.statistics;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Api(value = "usage-stats", description = "NetFlow statistics", produces = "application/json")
@Path("/")
@Service
public class StatsService {

    @Resource
    private StatsRepo statsRepo;

    @ApiOperation(value = "Get NetFlow statistics")
    @GET
    @Path("/stats")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProviderStats> getStatsSnapshot() {
        return statsRepo.getStatsSnapshot();
    }

    public void logSuccess(Provider provider, int milliseconds) {
        statsRepo.addSuccess(provider, milliseconds);
    }

    public void logFailure(Provider provider) {
        statsRepo.addFailure(provider);
    }

}
