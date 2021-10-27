import CartItem from 'components/CartItem'
import { useContext } from 'react'
import { DataContext } from 'store/GlobalState'

const CartPage = () => {

    const { state, dispatch } = useContext(DataContext)

    const { cart } = state

    return (
        <div className="container-fluid clear-filter">
            <div className="row justify-content-center">
                <div className="col-md-8 text-secondary table-responsive my-3">
                    <h2 className="text-uppercase">Shopping Cart</h2>
                    <table className="table my-3">
                        <tbody>
                            {
                                cart.map((item) => (
                                    <CartItem key={item.id} item={item} />
                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default CartPage