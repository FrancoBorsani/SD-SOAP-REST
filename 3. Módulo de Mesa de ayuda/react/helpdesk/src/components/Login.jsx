import React from 'react';
import '../assets/css/Login.css';
import {Apiurl} from '../services/apirest';
import Dashboard from './Dashboard';

import axios from 'axios';
import { withRouter } from 'react-router-dom';

class Login extends React.Component {

    constructor(props) {
        super(props);
    }

    state = {
        form:{
            "username":"",
            "password":""
        },
        error:false,
        errorMsg:""
    }

    handlerSubmit(e) {
        e.preventDefault();
    }

    changeHandler = async e => {
        await this.setState ({
            form: {
                ...this.state.form,
                [e.target.name]: e.target.value
            }
        })
    }

    buttonHandler = () => {
        let url = Apiurl + "auth";

        axios.post(url, this.state.form)
        .then(response => {
            if(response.status === 200) {
                localStorage.setItem("token", response.data.token);
                this.props.history.push("dashboard")
            }
            if(response.status === 401) {
                this.setState( {
                    error: true,
                    errorMsg: "Credenciales inválidas!"
                })
            }
        }).catch(error => {
            this.setState( {
                error: true,
                errorMsg: "Error en la conexión."
            })
        })
    }

    render() {
        if(window.localStorage.getItem("token")) {
            return <Dashboard></Dashboard>
        } else {
            return(
                <React.Fragment>
                    <div className="wrapper fadeInDown">

                        <div id="formContent">
                            <form onSubmit = {this.handlerSubmit}>
                                <input type="text"  className="fadeIn second" name="username" placeholder="Usuario" onChange = {this.changeHandler} />
                                <input type="password"  className="fadeIn third" name="password" placeholder="Password" onChange = {this.changeHandler} />
                                <input type="submit" className="fadeIn fourth" value="Log In" onClick = {this.buttonHandler} />
                            </form>
                            
                            {
                                this.state.error === true && 
                                <div className="alert alert-danger" role="alert">
                                    {this.state.errorMsg}
                                </div>
                            }
                        </div>
                    </div>
                </React.Fragment>
            );
        }
    }
}

export default withRouter(Login);