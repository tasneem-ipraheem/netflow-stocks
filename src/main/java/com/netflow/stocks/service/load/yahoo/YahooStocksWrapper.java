package com.netflow.stocks.service.load.yahoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class YahooStocksWrapper {

    @Autowired
    private RestTemplate restTemplate;
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

    public YahooAsset getStockBySymbol(String stockSymbol) {

        String query = String.format(queryTemplate, stockSymbol);
        YahooAsset yahooAsset = restTemplate.getForObject(query, YahooAsset.class);
//            Object o = restTemplate.getForEntity(query, Object.class);
//            Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

        return yahooAsset;

    }
}
