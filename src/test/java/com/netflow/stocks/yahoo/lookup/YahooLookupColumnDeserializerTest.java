package com.netflow.stocks.yahoo.lookup;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class YahooLookupColumnDeserializerTest {

    @InjectMocks
    private YahooLookupColumnDeserializer deserializer;
    @Mock
    private DeserializationContext deserializationContext;
    @Mock
    private JsonParser jsonParser;
    @Mock
    private ObjectCodec objectCodec;
    @Mock
    private JsonNode jsonNode;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        when(jsonParser.getCodec()).thenReturn(objectCodec);
        when(objectCodec.readTree(jsonParser)).thenReturn(jsonNode);
    }

    @Test
    public void testDeserializeString() throws Exception {

        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.STRING);
        when(jsonNode.asText()).thenReturn("some text value");

        YahooLookupResultColumn column = deserializer.deserialize(jsonParser, deserializationContext);
        Assertions.assertThat(column.getValue()).isEqualTo("some text value");

    }

    @Test
    public void testDeserializeTicker() throws Exception {

        JsonNode hrefNode = Mockito.mock(JsonNode.class);
        JsonNode tickerNode = Mockito.mock(JsonNode.class);

        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.OBJECT);
        when(jsonNode.findValue("a")).thenReturn(hrefNode);
        when(hrefNode.findValue("")).thenReturn(tickerNode);
        when(tickerNode.asText()).thenReturn("tickerSymbol");

        YahooLookupResultColumn column = deserializer.deserialize(jsonParser, deserializationContext);
        Assertions.assertThat(column.getValue()).isEqualTo("tickerSymbol");

    }

    @Test
    public void testDeserializePrice() throws Exception {

        JsonNode classNode = Mockito.mock(JsonNode.class);
        JsonNode priceNode = Mockito.mock(JsonNode.class);

        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.OBJECT);
        when(jsonNode.findValue("a")).thenReturn(null);
        when(jsonNode.get("class")).thenReturn(classNode);
        when(classNode.asText()).thenReturn("ticker_down");
        when(jsonNode.findValue("")).thenReturn(priceNode);
        when(priceNode.asText()).thenReturn("668.01");

        YahooLookupResultColumn column = deserializer.deserialize(jsonParser, deserializationContext);
        Assertions.assertThat(column.getValue()).isEqualTo("668.01");

    }

    @Test
    public void testDeserializeunknownObject() throws Exception {

        when(jsonNode.getNodeType()).thenReturn(JsonNodeType.OBJECT);
        when(jsonNode.findValue("a")).thenReturn(null);
        when(jsonNode.get("class")).thenReturn(null);

        YahooLookupResultColumn column = deserializer.deserialize(jsonParser, deserializationContext);
        Assertions.assertThat(column.getValue()).isEmpty();

    }


}