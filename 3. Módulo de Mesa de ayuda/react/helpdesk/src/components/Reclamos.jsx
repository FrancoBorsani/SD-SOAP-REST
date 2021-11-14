import React from 'react';

import {Apiurl} from '../services/apirest';
import axios from 'axios';
import { withRouter } from 'react-router-dom';

class Reclamos extends React.Component {

    state = {
        reclamos:[]
    }
    
    componentDidMount() {
        let url = Apiurl + "reclamo";
        let headers = {
            'Authorization': window.localStorage.getItem("token")
        }

        axios.get(url, {headers:headers, withCredentials:true})
        .then(response => {
            this.setState({
                reclamos: response.data
            })
        });
    }
    
    clickCompra(id) {
        this.props.history.push("reclamo/" + id);
    }

    render() {
        return(
            <div className="container">
                <br/>
                <table className="table table-hover table-dark">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Reclamo</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Resolucion</th>
                            <th scope="col">ID Compra</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.reclamos.map((value, index) => {
                            return (
                                <tr key={index} onClick={() => this.clickCompra(value.id)}>
                                    <td>{value.id}</td>
                                    <td>{value.reclamo}</td>
                                    <td>{value.estado}</td>
                                    <td>{value.decision}</td>
                                    <td>{value.idCompra}</td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default withRouter(Reclamos);