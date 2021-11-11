import { useEffect } from "react";
import { useState } from "react";
import { getData } from "utils/fetchData";

const ClaimCreate = () => {

    const [products, setProducts] = useState([]);

    const [claim, setClaim] = useState({
        idCompra: '',
        reclamo: ''
    });

    useEffect(() => {

        getData("productos")
            .then(res => {
                setProducts(res);
            })
            .catch(err => console.log(err));

    }, []);

    const handleChangeInput = e => {
        const { name, value } = e.target
        setClaim({ ...claim, [name]: value })
    }

    const handleSubmit = async e => {
        e.preventDefault();

        const response = await fetch(`http://localhost:8083/reclamo`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(claim),
        })

        const data = await response.json();

        console.log(data);

    }

    return (
        <div className="row justify-content-center">
            <div className="col-md-10">
                <div className="card">
                    <div className="card-header pt-3 pl-3">
                        Inicar Reclamo.
                    </div>
                    <hr />
                    <div className="card-body">
                        <form onSubmit={handleSubmit}>
                            <div className="form-group">
                                <label>Seleccione el producto sobre cual quiere hacer el reclamo:</label>
                                <select name="idCompra" value={claim.idCompra}
                                    className="form-control text-capitalize py-2 mt-2" onChange={handleChangeInput}>
                                    <option value="">Seleccione un producto</option>
                                    {
                                        products.map(product => (
                                            <option value={product.id}>{ product.descripcion }</option>
                                        ))
                                    }
                                </select>
                            </div>

                            <textarea className="form-control" name="reclamo" cols="30" rows="3"
                                placeholder="Ingrese su reclamo" value={claim.reclamo} onChange={handleChangeInput}
                            />

                            <div className="btn btn-primary w-100">
                                Realizar reclamo
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ClaimCreate;