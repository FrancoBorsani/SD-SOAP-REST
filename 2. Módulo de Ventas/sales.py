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
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(100), unique=True)
    password = db.Column(db.String(100))
    name = db.Column(db.String(1000))
    lastname = db.Column(db.String(1000))
    dni = db.Column(db.Integer)

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
    
db.create_all()



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

    @srpc(_returns=String,String,String,Float,Boolean,Integer,Integer)
    def get_products():
        db.session.
        return( descripcioncorta, descipcionlarga, imagen, precio, stock, categoria)
 """
    @srpc(String,String,String,Float,Boolean,Integer,Integer, _returns=String)
    def publish_product(descripcioncorta, descipcionlarga, imagen, precio, visible, stock, categoria_id_categoria):
        new_product = Product(descripcioncorta=descripcioncorta, descipcionlarga=descipcionlarga, imagen=imagen, precio=precio, visible=True, stock=stock, categoria_id_categoria=categoria_id_categoria)
        db.session.add(new_product)
        db.session.commit()
        return('Product published')

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
