package com.netflow.stocks.service.retrieval.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateUtils {

    public LocalDateTime now(){
        return LocalDateTime.now();
    }

}
