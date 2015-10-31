package com.netflow.stocks.yahoo.lookup;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class YahooLookupColumnDeserializer extends JsonDeserializer<YahooLookupResultColumn> {

    private static final String A_TAG = "a";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String TICKER_PRICE_CLASS = "ticker_down";

    @Override
    public YahooLookupResultColumn deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        YahooLookupResultColumn column = new YahooLookupResultColumn();

        JsonNodeType nodeType = node.getNodeType();

        switch (nodeType) {
            case STRING:
                column.setValue(node.asText());
                break;
            case OBJECT:
                column.setValue(parseObjectNode(node));
                break;
            default:
                throw new IllegalStateException("Check Yahoo lookup response, possible changes");
        }

        return column;

    }

    private String parseObjectNode(JsonNode node) {

        JsonNode hrefNode = node.findValue(A_TAG);
        if (hrefNode != null) {
            JsonNode tickerNode = hrefNode.findValue(StringUtils.EMPTY);
            String ticker = tickerNode.asText();
            return ticker;
        }

        JsonNode nodeClass = node.get(CLASS_ATTRIBUTE);
        if (nodeClass != null && TICKER_PRICE_CLASS.equals(nodeClass.asText())) {
            String price = node.findValue(StringUtils.EMPTY).asText();
            return price;
        }

        return StringUtils.EMPTY;

    }

}

