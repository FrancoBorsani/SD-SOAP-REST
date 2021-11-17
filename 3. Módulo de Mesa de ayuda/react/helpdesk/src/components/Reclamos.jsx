import React from 'react';

import { Apiurl } from '../services/apirest';
import axios from 'axios';
import { withRouter } from 'react-router-dom';

class Reclamos extends React.Component {

    state = {
        reclamos: [],
        filtroEstado: ""
    }

    componentDidMount() {
        let url = Apiurl + "reclamo";
        let headers = {
            'Authorization': window.localStorage.getItem("token")
        }

        axios.get(url, { headers: headers, withCredentials: true })
            .then(response => {
                this.setState({
                    reclamos: response.data
                })
            });
    }

    clickCompra(id) {
        this.props.history.push("reclamo/" + id);
    }

    tablaDeValores(value) {
        return (
            <>
                <td>{value.id}</td>
                <td>{value.reclamo}</td>
                <td>{value.estado}</td>
                <td>{value.decision}</td>
                <td>{value.idCompra}</td>
            </>
        )
    }

    render() {
        return (
            <div className="container">
                <br />
                <div className="container">
                    <h3>Reclamos</h3>
                </div>
                <br />
                <select className="form-control mb-2" value={this.state.filtroEstado} onChange={e => this.setState({ filtroEstado: e.target.value })}>
                    <option value="">Seleccione un estado para filtrar</option>
                    <option value="resuelto">Resuelto</option>
                    <option value="no resuelto">No Resuelto</option>
                </select>
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
                                    {
                                        this.state.filtroEstado === "resuelto" && value.estado === "resuelto" && (
                                            <>
                                                {this.tablaDeValores(value)}
                                            </>
                                        )
                                    }
                                    {
                                        this.state.filtroEstado === "no resuelto" && value.estado === "no resuelto" && (
                                            <>
                                                {this.tablaDeValores(value)}
                                            </>
                                        )
                                    }
                                    {
                                        this.state.filtroEstado === "" && (
                                            <>
                                                {this.tablaDeValores(value)}
                                            </>
                                        )
                                    }
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