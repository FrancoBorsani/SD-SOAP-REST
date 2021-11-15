
from flask import Flask
from flask_cors import CORS
from sqlalchemy import insert
from datetime import datetime
from flask_sqlalchemy import SQLAlchemy
from flask import jsonify
from sqlalchemy import insert
from datetime import datetime
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.ext.declarative import DeclarativeMeta

from schemas.user import UserSchema
from schemas.bank import BankSchema
from schemas.product import ProductSchema
from schemas.categoria import CategoriaSchema


app = Flask(__name__)


CORS(app)


app.config['SECRET_KEY'] = '9OLWxND4o83j4K4iuopO'
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:test@localhost/ecommerce'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False


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