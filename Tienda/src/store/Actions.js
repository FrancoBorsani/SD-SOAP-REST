export const ACTIONS = {
    NOTIFY: 'NOTIFY',
    AUTH: 'AUTH',
    ADD_CART: 'ADD_CART',
    ADD_MODAL: 'ADD_MODAL',
    ADD_ORDERS: 'ADD_ORDERS',
}


export const addToCart = (producto, cart) => {
    if (producto.stock === 0)
        return ({ type: 'NOTIFY', payload: { error: 'No hay stock de este producto.' } })

    const check = cart.every(item => {
        return item.idProducto !== producto.idProducto
    })

    if (!check)
        return ({ type: 'NOTIFY', payload: { error: 'Este producto ya se encuentra en el carrito.' } })

    return ({ type: 'ADD_CART', payload: [...cart, { ...producto, cantidad: 1 }] })

}

export const decrease = (data, id) => {
    const newData = [...data]

    newData.forEach(item => {
        if (item.idProducto === id) item.cantidad -= 1
    })

    return ({ type: 'ADD_CART', payload: newData })
}

export const increase = (data, id) => {
    const newData = [...data]

    newData.forEach(item => {
        if (item.idProducto === id) item.cantidad += 1
    })

    return ({ type: 'ADD_CART', payload: newData })
}

export const deleteItem = (data, id, type) => {
    const newData = data.filter(item => item.idProducto !== id)

    return ({ type: type, payload: newData })
}

export const updateItem = (data, id, post, type) => {
    const newData = data.map(item => (item._id === id ? post : item))

    return ({ type, payload: newData })
}

