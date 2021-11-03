import { useState } from "react"

const OrdersTable = () => {
    const [orders] = useState([
        {
            id: '123e4567-e89b-12d3-a456-426614174000',
            createdAt: Date.now(),
            total: '4000',
            delivered: true,
            paid: true
        },
        {
            id: '123e4567-e89b-12d3-a456-426614174001',
            createdAt: Date.now(),
            total: '7000',
            delivered: false,
            paid: true
        },
        {
            id: '123e4567-e89b-12d3-a456-426614174002',
            createdAt: Date.now(),
            total: '1500',
            delivered: true,
            paid: false
        },
    ])

    return (
        <div className="container clear-filter">
            <h3 className="text-uppercase">Orders</h3>
            <div className="my-3">
                <table className="table-bordered table-hover w-100 text-uppercase">

                    <thead className="bg-light font-weight-bold">
                        <tr>
                            <td className="p-2">id</td>
                            <td className="p-2">Fecha</td>
                            <td className="p-2">total</td>
                            <td className="p-2">entregado</td>
                            <td className="p-2">pagado</td>
                        </tr>
                    </thead>

                    <tbody>
                        {
                            orders.map((order, index) => (
                                <tr key={index}>
                                    <td className="p-2">
                                        <a className="text-dark">{order.id}</a>
                                    </td>
                                    <td className="p-2">{new Date(order.createdAt).toLocaleDateString()}</td>
                                    <td className="p-2">${order.total}</td>
                                    <td className="p-2">
                                        {
                                            order.delivered
                                                ? <i className="fas fa-check text-success"></i>
                                                : <i className="fas fa-times text-danger"></i>
                                        }
                                    </td>
                                    <td className="p-2">
                                        {
                                            order.paid
                                                ? <i className="fas fa-check text-success"></i>
                                                : <i className="fas fa-times text-danger"></i>
                                        }
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>

                </table>
            </div>
        </div>
    )
}

export default OrdersTable