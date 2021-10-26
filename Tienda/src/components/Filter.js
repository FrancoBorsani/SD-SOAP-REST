const Filter = () => {
    return (
        <div className="input-group">
            <div className="input-group-prepend col-md-2 px-0 mt-2">
                <select className="custom-select text-capitalize"
                    style={{ borderRadius: '5px 0px 0px 5px' }}>
                    <option value="all">All Products</option>
                </select>
            </div>

            <form autoComplete="off" className="mt-2 col-md-8 px-0">
                <input type="text" className="form-control" list="title_product" style={{ borderRadius: '0px 0px 0px 0px' }} />
                
                <datalist id="title_product">
                </datalist>

                <button className="position-absolute btn btn-info" type="submit"
                    style={{ top: 0, right: 0, visibility: 'hidden' }}>
                    Search
                </button>
            </form>

            <div className="input-group-prepend col-md-2 px-0 mt-2">
                <select className="custom-select text-capitalize"
                    style={{ borderRadius: '0px 5px 5px 0px' }}>
                    <option value="-createdAt">Newest</option>
                    <option value="oldest">Oldest</option>
                    <option value="-sold">Best sales</option>
                    <option value="-price">Price: High-Low</option>
                    <option value="price">Price: Low-High</option>
                </select>
            </div>

        </div>
    )
}

export default Filter