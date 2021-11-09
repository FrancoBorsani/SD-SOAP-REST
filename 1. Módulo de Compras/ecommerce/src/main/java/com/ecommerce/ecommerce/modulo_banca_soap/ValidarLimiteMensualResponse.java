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
 * <p>Clase Java para validar_limite_mensualResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="validar_limite_mensualResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="validar_limite_mensualResult" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validar_limite_mensualResponse", propOrder = {
    "validarLimiteMensualResult"
})
public class ValidarLimiteMensualResponse {

    @XmlElementRef(name = "validar_limite_mensualResult", namespace = "modulo_banca_soap", type = JAXBElement.class, required = false)
    protected JAXBElement<BigInteger> validarLimiteMensualResult;

    /**
     * Obtiene el valor de la propiedad validarLimiteMensualResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public JAXBElement<BigInteger> getValidarLimiteMensualResult() {
        return validarLimiteMensualResult;
    }

    /**
     * Define el valor de la propiedad validarLimiteMensualResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public void setValidarLimiteMensualResult(JAXBElement<BigInteger> value) {
        this.validarLimiteMensualResult = value;
    }

}
