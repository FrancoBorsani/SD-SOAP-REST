import CartItem from "components/CartItem";
import AddressForm from "components/Wizard/AddressForm";
import PaymentForm from "components/Wizard/PaymentForm";
import { useContext, useState } from "react";
import { useHistory } from "react-router";
import { DataContext } from "store/GlobalState";
import { postData } from "utils/fetchData";

const Wizard = () => {

    const steps = ["Dirección de entrega", "Forma de pago", "Confirmar Pedido"];
    const [activeStep, setActiveStep] = useState(0);

    const [address, setAddress] = useState("");

    const [paymentMethod, setPaymentMethod] = useState("");

    const [paymentMethodInfo, setPaymentMethodInfo] = useState("");

    const { state, dispatch } = useContext(DataContext);

    const { cart, auth } = state;

    const router = useHistory();

    const handleNext = () => {
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
        if (activeStep === steps.length - 1) {
            handleOrder();
        }
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    const handleOrder = async () => {

        const total = cart.reduce((prev, item) => {
            return prev + (item.precio * item.cantidad);
        }, 0);

        const listaItems = [];

        cart.forEach(item => {
            listaItems.push({ producto: item, cantidad: item.cantidad });
        });

        const order = {
            listaItems: listaItems,
            total: total,
            vendedor: cart[0].vendedor,
            idTarjetaUsada: paymentMethod
        }

        const response = await postData(`pedido/agregar`, order, auth.token);

        if(response.error) return alert('Error inesperado. Por favor intente más tarde.');

        dispatch({ type: 'ADD_CART', payload: [] });

        router.push('/my/orders');

    }

    function getStepContent(stepIndex) {
        switch (stepIndex) {
            case 0:
                return (
                    <AddressForm address={address} setAddress={setAddress} />
                );
            case 1:
                return (
                    <PaymentForm paymentMethod={paymentMethod} setPaymentMethod={setPaymentMethod} setPaymentMethodInfo={setPaymentMethodInfo} />
                );
            case 2:
                return (
                    <>
                    <h4 className="mt-1">Resumen</h4>
                    <hr />
                    <div className="row justify-content-center" style={{ overflowY: 'scroll', maxHeight: '200px' }}>
                        <div className="col-md-8">
                            {
                                cart.map(item => (
                                    <CartItem isCheckout item={item} />
                                ))
                            }
                        </div>
                        <div className="col-md-3">
                            <p>Dirección: {address}</p>
                            <p>Metodo de pago: {paymentMethodInfo}</p>
                        </div>
                    </div>
                    </>
                );
            default:
                return "Unknown step";
        }
    }

    return (
        <div>
            <div className="row justify-content-center p-2">
                <div className="card">
                    <div className="card-body">

                        <div className="row d-flex justify-content-center">
                            <div className="col-12">
                                <ul id="progressbar" className="text-center">
                                    <li className="step1 active"></li>
                                    <li className={`step3 ${activeStep === 1 || activeStep === 2 ? 'active' : ''}`}></li>
                                    <li className={`step3 ${activeStep === 2 ? 'active' : ''}`}></li>
                                </ul>
                            </div>
                        </div>


                        {getStepContent(activeStep)}

                        <div className="d-flex justify-content-between">
                            <button className="btn btn-dark" disabled={activeStep === 0} onClick={handleBack}>
                                Volver
                            </button>
                            <button
                                disabled={
                                    (activeStep === 0 && !address) ||
                                    (activeStep === 1 && !paymentMethod)
                                }
                                className="btn btn-dark py-2"
                                onClick={handleNext}
                            >
                                {
                                    activeStep === steps.length - 1 ? "Confirmar compra" : "Siguiente"
                                }
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Wizard;