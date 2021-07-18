package ru.testgu.jira_ws_plugin.impl;

import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPAuthenticator;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient3.HttpTransportPropertiesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.testgu.jira_ws_plugin.api.ExchangeRateService;
import ru.testgu.jira_ws_plugin.ws.CurrencyPortServiceStub;

import javax.inject.Named;
import java.rmi.RemoteException;

@Named("exchangeRate")
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private static final Logger log = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    @Override
    public double exchangeRateUSD() {
        double currencyValue = -1;
        try {
            CurrencyPortServiceStub stub = new CurrencyPortServiceStub();
            // Authentication
            // setAuth(stub, username, password);
            CurrencyPortServiceStub.GetExchangeRateRequest request = new CurrencyPortServiceStub.GetExchangeRateRequest();
            request.setCurrency(CurrencyPortServiceStub.Currency.USD);
            CurrencyPortServiceStub.GetExchangeRateResponse exchangeRateResponse = stub.getExchangeRate(request);
            currencyValue = exchangeRateResponse.getExchangeRate().getCurrencyValue();
        } catch (RemoteException rex) {
        }
        log.info("=============================");
        log.info("USD exchange rate: {}", currencyValue);
        log.info("=============================");
        return currencyValue;
    }

//    private void setAuth(CurrencyPortServiceStub stub, String username, String password) {
//        HTTPAuthenticator auth = new HttpTransportPropertiesImpl.Authenticator();
//        auth.setUsername(username);
//        auth.setPassword(password);
//        auth.setPreemptiveAuthentication(true);
//        Options options = stub._getServiceClient().getOptions();
//        options.setTimeOutInMilliSeconds(1000);
//        options.setProperty(HTTPConstants.AUTHENTICATE, auth);
//    }
}
