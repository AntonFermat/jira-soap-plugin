package ut.ru.testgu.jira_ws_plugin.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.testgu.jira_ws_plugin.rest.ExchangeRateRestResource;

import javax.ws.rs.core.Response;
import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

public class ExchangeRateRestResourceTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void messageIsValid() throws RemoteException {
        ExchangeRateRestResource resource = new ExchangeRateRestResource(null);
        Response response = resource.getMessage();
        int status = response.getStatus();
        assertEquals("wrong status", 500, status);
    }
}
