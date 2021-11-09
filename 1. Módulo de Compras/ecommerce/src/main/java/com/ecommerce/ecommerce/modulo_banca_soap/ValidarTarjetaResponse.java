//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.11.08 a las 11:42:27 PM ART 
//


package com.ecommerce.ecommerce.modulo_banca_soap;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para validar_tarjetaResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="validar_tarjetaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="validar_tarjetaResult" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validar_tarjetaResponse", propOrder = {
    "validarTarjetaResult"
})
public class ValidarTarjetaResponse {

    @XmlElementRef(name = "validar_tarjetaResult", namespace = "modulo_banca_soap", type = JAXBElement.class, required = false)
    protected JAXBElement<BigInteger> validarTarjetaResult;

    /**
     * Obtiene el valor de la propiedad validarTarjetaResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public JAXBElement<BigInteger> getValidarTarjetaResult() {
        return validarTarjetaResult;
    }

    /**
     * Define el valor de la propiedad validarTarjetaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public void setValidarTarjetaResult(JAXBElement<BigInteger> value) {
        this.validarTarjetaResult = value;
    }

}
