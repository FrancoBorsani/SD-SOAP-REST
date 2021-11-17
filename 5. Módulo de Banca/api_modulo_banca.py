import logging
from wsgiref.simple_server import make_server

from spyne import Application, rpc, ServiceBase, Integer, Unicode, Double
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
import mysql.connector

DB_NAME = "modulo_banca"

class CorsService(ServiceBase):
    origin = '*'
def _on_method_return_object(ctx):
    ctx.transport.resp_headers['Access-Control-Allow-Origin'] = \
                                              ctx.descriptor.service_class.origin
CorsService.event_manager.add_listener('method_return_object',
                                                        _on_method_return_object)

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

def update_row(query):
    try:
        conn = mysql.connector.connect(user='root', password='root', host='127.0.0.1', database='modulo_banca')
        cur = conn.cursor()
        cur.execute(query)
        conn.commit()
        cur.close()
        conn.close()
        print("Row updated.")
    except Exception as e:
        print(e)

  
class UsuarioService(CorsService):
    @rpc(Unicode(nullable=False), Unicode(nullable=False), Unicode(nullable=False), Unicode(nullable=False), Integer(nullable=False), _returns=Integer(nullable=False))
    def validar_tarjeta(ctx, nro_tarjeta, tipo_tarjeta, nombre, apellido, dni):
        """
        Valida que la informacion que registro el cliente coincida con el de la base de datos.
        @return 1 si la informacion ingresada coincide. Caso contrario, retorna 0.
        """
        try:
            idUsuario = make_query(f"SELECT u.idUsuario FROM usuario u WHERE u.nombre='{nombre}' AND u.apellido='{apellido}' AND u.dni={dni}")[0][0]
            tarjetas_usuario = make_query(f"SELECT t.numeroTarjeta FROM tarjeta t INNER JOIN cuenta_bancaria c ON c.idUsuario = {idUsuario} WHERE t.tipoTarjeta='{tipo_tarjeta}';")
            tarjetas_usuario = [str(t[0]) for t in tarjetas_usuario]            
        except:
            tarjetas_usuario = []
        finally:
            return 1 if nro_tarjeta in tarjetas_usuario else 0

    @rpc(Unicode(nullable=False), Unicode(nullable=False), Double(nullable=False), Double(nullable=False), _returns=Integer(nullable=False))
    def validar_limite_mensual(ctx, nro_tarjeta, tipo_tarjeta, total_a_pagar, total_gastado):
        """
        Valida si la compra a realizar con el medio de pago elegido no supera el limite mensual (teniendo en cuenta todas las compras del mes) y si es el caso,
        si se cuenta con saldo suficiente.
        @return 1 si la compra puede ser realizada (es decir, que el limite mensual aun no es alcanzado o que el saldo es suficiente). Caso contrario, retorna 0.
        """
        tipo_tarjeta = tipo_tarjeta.lower()
        if tipo_tarjeta not in ["credito","debito"]:
            return 0
        try:
            limite_mensual = make_query(f"SELECT t.limiteMensual FROM tarjeta t WHERE t.numeroTarjeta='{nro_tarjeta}' AND t.tipoTarjeta='{tipo_tarjeta}'")[0][0]
            saldo = make_query(f"SELECT c.saldo FROM cuenta_bancaria c WHERE c.idCuenta = (SELECT t.idCuenta FROM tarjeta t where t.numeroTarjeta='{nro_tarjeta}')")[0][0]
            #Solo para tarjetas de credito.
            if tipo_tarjeta=="credito" and (total_a_pagar+total_gastado) > int(limite_mensual):
                return 0
            #Solo para tarjetas de debito
            if tipo_tarjeta=="debito" and total_a_pagar > float(saldo):
                return 0
            return 1
        except Exception as e:
            print(e)
            return 0

    @rpc(Unicode(nullable=False), Double(nullable=False), _returns=Integer(nullable=False))
    def depositar_cuenta_bancaria(ctx, nro_cuenta, cantidad_a_depositar):
        """
        Deposita dinero en la cuenta asociada que se pasó por parametro.
        @return 1 si no hubo problemas al depositar la plata. Caso contrario, 0.
        """
        try:
            update_row(f"UPDATE cuenta_bancaria c SET c.saldo = c.saldo+{cantidad_a_depositar} where c.numeroCuenta='{nro_cuenta}'")
            return 1
        except Exception as e:
            print(e)
            return 0

    @rpc(Unicode(nullable=False), Double(nullable=False), _returns=Integer(nullable=False))
    def transferir_plata_por_reclamo(ctx, nro_tarjeta_usada_comprador, cantidad_a_devolver):
        """
        Deposita dinero en la cuenta asociada a la tarjeta que se pasó por parametro.
        @return 1 si no hubo problemas al depositar la plata. Caso contrario, 0.
        """
        try:
            update_row(f"UPDATE cuenta_bancaria c SET c.saldo = c.saldo+{cantidad_a_devolver} where c.idCuenta = (SELECT t.idCuenta FROM tarjeta t where t.numeroTarjeta='{nro_tarjeta_usada_comprador}')")
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