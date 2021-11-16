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
from spyne.model.primitive import String,Integer as Integer_spyne,Float,Boolean
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
    id = db.Column(db.Integer, primary_key=True,autoincrement=True)
    username = db.Column(db.String(100), unique=True)
    password = db.Column(db.String(100))
    nombre = db.Column(db.String(1000))
    apellido = db.Column(db.String(1000))
    dni = db.Column(db.String(1000))
    email = db.Column(db.String(1000))
    telefono = db.Column(db.String(1000))
    saldo = db.Column(db.Float, nullable=True)
    productos = db.relationship('Producto',backref='user',lazy='dynamic')
    cuentas = db.relationship('Cuentas',backref='user',lazy='dynamic')
    userRoles = db.relationship('User_role',backref='user',lazy='dynamic')

class User_role(db.Model):
    id = db.Column(db.Integer, primary_key=True,autoincrement=True)
    role = db.Column(db.String(1000))
    creatdat = db.Column(db.DateTime)
    updateat = db.Column(db.DateTime)
    user_id= db.Column(db.Integer, db.ForeignKey('user.id'))


class Cuentas(db.Model):
    id = db.Column(db.Integer, primary_key=True,autoincrement=True)
    banco = db.Column(db.String(1000))
    numero = db.Column(db.String(1000))
    user_id= db.Column(db.Integer, db.ForeignKey('user.id'))


class Pedido(db.Model):
    id_compra = db.Column(db.Integer, primary_key=True,autoincrement=True)
    codigoDeSeguimiento = db.Column(db.String(1000))
    idTarjetaUsada = db.Column(db.Integer)
    cobrado = db.Column(db.Integer,nullable=True)
    estadoDeEnvio = db.Column(db.String(1000))
    total = db.Column(db.Float, nullable=True)
    creatdat = db.Column(db.DateTime)
    updateat = db.Column(db.DateTime)
    items = db.relationship('Item',backref='pedidos',lazy='dynamic')
    comprador= db.Column(db.Integer, db.ForeignKey('user.id'))
    vendedor= db.Column(db.Integer, db.ForeignKey('user.id'))

class Item(db.Model):
    id_item = db.Column(db.Integer, primary_key=True,autoincrement=True)
    producto = db.Column(db.Integer, db.ForeignKey('producto.id'))
    cantidad = db.Column(db.Integer)
    pedido = db.Column(db.Integer, db.ForeignKey('pedido.id_compra'))


class Categoria(db.Model):
    id = db.Column(db.Integer, primary_key=True,autoincrement=True)
    nombre= db.Column(db.String(100))
    productos = db.relationship('Producto',backref='categoria',lazy='dynamic')

class Producto(db.Model):
    id_producto = db.Column(db.Integer, primary_key=True,autoincrement=True)
    nombre = db.Column(db.String(100))
    descripcion= db.Column(db.String(100))
    imagen = db.Column(db.String(100))
    precio = db.Column(db.Float)
    stock = db.Column(db.Integer)
    cantidad_vendida =  db.Column(db.Integer)
    forma_de_pago = db.Column(db.String(100))
    vendedor_id= db.Column(db.Integer, db.ForeignKey('user.id'))
    categoria_id_categoria= db.Column(db.Integer, db.ForeignKey('categoria.id'))
    def __repr__(self):
        rep = 'Producto(' '['+ self.nombre + ',' +self.descripcion+','+self.imagen+','+ str(self.precio) +','+ str(self.stock) +','+ str(self.cantidad_vendida) + self.forma_de_pago +']' ')'
        return rep


db.create_all()
user_schema = UserSchema()
product_schema = ProductSchema()


class CorsService(ServiceBase):
    origin = '*'

def _on_method_return_object(ctx):
    ctx.transport.resp_headers['Access-Control-Allow-Origin'] = \
                                              ctx.descriptor.service_class.origin

CorsService.event_manager.add_listener('method_return_object',
                                                        _on_method_return_object)

#metodos

class Sales(CorsService):
    
    @srpc(String,String,String,String,String,String,String, _returns=String)
    def register(nombre, apellido,username,password,dni,email,telefono):
        user = User.query.filter_by(username=username).first()
        if user:
            return('User already registered')
        new_user = User(username=username, password=generate_password_hash(password, method='sha256'), nombre=nombre, apellido=apellido,dni=dni, email=email,telefono=telefono)
        db.session.add(new_user)
        db.session.commit()
        return('User registered succesfully')

    @srpc(String, _returns=String)
    def publish_category(nombre):
        new_category = Categoria(nombre=nombre)
        db.session.add(new_category)
        db.session.commit()
        return('Category published')

    @srpc(String,String,String,String,Float,Integer_spyne(nullable=False),Integer_spyne(nullable=False),Integer_spyne(nullable=False),Integer_spyne(nullable=False), _returns=String)
    def publish_product(nombre, descripcion, imagen, forma_de_pago, precio, stock, cantidad_vendida, categoria_id_categoria, vendedor_id):
        new_product = Producto(nombre=nombre, descripcion=descripcion, forma_de_pago=forma_de_pago, imagen=imagen, precio=precio, stock=stock, cantidad_vendida=cantidad_vendida, categoria_id_categoria=categoria_id_categoria,vendedor_id=vendedor_id)
        db.session.add(new_product)
        db.session.commit()
        return('Producto published')    

    @srpc(Integer_spyne,String,String,String,String,Float,Integer_spyne,Integer_spyne,Integer_spyne, _returns=String)
    def edit_product(id,nombre, descripcion, imagen,forma_de_pago, precio, stock, categoria_id_categoria,vendedor_id):
        try:
            product = Producto.query.filter_by(id_producto=id).first()
            product.nombre=nombre
            product.descripcion=descripcion
            product.imagen=imagen
            product.precio=precio
            product.stock=stock
            product.forma_de_pago=forma_de_pago
            product.vendedor_id=vendedor_id
            product.categoria_id_categoria=categoria_id_categoria
            db.session.commit()
            return('Producto Updated')
        except:
            return("Product not found.")
   
    @srpc(Integer_spyne,_returns=Boolean)
    def check_edit(id):
        try:
            product = Producto.query.filter_by(id_producto=id).first()
            return(product.id_producto==id)
        except:
            return(-1)

    @srpc(_returns=String)
    def get_products():
        product = Producto.query.all()
        print(json.dumps(repr(product)))
        return(json.dumps(repr(product)))

    @srpc(Integer_spyne,_returns=String)
    def get_products_bycat(cat_id):
        try:
            product = Producto.query.filter_by(categoria_id_categoria=cat_id).all()
            print(json.dumps(repr(product)))
            return(json.dumps(repr(product)))
        except:
            return("Products not found.")
    @srpc(Integer_spyne,_returns=String)
    def get_products_byuser(usr_id):
        try:
            product = Producto.query.filter_by(vendedor_id=usr_id).all()
            print(json.dumps(repr(product)))
            return(json.dumps(repr(product)))
        except:
            return("Products not found.")
    @srpc(Integer_spyne,Integer_spyne,_returns=String)
    def get_products_byusercat(cat_id,usr_id):
        try:
            product = Producto.query.filter_by(vendedor_id=usr_id,categoria_id_categoria=cat_id).all()
            print(json.dumps(repr(product)))
            return(json.dumps(repr(product)))
        except:
            return("Products not found.")
    
    @srpc(Integer_spyne,_returns=Float)
    def check_dinero(id):
        try:
            pedidos = Pedido.query.filter_by(vendedor_id=id,cobrado=1)
            for p in pedidos:    
                saldo = 0
                duration= p.creatdat-datetime.now()
                if(duration.days>=2):
                    saldo+=p.total
            return(saldo)
        except:
            return(float(0))

    @srpc(Integer_spyne,_returns=Float)
    def retirar_dinero(id):
        try:
            pedidos = Pedido.query.filter_by(vendedor_id=id,cobrado=1)
            user = User.query.filter_by(id=id)
            for p in pedidos:
                duration= p.creatdat-datetime.now()
                if(duration.days>=2):
                    saldo+=p.total
                    p.cobrado=1
                user.saldo=user.saldo-saldo
            return(saldo)
        except:
            return(float(0))


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

