package com.netflow.stocks.provider.yahoo.lookup;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(namespace = "query")
public class YahooLookupQueryResponse {

    private YahooResults results;

    public YahooResults getResults() {
        return results;
    }

    public void setResults(YahooResults results) {
        this.results = results;
    }

}

class YahooResults {

    @JacksonXmlProperty(localName = "tr")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<YahooLookupResultRow> rows;

    public List<YahooLookupResultRow> getRows() {
        return rows;
    }

    public void setRows(List<YahooLookupResultRow> rows) {
        this.rows = rows;
    }

}

class YahooLookupResultRow {

    @JacksonXmlProperty(localName = "td")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<YahooLookupResultColumn> columns;

    public List<YahooLookupResultColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<YahooLookupResultColumn> columns) {
        this.columns = columns;
    }
}

@JsonDeserialize(using = YahooLookupColumnDeserializer.class)
class YahooLookupResultColumn {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
