package com.ecommerce.ecommerce.modulo_banca_soap;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import java.math.BigInteger;


@Service
public class SoapClient extends WebServiceGatewaySupport {

    private String endpoint = "http://localhost:8082/?wsdl";

    public JAXBElement<BigInteger> validar_tarjeta(JAXBElement<BigInteger> nro_tarjeta, JAXBElement<String> nombre, JAXBElement<String> apellido,
                                                   JAXBElement<BigInteger> dni) {

        ValidarTarjeta request = new ValidarTarjeta();
        request.setNroTarjeta(nro_tarjeta);
        request.setNombre(nombre);
        request.setApellido(apellido);
        request.setDni(dni);

        ValidarTarjetaResponse response = (ValidarTarjetaResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint, request);

        return response.getValidarTarjetaResult();
    }

    public JAXBElement<BigInteger> validar_limite_mensual(JAXBElement<BigInteger> nro_tarjeta, JAXBElement<String> tipo_tarjeta,
                                                          JAXBElement<BigInteger> total_a_pagar, JAXBElement<BigInteger> total_gastado){
        ValidarLimiteMensual request = new ValidarLimiteMensual();
        request.setNroTarjeta(nro_tarjeta);
        request.setTipoTarjeta(tipo_tarjeta);
        request.setTotalAPagar(total_a_pagar);
        request.setTotalGastado(total_gastado);

        ValidarLimiteMensualResponse response = (ValidarLimiteMensualResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint, request);

        return response.getValidarLimiteMensualResult();
    }

}