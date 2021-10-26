export const addItemToCart = (data) => dispach => {
    dispach({
        type : 'addItemToCart',
        payload : data
    })
 }