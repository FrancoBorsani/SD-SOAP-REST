import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router"
import { Container, Spinner } from "reactstrap";
import { addToCart } from "store/Actions";
import { DataContext } from "store/GlobalState";
import { getData } from "utils/fetchData";

const DetailProduct = () => {

    const { state, dispatch } = useContext(DataContext);
    const { cart } = state;

    const [product, setProduct] = useState({});
    const [loading, setLoading] = useState(true);

    const { id } = useParams();

    useEffect(() => {
        getData(`productos/${id}`).then(res => {
            setProduct(res)
            setLoading(false)
        })
    }, [id]);

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

                        <h6 className="text-danger">Vendidos: {product.sold} 30</h6>
                    </div>

                    <div className="my-2">{product.descripcionLarga}</div>
                     <div className="my-2">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo error sed obcaecati autem quas explicabo rerum delectus? Accusantium doloribus esse eaque non sint? Accusantium laboriosam praesentium dolores. Deleniti architecto harum saepe alias deserunt a, quibusdam veniam eaque sint ratione aspernatur.
                    </div>

                    <button type="button" className="btn-dark d-block my-3 px-5 py-2 w-100"
                        disabled={product.stock === 0 ? true : false}
                        onClick={() => dispatch(addToCart(product, cart))}>
                        Comprar
                    </button>
                </div>
            </div>
        </div>
    )
}

export default DetailProduct