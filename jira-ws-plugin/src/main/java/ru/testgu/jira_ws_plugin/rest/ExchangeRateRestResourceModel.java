package ru.testgu.jira_ws_plugin.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRateRestResourceModel {

    @XmlElement(name = "value")
    private String message;

    public ExchangeRateRestResourceModel() {
    }

    public ExchangeRateRestResourceModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}