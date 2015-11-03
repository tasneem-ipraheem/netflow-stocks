package com.netflow.stocks.provider.yahoo.lookup;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.netflow.stocks.api.LookupResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class YahooLookupTransformer implements Function<YahooLookupQueryResponse, Collection<LookupResultDto>> {

    @Autowired
    private YahooLookupRowTransformer rowTransformer;

    @Override
    public Collection<LookupResultDto> apply(YahooLookupQueryResponse yahooLookupQueryResponse) {

        Collection<LookupResultDto> results = Lists.newArrayList();

        YahooResults yahooResults = yahooLookupQueryResponse.getResults();
        if (yahooResults == null || yahooResults.getRows().isEmpty()) {
            return results;
        }

        for (YahooLookupResultRow row : yahooResults.getRows()) {
            Optional<LookupResultDto> resultOptional = rowTransformer.apply(row);
            if (resultOptional.isPresent()) {
                results.add(resultOptional.get());
            }
        }

        return results;

    }



}
