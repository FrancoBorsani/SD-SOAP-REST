import { useEffect, useState } from "react";
import { getData } from "utils/fetchData";

const ProductsTable = () => {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        getData("productos")
            .then(res => {
                setProducts(res);
            })
            .catch(err => console.log(err));
    }, [])


    return (
        <div className="container clear-filter">
            <h3 className="text-uppercase">Tus productos</h3>
            <div className="my-3">
                <table className="table-bordered table-hover w-100 text-uppercase">

                    <thead className="bg-light font-weight-bold">
                        <tr>
                            <td className="p-2">id</td>
                            <td className="p-2">Desc. corta</td>
                            <td className="p-2">Desc. larga</td>
                            <td className="p-2">Categoria</td>
                            <td className="p-2">Stock</td>
                            <td className="p-2">Precio</td>
                        </tr>
                    </thead>

                    <tbody>
                        {
                            products.map((producto, index) => (
                                <tr key={index}>
                                    <td className="p-2">
                                        <a className="text-dark">{producto.idProducto}</a>
                                    </td>
                                    <td className="p-2">{producto.descripcionCorta}</td>
                                    <td className="p-2">{producto.descripcionLarga}</td>
                                    <td className="p-2">{producto.categoria.nombre}</td>
                                    <td className="p-2">{producto.stock}</td>
                                    <td className="p-2">${producto.precio}</td>
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