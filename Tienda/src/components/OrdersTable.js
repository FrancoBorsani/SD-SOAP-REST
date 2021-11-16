import { useEffect } from "react";
import { useContext } from "react";
import { useState } from "react"
import { Link } from "react-router-dom"
import { Container, Spinner } from "reactstrap";
import { DataContext } from "store/GlobalState";
import { postData } from "utils/fetchData";
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

    const handleCancelOrder = async (idCompra, total) => {

        let confirm = window.confirm('Estas seguro que deseas cancelar el pedido?');

        if (confirm) {

            const response = await postData(`pedido/cancelar?idCompra=${idCompra}&total=${total}`, null, auth.token);

            if (response.error) alert('Error inesperado. Por favor intente más tarde.');

            window.location.reload();

        }

    }

    if (loading) return (
        <div className="page-header clear-filter">
            <Container>
                <Spinner color="info" />
            </Container>
        </div>
    )

    return (
        <div className="container clear-filter">
            <h3 className="text-uppercase">{auth.user && auth.user.roles.includes('ROLE_ADMIN') ? 'Mis ventas' : 'Mis ordenes'}</h3>
            <div className="table-responsive my-3">
                <table className="table-bordered table-hover w-100 text-uppercase">

                    <thead className="bg-light font-weight-bold">
                        <tr>
                            <td className="p-2">id</td>
                            <td className="p-2">Fecha</td>
                            <td className="p-2">Vendedor</td>
                            <td className="p-2">Comprador</td>
                            <td className="p-2">Total</td>
                            <td className="p-2">Estado</td>
                            <td className="p-2">Acciones</td>
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
                                    <td className="p-2">{order.estadoDeCompra === 'Cancelado' ? 'Cancelado' : order.estadoDeEnvio}</td>
                                    <td className="p-2">
                                        <Link className="text-dark" to={`/my/orders/${order.idCompra}`}>
                                            Ver
                                        </Link>
                                        {
                                            order.estadoDeCompra === 'Realizado' && order.estadoDeEnvio === 'En Preparación' && (
                                                <>
                                                    <span className="mx-1">-</span>
                                                    <span className="text-dark text-uppercase" style={{ cursor: 'pointer' }} onClick={() => handleCancelOrder(order.idCompra, order.total)}>
                                                        Cancelar
                                                    </span>
                                                </>
                                            )
                                        }

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