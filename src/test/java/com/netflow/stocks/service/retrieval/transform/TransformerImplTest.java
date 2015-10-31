package com.netflow.stocks.service.retrieval.transform;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.service.retrieval.transform.function.StockEntityToDtoTransformer;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class TransformerImplTest {

    @InjectMocks
    private TransformerImpl transformer;
    @Mock
    private StockEntityToDtoTransformer stockEntityToDtoTransformer;
    @Mock
    private NetflowStock netflowStock;
    @Mock
    private StockDto stockDto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testEntityToDto() throws Exception {
        when(stockEntityToDtoTransformer.apply(netflowStock)).thenReturn(stockDto);
        StockDto stockDtoResult = transformer.entityToDto(netflowStock);
        Assertions.assertThat(stockDtoResult).isSameAs(stockDto);
    }
}