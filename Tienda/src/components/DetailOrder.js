import { useContext } from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useParams } from "react-router";
import { Container, Spinner } from "reactstrap";
import { DataContext } from "store/GlobalState";
import { getData } from "utils/fetchData";

const DetailOrder = () => {

    const { id } = useParams();

    const [order, setOrder] = useState([]);
    const [loading, setLoading] = useState(true);

    const { state } = useContext(DataContext);

    const { auth } = state;

    useEffect(() => {
        getData(`pedido/${id}`, auth.token)
            .then(res => {
                setOrder(res);
                setLoading(false);
                console.log(res);
            })
            .catch(e => console.log(e));

    }, [id, auth.token]);

    if (loading) return (
        <div className="page-header clear-filter">
            <Container>
                <Spinner color="info" />
            </Container>
        </div>
    )

    return (
        <>
            <div className="row justify-content-center">

                <div className="col-md-12">

                    <div className="text-uppercase my-3">
                        <h2 className="text-break">#Orden {order.idCompra}</h2>

                        <hr />

                        {
                            order.estadoDeCompra === 'Cancelado' && (
                                <div className="alert alert-danger">
                                    Orden cancelada
                                </div>
                            )
                        }



                        <div className="row justify-content-between">
                            <div className="col-md-4">
                                <h3>Envio</h3>

                                <p>Nombre: {order.comprador?.nombre + " " + order.comprador?.apellido}</p>
                                <p>Email: {order.comprador?.email}</p>
                                <p>Domicilio: {order.direccionDeEntrega}</p>
                                <p>Telefono: {order.comprador?.telefono}</p>
                                <p>Estado: { order.estadoDeCompra === 'Cancelado' ? 'Cancelado' : order.estadoDeEnvio}</p>

                            </div>

                            <div className="col-md-4">

                                <h3>Pago</h3>
                                {
                                    order.method && <h6>Method: <em>{order.method}</em> </h6>
                                }
                                {
                                    order.paymentId && <p>PaymentId: <em>{order.paymentId}</em> </p>
                                }

                                <div className={`alert ${order.paid ? 'alert-success' : 'alert-danger'}
                            d-flex justify-content-between align-items-center`}>

                                    Agregen lo que consideren!

                                </div>

                            </div>

                            <div className="col-md-4">

                                <h3>Productos</h3>

                                {/*
                                order.listaitems.map(item => (
                                    <div className="row border-bottom m-0 p-2 justify-content-between align-items-center"
                                        style={{ maxWidth: '550px' }} key={item._id}>
                                        <img src={item.images[0].url} alt={item.images[0].url}
                                            className="img-thumbnail"
                                            style={{ width: '50px', height: '45px' }}
                                        />

                                        <h5 className="flex-fill text-secondary px-3 text-capitalize m-0">
                                            <Link href={`product/${item._id}`}>{item.title}</Link>
                                        </h5>

                                        <span className="text-info m-0">
                                            {item.quantity} x ${item.price} = ${item.price * item.quantity}
                                        </span>
                                    </div>
                                )) */
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default DetailOrder;