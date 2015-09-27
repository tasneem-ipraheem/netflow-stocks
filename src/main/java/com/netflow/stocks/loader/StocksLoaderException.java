package com.netflow.stocks.loader;

public class StocksLoaderException extends RuntimeException {

    public StocksLoaderException() {
    }

    public StocksLoaderException(String message) {
        super(message);
    }

    public StocksLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public StocksLoaderException(Throwable cause) {
        super(cause);
    }

    public StocksLoaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
