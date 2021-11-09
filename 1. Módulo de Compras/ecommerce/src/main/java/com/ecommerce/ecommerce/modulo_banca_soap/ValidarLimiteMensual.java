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
 * <p>Clase Java para validar_limite_mensual complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="validar_limite_mensual">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nro_tarjeta" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="tipo_tarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total_a_pagar" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="total_gastado" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validar_limite_mensual", propOrder = {
    "nroTarjeta",
    "tipoTarjeta",
    "totalAPagar",
    "totalGastado"
})
public class ValidarLimiteMensual {

    @XmlElementRef(name = "nro_tarjeta", namespace = "modulo_banca_soap", type = JAXBElement.class, required = false)
    protected JAXBElement<BigInteger> nroTarjeta;
    @XmlElementRef(name = "tipo_tarjeta", namespace = "modulo_banca_soap", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tipoTarjeta;
    @XmlElementRef(name = "total_a_pagar", namespace = "modulo_banca_soap", type = JAXBElement.class, required = false)
    protected JAXBElement<BigInteger> totalAPagar;
    @XmlElementRef(name = "total_gastado", namespace = "modulo_banca_soap", type = JAXBElement.class, required = false)
    protected JAXBElement<BigInteger> totalGastado;

    /**
     * Obtiene el valor de la propiedad nroTarjeta.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public JAXBElement<BigInteger> getNroTarjeta() {
        return nroTarjeta;
    }

    /**
     * Define el valor de la propiedad nroTarjeta.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public void setNroTarjeta(JAXBElement<BigInteger> value) {
        this.nroTarjeta = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoTarjeta.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Define el valor de la propiedad tipoTarjeta.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoTarjeta(JAXBElement<String> value) {
        this.tipoTarjeta = value;
    }

    /**
     * Obtiene el valor de la propiedad totalAPagar.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public JAXBElement<BigInteger> getTotalAPagar() {
        return totalAPagar;
    }

    /**
     * Define el valor de la propiedad totalAPagar.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public void setTotalAPagar(JAXBElement<BigInteger> value) {
        this.totalAPagar = value;
    }

    /**
     * Obtiene el valor de la propiedad totalGastado.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public JAXBElement<BigInteger> getTotalGastado() {
        return totalGastado;
    }

    /**
     * Define el valor de la propiedad totalGastado.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public void setTotalGastado(JAXBElement<BigInteger> value) {
        this.totalGastado = value;
    }

}
