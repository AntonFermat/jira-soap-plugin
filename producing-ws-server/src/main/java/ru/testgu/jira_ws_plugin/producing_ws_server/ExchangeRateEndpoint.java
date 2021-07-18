package ru.testgu.jira_ws_plugin.producing_ws_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static ru.testgu.jira_ws_plugin.producing_ws_server.WebServiceConfig.NAMESPACE_URI;


@Endpoint
public class ExchangeRateEndpoint {

    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateEndpoint(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExchangeRateRequest")
    @ResponsePayload
    public GetExchangeRateResponse getExchangeRate(@RequestPayload GetExchangeRateRequest request) {
        GetExchangeRateResponse response = new GetExchangeRateResponse();
        response.setExchangeRate(exchangeRateRepository.getExchangeRate(request.getCurrency()));
        return response;
    }
}
