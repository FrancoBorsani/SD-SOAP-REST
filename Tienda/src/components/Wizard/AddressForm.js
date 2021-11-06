const AddressForm = ({ address, setAddress }) => {
    return (
        <div className="my-3">
            <label>Seleccione la dirección adonde se enviara su pedido:</label>
            <select name="address" id="address" value={address}
                className="form-control text-capitalize py-2 mt-2" onChange={e=> setAddress(e.target.value)}>
                <option value="" selected>Seleccione una dirección adonde se enviara su pedido</option>
                <option value="Direccion1">Direccion1</option>
                <option value="Direccion2">Direccion2</option>
            </select>
        </div>
    )
}

export default AddressForm;