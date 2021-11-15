
import logging

from spyne.application import Application
from spyne.decorator import srpc
from spyne.protocol.json import JsonDocument
from spyne.protocol.http import HttpRpc
from spyne.service import ServiceBase
from spyne.model.complex import Iterable
from spyne.model.primitive import UnsignedInteger
from spyne.model.primitive import String
from spyne.server.wsgi import WsgiApplication
from flask import Flask
from flask_cors import CORS
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

CORS(app)


app.config['SECRET_KEY'] = '9OLWxND4o83j4K4iuopO'
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:test@localhost/ecommerce'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False


db = SQLAlchemy(app)
login_manager = LoginManager()
login_manager.login_view = 'log_in'
login_manager.init_app(app)

class CorsService(ServiceBase):
    origin = '*'

def _on_method_return_object(ctx):
    ctx.transport.resp_headers['Access-Control-Allow-Origin'] = \
                                              ctx.descriptor.service_class.origin

CorsService.event_manager.add_listener('method_return_object',
                                                        _on_method_return_object)


class HelloWorldService(CorsService):

    @srpc(String, UnsignedInteger, _returns=Iterable(String))
    def say_hello(name, times):

        for i in range(times):
            #yield '%s("Hello, %s")' % (callback, name)
            yield {"name": 'Hello (%d): %s' % (i, name), "address": "%d + %d" % (i, i)}


if __name__=='__main__':
    from wsgiref.simple_server import make_server
    logging.basicConfig(level=logging.DEBUG)

    application = Application([HelloWorldService], 'spyne.examples.hello.http',

          in_protocol=HttpRpc(validator='soft'),

          out_protocol=JsonDocument(ignore_wrappers=True),
      )

    wsgi_application = WsgiApplication(application)

    server = make_server('0.0.0.0', 8000, wsgi_application)

    logging.info("listening to http://127.0.0.1:8000")
    logging.info("wsdl is at: http://localhost:8000/?wsdl")

    server.serve_forever()