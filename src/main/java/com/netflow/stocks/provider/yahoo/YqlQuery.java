package com.netflow.stocks.provider.yahoo;

public class YqlQuery {

    private String query;

    private YqlQuery(YqlQueryBuilder yqlQueryBuilder) {

        StringBuilder querySb = new StringBuilder();
        querySb.append("https://query.yahooapis.com/v1/public/yql?q=");
        querySb.append(yqlQueryBuilder.statement);
        querySb.append("&format=json");
        querySb.append("&diagnostics=true");
        querySb.append("&env=store://datatables.org/alltableswithkeys");

        this.query = querySb.toString();

    }

    public String getQuery() {
        return query;
    }

    public static class YqlQueryBuilder {

        private String statement;

        public YqlQueryBuilder statement(String statement) {
            this.statement = statement;
            return this;
        }

        public YqlQuery build() {
            return new YqlQuery(this);
        }

    }

}
