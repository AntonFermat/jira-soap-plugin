package ru.testgu.jira_ws_plugin.producing_ws_server;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

@Component
public class ExchangeRateRepository {

    @PostConstruct
    public void initData() {

    }

    public ExchangeRate getExchangeRate(Currency currency) {
        Assert.notNull(currency, "The currency must not be null");
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrency(currency);
        switch (currency) {
            case RUR:
                exchangeRate.setCurrencyValue(1);
            case USD:
                exchangeRate.setCurrencyValue(2);
            case EUR:
                exchangeRate.setCurrencyValue(3);
        }
        return exchangeRate;
    }
}
