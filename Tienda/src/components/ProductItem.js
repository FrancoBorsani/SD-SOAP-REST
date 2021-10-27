import React from 'react';
import { Link } from 'react-router-dom';

const ProductItem = ({ product }) => {

    const userLink = () => {
        return (
            <>
                <Link to={`/product/${product.idProducto}`} className="btn btn-info mr-1 flex-fill">
                    View
                </Link>
                <button className="btn btn-success ml-1 flex-fill" disabled={product.stock === 0 ? true : false}>
                    Buy
                </button>
            </>
        )
    }

    return (
        <div className="products">
            <div className="card shadow p-2" style={{ width: '18rem' }}>

                <img className="card-img-top" src={product.imagen} alt={product.imagen} />
                <div className="card-body">
                    <h5 className="card-title text-capitalize" title={product.descripcionCorta}>
                        {product.descripcionCorta}
                    </h5>

                    <div className="row justify-content-between mx-0">
                        <h6 className="text-danger">${product.precio}</h6>
                        {
                            product.stock > 0
                            ? <h6 className="text-success">In Stock: {product.stock}</h6>
                            : <h6 className="text-danger">Out Stock</h6>
                        }
                    </div>

                    {/*<p className="card-text" title={product.description}>
                        {product.description}
                    </p> */}
                        
                    <div className="row justify-content-between mx-0">
                        {userLink()}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ProductItem