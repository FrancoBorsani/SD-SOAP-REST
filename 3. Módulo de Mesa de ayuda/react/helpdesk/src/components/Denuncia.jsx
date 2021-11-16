import React from 'react';

import Header from '../template/Header';
import {Apiurl, Ecommerce} from '../services/apirest';
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
        successMsg:"",
        urlDenuncia: "",
        denuncia:{
            idProducto: 0,
            nombre: "",
            descripcion: "",
            precio: 0,
            imagen: "",
            categoria: "",
            vendedor: "",
            nombre: "",
            apellido: "",
            urlPublicacion: ""
        }
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
                successMsg:"La denuncia ha sido aceptada.",
                urlDenuncia: Ecommerce + "api/v1/productos/denuncia/" + response.data.idProducto
            })
            axios.get(this.state.urlDenuncia)
            .then(response => {
                this.setState({
                    denuncia:{
                        nombre: response.data.nombre,
                        descripcion: response.data.descripcion,
                        categoria: response.data.categoria.nombre,
                        vendedor: response.data.vendedor.username,
                        nombre: response.data.vendedor.nombre,
                        apellido: response.data.vendedor.apellido,
                        urlPublicacion: "http://localhost:3000/product/" + this.state.form.idProducto
                    }
                })
            });
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
                
            });

            axios.post(this.state.urlDenuncia)
            .then(response => {
                console.log(response);
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
                            <div className="card" style={{ width: "35rem" }}>
                                <div className="card-body">
                                    <h5 className="card-title">Datos de la publicación</h5>
                                    <p className="card-text"><b>Denuncia:</b> {this.state.form.denuncia}</p>
                                    <p className="card-text"><b>Categoría:</b> {this.state.form.categoria}</p>
                                    <p className="card-text"><b>Producto:</b> {this.state.denuncia.nombre}</p>
                                    <p className="card-text"><b>Descripción:</b> {this.state.denuncia.descripcion}</p>
                                    <p className="card-text"><b>Categoría producto:</b> {this.state.denuncia.categoria}</p>
                                    <p className="card-text"><b>Vendedor:</b> {this.state.denuncia.vendedor}</p>
                                    <p className="card-text"><b>Nombre y apellido:</b> {this.state.denuncia.nombre} {this.state.denuncia.apellido}</p>
                                    <a className="card-text" href={this.state.denuncia.urlPublicacion}><b>Link</b></a>
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