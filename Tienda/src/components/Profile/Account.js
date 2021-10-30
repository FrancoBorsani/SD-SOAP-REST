const Account = ({ auth }) => {
    return (
        <div className="tab-pane fade show active" id="account" role="tabpanel" aria-labelledby="account-tab">
            <h3 className="mb-4">Account Settings</h3>
            <div className="row">
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Nombre</label>
                        <input type="text" className="form-control" value={auth.user.nombre} />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Apellido</label>
                        <input type="text" className="form-control" value={auth.user.apellido} />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Email</label>
                        <input type="text" className="form-control" value={auth.user.username} />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Email</label>
                        <input type="text" className="form-control" value={auth.user.email} />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Documento</label>
                        <input type="text" className="form-control" value={auth.user.dni} />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Telefono</label>
                        <input type="text" className="form-control" value="42269863" />
                    </div>
                </div>
            </div>
            <div>
                <button className="btn btn-primary btn-round w-100">Guardar cambios</button>
            </div>
        </div>
    )
}

export default Account