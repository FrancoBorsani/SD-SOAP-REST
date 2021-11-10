import { useContext } from "react";
import { useEffect } from "react";
import { useState } from "react";
import { DataContext } from "store/GlobalState";
import { getData } from "utils/fetchData";

const AddressForm = ({ address, setAddress }) => {

    const { state } = useContext(DataContext);

    const { auth } = state;

    const [addresses, setAddresses] = useState([]);

    useEffect(() => {

        const getAddresses = async () =>  {
            const response = await getData(`direccion/getDomiciliosDeUsuario`, auth.token);
            setAddresses(response);
        }

        getAddresses();

    }, [auth.token])

    return (
        <div className="my-3">
            <label>Seleccione la dirección adonde se enviara su pedido:</label>
            <select name="address" id="address" value={address}
                className="form-control text-capitalize py-2 mt-2" onChange={e=> setAddress(e.target.value)}>
                <option value="">Seleccione una dirección adonde se enviara su pedido</option>
                {
                    addresses.map(address => (
                        <option value={address.id}>{ address.calle + " " + address.numero + ", " + address.codigoPostal + " " + address.provincia }</option>
                    ))
                }
                
            </select>
        </div>
    )
}

export default AddressForm;