from suds.client import Client
from collections import namedtuple
import json
from flask import Flask
from flask_cors import CORS
from flask_login import LoginManager,current_user
from flask_wtf import FlaskForm
from flask import Flask, render_template, redirect, url_for, request
from flask_wtf.csrf import CSRFProtect
from flask_bcrypt import Bcrypt
from wtforms import (StringField, PasswordField, SubmitField,              
                     BooleanField)
from wtforms.validators import DataRequired, Length, Email, EqualTo
import os
from flask_login import LoginManager 
from flask_login import login_required, current_user, login_user, logout_user


app = Flask(__name__)

bcrypt = Bcrypt(app)

app.config['SECRET_KEY'] = '7110c8ae51a4b5af97be6534caef90e4bb9bdcb3380af008f90b23a5d1616bf319bc298105da20fe'
#csrf = CSRFProtect(app)
#csrf.init_app(app)
login_manager = LoginManager(app)
login_manager.login_view = 'login'
login_manager.login_message_category = 'info'


@login_manager.user_loader
def load_user(user_id):
    user = json.loads(hi_client.service.check_user(user_id), object_hook=lambda d: namedtuple('user', d.keys())(*d.values()))
    for u in user:
        us=u
        print (us)
    return us

hi_client= Client('http://localhost:8089/?wsdl')

class SignupForm(FlaskForm):
    name = StringField('Nombre', validators=[DataRequired(), Length(max=64)])
    password = PasswordField('Password', validators=[DataRequired()])
    email = StringField('Email', validators=[DataRequired(), Email()])
    dni  = StringField()
    apellido = StringField()
    username = StringField()
    submit = SubmitField('Registrar')



class LoginForm(FlaskForm):
    username = StringField('Username', validators=[DataRequired()])
    password = PasswordField('Password', validators=[DataRequired()])
    remember_me = BooleanField('Recuérdame')
    submit = SubmitField('Login')


@app.route('/login', methods=['GET', 'POST'])
def login():
    if current_user.is_authenticated:
            return redirect(url_for('index'))
    form = LoginForm()
    if form.validate_on_submit():
        #user = User.get_by_email(form.email.data)
        user = json.loads(hi_client.service.get_users_by_username(form.username.data), object_hook=lambda d: namedtuple('user', d.keys())(*d.values()))
        if user is not None and user.check_password(form.password.data):
            login_user(user, remember=form.remember_me.data)
            next_page = request.args.get('next')
            if not next_page or url_parse(next_page).netloc != '':
                next_page = url_for('index')
            return redirect(next_page)
    return render_template('login.html', form=form)




@app.route("/signup/", methods=["GET", "POST"])
def show_signup_form():
    if current_user.is_authenticated:
        return redirect(url_for('home'))
    form = SignupForm()
    if form.validate_on_submit():
        nombre = form.name.data
        apellido = form.apellido.data
        email = form.email.data
        username = form.username.data
        password = form.password.data
        dni = form.dni.data
        # Creamos el usuario y lo guardamos
        hi_client.service.register(nombre, apellido,username,password,dni,email)
        user = json.loads(hi_client.service.get_users_by_username(), object_hook=lambda d: namedtuple('product', d.keys())(*d.values()))
        # Dejamos al usuario logueado
        login_user(user, remember=True)
        next_page = request.args.get('next', None)
        if not next_page or url_parse(next_page).netloc != '':
            next_page = url_for('home')
        return redirect(next_page)
    return render_template("signup_form.html", form=form)

@app.route('/logout')
def logout():
    logout_user()
    return redirect(url_for('public.index'))

@app.route("/")
def index():
    logger.info('Mostrando los posts del blog')
    page = int(request.args.get('page', 1))
    per_page = current_app.config['ITEMS_PER_PAGE']
    post_pagination = Post.all_paginated(page, per_page)
    return render_template("public/index.html", post_pagination=post_pagination)





if __name__=='__main__':
	app.run(debug=True)







# 


# hi_client.service.register('matias', 'matias', 'matias','matias', 1299, "matias@", "113014")
# # print("Metodo 1 funcionando")
# hi_client.service.publish_category('mati')
# # print("Metodo 2 funcionando")
# hi_client.service.publish_product('Polo', 'Remera Blanca Polo Label', 'imagen', 1299, 50,  1, 1, 1, 1)
# # print("Metodo 3 funcionando")

# hi_client.service.edit_product(1,'tito', 'Remera Blanca Polo Label', 'imagen', 1299,  50, 1,1)

# # print("Metodo 4 funcionando")
# #product =json.loads(hi_client.service.get_products())
# product = json.loads(hi_client.service.get_products(), object_hook=lambda d: namedtuple('product', d.keys())(*d.values()))


# for p in product:
#     print (p.nombre)
#     print("\n")

    
# # print("Metodo 5 funcionando")
# # print((hi_client.service.check_edit(1)))
# # print("Metodo 6 funcionando")
# # print(json.loads((hi_client.service.get_products_bycat(1))))
# # print("Metodo 7 funcionando")
# # print(json.loads((hi_client.service.get_products_byuser(1))))
# # print("Metodo 8 funcionando")
# # print(json.loads((hi_client.service.get_products_byusercat(1,1))))
# # print("Metodo 9 funcionando")

