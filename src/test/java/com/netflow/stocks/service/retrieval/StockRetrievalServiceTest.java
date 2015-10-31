package com.netflow.stocks.service.retrieval;

import com.netflow.stocks.api.StockDto;
import com.netflow.stocks.data.NetflowStock;
import com.netflow.stocks.data.NetflowStockRepository;
import com.netflow.stocks.service.retrieval.transform.Transformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StockRetrievalServiceTest {

    @InjectMocks
    private StockRetrievalService stockRetrievalService;
    @Mock
    private NetflowStockRepository netflowStockRepository;
    @Mock
    private NetflowStockLoader netflowStockLoader;
    @Mock
    private Transformer transformer;
    @Mock
    private NetflowStock netflowStock;
    @Mock
    private StockDto stockDtoMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(transformer.entityToDto(netflowStock)).thenReturn(stockDtoMock);
    }

    @Test
    public void getStockWhenStockIsInRepository() throws Exception {
        when(netflowStockRepository.findOneBySymbol("AAPL")).thenReturn(netflowStock);
        StockDto stockDto = stockRetrievalService.getStockBySymbol("AAPL");
        assertThat(stockDto).isSameAs(stockDto);
    }

    @Test
    public void getStockWhenStockIsNotInRepository() throws Exception {

        when(netflowStockRepository.findOneBySymbol("AAPL")).thenReturn(null);
        when(netflowStockLoader.getNetflowStock("AAPL")).thenReturn(netflowStock);
        when(netflowStockRepository.save(netflowStock)).thenReturn(netflowStock);

        StockDto stockDto = stockRetrievalService.getStockBySymbol("AAPL");

        assertThat(stockDto).isSameAs(stockDtoMock);
    }


}