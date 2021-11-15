import { Button } from "reactstrap";

const Filter = ({ order, setOrder, handleChangeSearch, keyword, categories, categorySelected, handleChangeCategory, rangeOfPrice, handleChangeRangeOfPrice }) => {

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
                categories.map(category => (
                    <div className="mt-2 mb-2 pl-2" key={category.idCategoria}>
                        <div className="form-check">
                            <input type="radio" className="form-check-control" 
                                name="category-selected"
                                // eslint-disable-next-line 
                                checked={ categorySelected == category.idCategoria }
                                value={category.idCategoria}
                                onChange={handleChangeCategory}
                            />
                            <label className="form-check-label">{ category.nombre }</label>
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
                <input type="number" className="form-control" value={rangeOfPrice.minPrice} id="price-min-control" name="minPrice" onChange={handleChangeRangeOfPrice} />
                <input type="number" className="form-control" value={rangeOfPrice.maxPrice} id="price-max-control" name="maxPrice" onChange={handleChangeRangeOfPrice} />
            </div>

            <Button
                  className="btn-primary btn-round w-100" color="info" size="lg"  onClick={handleSelectChangeRangeOfPrice}>
                  Filtrar por precio
            </Button>
            
            <hr />            
        </div>
    )
}

export default Filter