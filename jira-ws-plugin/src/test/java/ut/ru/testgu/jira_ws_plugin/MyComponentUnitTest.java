package ut.ru.testgu.jira_ws_plugin;

import org.junit.Test;
import ru.testgu.jira_ws_plugin.api.MyPluginComponent;
import ru.testgu.jira_ws_plugin.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest {
    @Test
    public void testMyName() {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent", component.getName());
    }
}