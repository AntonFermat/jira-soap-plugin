package ru.testgu.jira_ws_plugin.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import ru.testgu.jira_ws_plugin.api.ExchangeRateService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.rmi.RemoteException;

/**
 * A resource of message.
 */
@Path("/message")
public class ExchangeRateRestResource {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateRestResource(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }


    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage() throws RemoteException {
        if (exchangeRateService != null) {
            double currencyValue = exchangeRateService.exchangeRateUSD();
            return Response.ok(new ExchangeRateRestResourceModel("USD exchange rate: " + currencyValue)).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}