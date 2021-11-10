
package com.ecommerce.ecommerce.modulo_banca_soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ecommerce.ecommerce.modulo_banca_soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ValidarLimiteMensual_QNAME = new QName("modulo_banca_soap", "validar_limite_mensual");
    private final static QName _ValidarLimiteMensualResponse_QNAME = new QName("modulo_banca_soap", "validar_limite_mensualResponse");
    private final static QName _ValidarTarjeta_QNAME = new QName("modulo_banca_soap", "validar_tarjeta");
    private final static QName _ValidarTarjetaResponse_QNAME = new QName("modulo_banca_soap", "validar_tarjetaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ecommerce.ecommerce.modulo_banca_soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidarLimiteMensual }
     * 
     */
    public ValidarLimiteMensual createValidarLimiteMensual() {
        return new ValidarLimiteMensual();
    }

    /**
     * Create an instance of {@link ValidarLimiteMensualResponse }
     * 
     */
    public ValidarLimiteMensualResponse createValidarLimiteMensualResponse() {
        return new ValidarLimiteMensualResponse();
    }

    /**
     * Create an instance of {@link ValidarTarjeta }
     * 
     */
    public ValidarTarjeta createValidarTarjeta() {
        return new ValidarTarjeta();
    }

    /**
     * Create an instance of {@link ValidarTarjetaResponse }
     * 
     */
    public ValidarTarjetaResponse createValidarTarjetaResponse() {
        return new ValidarTarjetaResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarLimiteMensual }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidarLimiteMensual }{@code >}
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "validar_limite_mensual")
    public JAXBElement<ValidarLimiteMensual> createValidarLimiteMensual(ValidarLimiteMensual value) {
        return new JAXBElement<ValidarLimiteMensual>(_ValidarLimiteMensual_QNAME, ValidarLimiteMensual.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarLimiteMensualResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidarLimiteMensualResponse }{@code >}
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "validar_limite_mensualResponse")
    public JAXBElement<ValidarLimiteMensualResponse> createValidarLimiteMensualResponse(ValidarLimiteMensualResponse value) {
        return new JAXBElement<ValidarLimiteMensualResponse>(_ValidarLimiteMensualResponse_QNAME, ValidarLimiteMensualResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarTarjeta }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidarTarjeta }{@code >}
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "validar_tarjeta")
    public JAXBElement<ValidarTarjeta> createValidarTarjeta(ValidarTarjeta value) {
        return new JAXBElement<ValidarTarjeta>(_ValidarTarjeta_QNAME, ValidarTarjeta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarTarjetaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidarTarjetaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "validar_tarjetaResponse")
    public JAXBElement<ValidarTarjetaResponse> createValidarTarjetaResponse(ValidarTarjetaResponse value) {
        return new JAXBElement<ValidarTarjetaResponse>(_ValidarTarjetaResponse_QNAME, ValidarTarjetaResponse.class, null, value);
    }

}
