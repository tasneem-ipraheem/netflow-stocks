package com.netflow.stocks.provider.yahoo.lookup;

import com.google.common.collect.Lists;
import com.netflow.stocks.api.LookupResultDto;
import org.apache.commons.lang3.StringUtils;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class YahooLookupRowTransformerTest {

    @InjectMocks
    private YahooLookupRowTransformer transformer;
    YahooLookupResultRow row;
    @Mock
    private YahooLookupResultColumn nameColumn;
    @Mock
    private YahooLookupResultColumn tickerColumn;
    @Mock
    private YahooLookupResultColumn exchangeColumn;
    @Mock
    private YahooLookupResultColumn randomColumn;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        row = new YahooLookupResultRow();
        row.setColumns(Lists.newArrayList(
                        tickerColumn,
                        nameColumn,
                        randomColumn,
                        randomColumn,
                        randomColumn,
                        exchangeColumn)
        );
    }

    @Test
    public void testApplyForNullRow() throws Exception {
        Optional<LookupResultDto> optionalDto = transformer.apply(null);
        Assertions.assertThat(optionalDto.isPresent()).isFalse();
    }

    @Test
    public void testApplyForLessColumnsThenExpected() throws Exception {
        Optional<LookupResultDto> optionalDto = transformer.apply(invalidRowWithLessColumns());
        Assertions.assertThat(optionalDto.isPresent()).isFalse();
    }

    @Test
    public void testApplyWithNullNameColumnValue() throws Exception {
        Mockito.when(nameColumn.getValue()).thenReturn(null);
        Optional<LookupResultDto> optionalDto = transformer.apply(invalidRowWithLessColumns());
        Assertions.assertThat(optionalDto.isPresent()).isFalse();
    }

    @Test
    public void testApplyWithEmptyNameColumnValue() throws Exception {
        Mockito.when(nameColumn.getValue()).thenReturn(StringUtils.EMPTY);
        Optional<LookupResultDto> optionalDto = transformer.apply(invalidRowWithLessColumns());
        Assertions.assertThat(optionalDto.isPresent()).isFalse();
    }

    @Test
    public void testApply() throws Exception {
        Mockito.when(tickerColumn.getValue()).thenReturn("ticker");
        Mockito.when(nameColumn.getValue()).thenReturn("name");
        Mockito.when(exchangeColumn.getValue()).thenReturn("exchange");
        Optional<LookupResultDto> optionalDto = transformer.apply(row);
        LookupResultDto dto = optionalDto.get();
        Assertions.assertThat(dto.getSymbol()).isEqualTo("ticker");
        Assertions.assertThat(dto.getName()).isEqualTo("name");
        Assertions.assertThat(dto.getExchange()).isEqualTo("exchange");
    }


    private YahooLookupResultRow invalidRowWithLessColumns() {
        YahooLookupResultRow row = new YahooLookupResultRow();
        row.setColumns(Lists.newArrayList(tickerColumn));
        return row;
    }

}