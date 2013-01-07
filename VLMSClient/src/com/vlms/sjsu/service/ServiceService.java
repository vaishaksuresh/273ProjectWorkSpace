/**
 * ServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.vlms.sjsu.service;

public interface ServiceService extends javax.xml.rpc.Service {
    public java.lang.String getServiceAddress();

    public com.vlms.sjsu.service.Service getService() throws javax.xml.rpc.ServiceException;

    public com.vlms.sjsu.service.Service getService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
