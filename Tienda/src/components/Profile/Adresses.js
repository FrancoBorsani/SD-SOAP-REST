const Adresses = () => {
    return (
        <div className="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
            <h3 className="mb-3">Direcciones</h3>
            <div className="row">
                <div className="col-md-12">
                    <ul class="list-group w-100 mb-4">
                        <li class="list-group-item">29 de Septiembre 3901, B1832 Remedios de Escalada, Provincia de Buenos Aires</li>
                    </ul>
                </div>
            </div>
            <div className="card">
                <div className="card-body row">
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>Calle</label>
                            <input type="text" className="form-control" />
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>Número</label>
                            <input type="number" className="form-control" />
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>Código Postal</label>
                            <input type="text" className="form-control" />
                        </div>
                    </div>
                    <div className="col-md-3">
                        <div className="form-group">
                            <label>Provincia</label>
                            <input type="text" className="form-control" />
                        </div>
                    </div>
                    <button className="btn btn-primary btn-round w-100 mx-3">Agregar dirección</button>
                </div>
            </div>
        </div>
    )
}

export default Adresses