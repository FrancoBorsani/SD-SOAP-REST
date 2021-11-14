import React from 'react';

import Header from '../template/Header';
import {Apiurl} from '../services/apirest';
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
        successMsg:""
    }
    
    componentDidMount() {
        let reclamoId = this.props.match.params.id;
        let url = Apiurl + "reclamo/" + reclamoId;
        let headers = {
            'Authorization': window.localStorage.getItem("token")
        }

        axios.get(url, {headers:headers, withCredentials:true})
        .then(response => {
            console.log(response);
            this.setState({
                form:{
                    decision: response.data.decision,
                    estado: response.data.estado,
                    id: response.data.id,
                    idCompra: response.data.idCompra,
                    reclamo: response.data.reclamo
                },
                success:true,
                successMsg:"El reclamo ha sido aceptado."
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
            })
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
                            <div className="col-sm-12">
                                <label className="col-md-2 control-label">Reclamo</label>
                                <div className="col-md-10">
                                    <input type="text" className="form-control" name="denuncia" value={this.state.form.reclamo}/>
                                </div>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-sm-12">
                                <label className="col-md-2 control-label">Detalle de compra</label>
                                <div className="col-md-10">
                                    <input type="text" className="form-control" name="denuncia" value={this.state.form.idCompra}/>
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