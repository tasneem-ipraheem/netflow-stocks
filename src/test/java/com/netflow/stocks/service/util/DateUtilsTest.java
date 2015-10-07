package com.netflow.stocks.service.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.fest.assertions.api.Assertions.*;

public class DateUtilsTest {

    @InjectMocks
    private DateUtils dateUtils;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNow() throws Exception {
        LocalDateTime localDateTime = dateUtils.now();
        assertThat(localDateTime).isNotNull();
    }
}