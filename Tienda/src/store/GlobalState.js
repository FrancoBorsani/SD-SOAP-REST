import { createContext, useEffect, useReducer } from 'react'
import reducers from './Reducers'

export const DataContext = createContext()

export const DataProvider = ({ children }) => {

    const initialState = {
        notify: {}, auth: {}, modal: [], cart: []
    }

    const [state, dispatch] = useReducer(reducers, initialState);

    const { cart } = state;

    useEffect(() => {
        const __ecommerce__cart01 = JSON.parse(localStorage.getItem('__ecommerce__cart01'));

        if (__ecommerce__cart01) dispatch({ type: 'ADD_CART', payload: __ecommerce__cart01 });
    }, []);

    useEffect(() => {
        localStorage.setItem('__ecommerce__cart01', JSON.stringify(cart));
    }, [cart]);

    return (
        <DataContext.Provider value={{ state, dispatch }}>
            {children}
        </DataContext.Provider>
    )

}