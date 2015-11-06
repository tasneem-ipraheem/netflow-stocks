package com.netflow.stocks.statistics;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProviderStats {

    private Provider provider;
    private int executionTimeMilliseconds;
    private int successNumber;
    private int failureNumber;

    public ProviderStats(Provider provider) {
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public int getExecutionTimeMilliseconds() {
        return executionTimeMilliseconds;
    }

    public void setExecutionTimeMilliseconds(int executionTimeMilliseconds) {
        this.executionTimeMilliseconds = executionTimeMilliseconds;
    }

    public int getSuccessNumber() {
        return successNumber;
    }

    public void setSuccessNumber(int successNumber) {
        this.successNumber = successNumber;
    }

    public int getFailureNumber() {
        return failureNumber;
    }

    public void setFailureNumber(int failureNumber) {
        this.failureNumber = failureNumber;
    }
}