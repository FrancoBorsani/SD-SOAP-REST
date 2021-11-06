import { Link } from "react-router-dom"
import { increase } from "store/Actions"
import { decrease } from "store/Actions"

const CartItem = ({ item, dispatch, cart, isCheckout = false }) => {
    return (
        <div className="row border-top border-bottom">
            <div className="row align-items-center justify-content-between w-100">
                <div className="col-2 p-1">
                    <img src={item.imagen} alt={item.imagen} width="40" />
                </div>
                <div className="col">
                    <div className="row">
                        <Link to={`/product/${item.idProducto}`}>
                            {item.descripcion}
                        </Link>
                    </div>
                </div>

                <div className="col">

                    {
                        !isCheckout && (
                            <button className="mr-1" onClick={() => dispatch(decrease(cart, item.idProducto))}
                                style={{ cursor: 'pointer', border: 'none' }}
                                disabled={item.cantidad === 1 ? true : false}
                            >
                                -
                            </button>
                        )
                    }

                    <button className="border mr-1">{item.cantidad}</button>

                    {
                        !isCheckout && (
                            <button onClick={() => dispatch(increase(cart, item.idProducto))} style={{ cursor: 'pointer', border: 'none' }}
                                disabled={item.stock === item.cantidad ? true : false}
                            >
                                +
                            </button>
                        )
                    }

                </div>
                <div className="col">
                    <span>${item.precio * item.cantidad}</span>

                    {
                        !isCheckout && (
                            <span className="close" style={{ cursor: 'pointer' }}
                                onClick={() => dispatch({
                                    type: 'ADD_MODAL',
                                    payload: [{ data: cart, id: item.idProducto, title: item.nombre, type: 'ADD_CART' }]
                                })}
                            >&#10005;</span>
                        )
                    }
                </div>
            </div>
        </div>
    )
}

export default CartItem
