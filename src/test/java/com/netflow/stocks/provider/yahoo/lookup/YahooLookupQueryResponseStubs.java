package com.netflow.stocks.provider.yahoo.lookup;


import com.google.common.collect.Lists;

public class YahooLookupQueryResponseStubs {

    private YahooLookupQueryResponseStubs() {
    }

    public static YahooLookupQueryResponse stubYahooLookupResponse(String symbol, String name) {

        Diagnostics diagnostics = new Diagnostics();
        diagnostics.setExecutionTime(101);

        YahooResults yahooResults = new YahooResults();
        yahooResults.setRows(Lists.newArrayList(stubRow(symbol, name)));

        YahooLookupQueryResponse response = new YahooLookupQueryResponse();
        response.setDiagnostics(diagnostics);
        response.setResults(yahooResults);

        return response;

    }


    private static YahooLookupResultRow stubRow(String symbol, String name) {
        YahooLookupResultRow row = new YahooLookupResultRow();
        row.setColumns(Lists.newArrayList(
                        stubColumn(symbol),
                        stubColumn(name),
                        stubColumn(""),
                        stubColumn(""),
                        stubColumn(""),
                        stubColumn("NYC"))
        );
        return row;
    }

    private static YahooLookupResultColumn stubColumn(String value) {
        YahooLookupResultColumn column = new YahooLookupResultColumn();
        column.setValue(value);
        return column;
    }


}
