package com.netflow.stocks.yahoo.data;

public class YahooStocksException extends RuntimeException {

    public YahooStocksException() {
    }

    public YahooStocksException(String message) {
        super(message);
    }

    public YahooStocksException(String message, Throwable cause) {
        super(message, cause);
    }

    public YahooStocksException(Throwable cause) {
        super(cause);
    }

    public YahooStocksException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
