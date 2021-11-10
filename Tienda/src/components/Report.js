import { useState } from "react";
import { Button, Form, Modal, ModalBody, ModalHeader } from "reactstrap";

const Report = () => {

    const [showModal, setShowModal] = useState(false);

    const reportsTypes = ['Falsificación', 'Producto ilegal', 'Fraude', 'Contenido inapropiado'];

    const handleSubmit = e => {
        e.preventDefault();
    }

    return (
        <>

            <span onClick={() => setShowModal(true)} style={{ textDecorationLine: 'underline' }}>Denunciar</span>

            <Modal isOpen={showModal} fade={true} toggle={() => setShowModal(false)}>
                <Form onSubmit={handleSubmit}>
                    <ModalHeader toggle={() => setShowModal(false)}>Denunciar publicación</ModalHeader>
                    <ModalBody>

                            <div className="form-group">
                                <label>Seleccione el tipo de denuncia:</label>
                                <select name="categoria" required
                                    className="form-control rounded text-capitalize py-2 mt-2">
                                    <option value="">Seleccione tipo de denuncia</option>
                                    {
                                        reportsTypes.map(reportType => (
                                            <option key={reportType} value={reportType}>{reportType}</option>
                                        ))
                                    }
                                </select>
                            </div>
                            <textarea className="form-control mb-2" name="reclamo" cols="30" rows="3"
                                    placeholder="Ingrese su denuncia" required
                                />

                            <Button className="w-100" color="info" type="submit">Denunciar publicación</Button>
                    </ModalBody>
                </Form>
            </Modal>
        </>
    )
}

export default Report;