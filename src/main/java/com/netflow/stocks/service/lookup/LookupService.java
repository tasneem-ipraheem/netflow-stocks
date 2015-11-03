package com.netflow.stocks.service.lookup;

import com.netflow.stocks.api.LookupResultDto;
import com.netflow.stocks.provider.yahoo.lookup.YahooLookupDao;
import com.netflow.stocks.provider.yahoo.lookup.YahooLookupQueryResponse;
import com.netflow.stocks.provider.yahoo.lookup.YahooLookupTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LookupService {

    @Autowired
    private YahooLookupDao yahooLookupDao;
    @Autowired
    private YahooLookupTransformer yahooLookupTransformer;

    public Collection<LookupResultDto> lookupSymbolByName(String name) {
        YahooLookupQueryResponse response = yahooLookupDao.lookupSymbolByName(name);
        Collection<LookupResultDto> lookupResultDtos = yahooLookupTransformer.apply(response);
        return lookupResultDtos;
    }

}
