import { useEffect } from "react";
import { useContext } from "react";
import { useState } from "react";
import { DataContext } from "store/GlobalState";
import { getData } from "utils/fetchData";
import { postData } from "utils/fetchData";

const CreditCards = () => {

    const initialState = {
        tipo: '',
        numero: '',
    }

    const [card, setCard] = useState(initialState);

    const [cards, setCards] = useState([]);

    const { state, dispatch } = useContext(DataContext);

    const { auth } = state;

    useEffect(() => {

        getData(`tarjeta/getTarjetasDeUsuario`, auth.token)
            .then(res => {
                setCards(res);
            })
            .catch(e => console.log(e))

    }, [auth.token]);

    const handleChangeInput = e => {
        const { name, value } = e.target;
        setCard({ ...card, [name]: value });
    }

    const handleSubmit = async e => {

        e.preventDefault();

        const response = await postData('tarjeta/agregar', card, auth.token);

        //if (response.error) return dispatch({ type: 'NOTIFY', payload: { error: 'Error inesperado. Por favor intente más tarde.' } })
        if (response.error) return alert(response.message)
        
        setCards([...cards, response]);

    }

    return (
        <div className="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
            <h3 className="mb-4">Tarjetas</h3>
            <div className="row">
                <div className="col-md-12">

                    {
                        cards.length === 0 && (
                            <div className="card">
                                <div className="card-body">
                                    Usted no posee tarjetas registradas.
                                </div>
                            </div>
                        )

                    }

                    <ul className="list-group w-100 mb-4">
                        {
                            cards.length > 0 &&
                            cards.map(card => (
                                <li className="list-group-item" key={card.id}>{card.numero + ' - ' +  card.tipo}</li>
                            ))
                        }
                    </ul>
                </div>
            </div>
            <div className="card">
                <form className="card-body row" onSubmit={handleSubmit}>
                    <div className="col-md-6">
                        <div className="form-group">
                            <label>Tipo</label>
                            <select className="form-control" 
                                name="tipo"
                                onChange={handleChangeInput}
                            >
                            <option value="" disabled selected hidden>Seleccione el tipo de tarjeta</option>
                            <option value="credito">Credito</option>
                            <option value="debito">Debito</option>
                            </select>
                        </div>
                    </div>
                    <div className="col-md-6">
                        <div className="form-group">
                            <label>Número de Tarjeta</label>
                            <input type="text" className="form-control"
                                name="numero"
                                value={card.numero}
                                onChange={handleChangeInput} 
                            />
                        </div>
                    </div>
                    <button className="btn btn-primary btn-round w-100 mx-3" type="submit">Agregar tarjeta</button>
                </form>
            </div>
        </div>
    )
}

export default CreditCards;