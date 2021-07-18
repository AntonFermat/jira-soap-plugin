package it.ru.testgu.jira_ws_plugin.rest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ru.testgu.jira_ws_plugin.rest.ExchangeRateRestResource;
import ru.testgu.jira_ws_plugin.rest.ExchangeRateRestResourceModel;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class ExchangeRateRestResourceFuncTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void messageIsValid() {

        String baseUrl = System.getProperty("baseurl");
        String resourceUrl = baseUrl + "/rest/exchangerate/1.0/message";

        RestClient client = new RestClient();
        Resource resource = client.resource(resourceUrl);

        ExchangeRateRestResourceModel message = resource.get(ExchangeRateRestResourceModel.class);

        assertEquals("wrong message","Hello World",message.getMessage());
    }
}
