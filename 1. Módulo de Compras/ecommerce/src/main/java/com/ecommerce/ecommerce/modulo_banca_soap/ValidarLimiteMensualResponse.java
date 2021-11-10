
package com.ecommerce.ecommerce.modulo_banca_soap;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para validar_limite_mensualResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="validar_limite_mensualResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="validar_limite_mensualResult" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validar_limite_mensualResponse", propOrder = {
    "validarLimiteMensualResult"
})
public class ValidarLimiteMensualResponse {

    @XmlElement(name = "validar_limite_mensualResult")
    protected BigInteger validarLimiteMensualResult;

    /**
     * Obtiene el valor de la propiedad validarLimiteMensualResult.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValidarLimiteMensualResult() {
        return validarLimiteMensualResult;
    }

    /**
     * Define el valor de la propiedad validarLimiteMensualResult.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValidarLimiteMensualResult(BigInteger value) {
        this.validarLimiteMensualResult = value;
    }

}
