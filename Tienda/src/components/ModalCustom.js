import React, { useContext } from "react";
import { Button, Modal, ModalBody, ModalFooter, ModalHeader } from "reactstrap";
import { deleteItem } from "store/Actions";
import { DataContext } from "store/GlobalState";

const ModalCustom = () => {

    const { state, dispatch } = useContext(DataContext);

    const { modal } = state;

    const handleSubmit = () => {

        if(modal.length !== 0) {

            for(const item of modal) {

                if (item.type === 'ADD_CART') dispatch(deleteItem(item.data, item.id, item.type))

                dispatch({ type: 'ADD_MODAL', payload: [] })

            }

        }

    };

    const handleClose = () => {
        dispatch({ type: 'ADD_MODAL', payload: [] })
    };

    return (
        <Modal isOpen={modal.length !== 0 ? true : false} toggle={handleClose}>
            <ModalHeader toggle={handleClose}>
                {modal.length !== 0 && modal[0].title}
            </ModalHeader>
            <ModalBody>
                Deseas eliminar este elemento?
            </ModalBody>
            <ModalFooter>
                <Button className="w-100"color="danger" onClick={handleSubmit}>
                    Eliminar
                </Button>
            </ModalFooter>
        </Modal>
    );
}

export default ModalCustom;