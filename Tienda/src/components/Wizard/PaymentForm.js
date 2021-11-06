const PaymentForm = ({ paymentMethod, setPaymentMethod }) => {
    return (
        <div className="my-3">
            <label>Seleccione el metodo de Pago:</label>
            <select name="address" id="address" value={paymentMethod}
                className="form-control text-capitalize py-2 mt-2" onChange={e => setPaymentMethod(e.target.value)}>
                <option value="" selected>Seleccione el metodo de Pago</option>
                <option value="Débito">Débito</option>
                <option value="Crédito">Crédito</option>
            </select>
        </div>
    )
}

export default PaymentForm;