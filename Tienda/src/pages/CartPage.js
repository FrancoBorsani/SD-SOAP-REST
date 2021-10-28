import CartItem from 'components/CartItem'
import { useContext, useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { DataContext } from 'store/GlobalState'

const CartPage = () => {

    const { state, dispatch } = useContext(DataContext);

    const { cart } = state;

    const [total, setTotal] = useState(0);

    useEffect(() => {
        const getTotal = () => {
            const res = cart.reduce((prev, item) => {
                return prev + (item.precio * item.cantidad)
            }, 0)

            setTotal(res)
        }

        getTotal()

    }, [cart]);

    if (cart.length === 0) {
        return (
            <div className="container-fluid mt-100">
                <div className="row">
                    <div className="col-md-12">
                        <div className="card-body cart">
                            <div className="col-sm-12 empty-cart-cls text-center"> <img src="https://i.imgur.com/dCdflKN.png" alt="cart_empty" width="130" height="130" className="img-fluid mb-4 mr-3"/>
                                <h3><strong>El Carrito Esta Vacio</strong></h3>
                                <h4>Parece que aún no ha hecho su elección...</h4>
                                <Link to="/" className="btn btn-info cart-btn-transform m-3">Continue Shopping</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    return (
        <div className="container clear-filter">
            <div className="row justify-content-center">
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
                                <h5>Summary</h5>
                                <hr />
                                <div className="row">
                                    <span className="text-uppercase">ITEMS {cart.length}</span>
                                    <div className="col text-right">$ {total}</div>
                                </div>
                                <form>
                                    <p>SHIPPING</p> <select>
                                        <option className="text-muted">Standard-Delivery- $0.00</option>
                                    </select>
                                </form>
                                <div className="row">
                                    <div className="col">TOTAL PRICE</div>
                                    <div className="col text-right">$ {total}</div>
                                </div> 
                                <button className="btn">CHECKOUT</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CartPage