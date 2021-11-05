import Layout from "components/Layout/Layout";
import Adresses from "components/Profile/Adresses";
import CreditCards from "components/Profile/CreditCards";
import { useContext } from "react";
import { Link, useHistory } from "react-router-dom";
import { DataContext } from "store/GlobalState";
import Account from '../components/Profile/Account'

const ProfilePage = () => {

    const { state } = useContext(DataContext);

    const { auth } = state;

    const router = useHistory();

    const isActive = (r) => {
        if (r === router.location.pathname) {
            return "active"
        } else {
            return ""
        }
    }

    return (
        <Layout>
            <div className="bg-white shadow rounded-lg d-block d-sm-flex">
                <div className="profile-tab-nav border-right">
                    <div className="p-4">
                        <div className="img-circle text-center mb-3">
                            <img src="https://st.depositphotos.com/2101611/3925/v/600/depositphotos_39258143-stock-illustration-businessman-avatar-profile-picture.jpg" alt="profile-photo" className="shadow"/>
                        </div>
                        <h4 className="text-center text-capitalize">{ auth.user.username }</h4>
                    </div>
                    <div className="nav flex-column nav-pills">
                        <Link className={"nav-link " + isActive('/profile')} to="/profile">
                            <i className="fa fa-home text-center mr-1"></i>
                            Cuenta
                        </Link>
                        <Link className={"nav-link " + isActive('/profile/cards')} to="/profile/cards">
                            <i className="fa fa-credit-card text-center mr-1"></i>
                            Tarjetas
                        </Link>
                        <Link className={"nav-link " + isActive('/profile/adresses')} to="/profile/adresses">
                            <i className="fa fa-home text-center mr-1"></i>
                            Direcciones
                        </Link>
                        <Link className="nav-link">
                            <i className="fa fa-tv text-center mr-1"></i>
                            Application
                        </Link>
                    </div>
                </div>

                <div className="tab-content p-4" id="v-pills-tabContent">
                    
                    {
                        router.location.pathname === '/profile' && <Account auth={auth} />
                    }

                    {
                        router.location.pathname === '/profile/cards' && <CreditCards />
                    }
                    
                    {
                        router.location.pathname === '/profile/adresses' && <Adresses />
                    }


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
        </Layout>
    )
}

export default ProfilePage