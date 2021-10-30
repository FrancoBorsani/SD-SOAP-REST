const ChangePassword = () => {
    return (
        <div className="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
            <h3 className="mb-4">Password Settings</h3>
            <div className="row">
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Old password</label>
                        <input type="password" className="form-control" />
                    </div>
                </div>
            </div>
            <div className="row">
                <div className="col-md-6">
                    <div className="form-group">
                        <label>New password</label>
                        <input type="password" className="form-control" />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Confirm new password</label>
                        <input type="password" className="form-control" />
                    </div>
                </div>
            </div>
            <div>
                <button className="btn btn-primary">Update</button>
                <button className="btn btn-light">Cancel</button>
            </div>
        </div>
    )
}

export default ChangePassword