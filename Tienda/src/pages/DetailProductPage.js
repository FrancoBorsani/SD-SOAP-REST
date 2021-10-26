import { useEffect, useState } from "react";
import { useParams } from "react-router"
import { Container, Spinner } from "reactstrap";
import { getData } from "utils/fetchData";

const DetailProduct = () => {

    const [product, setProduct] = useState({});
    const [loading, setLoading] = useState(true);

    const { id } = useParams()

    useEffect(() => {
        getData(`productos/${id}`).then(res => {
            setProduct(res)
            setLoading(false)
        })
    })

    if (loading) return (
        <div className="page-header clear-filter">
            <Container>
                <Spinner color="info" />
            </Container>
        </div>
    )

    return (
        <div className="container clear-filter">
            <div className="row detail-page">

                <div className="col-md-6">
                    <img src={product.imagen} alt={product.imagen}
                        className="d-block img-thumbnail rounded mt-4 w-100"
                        style={{ height: '300px' }} />
                </div>

                <div className="col-md-6 mt-3">
                    <div className="row d-flex justify-content-between mx-0">
                        <h2 className="text-uppercase">{product.descripcionCorta}</h2>
                        <h5 className="text-danger">${product.precio}</h5>
                    </div>

                    <div className="row d-flex justify-content-between mx-0">
                        {
                            product.stock > 0 ?
                                <h6 className="text-danger">Stock: {product.stock}</h6>
                                :
                                <h6 className="text-danger">Stock: Out Stock</h6>
                        }

                        <h6 className="text-danger">Sold: {product.sold} 30</h6>
                    </div>

                    <div className="my-2">{product.descripcionLarga}</div>

                    <button type="button" className="btn btn-dark d-block my-3 px-5 w-100"
                        disabled={product.stock === 0 ? true : false}>
                        Buy
                    </button>

                </div>
            </div>
        </div>
    )
}

export default DetailProduct