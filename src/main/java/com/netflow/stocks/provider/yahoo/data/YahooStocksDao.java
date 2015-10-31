package com.netflow.stocks.provider.yahoo.data;

import com.google.common.base.Preconditions;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.provider.yahoo.YqlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

@Repository
public class YahooStocksDao {

    @Autowired
    private RestTemplate yahooRestTemplate;
    @Autowired
    private YahooStockTransformer yahooStockTransformer;
    private String queryTemplate;

    @PostConstruct
    public void postConstruct() {

        YqlQuery yqlQuery = new YqlQuery.YqlQueryBuilder()
                .statement("select symbol, Name, Currency, LastTradePriceOnly from yahoo.finance.quotes where symbol = \"%s\"")
                .build();

        queryTemplate = yqlQuery.getQuery();

    }

    @NotNull
    public NetflowStock getStockBySymbol(String stockSymbol) {
        YahooQuote yahooQuote = getYahooQuote(stockSymbol);
        NetflowStock netflowStock = yahooStockTransformer.apply(yahooQuote);
        return netflowStock;
    }


    private YahooQuote getYahooQuote(String stockSymbol) {

        String query = String.format(queryTemplate, stockSymbol);
        YahooFinanceResponse yahooFinanceResponse = yahooRestTemplate.getForObject(query, YahooFinanceResponse.class);
        validateResponse(stockSymbol, yahooFinanceResponse);
        YahooQuote yahooQuote = yahooFinanceResponse.getQuery().getResults().getQuote();
        return yahooQuote;

    }

    private void validateResponse(String stockSymbol, YahooFinanceResponse yahooFinanceResponse) {
        YahooQuery yahooQuery = yahooFinanceResponse.getQuery();
        YahooResults results = yahooQuery.getResults();
        Preconditions.checkNotNull(results, "Stock '" + stockSymbol + "' request results invalid");
    }


}
