/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-primefaces-sd:src/main/java/component/Message.p.vm.java
 */
package com.company.demo.web.component;

import javax.faces.application.FacesMessage;

/**
 * Wrap {@link FacesMessage} along with the id of the associated component.
 */
public class Message {

    private String sourceId;
    private FacesMessage facesMessage;

    public Message(String sourceId, FacesMessage facesMessage) {
        this.sourceId = sourceId;
        this.facesMessage = facesMessage;
    }

    public String getSourceId() {
        return sourceId;
    }

    public FacesMessage getFacesMessage() {
        return facesMessage;
    }
}
