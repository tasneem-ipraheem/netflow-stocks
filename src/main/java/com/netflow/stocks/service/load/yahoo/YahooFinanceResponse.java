package com.netflow.stocks.service.load.yahoo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YahooFinanceResponse {

    private YahooQuery query;

    public YahooQuery getQuery() {
        return query;
    }

    public void setQuery(YahooQuery query) {
        this.query = query;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class YahooQuery {

    @JsonProperty(value = "count")
    private int resultCount;
    private YahooResults results;


    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public YahooResults getResults() {
        return results;
    }

    public void setResults(YahooResults results) {
        this.results = results;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class YahooResults {

    @JsonProperty(value = "quote")
    private List<YahooQuote> quotes;

    public List<YahooQuote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<YahooQuote> quotes) {
        this.quotes = quotes;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class YahooQuote {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Currency")
    private String currency;

    @JsonProperty("LastTradePriceOnly")
    private String lastTradePriceOnly;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLastTradePriceOnly() {
        return lastTradePriceOnly;
    }

    public void setLastTradePriceOnly(String lastTradePriceOnly) {
        this.lastTradePriceOnly = lastTradePriceOnly;
    }

}