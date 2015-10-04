package com.netflow.stocks.data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.fest.assertions.api.Assertions.*;

public class LocalDateTimeAttributeConverterTest {

    @InjectMocks
    private LocalDateTimeAttributeConverter converter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConvertToDatabaseColumn() throws Exception {

        Instant instant = Instant.parse("2007-12-03T10:15:30.00Z");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        Timestamp timestamp = converter.convertToDatabaseColumn(localDateTime);

        Instant timestampInstant = timestamp.toInstant();

        assertThat(timestampInstant).isEqualTo(instant);

    }

    @Test
    public void testConvertToDatabaseColumnWhenNull() throws Exception {
        Timestamp timestamp = converter.convertToDatabaseColumn(null);
        assertThat(timestamp).isNull();
    }

    @Test
    public void testConvertToEntityAttribute() throws Exception {

        Instant instant = Instant.parse("2007-12-03T10:15:30.00Z");
        Timestamp timestamp = Timestamp.from(instant);

        LocalDateTime localDateTime = converter.convertToEntityAttribute(timestamp);

        Instant localDateInstant = localDateTime.toInstant(ZoneOffset.UTC);
        assertThat(instant).isEqualTo(localDateInstant);

    }

    @Test
    public void testConvertToEntityAttributeWhenNull() throws Exception {
        LocalDateTime localDateTime = converter.convertToEntityAttribute(null);
        assertThat(localDateTime).isNull();
    }

}