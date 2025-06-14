
package com.example.presentation.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.1
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Calculadora", targetNamespace = "http://soap.services.presentation.example.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Calculadora {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "divide", targetNamespace = "http://soap.services.presentation.example.com/", className = "com.example.presentation.services.soap.Divide")
    @ResponseWrapper(localName = "divideResponse", targetNamespace = "http://soap.services.presentation.example.com/", className = "com.example.presentation.services.soap.DivideResponse")
    @Action(input = "http://soap.services.presentation.example.com/Calculadora/divideRequest", output = "http://soap.services.presentation.example.com/Calculadora/divideResponse")
    public double divide(
        @WebParam(name = "arg0", targetNamespace = "")
        double arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        double arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "resta", targetNamespace = "http://soap.services.presentation.example.com/", className = "com.example.presentation.services.soap.Resta")
    @ResponseWrapper(localName = "restaResponse", targetNamespace = "http://soap.services.presentation.example.com/", className = "com.example.presentation.services.soap.RestaResponse")
    @Action(input = "http://soap.services.presentation.example.com/Calculadora/restaRequest", output = "http://soap.services.presentation.example.com/Calculadora/restaResponse")
    public double resta(
        @WebParam(name = "arg0", targetNamespace = "")
        double arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        double arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "suma", targetNamespace = "http://soap.services.presentation.example.com/", className = "com.example.presentation.services.soap.Suma")
    @ResponseWrapper(localName = "sumaResponse", targetNamespace = "http://soap.services.presentation.example.com/", className = "com.example.presentation.services.soap.SumaResponse")
    @Action(input = "http://soap.services.presentation.example.com/Calculadora/sumaRequest", output = "http://soap.services.presentation.example.com/Calculadora/sumaResponse")
    public double suma(
        @WebParam(name = "arg0", targetNamespace = "")
        double arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        double arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "multiplica", targetNamespace = "http://soap.services.presentation.example.com/", className = "com.example.presentation.services.soap.Multiplica")
    @ResponseWrapper(localName = "multiplicaResponse", targetNamespace = "http://soap.services.presentation.example.com/", className = "com.example.presentation.services.soap.MultiplicaResponse")
    @Action(input = "http://soap.services.presentation.example.com/Calculadora/multiplicaRequest", output = "http://soap.services.presentation.example.com/Calculadora/multiplicaResponse")
    public double multiplica(
        @WebParam(name = "arg0", targetNamespace = "")
        double arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        double arg1);

}
