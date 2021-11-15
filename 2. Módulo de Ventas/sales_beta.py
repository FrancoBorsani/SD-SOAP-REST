import json
from datetime import datetime
import logging
from wsgiref.simple_server import make_server

from spyne import Application,AnyDict
from spyne.decorator import srpc
from spyne.service import ServiceBase

from spyne.model.complex import Iterable
from spyne.model.primitive import UnsignedInteger
from spyne.model.primitive import String,Integer,Float,Integer,Boolean

from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication


from flask import Flask
from flask_cors import CORS
from sqlalchemy import insert
from datetime import datetime
from flask_sqlalchemy import SQLAlchemy
from flask_login import UserMixin
from werkzeug.security import generate_password_hash, check_password_hash
from flask_login import LoginManager 
from flask_login import login_required, current_user, login_user, logout_user
from sqlalchemy import Column, Integer, DateTime



app = Flask(__name__)

CORS(app)

app.config['SECRET_KEY'] = '9OLWxND4o83j4K4iuopO'
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:test@localhost/ecommerce'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)
login_manager = LoginManager()
login_manager.login_view = 'log_in'
login_manager.init_app(app)

class User(UserMixin, db.Model):
    id = db.Column(db.Integer, primary_key=True,autoincrement=True)
    usernombre = db.Column(db.String(100), unique=True)
    password = db.Column(db.String(100))
    nombre = db.Column(db.String(1000))
    apellido = db.Column(db.String(1000))
    dni = db.Column(db.String(1000))
    email = db.Column(db.String(1000))
    telefono = db.Column(db.String(1000))
    saldo = db.Column(db.Float)
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


class Categoria(db.Model):
    id = db.Column(db.Integer, primary_key=True,autoincrement=True)
    nombre= db.Column(db.String(100))
    productos = db.relationship('Producto',backref='categoria',lazy='dynamic')

class Producto(db.Model):
    idProducto = db.Column(db.Integer, primary_key=True,autoincrement=True)
    nombre = db.Column(db.String(100))
    descripcion= db.Column(db.String(100))
    imagen = db.Column(db.String(100))
    precio = db.Column(db.Float)
    visible = db.Column(db.Boolean)
    stock = db.Column(db.Integer)
    cantidadVendida =  db.Column(db.Integer)
    formaDePago = db.Column(db.String(100))
    user_id= db.Column(db.Integer, db.ForeignKey('user.id'))
    categoria_id_categoria= db.Column(db.Integer, db.ForeignKey('categoria.id'))
    def __repr__(self):
        rep = 'Producto(' + self.nombre + ',' +self.descripcion+','+self.imagen+','+ str(self.precio) +','+ str(self.stock) +','+ str(self.cantidadVendida) + self.formaDePago + ')'
        return rep

db.create_all()


class Sales(ServiceBase):

    

    @srpc(String,String,String,String,String, _returns=String)
    def register(nombre, apellido,usernombre,password,dni):
        user = User.query.filter_by(usernombre=usernombre).first()
        if user:
            return('User already registered')
        new_user = User(usernombre=usernombre, password=generate_password_hash(password, method='sha256'), nombre=nombre, apellido=apellido,dni=dni)
        db.session.add(new_user)
        db.session.commit()
        return('User registered succesfull')

    @srpc(String, _returns=String)
    def publish_category(nombre):
        new_category = Categoria(nombre=nombre)
        db.session.add(new_category)
        db.session.commit()
        return('Category published')

    @srpc(String,String,String,String,Float,Boolean,Integer,Integer,Integer, _returns=String)
    def publish_product(nombre, descripcion, imagen,formadepago, precio, visible, stock, categoria_id_categoria,user_id):
        new_product = Producto(nombre=nombre, descripcion=descripcion, formaDePago=formadepago, imagen=imagen, precio=precio, visible=True, stock=stock, categoria_id_categoria=categoria_id_categoria,user_id=user_id)
        db.session.add(new_product)
        db.session.commit()
        return('Producto published')    

    @srpc(Integer,String,String,String,String,Float,Boolean,Integer,Integer,Integer, _returns=String)
    def edit_product(id,nombre, descripcion, imagen,formadepago, precio, visible, stock, categoria_id_categoria,user_id):
        product = Producto.query.filter_by(id=id).first()
        product.nombre=nombre
        product.descripcion=descripcion
        product.imagen=imagen
        product.precio=precio
        product.visible=True
        product.stock=stock
        product.formaDePago=formadepago
        product.user_id=user_id
        product.categoria_id_categoria=categoria_id_categoria
        db.session.commit()
        return('Producto Updated')
   
    @srpc(Integer,_returns=Boolean)
    def check_edit(id):
        product = Producto.query.filter_by(id=id,visible=True).first()
        return(product.id==id)

    @srpc(_returns=String)
    def get_products():
        product = Producto.query.filter_by(visible=True).first()
        print(json.dumps(repr(product)))
        return(json.dumps(repr(product)))

    @srpc(Integer,_returns=String)
    def get_products_bycat(cat_id):
        product = Producto.query.filter_by(visible=True,categoria_id_categoria=cat_id).first()
        print(json.dumps(repr(product)))
        return(json.dumps(repr(product)))

    @srpc(Integer,_returns=String)
    def get_products_byuser(usr_id):
        product = Producto.query.filter_by(visible=True,user_id=usr_id).first()
        print(json.dumps(repr(product)))
        return(json.dumps(repr(product)))

    @srpc(Integer,Integer,_returns=String)
    def get_products_byusercat(cat_id,usr_id):
        product = Producto.query.filter_by(visible=True,user_id=usr_id,categoria_id_categoria=cat_id).first()
        print(json.dumps(repr(product)))
        return(json.dumps(repr(product)))


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