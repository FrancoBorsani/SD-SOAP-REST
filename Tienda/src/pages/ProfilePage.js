import { useContext } from "react";
import { DataContext } from "store/GlobalState";

const ProfilePage = () => {

    const { state } = useContext(DataContext);

    const { auth } = state;

    return (
        <div className="container">
            <div className="bg-white shadow rounded-lg d-block d-sm-flex">
                <div className="profile-tab-nav border-right">
                    <div className="p-4">
                        <div className="img-circle text-center mb-3">
                            <img src="https://st.depositphotos.com/2101611/3925/v/600/depositphotos_39258143-stock-illustration-businessman-avatar-profile-picture.jpg" alt="profile-photo" className="shadow"/>
                        </div>
                        <h4 className="text-center text-capitalize">{ auth.user.username }</h4>
                    </div>
                    <div className="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                        <a className="nav-link active" id="account-tab" data-toggle="pill" href="#account" role="tab" aria-controls="account" aria-selected="true">
                            <i className="fa fa-home text-center mr-1"></i>
                            Account
                        </a>
                        <a className="nav-link" id="password-tab" data-toggle="pill" href="#password" role="tab" aria-controls="password" aria-selected="false">
                            <i className="fa fa-key text-center mr-1"></i>
                            Password
                        </a>
                        <a className="nav-link" id="security-tab" data-toggle="pill" href="#security" role="tab" aria-controls="security" aria-selected="false">
                            <i className="fa fa-user text-center mr-1"></i>
                            Security
                        </a>
                        <a className="nav-link" id="application-tab" data-toggle="pill" href="#application" role="tab" aria-controls="application" aria-selected="false">
                            <i className="fa fa-tv text-center mr-1"></i>
                            Application
                        </a>
                        <a className="nav-link" id="notification-tab" data-toggle="pill" href="#notification" role="tab" aria-controls="notification" aria-selected="false">
                            <i className="fa fa-bell text-center mr-1"></i>
                            Notification
                        </a>
                    </div>
                </div>
                <div className="tab-content p-4 p-md-5" id="v-pills-tabContent">
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
                                    <input type="text" className="form-control" value={auth.user.apellido}/>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group">
                                    <label>Email</label>
                                    <input type="text" className="form-control" value={auth.user.email}/>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group">
                                    <label>Documento</label>
                                    <input type="text" className="form-control" value={auth.user.dni}/>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group">
                                    <label>Telefono</label>
                                    <input type="text" className="form-control" value="42269863"/>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group">
                                    <label>Designation</label>
                                    <input type="text" className="form-control" value="UI Developer"/>
                                </div>
                            </div>
                        </div>
                        <div>
                            <button className="btn btn-primary btn-round w-100">Update</button>
                        </div>
                    </div>
                    <div className="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
                        <h3 className="mb-4">Password Settings</h3>
                        <div className="row">
                            <div className="col-md-6">
                                <div className="form-group">
                                    <label>Old password</label>
                                    <input type="password" className="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-md-6">
                                <div className="form-group">
                                    <label>New password</label>
                                    <input type="password" className="form-control"/>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group">
                                    <label>Confirm new password</label>
                                    <input type="password" className="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div>
                            <button className="btn btn-primary">Update</button>
                            <button className="btn btn-light">Cancel</button>
                        </div>
                    </div>
                    <div className="tab-pane fade" id="security" role="tabpanel" aria-labelledby="security-tab">
                        <h3 className="mb-4">Security Settings</h3>
                        <div className="row">
                            <div className="col-md-6">
                                <div className="form-group">
                                    <label>Login</label>
                                    <input type="text" className="form-control"/>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group">
                                    <label>Two-factor auth</label>
                                    <input type="text" className="form-control"/>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <div className="form-group">
                                    <div className="form-check">
                                        <input className="form-check-input" type="checkbox" value="" id="recovery"/>
                                        <label className ="form-check-label" for="recovery">
                                        Recovery
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <button className="btn btn-primary">Update</button>
                            <button className="btn btn-light">Cancel</button>
                        </div>
                    </div>
                    <div className="tab-pane fade" id="application" role="tabpanel" aria-labelledby="application-tab">
                        <h3 className="mb-4">Application Settings</h3>
                        <div className="row">
                            <div className="col-md-6">
                                <div className="form-group">
                                    <div className="form-check">
                                        <input className="form-check-input" type="checkbox" value="" id="app-check"/>
                                        <label className ="form-check-label" for="app-check">
                                        App check
                                        </label>
                                    </div>
                                    <div className="form-check">
                                        <input className="form-check-input" type="checkbox" value="" id="defaultCheck2"/>
                                        <label className ="form-check-label" for="defaultCheck2">
                                        Lorem ipsum dolor sit.
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <button className="btn btn-primary">Update</button>
                            <button className="btn btn-light">Cancel</button>
                        </div>
                    </div>
                    <div className="tab-pane fade" id="notification" role="tabpanel" aria-labelledby="notification-tab">
                        <h3 className="mb-4">Notification Settings</h3>
                        <div className="form-group">
                            <div className="form-check">
                                <input className="form-check-input" type="checkbox" value="" id="notification1"/>
                                <label className ="form-check-label" for="notification1">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit.Dolorum accusantium accusamus, neque cupiditate quis
                                </label>
                            </div>
                        </div>
                        <div className="form-group">
                            <div className="form-check">
                                <input className="form-check-input" type="checkbox" value="" id="notification2"/>
                                <label className ="form-check-label" for="notification2">
                                hic nesciunt repellat perferendis voluptatum totam porro eligendi.
                                </label>
                            </div>
                        </div>
                        <div className="form-group">
                            <div className="form-check">
                                <input className="form-check-input" type="checkbox" value="" id="notification3"/>
                                <label className ="form-check-label" for="notification3">
                                commodi fugiat molestiae tempora corporis.Sed dignissimos suscipit
                                </label>
                            </div>
                        </div>
                        <div>
                            <button className="btn-primary btn-round w-100">Update</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ProfilePage