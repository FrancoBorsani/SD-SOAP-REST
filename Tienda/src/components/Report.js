import { useState } from "react";
import { Button, Form, Modal, ModalBody, ModalHeader } from "reactstrap";

const Report = () => {

    const [showModal, setShowModal] = useState(false);

    const [report, setReport] = useState({
        idProducto: '',
        denuncia: '',
        categoria: ''
    })

    const reportsTypes = ['Falsificación', 'Producto ilegal', 'Fraude', 'Contenido inapropiado'];

    const handleChangeInput = e => {
        const { name, value } = e.target;
        setReport({ ...report, [name]: value });
    }

    const handleSubmit = async e => {

        e.preventDefault();

        const response = await fetch(`http://localhost:8083/denuncia`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(report),
        })

        const data = await response.json();

        console.log(data);

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
                                <select name="categoria" value={report.categoria} required
                                    className="form-control rounded text-capitalize py-2 mt-2"
                                    onChange={handleChangeInput}>
                                    <option value="">Seleccione tipo de denuncia</option>
                                    {
                                        reportsTypes.map(reportType => (
                                            <option key={reportType} value={reportType}>{reportType}</option>
                                        ))
                                    }
                                </select>
                            </div>
                            <textarea className="form-control mb-2" name="denuncia" cols="30" rows="3"
                                value={report.denuncia} placeholder="Ingrese su denuncia" required
                                onChange={handleChangeInput}
                            />

                            <Button className="w-100" color="info" type="submit">Denunciar publicación</Button>
                    </ModalBody>
                </Form>
            </Modal>
        </>
    )
}

export default Report;