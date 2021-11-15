import { useEffect } from "react";
import { useContext } from "react";
import { useState } from "react";
import { DataContext } from "store/GlobalState";
import { postData } from "utils/fetchData";

const Account = () => {

    const initialState = {
        nombre: '', apellido: '', username: '', email: '', dni: '', telefono: ''
    };

    const [userData, setUserData] = useState(initialState);

    const [message, setMessage] = useState('');

    const { state, dispatch } = useContext(DataContext);

    const { auth } = state;

    useEffect(() => {
        setUserData(auth.user);
    }, [auth.user]);

    const handleChangeInput = e => {
        const { name, value } = e.target
        setUserData({ ...userData, [name]: value })
    }

    const handleSubmit = async e => {
        e.preventDefault();

        const response = await postData('registro/update', userData, auth.token);

        if(response.error) alert('Error inesperado. Por favor intente más tarde.');

        setUserData(response);

        setMessage('Sus datos han sido actualizados correctamente.');
        
        dispatch({
            type: 'AUTH', payload: {
                token: auth.token,
                user: response
            }
        })
        
    }

    return (
        <div className="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
            <h3 className="mb-4">Configuración de cuenta</h3>
            {
                message && (
                    <div className="row">
                        <div className="col-md-12">
                            <div className="alert alert-info rounded">
                                { message }
                            </div>
                        </div>
                    </div>
                )
            }
            <form className="row" onSubmit={handleSubmit}>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Nombre</label>
                        <input type="text" 
                            className="form-control" 
                            name="nombre" 
                            value={userData?.nombre} 
                            onChange={handleChangeInput}
                        />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Apellido</label>
                        <input type="text" 
                            className="form-control" 
                            name="apellido" 
                            value={userData?.apellido} 
                            onChange={handleChangeInput}
                        />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Username</label>
                        <input type="text" 
                            className="form-control" 
                            name="username" 
                            value={userData?.username} 
                            onChange={handleChangeInput}
                        />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Email</label>
                        <input type="text" 
                            className="form-control" 
                            name="email" 
                            value={userData?.email} 
                            onChange={handleChangeInput}
                        />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Documento</label>
                        <input type="text" 
                            className="form-control" 
                            name="dni" 
                            value={userData?.dni} 
                            onChange={handleChangeInput}
                        />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Telefono</label>
                        <input type="text" 
                            className="form-control"
                            name="telefono" 
                            value={userData?.telefono} 
                            onChange={handleChangeInput}
                        />
                    </div>
                </div>

                <div className="col-md-12">
                    <button className="btn btn-primary btn-round w-100" type="submit">Guardar cambios</button>
                </div>
            </form>
        </div>
    )
}

export default Account