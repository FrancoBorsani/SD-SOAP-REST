import { useEffect } from "react";
import { useContext } from "react";
import { useState } from "react";
import { Container, Spinner } from "reactstrap";
import { DataContext } from "store/GlobalState";
import { getData } from "utils/fetchData";

const ClaimCreate = () => {

    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);

    const { state } = useContext(DataContext);

    const { auth } = state;

    useEffect(() => {

        getData(`pedido/getByVendedorOCliente`, auth.token)
            .then(res => {
                setOrders(res);
                console.log(res);
                setLoading(false);
            })
            .catch(e => console.log(e))

    }, [auth.token]);

    const [claim, setClaim] = useState({
        idCompra: '',
        reclamo: ''
    });

    const handleChangeInput = e => {
        const { name, value } = e.target
        setClaim({ ...claim, [name]: value })
    }

    const handleSubmit = async e => {
        e.preventDefault();

        const response = await fetch(`http://localhost:8083/reclamo`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(claim),
        })

        const data = await response.json();

        console.log(data);

    }

    if (loading) return (
        <div className="page-header clear-filter">
            <Container>
                <Spinner color="info" />
            </Container>
        </div>
    )

    return (
        <div className="row justify-content-center">
            <div className="col-md-10">
                <div className="card">
                    <div className="card-header pt-3 pl-3">
                        Inicar Reclamo.
                    </div>
                    <hr />
                    <div className="card-body">
                        <form onSubmit={handleSubmit}>
                            <div className="form-group">
                                <label>Seleccione la compra sobre cual quiere hacer el reclamo:</label>
                                <select name="idCompra" value={claim.idCompra} required
                                    className="form-control text-capitalize py-2 mt-2" onChange={handleChangeInput}>
                                    <option value="">Seleccione una compra</option>
                                    {
                                        orders && orders.length > 0 && orders.map(order => (
                                            <option value={order.idCompra}>{order.idCompra}</option>
                                        ))
                                    }
                                </select>
                            </div>

                            <textarea className="form-control" name="reclamo" cols="30" rows="3" required
                                placeholder="Ingrese su reclamo" value={claim.reclamo} onChange={handleChangeInput}
                            />

                            <button className="btn btn-primary rounded w-100" color="info" type="submit">Realizar reclamo</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ClaimCreate;