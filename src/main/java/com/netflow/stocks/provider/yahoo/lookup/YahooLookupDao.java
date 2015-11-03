package com.netflow.stocks.provider.yahoo.lookup;

import com.google.common.base.Optional;
import com.netflow.stocks.provider.yahoo.YqlQuery;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Repository
public class YahooLookupDao {

    private static Logger LOGGER = Logger.getLogger(YahooLookupDao.class);

    @Value("${netflow.stocks.lookup.retry.times}")
    private int retryTimes;

    @Autowired
    private RestTemplate yahooLookupRestTemplate;

    private String queryTemplate;

    @PostConstruct
    public void postConstruct() {

        YqlQuery yqlQuery = new YqlQuery.YqlQueryBuilder()
                .statement("select * from html where url=\"http://finance.yahoo.com/lookup?s=%s\" and xpath=\"//table[@summary='YFT_SL_TABLE_SUMMARY']/tbody/*\"")
                .format(YqlQuery.FORMAT.xml)
                .build();

        queryTemplate = yqlQuery.getQuery();

    }

    public YahooLookupQueryResponse lookupSymbolByName(String name) {

        String query = String.format(queryTemplate, name);

        for (int i = 0; i < retryTimes; i++) {
            Optional<YahooLookupQueryResponse> yahooLookupQueryResponseOptional = getResponse(query);
            if (yahooLookupQueryResponseOptional.isPresent()) {
                return yahooLookupQueryResponseOptional.get();
            }
        }

        throw new RuntimeException("Could not lookup symbol for query '" + name + "' ");
    }


    private Optional<YahooLookupQueryResponse> getResponse(String query) {

        try {
            YahooLookupQueryResponse yahooLookupQueryResponse = yahooLookupRestTemplate.getForObject(query, YahooLookupQueryResponse.class);
            return Optional.of(yahooLookupQueryResponse);
        } catch (ResourceAccessException e) {
            LOGGER.error("Lookup timeout exception for '" + query + "'");
            return Optional.absent();
        }

    }

}
