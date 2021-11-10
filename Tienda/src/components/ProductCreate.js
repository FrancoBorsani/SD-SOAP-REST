import { useContext, useEffect, useState } from "react";
import { DataContext } from "store/GlobalState";
import { getData } from "utils/fetchData";

const ProductCreate = () => {
    
    const initialState = {
        title: '',
        price: 0,
        inStock: 0,
        description: '',
        category: ''
    }

    const [product, setProduct] = useState(initialState);

    const { title, price, inStock, description, category } = product;

    const [categories, setCategories] = useState([]);

    const paymentMethods = [
        { name: 'Débito' },
        { name: 'Crédito' },
        { name: 'Ambos' }
    ]

    const { dispatch } = useContext(DataContext);

    useEffect(() => {

        getData("categorias")
        .then(res => {
          setCategories(res);
        })
        .catch(err => console.log(err));
    
      }, [])

    const handleChangeInput = e => {
        const { name, value } = e.target;
        setProduct({ ...product, [name]: value });
    }

    const handleSubmit = async (e) => {

        e.preventDefault();

        if (!title || !price || !inStock || !description || !category === 'all')
            return dispatch({ type: 'NOTIFY', payload: { error: 'Please add all fields.' } });

        /*

        dispatch({ type: 'NOTIFY', payload: { loading: true } })

        const res = await postData('product', product)

        if (res.error) return dispatch({ type: 'NOTIFY', payload: { error: res.err } })

        return dispatch({ type: 'NOTIFY', payload: { success: res.msg } }) */

    }

    return (
        <>
            <div className="row mb-3">
                <div className="card-body shadow">
                    <i class="fas fa-plus-circle"></i> Agregar Producto
                </div>
            </div>

            <form className="row shadow p-3 my-0" onSubmit={handleSubmit}>

                <div className="col-md-12">
                    <div className="form-group">
                        <label>Nombre</label>
                        <input type="text" name="title" value={title}
                            placeholder="Nombre" className="form-control w-100"
                            onChange={handleChangeInput} 
                        />
                    </div>
                    <div className="row">
                        <div className="col-md-6">
                            <div className="form-group">
                                <label>Precio</label>
                                <input type="number" name="price" value={price}
                                    placeholder="Price" className="form-control w-100"
                                    onChange={handleChangeInput} 
                                />
                            </div>
                        </div>

                        <div className="col-md-6">
                            <div className="form-group">
                                <label>Stock</label>
                                <input type="number" name="inStock" value={inStock}
                                    placeholder="In Stock" className="form-control w-100"
                                    onChange={handleChangeInput} 
                                />
                            </div>
                        </div>
                    </div>

                    <textarea className="form-control" name="description" id="description" cols="30" rows="2"
                        placeholder="Description" value={description} onChange={handleChangeInput}
                    />

                    <div className="row">
                        <div className="col-md-6">
                            <div className="input-group-prepend my-3">
                                <select name="category" id="category" value={category}
                                    onChange={handleChangeInput} className="form-control text-capitalize py-2">
                                    <option value="all">All products</option>
                                    {
                                        categories.map(category => (
                                            <option value={category.id} key={category.id}>{category.nombre}</option>
                                        ))
                                    }
                                </select>
                            </div>
                        </div>

                        <div className="col-md-6">
                            <div className="input-group-prepend my-3">
                                <select name="category" id="category" value={category}
                                    onChange={handleChangeInput} className="form-control text-capitalize py-2">
                                    {
                                        paymentMethods.map((paymentMethod, index) => (
                                            <option value={paymentMethod.name} key={index}>{paymentMethod.name}</option>
                                        ))
                                    }
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="submit" className="btn btn-info px-4 mx-3 w-100" value="Agregar Producto" />

            </form>

        </>
    )
}

export default ProductCreate;