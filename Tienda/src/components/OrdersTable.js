import { useEffect } from "react";
import { useContext } from "react";
import { useState } from "react"
import { Link } from "react-router-dom"
import { Container, Spinner } from "reactstrap";
import { DataContext } from "store/GlobalState";
import { getData } from "utils/fetchData";

const OrdersTable = () => {
    
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);

    const { state } = useContext(DataContext);

    const { auth } = state;

    useEffect(() => {

        getData(`pedido/getByVendedorOCliente`, auth.token)
            .then(res => {
                setOrders(res);
                setLoading(false);
            })
            .catch(e => console.log(e))

    }, [auth.token]);

    if (loading) return (
        <div className="page-header clear-filter">
            <Container>
                <Spinner color="info" />
            </Container>
        </div>
    )

    return (
        <div className="container clear-filter">
            <h3 className="text-uppercase">{ auth.user && auth.user.roles.includes('ROLE_ADMIN') ? 'Mis ventas' : 'Mis ordenes' }</h3>
            <div className="my-3">
                <table className="table-bordered table-hover w-100 text-uppercase">

                    <thead className="bg-light font-weight-bold">
                        <tr>
                            <td className="p-2">id</td>
                            <td className="p-2">Fecha</td>
                            <td className="p-2">Vendedor</td>
                            <td className="p-2">Comprador</td>
                            <td className="p-2">total</td>
                            <td className="p-2">Estado</td>
                            <td className="p-2">Detalle</td>
                        </tr>
                    </thead>

                    <tbody>
                        {
                            orders && orders.length > 0 && orders.map((order, index) => (
                                <tr key={index}>
                                    <td className="p-2">
                                        <Link className="text-dark" to={`/${order.idCompra}`}>{order.idCompra}</Link>
                                    </td>
                                    <td className="p-2">{new Date(order.createdAt).toLocaleDateString()}</td>
                                    <td className="p-2">{order.vendedor.username}</td>
                                    <td className="p-2">{order.comprador.username}</td>
                                    <td className="p-2">${order.total}</td>
                                    <td className="p-2">
                                        {
                                            order.delivered
                                                ? <i className="fas fa-check text-success"></i>
                                                : <i className="fas fa-times text-danger"></i>
                                        }
                                    </td>
                                    <td className="p-2">
                                        <Link className="text-dark" to={`/my/orders/${order.idCompra}`}>Ver</Link>
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>

                </table>
            </div>
        </div>
    )
}

export default OrdersTable