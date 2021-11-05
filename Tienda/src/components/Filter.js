const Filter = ({ order, setOrder, handleChangeSearch, keyword, categories, categorySelected, handleChangeCategory }) => {

    return (
        <div className="col-md-4 col-lg-3 mt-3">
            <h6 className="text-uppercase font-weight-bold mt-0 mb-3">Nombre del producto</h6>
            <input type="text" className="form-control" name={keyword} placeholder="Buscar..." onChange={e => handleChangeSearch(e)} />
            <hr />
            <h6 className="text-uppercase font-weight-bold mt-0 mb-3">Categorias</h6>

            <div className="mt-2 mb-2 pl-2">
                <div className="form-check">
                    <input type="radio" className="form-check-control" 
                        name="category-selected"
                        checked={ categorySelected === "" }
                        value=""
                        onChange={handleChangeCategory}
                    />
                    <label className="form-check-label">Todas las categorias</label>
                </div>
            </div>

            {
                categories.map(c => (
                    <div className="mt-2 mb-2 pl-2">
                        <div className="form-check">
                            <input type="radio" className="form-check-control" 
                                name="category-selected"
                                checked={ categorySelected == c.idCategoria }
                                value={c.idCategoria}
                                onChange={handleChangeCategory}
                            />
                            <label className="form-check-label">{ c.nombre }</label>
                        </div>
                    </div>
                ))
            }

            <hr />            
            <h6 className="text-uppercase mb-3 font-weight-bold">Orden</h6>
            <div className="mt-2 mb-2 pl-2">
                <div className="form-check">
                    <input type="radio" className="form-check-control" 
                        name="order" checked={ order === 'Precio ascendente' ? true : false } 
                        onChange={() => setOrder('Precio ascendente')}
                    />
                    <label className="form-check-label">Precio Ascendente</label>
                </div>
            </div>
            <div className="mt-2 mb-2 pl-2">
                <div className="form-check">
                    <input type="radio" className="form-check-control" 
                        name="order" checked={order === 'Precio descendente' ? true : false} 
                        onChange={() => setOrder('Precio descendente')} 
                    />
                    <label className="form-check-label">Precio Descendente</label>
                </div>
            </div>
            <hr />            
            <h6 className="text-uppercase mb-3 font-weight-bold">Precio</h6>
            <div className="d-flex">
                <input type="number" className="form-control" value="50" id="price-min-control" />
                <input type="number" className="form-control" value="150" id="price-max-control" />
            </div>
            <input type="range" className="form-control mt-1" />
            <hr />            
        </div>
    )
}

export default Filter