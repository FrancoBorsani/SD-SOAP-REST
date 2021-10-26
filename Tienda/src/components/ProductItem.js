import React from 'react';
import { Link } from 'react-router-dom';

const ProductItem = ({ product }) => {

    const userLink = () => {
        return (
            <>
                <Link to={`/product/${product._id}`} className="btn btn-info mr-1 flex-fill">
                    View
                </Link>
                <button className="btn btn-success ml-1 flex-fill" disabled={product.inStock === 0 ? true : false}>
                    Buy
                </button>
            </>
        )
    }

    const adminLink = () => {
        return (
            <h1>Admin!</h1>
        )
    }

    return (
        <div className="card complete-shadow p-2" style={{ width: '18rem' }}>

            <img className="card-img-top" src={product.image} alt={product.image} />
            <div className="card-body">
                <h5 className="card-title text-capitalize" title={product.title}>
                    {product.title}
                </h5>

                <div className="row justify-content-between mx-0">
                    <h6 className="text-danger">${product.price}</h6>
                    {
                        product.inStock > 0
                        ? <h6 className="text-danger">In Stock: {product.inStock}</h6>
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
    )
}

export default ProductItem