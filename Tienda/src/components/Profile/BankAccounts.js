import { useState } from "react";
import { useEffect } from "react";
import { useContext } from "react";
import { DataContext } from "store/GlobalState";

const BankAccounts = () => {

    const initialState = {
        banco: '',
        numero: '',
    }

    const [bankAccount, setBankAccount] = useState(initialState);

    const [bankAccounts, setBankAccounts] = useState([]);

    const { state, dispatch } = useContext(DataContext);

    const { auth } = state;

    useEffect(() => {

        /******** ACA TENDRIA QUE TRAER LAS CUENTAS BANCARIAS DEL VENDEDOR *******/

        /*

        getData(`tarjeta/getCuentasBancariasDelUsuario`, auth.token)
            .then(res => {
                setBankAccounts(res);
            })
            .catch(e => console.log(e)) 

        */


    }, [auth.token]);

    const handleChangeInput = e => {
        const { name, value } = e.target;
        setBankAccount({ ...bankAccount, [name]: value });
    }

    const handleSubmit = async e => {

        e.preventDefault();

        /******** ACA TENDRIA QUE GUARDAR LA NUEVA CUENTA BANCARIA DEL VENDEDOR *******/

        /*

        const response = await postData('tarjeta/agregar', card, auth.token);

        if (response.error) return alert(response.message) 

        setBankAccounts([...bankAccounts, response]); 
        
        */

    }

    return (
        <div className="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
            <h3 className="mb-4">Cuentas Bancarias</h3>
            <div className="row">
                <div className="col-md-12">

                    {
                        bankAccounts.length === 0 && (
                            <div className="card">
                                <div className="card-body">
                                    Usted no posee cuentas bancarias registradas.
                                </div>
                            </div>
                        )

                    }

                    <ul className="list-group w-100 mb-4">
                        {
                            bankAccounts.length > 0 &&
                            bankAccounts.map(bankAccount => (
                                <li className="list-group-item" key={bankAccount.id}>{bankAccount.numero + ' - ' + bankAccount.banco}</li>
                            ))
                        }
                    </ul>
                </div>
            </div>
            <div className="card">
                <form className="card-body row" onSubmit={handleSubmit}>
                    <div className="col-md-6">
                        <div className="form-group">
                            <label>Banco</label>
                            <input type="text" className="form-control"
                                name="banco"
                                value={bankAccount.banco}
                                onChange={handleChangeInput}
                            />
                        </div>
                    </div>
                    <div className="col-md-6">
                        <div className="form-group">
                            <label>Número</label>
                            <input type="text" className="form-control"
                                name="numero"
                                value={bankAccount.numero}
                                onChange={handleChangeInput}
                            />
                        </div>
                    </div>
                    <button className="btn btn-primary btn-round w-100 mx-3" type="submit">Agregar cuenta</button>
                </form>
            </div>
        </div>
    )
}

export default BankAccounts;