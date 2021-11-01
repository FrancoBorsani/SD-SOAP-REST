import logging
from wsgiref.simple_server import make_server

from spyne import Application, rpc, ServiceBase, Integer, Unicode, AnyDict
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
import json
import mysql.connector

DB_NAME = "modulo_banca"

def make_query(query):
    try:
        conn = mysql.connector.connect(user='root', password='root',
                                    host='127.0.0.1',
                                    database='modulo_banca')
        cur = conn.cursor()
        cur.execute(query)
        query_result = cur.fetchall()
        cur.close()
        conn.close()
        return query_result
    except Exception as e:
        print(e)

  

class UsuarioService(ServiceBase):
    @rpc(AnyDict, AnyDict, _returns=Integer)
    def validar_tarjeta(ctx, tarjeta_info, usuario_info):
        """
        Valida que la informacion que registro el cliente coincida con el de la base de datos.
        @param tarjeta_info contiene la informacion de la tarjeta.
        @param usuario_info contiene la informacion del usuario.
        @return 1 si la informacion ingresada coincide. Caso contrario, retorna 0.
        """
        idUsuario = make_query(f"SELECT u.idUsuario FROM usuario u WHERE u.nombre={usuario_info['nombre']} and u.apellido={usuario_info['apellido']} and u.dni={usuario_info['dni']}")
        tarjetas_usuario =  make_query(f"SELECT t.numeroTarjeta from tarjeta t where t.idUsuario={idUsuario}")
        return 1 if tarjeta_info['numeroTarjeta'] in tarjetas_usuario else 0

    @rpc(Integer, Unicode, Integer, Integer, _returns=Integer)
    def validar_limite_mensual(ctx, nro_tarjeta, tipo_tarjeta, total_a_pagar, total_gastado):
        """
        Valida si la compra a realizar con el medio de pago elegido no supera el limite mensual (teniendo en cuenta todas las compras del mes) y si es el caso,
        si se cuenta con saldo suficiente.
        @param tarjeta_info contiene la informacion de la tarjeta.
        @param total_a_pagar contiene el total de la venta que se quiere pagar.
        @param total_gastado contiene el total gastado con la tarjeta en el mes.
        @return 1 si la compra puede ser realizada (es decir, que el limite mensual aun no es alcanzado). Caso contrario, retorna 0.
        """
        tipo_tarjeta = tipo_tarjeta.lower()
        saldo, limite_mensual = make_query(f"SELECT t.saldo, t.limiteMensual from tarjeta t where t.numeroTarjeta={nro_tarjeta} and t.tipoTarjeta='{tipo_tarjeta}'")[0]
        
        #Solo para tarjetas de credito.
        if tipo_tarjeta=="credito" and (total_a_pagar+total_gastado) > limite_mensual:
            return 0
        #Solo para tarjetas de debito
        if tipo_tarjeta=="debito" and int(total_a_pagar) > int(saldo):
            return 0
            
        return 1

application = Application([UsuarioService], 'modulo_banca_soap',
                          in_protocol=Soap11(validator='lxml'),
                          out_protocol=Soap11())

wsgi_application = WsgiApplication(application)


if __name__ == '__main__':
    logging.basicConfig(level=logging.INFO)
    logging.getLogger('spyne.protocol.xml').setLevel(logging.INFO)

    PORT = 8082

    logging.info(f"listening to http://127.0.0.1:{PORT}")
    logging.info(f"wsdl at: http://localhost:{PORT}/?wsdl")

    server = make_server('127.0.0.1', PORT, wsgi_application)
    server.serve_forever()