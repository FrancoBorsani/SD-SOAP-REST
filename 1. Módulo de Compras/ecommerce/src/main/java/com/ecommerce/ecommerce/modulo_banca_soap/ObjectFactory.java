//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.11.08 a las 11:42:27 PM ART 
//


package com.ecommerce.ecommerce.modulo_banca_soap;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the modulo_banca_soap package. 
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

    private final static QName _ValidarLimiteMensualResponse_QNAME = new QName("modulo_banca_soap", "validar_limite_mensualResponse");
    private final static QName _ValidarTarjeta_QNAME = new QName("modulo_banca_soap", "validar_tarjeta");
    private final static QName _ValidarLimiteMensual_QNAME = new QName("modulo_banca_soap", "validar_limite_mensual");
    private final static QName _ValidarTarjetaResponse_QNAME = new QName("modulo_banca_soap", "validar_tarjetaResponse");
    private final static QName _ValidarLimiteMensualTipoTarjeta_QNAME = new QName("modulo_banca_soap", "tipo_tarjeta");
    private final static QName _ValidarLimiteMensualNroTarjeta_QNAME = new QName("modulo_banca_soap", "nro_tarjeta");
    private final static QName _ValidarLimiteMensualTotalGastado_QNAME = new QName("modulo_banca_soap", "total_gastado");
    private final static QName _ValidarLimiteMensualTotalAPagar_QNAME = new QName("modulo_banca_soap", "total_a_pagar");
    private final static QName _ValidarTarjetaResponseValidarTarjetaResult_QNAME = new QName("modulo_banca_soap", "validar_tarjetaResult");
    private final static QName _ValidarTarjetaDni_QNAME = new QName("modulo_banca_soap", "dni");
    private final static QName _ValidarTarjetaNombre_QNAME = new QName("modulo_banca_soap", "nombre");
    private final static QName _ValidarTarjetaApellido_QNAME = new QName("modulo_banca_soap", "apellido");
    private final static QName _ValidarLimiteMensualResponseValidarLimiteMensualResult_QNAME = new QName("modulo_banca_soap", "validar_limite_mensualResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: modulo_banca_soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidarTarjetaResponse }
     * 
     */
    public ValidarTarjetaResponse createValidarTarjetaResponse() {
        return new ValidarTarjetaResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarLimiteMensualResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "validar_limite_mensualResponse")
    public JAXBElement<ValidarLimiteMensualResponse> createValidarLimiteMensualResponse(ValidarLimiteMensualResponse value) {
        return new JAXBElement<ValidarLimiteMensualResponse>(_ValidarLimiteMensualResponse_QNAME, ValidarLimiteMensualResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarTarjeta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "validar_tarjeta")
    public JAXBElement<ValidarTarjeta> createValidarTarjeta(ValidarTarjeta value) {
        return new JAXBElement<ValidarTarjeta>(_ValidarTarjeta_QNAME, ValidarTarjeta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarLimiteMensual }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "validar_limite_mensual")
    public JAXBElement<ValidarLimiteMensual> createValidarLimiteMensual(ValidarLimiteMensual value) {
        return new JAXBElement<ValidarLimiteMensual>(_ValidarLimiteMensual_QNAME, ValidarLimiteMensual.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarTarjetaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "validar_tarjetaResponse")
    public JAXBElement<ValidarTarjetaResponse> createValidarTarjetaResponse(ValidarTarjetaResponse value) {
        return new JAXBElement<ValidarTarjetaResponse>(_ValidarTarjetaResponse_QNAME, ValidarTarjetaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "tipo_tarjeta", scope = ValidarLimiteMensual.class)
    public JAXBElement<String> createValidarLimiteMensualTipoTarjeta(String value) {
        return new JAXBElement<String>(_ValidarLimiteMensualTipoTarjeta_QNAME, String.class, ValidarLimiteMensual.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "nro_tarjeta", scope = ValidarLimiteMensual.class)
    public JAXBElement<BigInteger> createValidarLimiteMensualNroTarjeta(BigInteger value) {
        return new JAXBElement<BigInteger>(_ValidarLimiteMensualNroTarjeta_QNAME, BigInteger.class, ValidarLimiteMensual.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "total_gastado", scope = ValidarLimiteMensual.class)
    public JAXBElement<BigInteger> createValidarLimiteMensualTotalGastado(BigInteger value) {
        return new JAXBElement<BigInteger>(_ValidarLimiteMensualTotalGastado_QNAME, BigInteger.class, ValidarLimiteMensual.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "total_a_pagar", scope = ValidarLimiteMensual.class)
    public JAXBElement<BigInteger> createValidarLimiteMensualTotalAPagar(BigInteger value) {
        return new JAXBElement<BigInteger>(_ValidarLimiteMensualTotalAPagar_QNAME, BigInteger.class, ValidarLimiteMensual.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "validar_tarjetaResult", scope = ValidarTarjetaResponse.class)
    public JAXBElement<BigInteger> createValidarTarjetaResponseValidarTarjetaResult(BigInteger value) {
        return new JAXBElement<BigInteger>(_ValidarTarjetaResponseValidarTarjetaResult_QNAME, BigInteger.class, ValidarTarjetaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "nro_tarjeta", scope = ValidarTarjeta.class)
    public JAXBElement<BigInteger> createValidarTarjetaNroTarjeta(BigInteger value) {
        return new JAXBElement<BigInteger>(_ValidarLimiteMensualNroTarjeta_QNAME, BigInteger.class, ValidarTarjeta.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "dni", scope = ValidarTarjeta.class)
    public JAXBElement<BigInteger> createValidarTarjetaDni(BigInteger value) {
        return new JAXBElement<BigInteger>(_ValidarTarjetaDni_QNAME, BigInteger.class, ValidarTarjeta.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "nombre", scope = ValidarTarjeta.class)
    public JAXBElement<String> createValidarTarjetaNombre(String value) {
        return new JAXBElement<String>(_ValidarTarjetaNombre_QNAME, String.class, ValidarTarjeta.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "apellido", scope = ValidarTarjeta.class)
    public JAXBElement<String> createValidarTarjetaApellido(String value) {
        return new JAXBElement<String>(_ValidarTarjetaApellido_QNAME, String.class, ValidarTarjeta.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "modulo_banca_soap", name = "validar_limite_mensualResult", scope = ValidarLimiteMensualResponse.class)
    public JAXBElement<BigInteger> createValidarLimiteMensualResponseValidarLimiteMensualResult(BigInteger value) {
        return new JAXBElement<BigInteger>(_ValidarLimiteMensualResponseValidarLimiteMensualResult_QNAME, BigInteger.class, ValidarLimiteMensualResponse.class, value);
    }

}
