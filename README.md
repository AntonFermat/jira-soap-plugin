# SOAP Web Service Client in Jira 8 plugin

## jira-ws-plugin

The custom Jira plugin (8.13.6) with Axis2 SOAP web service client and its scheduled background job runner. Run
*atlas-package* to build the plugin artifacts.

## producing-ws-server

The Spring-boot sample web service server for the **jira-ws-plugin** demo. Run *mvn spring-boot:run* to
start http://localhost:8888/ws/currency.wsdl