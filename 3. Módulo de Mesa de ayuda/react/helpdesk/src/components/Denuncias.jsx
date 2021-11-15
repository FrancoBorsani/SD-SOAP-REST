import React from 'react';

import {Apiurl} from '../services/apirest';
import axios from 'axios';
import { withRouter } from 'react-router-dom';

class Denuncias extends React.Component {

    state = {
        denuncias:[]
    }
    
    componentDidMount() {
        let url = Apiurl + "denuncia";
        let headers = {
            'Authorization': window.localStorage.getItem("token")
        }

        axios.get(url, {headers:headers, withCredentials:true})
        .then(response => {
            this.setState({
                denuncias: response.data
            })
        });
    }
    
    clickProducto(id) {
        this.props.history.push("denuncia/" + id);   
    }

    render() {
        return(
            <div className="container">
                <br/>  
                <table className="table table-hover table-dark">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Categoria</th>
                            <th scope="col">Denuncia</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Resolucion</th>
                            <th scope="col">ID Producto</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.denuncias.map((value, index) => {
                            return (
                                <tr key={index} onClick={() => this.clickProducto(value.id)}>
                                    <td>{value.id}</td>
                                    <td>{value.categoria}</td>
                                    <td>{value.denuncia}</td>
                                    <td>{value.estado}</td>
                                    <td>{value.decision}</td>
                                    <td>{value.idProducto}</td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default withRouter(Denuncias);