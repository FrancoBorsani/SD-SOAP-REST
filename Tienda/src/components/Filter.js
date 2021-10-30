const Filter = () => {
    return (
        <div className="col-md-4 col-lg-3 mt-3">
            <h6 className="text-uppercase font-weight-bold mt-0 mb-3">Categorias</h6>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="category-1" />
                    <label className="custom-control-label" for="category-1">Accessories</label>
                </div>
            </div>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="category-2" />
                    <label className="custom-control-label" for="category-2">Coats &amp; Jackets</label>
                </div>
            </div>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="category-3" />
                    <label className="custom-control-label" for="category-3">Hoodies &amp; Sweatshirts</label>
                </div>
            </div>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="category-4" />
                    <label className="custom-control-label" for="category-4">Jeans</label>
                </div>
            </div>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="category-5" />
                    <label className="custom-control-label" for="category-5">Shirts</label>
                </div>
            </div>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="category-6" />
                    <label className="custom-control-label" for="category-6">Underwear</label>
                </div>
            </div>
            <hr />            
            <h6 className="text-uppercase mt-5 mb-3 font-weight-bold">Size</h6>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="filter-size-1" />
                    <label className="custom-control-label" for="filter-size-1">X-Small</label>
                </div>
            </div>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="filter-size-2" />
                    <label className="custom-control-label" for="filter-size-2">Small</label>
                </div>
            </div>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="filter-size-3" />
                    <label className="custom-control-label" for="filter-size-3">Medium</label>
                </div>
            </div>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="filter-size-4" />
                    <label className="custom-control-label" for="filter-size-4">Large</label>
                </div>
            </div>
            <div className="mt-2 mb-2 pl-2">
                <div className="custom-control custom-checkbox">
                    <input type="checkbox" className="custom-control-input" id="filter-size-5" />
                    <label className="custom-control-label" for="filter-size-5">X-Large</label>
                </div>
            </div>
            <hr />            
            <h6 className="text-uppercase mt-5 mb-3 font-weight-bold">Precio</h6>
            <div className="d-flex">
                <input type="number" className="form-control" value="50" id="price-min-control" />
                <input type="number" className="form-control" value="150" id="price-max-control" />
            </div>
            <input type="range" className="form-control mt-1" />
            <hr />            
            <button className="btn btn-lg btn-block btn-primary mt-5">Actualizar resultados</button>
        </div>
    )
}

export default Filter