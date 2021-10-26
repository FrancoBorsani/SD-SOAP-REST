import { Link } from "react-router-dom"

const CartItem = ({ item }) => {
    return (
        <tr>
            <td style={{ width: '100px' }}>
                <img src={item.imagen} alt={item.imagen}
                    className="img-thumbnail w-100"
                    style={{ minWidth: '80px', height: '80px' }}
                />
            </td>

            <td style={{ width: '200px' }} className="w-50 align-middle">
                <h5 className="text-capitalize">
                    <Link to={`product/${item.idProducto}`}>
                        {item.descripcionLarga}
                    </Link>
                </h5>

                <h6 className="text-danger">${item.precio /* * item.quantity */}</h6>
                {
                    item.stock > 0
                        ? <p className="text-danger mb-1">In Stock: {item.stock}</p>
                        : <p className="text-danger mb-1">Out Stock</p>
                }
            </td>

            <td className="align-middle" style={{ minWidth: '150px' }}>
                <button className="btn btn-secondary" 
                >-</button>
                <span className="px-3">{/*item.quantity */ 10}</span>
                <button className="btn btn-secondary" 
                >+</button>
            </td>

            <td className="align-middle" style={{ minWidth: '50px', cursor: 'pointer' }}>
                <i className="fas fa-trash-alt text-danger" data-toggle="modal" data-target="#exampleModal"></i>  
            </td>

        </tr>
    )
}

export default CartItem
