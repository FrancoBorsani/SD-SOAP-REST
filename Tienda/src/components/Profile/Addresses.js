import { useContext } from "react";
import { useEffect } from "react";
import { useState } from "react";
import { DataContext } from "store/GlobalState";
import { getData } from "utils/fetchData";
import { postData } from "utils/fetchData";

const Adresses = () => {

    const initialState = {
        calle: '',
        numero: '',
        codigoPostal: '',
        provincia: '',
    }

    const [address, setAddress] = useState(initialState);

    const [addresses, setAddresses] = useState([]);

    const { state, dispatch } = useContext(DataContext);

    const { auth } = state;

    useEffect(() => {

        getData(`direccion/getDomiciliosDeUsuario`, auth.token)
            .then(res => {
                setAddresses(res);
            })
            .catch(e => console.log(e))

    }, [auth.token]);

    const handleChangeInput = e => {
        const { name, value } = e.target;
        setAddress({ ...address, [name]: value });
    }

    const handleSubmit = async e => {
        e.preventDefault();

        const response = await postData('direccion/agregar', address, auth.token);

        if (response.error) return dispatch({ type: 'NOTIFY', payload: { error: 'Error inesperado. Por favor intente más tarde.' } })
        
        setAddresses([...addresses, response]);

    }

    return (
        <div className="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
            <h3 className="mb-3">Direcciones</h3>
            
            <div className="row">
                <div className="col-md-12">

                    {
                        addresses.length === 0 && (
                            <div className="card">
                                <div className="card-body">
                                    Usted no posee direcciones registradas.
                                </div>
                            </div>
                        )

                    }

                    <ul className="list-group w-100 mb-4">
                        {
                            addresses.map(address => (
                                <li className="list-group-item" key={address.id}> { address.calle + " " + address.numero + ", " + address.codigoPostal + " " + address.provincia } </li>
                            ))
                        }
                    </ul>
                </div>
            </div>
            <div className="card">
                <form className="card-body row" onSubmit={handleSubmit}>
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>Calle</label>
                            <input type="text" className="form-control" 
                                name="calle"
                                value={address.calle}
                                onChange={handleChangeInput}
                                required
                            />
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>Número</label>
                            <input type="number" className="form-control" 
                                name="numero"
                                value={address.numero}
                                onChange={handleChangeInput}
                                required
                            />
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>Código Postal</label>
                            <input type="text" className="form-control" 
                                name="codigoPostal"
                                value={address.codigoPostal}
                                onChange={handleChangeInput}
                                required
                            />
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>Provincia</label>
                            <input type="text" className="form-control" 
                                name="provincia"
                                value={address.provincia}
                                onChange={handleChangeInput}
                                required
                            />
                        </div>
                    </div>
                    <button className="btn btn-primary btn-round w-100 mx-3" type="submit">Agregar dirección</button>
                </form>
            </div>
        </div>
    )
}

export default Adresses;