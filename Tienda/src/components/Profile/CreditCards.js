const CreditCards = () => {
    return (
        <div className="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
            <h3 className="mb-4">Tarjetas</h3>
            <div className="row">
                <div className="col-md-12">
                    <ul class="list-group w-100 mb-4">
                        <li class="list-group-item active">XXXXXXXXXXXX-8181 - Visa Crédito</li>
                        <li class="list-group-item">XXXXXXXXXXXX-8182 - MasterCard</li>
                    </ul>
                </div>
            </div>
            <div className="card">
                <div className="card-body row">
                    <div className="col-md-6">
                        <div className="form-group">
                            <label>Tipo</label>
                            <input type="text" className="form-control"  />
                        </div>
                    </div>
                    <div className="col-md-6">
                        <div className="form-group">
                            <label>Número de Tarjeta</label>
                            <input type="text" className="form-control" />
                        </div>
                    </div>
                    <button className="btn btn-primary btn-round w-100 mx-3">Agregar tarjeta</button>
                </div>
            </div>
        </div>
    )
}

export default CreditCards