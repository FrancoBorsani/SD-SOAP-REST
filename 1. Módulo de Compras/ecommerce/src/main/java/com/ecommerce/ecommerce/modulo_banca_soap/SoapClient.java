package com.ecommerce.ecommerce.modulo_banca_soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.math.BigInteger;

public class SoapClient extends WebServiceGatewaySupport {

    private String endpoint = "http://localhost:8082/?wsdl";

    public BigInteger callValidarTarjeta(BigInteger nro_tarjeta, String nombre, String apellido, BigInteger dni){
        ValidarTarjeta request = new ValidarTarjeta();
        request.setNroTarjeta(nro_tarjeta);
        request.setNombre(nombre);
        request.setApellido(apellido);
        request.setDni(dni);
        ValidarTarjetaResponse response = (ValidarTarjetaResponse) getWebServiceTemplate().marshalSendAndReceive(endpoint, request);
        return BigInteger.valueOf(12);

        //return response.getValidarTarjetaResult();
    }

}
