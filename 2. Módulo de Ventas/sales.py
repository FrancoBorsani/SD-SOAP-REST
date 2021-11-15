import logging
import json
from wsgiref.simple_server import make_server
from spyne.protocol.soap import Soap11
from spyne import Application,AnyDict
from spyne.decorator import srpc
from spyne.service import ServiceBase
from spyne.protocol.http import HttpRpc
from spyne.protocol.json import JsonDocument
from spyne.model.complex import Iterable
from spyne.model.primitive import UnsignedInteger
from spyne.model.primitive import String,Integer,Float,Integer,Boolean
from spyne.server.wsgi import WsgiApplication
from flask import jsonify
from sqlalchemy import insert
from datetime import datetime
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.ext.declarative import DeclarativeMeta
from flask import Flask
from flask_cors import CORS


from schemas.user import UserSchema
from schemas.bank import BankSchema
from schemas.product import ProductSchema
from schemas.categoria import CategoriaSchema

app = Flask(__name__)

CORS(app)

app.config['SECRET_KEY'] = '9OLWxND4o83j4K4iuopO'
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:test@localhost/ecommerce'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

#db config

db = SQLAlchemy(app)

class User( db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(100), unique=True)
    password = db.Column(db.String(100))
    name = db.Column(db.String(1000))
    lastname = db.Column(db.String(1000))
    dni = db.Column(db.Integer)
    saldo = db.Column(db.Float)
    cuentas = db.relationship('Bank',backref='user',lazy='dynamic')
    tarjetas = db.relationship('Tarjeta',backref='user',lazy='dynamic')
    productos = db.relationship('Product',backref='user',lazy='dynamic')

class Bank(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    cbu = db.Column(db.Integer)
    banco = db.Column(db.String(1000))
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'))

class Tarjeta(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    numero = db.Column(db.Integer)
    tipo = db.Column(db.String(1000))
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'))

class Categoria(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    nombre= db.Column(db.String(100), unique=True)
    productos = db.relationship('Product',backref='categoria',lazy='dynamic')

class Product(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    descripcioncorta = db.Column(db.String(100))
    descipcionlarga= db.Column(db.String(100))
    imagen = db.Column(db.String(100))
    precio = db.Column(db.Float)
    visible = db.Column(db.Boolean)
    stock = db.Column(db.Integer)
    categoria_id_categoria= db.Column(db.Integer, db.ForeignKey('categoria.id'))
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'))

db.create_all()
user_schema = UserSchema()
product_schema = ProductSchema()

"""
class CorsService(ServiceBase):
    origin = '*'

def _on_method_return_object(ctx):
    ctx.transport.resp_headers['Access-Control-Allow-Origin'] = \
                                              ctx.descriptor.service_class.origin

CorsService.event_manager.add_listener('method_return_object',
                                                        _on_method_return_object)
"""
#metodos

class Sales(ServiceBase):
    
    @srpc(String,String,String,String,Integer, _returns=String)
    def register(nombre, apellido,username,password,dni):
        user = User.query.filter_by(username=username).first()
        if user:
            return('User already registered')
        new_user = User(username=username, password=generate_password_hash(password, method='sha256'), name=nombre, lastname=apellido,dni=dni)
        db.session.add(new_user)
        db.session.commit()
        return('User registered succesfull')

    @srpc(Integer,String,Integer, _returns=String)
    def add_bank_account(cbu,banco,user_id):
        new_bank = Bank(banco=banco,cbu=cbu,user_id=user_id)
        db.session.add(new_bank)
        db.session.commit()
        return('New account saved')

    @srpc(Integer,Integer,String,Integer, _returns=String)
    def send_money(transaccion,cbu,banco,user_id):
        tmp_user= User.query.filter_by(id=user_id).first()
        usaldo=tmp_user.saldo
        if(usaldo>=transaccion):
            tmp_user.saldo=tmp_user.saldo-transaccion
            db.session.add(tmp_user)
            return("Transferencia aceptada")
        else:
            return('No tiene suficiente saldo en la cuenta')

    @srpc(_returns= String)
    def get_accounts():
        bank = bank.query.all()
        for i in bank:
            message = message +";"+bank.banco+","+bank.cbu+","+bank.user_id 
        return(messsage)

    @srpc(String, _returns=String)
    def publish_category(nombre):
        new_category = Categoria(nombre=nombre)
        db.session.add(new_category)
        db.session.commit()
        return('Category published')

    """ @srpc(String,String,String,Float,Boolean,Integer,Integer, _returns=String)
    def edit_product(descripcioncorta, descipcionlarga, imagen, precio, visible, stock, categoria_id_categoria):
        new_product = Product(descripcioncorta=descripcioncorta, descipcionlarga=descipcionlarga, imagen=imagen, precio=precio, visible=True, stock=stock, categoria_id_categoria=categoria_id_categoria)
        db.session.add(new_product)
        db.session.commit()
        return('Product published')
 """
    @srpc(_returns=AnyDict)
    def get_products():
        #p_message = []
        tmp_products = db.session.query(Product).filter_by(visible=True)
        #tmp = Product.query(all)
        #print(tmp_products)
        #for i in tmp_products:
        #p_message = product_schema.dump(Product)
        #p_message = json.dumps(tmp_products, cls=AlchemyEncoder)
        # p_message.append(";"+i.descripcioncorta+","+str(i.precio)+","+str(i.stock)+","+i.descipcionlarga+","+i.categoria.nombre+";" )
        #print(p_message)
        return jsonify(tmp_products)

    
    @srpc(Integer,_returns=String)
    def get_products_bysalesman(user_id):
        tmp_products = db.session.query(Product).filter_by(visible=Trueuser_id)
        #for i in tmp_products:
        p_message = json.dumps(tmp_products, cls=AlchemyEncoder)
            #append(";"+i.descripcioncorta+","+str(i.precio)+","+str(i.stock)+","+i.descipcionlarga+","+i.categoria.nombre+";" )
        print(p_message)
        return(str(p_message))
    

    @srpc(String,String,String,Float,Boolean,Integer,Integer, _returns=String)
    def publish_product(descripcioncorta, descipcionlarga, imagen, precio, visible, stock, categoria_id_categoria):
        new_product = Product(descripcioncorta=descripcioncorta, descipcionlarga=descipcionlarga, imagen=imagen, precio=precio, visible=True, stock=stock, categoria_id_categoria=categoria_id_categoria)
        db.session.add(new_product)
        db.session.commit()
        return('Product published')

"""
if __name__=='__main__':
    from wsgiref.simple_server import make_server
    logging.basicConfig(level=logging.DEBUG)

    application = Application([Sales], 'spyne.examples.hello.http',

          in_protocol=HttpRpc(validator='soft'),

          out_protocol=JsonDocument(ignore_wrappers=True),
      )

    wsgi_application = WsgiApplication(application)
    server = make_server('0.0.0.0', 8089, wsgi_application)

    logging.info("listening to http://127.0.0.1:8089")
    logging.info("wsdl is at: http://localhost:8089/?wsdl")
    server.serve_forever()
"""
application = Application([Sales], 'sales',
                          in_protocol=Soap11(validator='lxml'),
                          out_protocol=Soap11())

wsgi_application = WsgiApplication(application)

if __name__ == '__main__':
    logging.basicConfig(level=logging.INFO)
    logging.getLogger('spyne.protocol.xml').setLevel(logging.INFO)

    PORT = 8089

    logging.info(f"listening to http://127.0.0.1:{PORT}")
    logging.info(f"wsdl at: http://localhost:{PORT}/?wsdl")

    server = make_server('127.0.0.1', PORT, wsgi_application)
    server.serve_forever()