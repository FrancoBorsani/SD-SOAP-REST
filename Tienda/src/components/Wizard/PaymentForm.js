import { useContext } from "react";
import { useEffect } from "react";
import { useState } from "react";
import { DataContext } from "store/GlobalState";
import { getData } from "utils/fetchData";

const PaymentForm = ({ paymentMethod, setPaymentMethod }) => {

    const { state } = useContext(DataContext);

    const { auth } = state;

    const [cards, setCards] = useState([]);

    useEffect(() => {

        const getCards = async () =>  {
            const response = await getData(`tarjeta/getTarjetasDeUsuario`, auth.token);
            setCards(response);
        }

        getCards();

    }, [auth.token])

    return (
        <div className="my-3">
            <label>Seleccione el metodo de Pago:</label>
            <select name="address" id="address" value={paymentMethod}
                className="form-control text-capitalize py-2 mt-2" onChange={e => setPaymentMethod(e.target.value)}>
                <option value="">Seleccione el metodo de Pago</option>
                {   
                    cards && cards.length > 0 && cards.map(card => (
                        <option value={card.id}>{card.numero + ' - ' +  card.tipo}</option>
                    ))
                }   
            </select>
        </div>
    )
}

export default PaymentForm;