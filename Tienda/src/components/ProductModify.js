import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Container, Spinner } from "reactstrap";
import { getData } from "utils/fetchData";

const ProductModify = () => {

    const [product, setProduct] = useState({
        nombre: '',
        descripcion: '',
        precio: 0,
        stock: 0,
        cantidadVendida: 0,
        categoria: ''
    });

    const paymentMethods = [
        { name: 'Débito' },
        { name: 'Crédito' },
        { name: 'Ambos' }
    ]

    const [loading, setLoading] = useState(true);

    const { id } = useParams();

    useEffect(() => {

        getData(`productos/${id}`)
            .then(res => {
                setProduct(res);
                setLoading(false);
            })
            .catch(error => console.log(error))

    }, [id]);

    const handleChangeInput = e => {
        const { name, value } = e.target;
        setProduct({ ...product, [name]: value });
    }

    const handleSubmit = e => {
        e.preventDefault();
    }

    if (loading) return (
        <div className="page-header clear-filter">
            <Container>
                <Spinner color="info" />
            </Container>
        </div>
    )

    return (
        <>
            <div className="row mb-3">
                <div className="card-body shadow">
                    <i class="fas fa-edit"></i> Editar Producto
                </div>
            </div>

            <form className="row shadow p-3 my-0" onSubmit={handleSubmit}>

                <div className="col-md-12">
                    <div className="form-group">
                        <label>Nombre</label>
                        <input type="text" name="nombre" value={product.nombre}
                            placeholder="Nombre" className="form-control w-100"
                            disabled={product.cantidadVendida > 0 ? true : false}
                        />
                    </div>
                    <div className="row">
                        <div className="col-md-6">
                            <div className="form-group">
                                <label>Precio</label>
                                <input type="number" name="precio" value={product.precio}
                                    placeholder="Precio" className="form-control w-100"
                                    onChange={handleChangeInput}
                                    disabled={product.cantidadVendida > 0 ? true : false}
                                />
                            </div>
                        </div>

                        <div className="col-md-6">
                            <div className="form-group">
                                <label>Stock</label>
                                <input type="number" name="stock" value={product.stock}
                                    placeholder="Stock" className="form-control w-100"
                                    onChange={handleChangeInput}
                                />
                            </div>
                        </div>
                    </div>

                    <textarea className="form-control" 
                        name="descripcion" 
                        id="descripcion" 
                        cols="30" 
                        rows="2"
                        placeholder="Descripcion" 
                        value={product.descripcion} 
                        onChange={handleChangeInput}
                        disabled={product.cantidadVendida > 0 ? true : false}
                    />

                    <div className="row">
                        <div className="col-md-6">
                            <div className="input-group-prepend my-3">
                                <select name="category" id="category" 
                                    value={product.categoria}
                                    onChange={handleChangeInput} 
                                    className="form-control text-capitalize py-2"
                                    disabled={product.cantidadVendida > 0 ? true : false}>
                                    <option value="all">All products</option>
                                    {   /*
                                        categories.map(category => (
                                            <option value={category.id} key={category.id}>{category.nombre}</option>
                                        )) */
                                    }
                                </select>
                            </div>
                        </div>

                        <div className="col-md-6">
                            <div className="input-group-prepend my-3">
                                <select name="metodoDePago" id="metodoDePago"
                                    onChange={handleChangeInput} className="form-control text-capitalize py-2">
                                    {
                                        paymentMethods.map((paymentMethod, index) => (
                                            <option value={paymentMethod.name} key={index}>{paymentMethod.name}</option>
                                        ))
                                    }
                                </select>
                            </div>
                        </div> 
                    </div>
                </div>
                <input type="submit" className="btn btn-info px-4 mx-3 w-100" value="Editar Producto" />

            </form>

        </>
    )
}

export default ProductModify;