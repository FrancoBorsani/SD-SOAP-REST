import logging
from wsgiref.simple_server import make_server

from spyne import Application, rpc, ServiceBase, Integer, Unicode
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
import mysql.connector

DB_NAME = "modulo_banca"

class CorsService(ServiceBase):
    origin = '*'

def _on_method_return_object(ctx):
    ctx.transport.resp_headers['Access-Control-Allow-Origin'] = ctx.descriptor.service_class.origin

CorsService.event_manager.add_listener('method_return_object', _on_method_return_object)

def make_query(query):
    try:
        conn = mysql.connector.connect(user='root', password='root', host='127.0.0.1', database='modulo_banca')
        cur = conn.cursor()
        cur.execute(query)
        query_result = cur.fetchall()
        cur.close()
        conn.close()
        if query_result:
            return query_result
        else:
            raise Exception("### La query no devolvio ningun resultado.")
    except Exception as e:
        print(e)

  
class UsuarioService(CorsService):
    @rpc(Integer(nullable=False), Unicode(nullable=False), Unicode(nullable=False), Unicode(nullable=False), Integer(nullable=False), _returns=Integer(nullable=False))
    def validar_tarjeta(ctx, nro_tarjeta, tipo_tarjeta, nombre, apellido, dni):
        """
        Valida que la informacion que registro el cliente coincida con el de la base de datos.
        @return 1 si la informacion ingresada coincide. Caso contrario, retorna 0.
        """
        print(nombre)
        try:
            idUsuario = make_query(f"SELECT u.idUsuario FROM usuario u WHERE u.nombre='{nombre}' and u.apellido='{apellido}' and u.dni={dni}")[0][0]
            tarjetas_usuario = make_query(f"SELECT t.numeroTarjeta from tarjeta t where t.idUsuario={idUsuario} and t.tipoTarjeta='{tipo_tarjeta}'")
            tarjetas_usuario = [int(t[0]) for t in tarjetas_usuario]            
        except:
            tarjetas_usuario = []
        finally:
            return 1 if nro_tarjeta in tarjetas_usuario else 0

    @rpc(Integer(nullable=False), Unicode(nullable=False), Integer(nullable=False), Integer(nullable=False), _returns=Integer(nullable=False))
    def validar_limite_mensual(ctx, nro_tarjeta, tipo_tarjeta, total_a_pagar, total_gastado):
        """
        Valida si la compra a realizar con el medio de pago elegido no supera el limite mensual (teniendo en cuenta todas las compras del mes) y si es el caso,
        si se cuenta con saldo suficiente.
        @return 1 si la compra puede ser realizada (es decir, que el limite mensual aun no es alcanzado). Caso contrario, retorna 0.
        """
        tipo_tarjeta = tipo_tarjeta.lower()
        if tipo_tarjeta not in ["credito","debito"]:
            return 0
            
        try:
            saldo, limite_mensual = make_query(f"SELECT t.saldo, t.limiteMensual from tarjeta t where t.numeroTarjeta={nro_tarjeta} and t.tipoTarjeta='{tipo_tarjeta}'")[0]
            #Solo para tarjetas de credito.
            if tipo_tarjeta=="credito" and (total_a_pagar+total_gastado) > int(limite_mensual):
                return 0
            #Solo para tarjetas de debito
            if tipo_tarjeta=="debito" and total_a_pagar > int(saldo):
                return 0
            return 1
        except Exception as e:
            print(e)
            return 0


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