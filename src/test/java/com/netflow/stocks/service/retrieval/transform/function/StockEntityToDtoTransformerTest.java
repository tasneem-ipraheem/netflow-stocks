package com.netflow.stocks.service.retrieval.transform.function;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.data.NetflowStock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.*;

import static org.fest.assertions.api.Assertions.*;

public class StockEntityToDtoTransformerTest {

    @InjectMocks
    private StockEntityToDtoTransformer stockEntityToDtoTransformer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApply() throws Exception {
        StockDto stockDto = stockEntityToDtoTransformer.apply(stubNetflowStock());
        assertThat(stockDto.getId()).isEqualTo(1);
        assertThat(stockDto.getName()).isEqualTo("Apple");
        assertThat(stockDto.getSymbol()).isEqualTo("AAPL");
        assertThat(stockDto.getPrice()).isEqualTo(new BigDecimal("10.01"));
        assertThat(stockDto.getUpdated()).isEqualTo(LocalDateTime.of(2000, 1, 31, 13, 10, 59));
    }

    private NetflowStock stubNetflowStock() {
        NetflowStock netflowStock = new NetflowStock();
        netflowStock.setId(1);
        netflowStock.setName("Apple");
        netflowStock.setSymbol("AAPL");
        netflowStock.setPrice(new BigDecimal("10.01"));
        netflowStock.setUpdated(LocalDateTime.of(2000, 1, 31, 13, 10, 59));
        return netflowStock;
    }

}