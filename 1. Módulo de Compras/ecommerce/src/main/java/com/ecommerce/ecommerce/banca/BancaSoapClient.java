package com.ecommerce.ecommerce.banca;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class BancaSoapClient {

    public String validar_limite_mensual(long nro_tarjeta, String tipo_tarjeta, int total_a_pagar, int total_gastado) {

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

    public String validar_tarjeta(long nro_tarjeta, String nombre, String apellido, long dni) {

        String xml_parsed="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:mod=\"modulo_banca_soap\">\r\n" +
                "   <soapenv:Header/>\r\n" +
                "   <soapenv:Body>\r\n" +
                "      <mod:validar_tarjeta>\r\n" +
                "         <!--Optional:-->\r\n" +
                "         <mod:nro_tarjeta>"+ nro_tarjeta +"</mod:nro_tarjeta>\r\n" +
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
