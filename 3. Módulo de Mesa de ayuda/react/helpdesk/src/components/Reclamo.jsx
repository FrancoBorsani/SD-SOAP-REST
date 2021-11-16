import React from 'react';

import Header from '../template/Header';
import {Apiurl, Ecommerce} from '../services/apirest';
import axios from 'axios';

import { withRouter } from 'react-router-dom';

class Reclamo extends React.Component {

    state = {
        form:{
            decision: "",
            estado: "",
            id: 0,
            idCompra: 0,
            reclamo: ""
        },
        error: false,
        errorMsg: "",
        success:false,
        successMsg:"",
        urlCompra: "",
        pedido:{
            idPedido: 0,
            monto: 0,
            usuario: 0,
            nombre: "",
            apellido: "",
            username: ""
        }
    }
    
    componentDidMount() {
        let reclamoId = this.props.match.params.id;
        let url = Apiurl + "reclamo/" + reclamoId;
        let headers = {
            'Authorization': window.localStorage.getItem("token")
        }

        axios.get(url, {headers:headers, withCredentials:true})
        .then(response => {
            this.setState({
                form:{
                    decision: response.data.decision,
                    estado: response.data.estado,
                    id: response.data.id,
                    idCompra: response.data.idCompra,
                    reclamo: response.data.reclamo,
                },
                success:true,
                successMsg:"El reclamo ha sido aceptado.",
                urlCompra: Ecommerce + "api/v1/pedido/reclamos/" + response.data.idCompra
            });

            axios.get(this.state.urlCompra)
            .then(response => {
            this.setState({
                pedido:{
                    idPedido: response.data.idCompra,
                    monto: response.data.total,
                    usuario: response.data.comprador.id,
                    nombre: response.data.comprador.nombre,
                    apellido: response.data.comprador.apellido,
                    username: response.data.comprador.username
                }
            })
        })
        });
    }

    aceptar = () => {
        let url = Apiurl + "reclamo/aceptar";
        let headers = {
            'Authorization': window.localStorage.getItem("token")
        }
        
        console.log(this.state.form)

        if(this.state.form.estado === "A Resolver") {
            axios.post(url, this.state.form, {headers:headers, withCredentials:true})
            .then(response => {
                
                this.props.history.push("/dashboard");
            });
            
        } else {
            this.setState({
                error:true,
                errorMsg: "El reclamo ya fué resuelto."
            })
        }
    }

    rechazar = () => {
        let url = Apiurl + "reclamo/rechazar";
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
                errorMsg: "El reclamo ya fué resuelto."
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
                    <h3>Resolver Reclamo</h3>
                </div>
                <div className="container">
                    <br/>
                    <form className="form-horizontal" onSubmit={this.handlerSubmit}>
                        <div className="row">
                            <div className="card" style={{ width: "35rem" }}>
                                <div className="card-body">
                                    <h5 className="card-title">Datos de la compra</h5>
                                    <p className="card-text"><b>Reclamo:</b> {this.state.form.reclamo}</p>
                                    <p className="card-text"><b>Usuario:</b> {this.state.pedido.username}</p>
                                    <p className="card-text"><b>Nombre y apellido:</b> {this.state.pedido.nombre} {this.state.pedido.apellido}</p>
                                    <p className="card-text"><b>Monto de la compra:</b> {this.state.pedido.monto}</p>
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

export default withRouter(Reclamo);