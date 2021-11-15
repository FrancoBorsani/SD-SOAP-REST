import React from 'react';

import Header from '../template/Header';
import {Apiurl} from '../services/apirest';
import axios from 'axios';

import { withRouter } from 'react-router-dom';

class Denuncia extends React.Component {

    state = {
        form:{
            categoria: "",
            decision: "",
            denuncia: "",
            estado: "",
            id: 0,
            idProducto: 0
        },
        error: false,
        errorMsg: "",
        success:false,
        successMsg:""
    }
    
    componentDidMount() {
        let denunciaId = this.props.match.params.id;
        let url = Apiurl + "denuncia/" + denunciaId;
        let headers = {
            'Authorization': window.localStorage.getItem("token")
        }

        axios.get(url, {headers:headers, withCredentials:true})
        .then(response => {
            this.setState({
                form:{
                    categoria: response.data.categoria,
                    decision: response.data.decision,
                    denuncia: response.data.denuncia,
                    estado: response.data.estado,
                    id: response.data.id,
                    idProducto: response.data.idProducto
                },
                success:true,
                successMsg:"La denuncia ha sido aceptada."
            })
        });
    }

    aceptar = () => {
        let url = Apiurl + "denuncia/aceptar";
        let headers = {
            'Authorization': window.localStorage.getItem("token")
        }

        if(this.state.form.estado === "A Resolver") {
            axios.post(url, this.state.form, {headers:headers, withCredentials:true})
            .then(response => {
                this.props.history.push("/dashboard");
            })
        } else {
            this.setState({
                error:true,
                errorMsg: "La denuncia ya fué resuelta."
            })
        }
    }

    rechazar = () => {
        let url = Apiurl + "denuncia/rechazar";
        let headers = {
            'Authorization': window.localStorage.getItem("token")
        }

        if(this.state.form.estado === "A Resolver") {
            axios.post(url, this.state.form, {headers:headers, withCredentials:true})
            .then(response => {
                this.props.history.push("/dashboard");
            })
        } else {
            this.setState({
                error:true,
                errorMsg: "La denuncia ya fué resuelta."
            })
        }
    }

    handlerSubmit = e => {
        e.preventDefault();
    }

    render() {
        return(
            <React.Fragment>
                <Header></Header>
                <br/><br/>
                <div className="container">
                    <h3>Resolver denuncia</h3>
                </div>
                <div className="container">
                    <br/>
                    <form className="form-horizontal" onSubmit={this.handlerSubmit}>
                        <div className="row">
                            <div className="col-sm-12">
                                <label className="col-md-2 control-label">Categoria</label>
                                <div className="col-md-10">
                                    <input type="text" className="form-control" name="categoria" value={this.state.form.categoria}/>
                                </div>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-sm-12">
                                <label className="col-md-2 control-label">Denuncia</label>
                                <div className="col-md-10">
                                    <input type="text" className="form-control" name="denuncia" value={this.state.form.denuncia}/>
                                </div>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-sm-12">
                                <label className="col-md-2 control-label">Link publicación</label>
                                <div className="col-md-10">
                                    <a className="link-primary" name="publicacion" value={this.state.form.idProducto}>A</a>
                                </div>
                            </div>
                        </div>
                        <br/><br/>
                        <button type="submit" className="btn btn-primary" style={{ marginRight: "10px" }} onClick={() => this.aceptar()}>Aceptar</button>
                        <button type="submit" className="btn btn-danger" style={{ marginRight: "10px" }} onClick={() => this.rechazar()}>Rechazar</button>
                        <a className="btn btn-dark" style={{ marginRight: "10px" }} href="/dashboard">Volver</a>
                        
                        <br/><br/>

                        {
                            this.state.error === true && 
                            <div className="alert alert-danger" role="alert">
                                {this.state.errorMsg}
                            </div>
                        }
                    </form>
                </div>
            </React.Fragment>
        );
    }
}

export default withRouter(Denuncia);