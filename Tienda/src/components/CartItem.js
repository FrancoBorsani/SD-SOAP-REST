import { Link } from "react-router-dom"
import { increase } from "store/Actions"
import { decrease } from "store/Actions"

const CartItem = ({ item, dispatch, cart }) => {
    return (
        <div className="row border-top border-bottom">
            <div className="row main align-items-center">
                <div className="col-2">
                    <img className="img-fluid" src={item.imagen} alt={item.imagen} />
                </div>
                <div className="col">
                    <div className="row">
                        <Link to={`/product/${item.idProducto}`}>
                            { item.descripcionLarga }
                        </Link>
                    </div>
                </div>
                <div className="col">
                    <button className="mr-1" onClick={() => dispatch(decrease(cart, item.idProducto))} 
                        style={{ cursor: 'pointer', border: 'none' }}
                        disabled={item.cantidad === 1 ? true : false}
                    >
                        -
                    </button>

                    <button href="#" className="border mr-1">{ item.cantidad }</button>

                    <button onClick={() => dispatch(increase(cart, item.idProducto))} style={{ cursor: 'pointer', border: 'none' }}
                        disabled={item.stock === item.cantidad ? true : false}
                    >
                        +
                    </button>
                    
                </div>
                <div className="col">
                    <span>${ item.precio * item.cantidad }</span>
                    <span className="close" style={{ cursor: 'pointer' }}
                    onClick={() => dispatch({
                        type: 'ADD_MODAL',
                        payload: [{data: cart, id: item.idProducto, title: item.descripcionCorta, type: 'ADD_CART'}]
                    })}
                    >&#10005;</span>
                </div>
            </div>
        </div>
    )
}

export default CartItem
