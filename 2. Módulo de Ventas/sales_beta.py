import json
from datetime import datetime
import logging
from wsgiref.simple_server import make_server

from spyne import Application,AnyDict
from spyne.decorator import srpc
from spyne.service import ServiceBase

from spyne.model.complex import Iterable
from spyne.model.primitive import UnsignedInteger
from spyne.model.primitive import String,Integer as Integer_spyne,Float,Boolean

from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
from sqlalchemy.ext.declarative import DeclarativeMeta


from flask import Flask
from flask_cors import CORS
from sqlalchemy import insert
from datetime import datetime
from flask_sqlalchemy import SQLAlchemy
from flask_login import UserMixin
from werkzeug.security import generate_password_hash, check_password_hash
from flask_login import LoginManager 
from flask_login import login_required, current_user, login_user, logout_user


app = Flask(__name__)

CORS(app)

app.config['SECRET_KEY'] = '9OLWxND4o83j4K4iuopO'
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:test@localhost/ecommerce'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)
login_manager = LoginManager()
login_manager.login_view = 'log_in'
login_manager.init_app(app)



def json_serializer(data):
    return json.dumps(data).encode("utf-8")

def new_alchemy_encoder():
    _visited_objs = []

    class AlchemyEncoder(json.JSONEncoder):
        def default(self, obj):
            if isinstance(obj.__class__, DeclarativeMeta):
                # don't re-visit self
                if obj in _visited_objs:
                    return None
                _visited_objs.append(obj)

                # an SQLAlchemy class
                fields = {}
                for field in [x for x in dir(obj) if not x.startswith('_') and x != 'metadata']:
                    print( obj.__getattribute__(field))
                    print("\n")
                    fields[field] = obj.__getattribute__(field)
                # a json-encodable dict
                return fields

            return json.JSONEncoder.default(self, obj)

    return AlchemyEncoder




class User( db.Model):
    id = db.Column(db.Integer, primary_key=True,autoincrement=True)
    username = db.Column(db.String(100), unique=True)
    password = db.Column(db.String(100))
    nombre = db.Column(db.String(1000))
    apellido = db.Column(db.String(1000))
    enabled = db.Column(db.Boolean)
    dni = db.Column(db.String(1000))
    email = db.Column(db.String(1000))
    telefono = db.Column(db.String(1000))
    saldo = db.Column(db.Float, nullable=True)
    productos = db.relationship('Producto',backref='user',lazy='dynamic')
    cuentas = db.relationship('Cuentas',backref='user',lazy='dynamic')
    userRoles = db.relationship('User_role',backref='user',lazy='dynamic')
    def serialize(self):
            serialize_obj =  {
            'id' : self.id,
            'username' : self.username,
            'password' : self.password,
            'nombre' : self.nombre,
            'apellido' : self.apellido,
            'dni' : self.dni,
            'email' : self.email,
            'saldo' : self.saldo}
            return serialize_obj

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
    producto = db.Column(db.Integer, db.ForeignKey('producto.id_producto'))
    cantidad = db.Column(db.Integer)
    pedido = db.Column(db.Integer, db.ForeignKey('pedido.id_compra'))


class Categoria(db.Model):
    id_categoria = db.Column(db.Integer, primary_key=True,autoincrement=True)
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
    categoria_id_categoria= db.Column(db.Integer, db.ForeignKey('categoria.id_categoria'))
    def serialize(self):
            serialize_obj =  {
            'id_producto' : self.id_producto,
            'nombre' : self.nombre,
            'descripcion' : self.descripcion,
            'imagen' : self.imagen,
            'precio' : self.precio,
            'stock' : self.stock,
            'cantidad_vendida' : self.cantidad_vendida,
            'forma_de_pago' : self.forma_de_pago,
            'vendedor_id' : self.vendedor_id,
            'categoria_id_categoria' : self.categoria_id_categoria}
            return serialize_obj

    def __repr__(self):
        rep = 'Producto(' '['+ self.nombre +','+ str(self.precio) + ','+ str(self.stock) +',' +self.descripcion+','+self.imagen+','+ str(self.cantidad_vendida) + self.forma_de_pago +']' ')'
        return rep
    #            stringData: '["title", "price", "inStock", "description", "category"]'

db.create_all()


class Sales(ServiceBase):

    @srpc(String,String,String,String,String,String, _returns=String)
    def register(nombre, apellido,username,password,dni,email):
        user = User.query.filter_by(username=username).first()
        if user:
            return('User already registered')
        new_user = User(username=username, password=generate_password_hash(password, method='sha256'), nombre=nombre, apellido=apellido,dni=dni, email=email)
        db.session.add(new_user)
        db.session.commit()
        return('User registered succesfully')
    

    @srpc(_returns=String)
    def get_users():
        user = User.query.all()
        return json.dumps([u.serialize() for u in user]) 
    @srpc(String,_returns=String)
    def get_users_by_username(username):
        user = User.query.filter_by(username=username)
        return json.dumps([u.serialize() for u in user]) 
          
    @srpc(Integer_spyne,_returns=String)
    def check_user(id):
        user = User.query.filter_by(id=id)
        return json.dumps([u.serialize() for u in user])

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
        return json.dumps([p.serialize() for p in product]) 

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

application = Application([Sales], 'modulo_ventas_soap',
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