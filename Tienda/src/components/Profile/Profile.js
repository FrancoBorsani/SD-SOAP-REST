import { useContext, useState } from "react";
import { useHistory } from "react-router";
import { Link } from "react-router-dom";
import { DataContext } from "store/GlobalState";
import { TabContent, TabPane, Nav, NavItem, NavLink, Row, Col } from 'reactstrap';
import classnames from 'classnames';
import Account from "./Account";
import Addresses from "./Addresses";
import CreditCards from "./CreditCards";

const Profile = () => {

    const { state } = useContext(DataContext);

    const { auth } = state;

    const [currentActiveTab, setCurrentActiveTab] = useState('1');

    const toggle = tab => {
        if (currentActiveTab !== tab) setCurrentActiveTab(tab);
    }

    return (
        <div className="bg-white shadow rounded-lg d-block d-sm-flex">
            <div className="profile-tab-nav border-right">
                <div className="p-4">
                    <div className="img-circle text-center mb-3">
                        <img className="shadow" src="https://st.depositphotos.com/2101611/3925/v/600/depositphotos_39258143-stock-illustration-businessman-avatar-profile-picture.jpg" alt="profile" />
                    </div>
                    <h4 className="text-center text-capitalize">{auth.user.username}</h4>
                </div>
                <div className="nav flex-column nav-pills">
                    <Nav tabs className="nav flex-column nav-pills">
                        <NavItem>
                            <NavLink
                                className={classnames({
                                    active:
                                        currentActiveTab === '1'
                                })}
                                onClick={() => { toggle('1'); }}
                            >
                                Cuentas
                            </NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink
                                className={classnames({
                                    active:
                                        currentActiveTab === '2'
                                })}
                                onClick={() => { toggle('2'); }}
                            >
                                Direcciones
                            </NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink
                                className={classnames({
                                    active:
                                        currentActiveTab === '3'
                                })}
                                onClick={() => { toggle('3'); }}
                            >
                                Tarjetas
                            </NavLink>
                        </NavItem>
                    </Nav>
                </div>
            </div>

            <TabContent activeTab={currentActiveTab} className="p-4">
                <TabPane tabId="1">
                    <Row>
                        <Col sm="12">
                            <Account auth={auth} />
                        </Col>
                    </Row>
                </TabPane>
                <TabPane tabId="2">
                    <Row>
                        <Col sm="12">
                            <Addresses />
                        </Col>
                    </Row>
                </TabPane>
                <TabPane tabId="3">
                    <Row>
                        <Col sm="12">
                            <CreditCards />
                        </Col>
                    </Row>
                </TabPane>
            </TabContent>
        </div>
    )
}

export default Profile;