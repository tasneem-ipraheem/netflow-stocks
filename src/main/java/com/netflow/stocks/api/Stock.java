package com.netflow.stocks.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "stock")
public class Stock {

    private String id;

    public Stock(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
