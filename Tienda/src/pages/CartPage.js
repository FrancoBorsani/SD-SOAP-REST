import CartItem from 'components/CartItem'
import Layout from 'components/Layout/Layout'
import { useContext, useEffect, useState } from 'react'
import { Link, useHistory } from 'react-router-dom'
import { DataContext } from 'store/GlobalState'
import { getData } from 'utils/fetchData'
import { ClientRoutes } from '../helpers/enums'

const CartPage = () => {

    const { state, dispatch } = useContext(DataContext);

    const { cart, auth } = state;

    const [total, setTotal] = useState(0);
    
    const [error, setError] = useState("");
    
    const router = useHistory();

    useEffect(() => {
        const getTotal = () => {
            const res = cart.reduce((prev, item) => {
                return prev + (item.precio * item.cantidad)
            }, 0);

            setTotal(res);
        }

        getTotal();

    }, [cart]);

    const handleRedirectCheckout = async () => {

        const cards = await getData(`tarjeta/getTarjetasDeUsuario`, auth.token);

        const addresses = await getData(`direccion/getDomicilioDeUsuario`, auth.token);

        if(addresses.length === 0 || cards.length === 0) return setError("Para realizar una compra debe tener registrado una tarjeta y una dirección.");

        router.push(ClientRoutes.CHECKOUT);

    }

    if (cart.length === 0) {
        return (
            <Layout>
                <div className="mt-100">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="card-body cart">
                                <div className="col-sm-12 empty-cart-cls text-center"> <img src="https://i.imgur.com/dCdflKN.png" alt="cart_empty" width="130" height="130" className="img-fluid mb-4 mr-3"/>
                                    <h3><strong>El Carrito Esta Vacio</strong></h3>
                                    <h4>Parece que aún no ha hecho su elección...</h4>
                                    <Link to="/" className="btn btn-info cart-btn-transform m-3">Seguir Comprando</Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </Layout>
        )
    }

    return (
        <Layout>
            <div className="row justify-content-center">

                {
                    error && (
                        <div className="col-md-12">
                            <div className="alert alert-danger" >
                                {error} Puede registrarlas <Link to="/profile">aquí.</Link>
                            </div>
                        </div>
                    )
                }

                <div className="col-md-12 text-secondary table-responsive my-3">
                    <div className="checkout card">
                        <div className="row">
                            <div className="col-md-8 cart pt-3" style={{ overflowY: 'scroll', height: '400px' }}>
                                <h4>Carrito de compras</h4>
                                {
                                    cart.map(item => (
                                        <CartItem item={item} dispatch={dispatch} cart={cart} key={item.idProducto} />
                                    ))
                                }
                            </div>
                            <div className="col-md-4 summary">
                                <h5>Resumen</h5>
                                <hr />
                                <div className="row">
                                    <span className="text-uppercase">ITEMS {cart.length}</span>
                                    <div className="col text-right">$ {total}</div>
                                </div>
                                <div className="row mt-2">
                                    <span className="text-uppercase">PRECIO TOTAL</span>
                                    <div className="col text-right">$ {total}</div>
                                </div> 
                                <button className="btn btn-dark py-2" onClick={handleRedirectCheckout}>CHECKOUT</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </Layout>   
    )
}

export default CartPage;