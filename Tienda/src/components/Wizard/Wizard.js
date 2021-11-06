import CartItem from "components/CartItem";
import AddressForm from "components/Wizard/AddressForm";
import PaymentForm from "components/Wizard/PaymentForm";
import { useContext, useState } from "react";
import { DataContext } from "store/GlobalState";

const Wizard = () => {

    const steps = ["Dirección de entrega", "Forma de pago", "¡Ultimo paso!"];
    const [activeStep, setActiveStep] = useState(0);

    const [address, setAddress] = useState("");

    const [paymentMethod, setPaymentMethod] = useState("");

    const { state } = useContext(DataContext);

    const { cart } = state;

    const handleNext = () => {
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
        if (activeStep === steps.length - 1) {
            alert('Checkout Finished!');
        }
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    function getStepContent(stepIndex) {
        switch (stepIndex) {
            case 0:
                return (
                    <AddressForm address={address} setAddress={setAddress} />
                );
            case 1:
                return (
                    <PaymentForm paymentMethod={paymentMethod} setPaymentMethod={setPaymentMethod} />
                );
            case 2:
                return (
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
                            <p>Metodo de pago: {paymentMethod}</p>
                        </div>
                    </div>
                );
            default:
                return "Unknown stepIndex";
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
                                className="btn btn-dark"
                                onClick={handleNext}
                            >
                                {
                                    activeStep === steps.length - 1 ? "Finalizar" : "Siguiente"
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