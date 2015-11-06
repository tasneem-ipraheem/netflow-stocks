package com.netflow.stocks.provider.yahoo;

public final class YqlQuery {

    private String query;

    private YqlQuery(YqlQueryBuilder yqlQueryBuilder) {

        StringBuilder querySb = new StringBuilder();
        querySb.append("https://query.yahooapis.com/v1/public/yql?q=");
        querySb.append(yqlQueryBuilder.statement);
        querySb.append("&format=" + yqlQueryBuilder.format.toString());
        querySb.append("&diagnostics=true");
        querySb.append("&env=store://datatables.org/alltableswithkeys");

        this.query = querySb.toString();

    }

    public String getQuery() {
        return query;
    }

    public static class YqlQueryBuilder {

        private String statement;
        private FORMAT format = FORMAT.json;

        public YqlQueryBuilder statement(String statement) {
            this.statement = statement;
            return this;
        }

        public YqlQueryBuilder format(FORMAT format) {
            this.format = format;
            return this;
        }

        public YqlQuery build() {
            return new YqlQuery(this);
        }

    }

    public enum FORMAT {
        xml, json
    }

}
