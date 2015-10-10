package com.netflow.stocks.service.load.yahoo;

import com.google.common.base.Preconditions;
import com.netflow.stocks.data.NetflowStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class YahooStocksWrapper {

    private static final int ONE = 1;
    private static final int FIRST = 0;

    @Autowired
    private RestTemplate yahooRestTemplate;
    @Autowired
    private YahooStockTransformer yahooStockTransformer;
    private String queryTemplate;

    @PostConstruct
    public void postConstruct() {

        StringBuilder sb = new StringBuilder();
        sb.append("https://query.yahooapis.com/v1/public/yql?q=");
        sb.append("select symbol, Name, Currency, LastTradePriceOnly from yahoo.finance.quotes where symbol = \"%s\"");
        sb.append("&format=json");
        sb.append("&diagnostics=true");
        sb.append("&env=store://datatables.org/alltableswithkeys");

        queryTemplate = sb.toString();

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
        YahooQuote yahooQuote = yahooFinanceResponse.getQuery().getResults().getQuotes().get(FIRST);
        return yahooQuote;

    }

    private void validateResponse(String stockSymbol, YahooFinanceResponse yahooFinanceResponse) {

        YahooQuery yahooQuery = yahooFinanceResponse.getQuery();
        YahooResults results = yahooQuery.getResults();

        Preconditions.checkNotNull(results, "Stock '" + stockSymbol + "' request results invalid");

        List<YahooQuote> yahooQuotes = results.getQuotes();
        Preconditions.checkState(!CollectionUtils.isEmpty(yahooQuotes), "Stock '" + stockSymbol + "' request does not contain any quotes");
        Preconditions.checkState(yahooQuotes.size() == ONE, "Stock '" + stockSymbol + "' request quote count: " + yahooQuotes.size());
    }


}
