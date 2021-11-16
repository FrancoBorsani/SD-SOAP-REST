package com.ecommerce.ecommerce.banca;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class BancaSoapClient {

    public String validar_limite_mensual(String nro_tarjeta, String tipo_tarjeta, double total_a_pagar, double total_gastado) {

        String xml_parsed="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mod=\"modulo_banca_soap\">\r\n" +
                "   <soapenv:Header/>\r\n" +
                "   <soapenv:Body>\r\n" +
                "      <mod:validar_limite_mensual>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <mod:nro_tarjeta>"+ nro_tarjeta +"</mod:nro_tarjeta>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <mod:tipo_tarjeta>"+ tipo_tarjeta +"</mod:tipo_tarjeta>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <mod:total_a_pagar>"+ total_a_pagar +"</mod:total_a_pagar>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <mod:total_gastado>"+ total_gastado +"</mod:total_gastado>\r\n" +
                "      </mod:validar_limite_mensual>\r\n" +
                "   </soapenv:Body>\r\n" +
                "</soapenv:Envelope>";
        return callService(xml_parsed, "validar_limite_mensual");
    }

    public String validar_tarjeta(String nro_tarjeta, String tipo_tarjeta, String nombre, String apellido, long dni) {

        String xml_parsed="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mod=\"modulo_banca_soap\">\r\n" +
                "   <soapenv:Header/>\r\n" +
                "   <soapenv:Body>\r\n" +
                "      <mod:validar_tarjeta>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <mod:nro_tarjeta>"+ nro_tarjeta +"</mod:nro_tarjeta>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <mod:tipo_tarjeta>"+ tipo_tarjeta +"</mod:tipo_tarjeta>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <mod:nombre>"+ nombre +"</mod:nombre>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <mod:apellido>"+ apellido  +"</mod:apellido>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <mod:dni>"+ dni +"</mod:dni>\r\n" +
                "      </mod:validar_tarjeta>\r\n" +
                "   </soapenv:Body>\r\n" +
                "</soapenv:Envelope>";

        return callService(xml_parsed, "validar_tarjeta");
    }

    public String depositar_cuenta_bancaria(String nro_cuenta, double cantidad_a_depositar) {

        String xml_parsed="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mod=\"modulo_banca_soap\">\r\n" +
                "   <soapenv:Header/>\n"+
                "   <soapenv:Body>\n"+
                "      <mod:depositar_cuenta_bancaria>\n"+
                "         <!--Optional:-->\n"+
                "         <mod:nro_cuenta>"+ nro_cuenta +"</mod:nro_cuenta>\n"+
                "         <!--Optional:-->\n"+
                "         <mod:cantidad_a_depositar>"+ cantidad_a_depositar +"</mod:cantidad_a_depositar>\n"+
                "      </mod:depositar_cuenta_bancaria>\n"+
                "   </soapenv:Body>\n"+
                "</soapenv:Envelope>\n";

        return callService(xml_parsed, "depositar_cuenta_bancaria");
    }

    public String transferir_plata(String nro_tarjeta_usada_comprador, double cantidad_a_devolver) {

        String xml_parsed="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mod=\"modulo_banca_soap\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <mod:transferir_plata_por_reclamo>\n" +
                "         <!--Optional:-->\n" +
                "         <mod:nro_tarjeta_usada_comprador>"+ nro_tarjeta_usada_comprador +"</mod:nro_tarjeta_usada_comprador>\n" +
                "         <!--Optional:-->\n" +
                "         <mod:cantidad_a_devolver>"+ cantidad_a_devolver +"</mod:cantidad_a_devolver>\n" +
                "      </mod:transferir_plata_por_reclamo>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        return callService(xml_parsed, "transferir_plata_por_reclamo");
    }

    public static String callService(String request, String method_name) {
        try {
            String url = "http://localhost:8082/?wsdl";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","text/xml; charset=utf-8");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(request);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            System.out.println(responseStatus);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String result= response.toString();
            String tag_beginning = "<tns:"+ method_name +"Result>";
            String tag_end = "</tns:"+ method_name +"Result>";

            return result.substring(result.indexOf(tag_beginning) + tag_beginning.length(), result.indexOf(tag_end));
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
