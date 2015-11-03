package com.netflow.stocks.provider.yahoo.lookup;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.netflow.stocks.api.LookupResultDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class YahooLookupTransformer implements Function<YahooLookupQueryResponse, Collection<LookupResultDto>> {

    private static final byte EXPECTED_COLUMN_SIZE = 6;
    private static final byte TICKER_COLUMN_INDEX = 0;
    private static final byte NAME_COLUMN_INDEX = 1;
    private static final byte EXCHANGE_COLUMN_INDEX = 5;

    @Override
    public Collection<LookupResultDto> apply(YahooLookupQueryResponse yahooLookupQueryResponse) {

        Collection<LookupResultDto> results = Lists.newArrayList();

        YahooResults yahooResults = yahooLookupQueryResponse.getResults();
        if (yahooResults == null || yahooResults.getRows().isEmpty()) {
            return results;
        }


        for (YahooLookupResultRow row : yahooResults.getRows()) {
            Optional<LookupResultDto> resultOptional = transform(row);
            if (resultOptional.isPresent()) {
                results.add(resultOptional.get());
            }
        }

        return results;

    }

    private Optional<LookupResultDto> transform(YahooLookupResultRow row) {

        if (!containsValidResult(row)) {
            return Optional.empty();
        }

        List<YahooLookupResultColumn> columns = row.getColumns();

        LookupResultDto lookupResultDto = new LookupResultDto();
        lookupResultDto.setSymbol(getColumnValue(columns.get(TICKER_COLUMN_INDEX)));
        lookupResultDto.setName(getColumnValue(columns.get(NAME_COLUMN_INDEX)));
        lookupResultDto.setExchange(getColumnValue(columns.get(EXCHANGE_COLUMN_INDEX)));

        return Optional.of(lookupResultDto);

    }

    private boolean containsValidResult(YahooLookupResultRow row) {

        boolean containsValidResult = true;

        List<YahooLookupResultColumn> columns = row.getColumns();
        if (columns.size() < EXPECTED_COLUMN_SIZE) {
            containsValidResult = false;
        }

        YahooLookupResultColumn nameColumn = columns.get(NAME_COLUMN_INDEX);
        if (StringUtils.isEmpty(getColumnValue(nameColumn))) {
            containsValidResult = false;
        }

        return containsValidResult;

    }

    private String getColumnValue(YahooLookupResultColumn column) {

        if (column == null) {
            return StringUtils.EMPTY;
        }

        return StringUtils.defaultString(column.getValue());

    }

}
