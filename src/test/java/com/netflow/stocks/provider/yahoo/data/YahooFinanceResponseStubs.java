package com.netflow.stocks.provider.yahoo.data;

public class YahooFinanceResponseStubs {

    private YahooFinanceResponseStubs() {
    }

    public static YahooFinanceResponse stubYahooResponseWithQuoteKnown(String symbol) {
        YahooQuote yahooQuote = stubYahooQuote(symbol);
        YahooFinanceResponse response = stubResponse(yahooQuote);
        return response;
    }


    public static YahooFinanceResponse stubYahooResponseWithQuoteUnknown(String symbol) {
        YahooQuote yahooQuote = stubYahooQuoteUnknown(symbol);
        YahooFinanceResponse response = stubResponse(yahooQuote);
        return response;
    }

    private static YahooFinanceResponse stubResponse(YahooQuote yahooQuote) {

        YahooResults yahooResults = new YahooResults();
        yahooResults.setQuote(yahooQuote);

        Diagnostics diagnostics = new Diagnostics();
        diagnostics.setExecutionTime(301);

        YahooQuery yahooQuery = new YahooQuery();
        yahooQuery.setResults(yahooResults);
        yahooQuery.setDiagnostics(diagnostics);

        YahooFinanceResponse response = new YahooFinanceResponse();
        response.setQuery(yahooQuery);

        return response;
    }

    private static YahooQuote stubYahooQuote(String symbol) {
        YahooQuote yahooQuote = new YahooQuote();
        yahooQuote.setName("Mocked name Inc.");
        yahooQuote.setSymbol(symbol);
        yahooQuote.setLastTradePriceOnly("13.98");
        return yahooQuote;
    }

    private static YahooQuote stubYahooQuoteUnknown(String symbol) {
        YahooQuote yahooQuote = new YahooQuote();
        yahooQuote.setSymbol(symbol);
        return yahooQuote;
    }

}
