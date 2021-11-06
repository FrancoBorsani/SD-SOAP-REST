import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { DataContext } from "store/GlobalState";
import { getData } from "utils/fetchData";

const ProductsTable = () => {

    const [products, setProducts] = useState([]);

    const { state } = useContext(DataContext);

    const { auth } = state;

    useEffect(() => {
        getData(`productos/getByVendedor?idVendedor=${auth.user.id}`)
            .then(res => {
                setProducts(res);
            })
            .catch(err => console.log(err));
    }, [auth.user.id]);

    if (!auth.user.roles.includes('ROLE_ADMIN')) return null;

    return (
        <div className="container clear-filter">
            <div className="d-flex justify-content-between">
                <h3 className="text-uppercase pt-1">Mis productos</h3>
                <Link to="products/create">
                    <button className="btn btn-success"> + Agregar Producto</button>
                </Link>
            </div>
            <div className="my-3">
                <table className="table-bordered table-hover w-100 text-uppercase">

                    <thead className="bg-light font-weight-bold">
                        <tr>
                            <td className="p-2">id</td>
                            <td className="p-2">Nombre</td>
                            <td className="p-2">Descripcion</td>
                            <td className="p-2">Categoria</td>
                            <td className="p-2">Stock</td>
                            <td className="p-2">Vendido</td>
                            <td className="p-2">Precio</td>
                            <td className="p-2">Acciones</td>
                        </tr>
                    </thead>

                    <tbody>
                        {
                            products.map(producto => (
                                <tr key={producto.idProducto}>
                                    <td className="p-2">
                                        <Link className="text-dark" to={`/product/${producto.idProducto}`}>{producto.idProducto}</Link>
                                    </td>
                                    <td className="p-2">{producto.nombre}</td>
                                    <td className="p-2">{producto.descripcion}</td>
                                    <td className="p-2">{producto.categoria.nombre}</td>
                                    <td className="p-2">{producto.stock}</td>
                                    <td className="p-2">{producto.cantidadVendida}</td>
                                    <td className="p-2">${producto.precio}</td>
                                    <td className="p-2">
                                        <Link className="text-dark" to={`products/edit/${producto.idProducto}`}>Editar</Link>
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>

                </table>
            </div>
        </div>
    )
}

export default ProductsTable;