import React, { useContext } from "react";
import { Link, useHistory } from "react-router-dom";
import {
  Collapse, NavbarBrand, Navbar, NavItem, NavLink, Nav, Container, DropdownMenu,
  DropdownItem, DropdownToggle, UncontrolledDropdown
} from "reactstrap";
import { DataContext } from "store/GlobalState";

function IndexNavbar() {

  const [collapseOpen, setCollapseOpen] = React.useState(false);

  const { state, dispatch } = useContext(DataContext);

  const { auth, cart } = state;

  const router = useHistory();

  const handleLogout = () => {
    localStorage.clear();
    dispatch({ type: 'AUTH', payload: {} });

    router.push('/');
}

  const noLogged = () => {
    return (
      <>
        <NavItem>
          <NavLink to="/login" tag={Link}>
            Login
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink to="/registro" tag={Link}>
            Registro
          </NavLink>
        </NavItem>
      </>
    )
  }

  const userRouter = () => {
    return (
      <>
        <DropdownItem tag={Link} to="/my/orders">
          Mis compras
        </DropdownItem>
        <DropdownItem tag={Link} to="/my/claims/create">
          Mis reclamos
        </DropdownItem>
      </>
    )
  }

  const adminRouter = () => {
    return (
      <>
        <DropdownItem tag={Link} to="/my/products" >
          Mis productos
        </DropdownItem>
        <DropdownItem tag={Link} to="/my/orders">
          Mis ventas
        </DropdownItem>
      </>
    )
  }

  const loggedUser = () => {
    return (
      <UncontrolledDropdown className="button-dropdown">
        <DropdownToggle
          caret
          data-toggle="dropdown"
          id="navbarDropdown"
          tag="a"
        >
          <i className="now-ui-icons users_circle-08" style={{ fontSize: '20px' }}></i>
        </DropdownToggle>
        <DropdownMenu aria-labelledby="navbarDropdown" right>
          <DropdownItem header tag="a">
            {auth.user.username}
          </DropdownItem>
          <DropdownItem tag={Link} to="/profile">
            Perfil
          </DropdownItem>

          {
             auth.user.roles.includes('ROLE_ADMIN') ? adminRouter() : userRouter()
          }

          <DropdownItem divider></DropdownItem>
          <DropdownItem onClick={handleLogout}>
            Cerrar sesión
          </DropdownItem>
        </DropdownMenu>
      </UncontrolledDropdown>
    )
  }

  return (
    <>
      {collapseOpen ? (
        <div
          id="bodyClick"
          onClick={() => {
            document.documentElement.classList.toggle("nav-open");
            setCollapseOpen(false);
          }}
        />
      ) : null}
      <Navbar className="navbar-secondary" expand="lg" color="info" sticky="top">
        <Container>
          <div className="navbar-translate">
              <NavbarBrand id="navbar-brand" tag={Link} to="/">
                ECOMMERCE
              </NavbarBrand>
            <button
              className="navbar-toggler navbar-toggler"
              onClick={() => {
                document.documentElement.classList.toggle("nav-open");
                setCollapseOpen(!collapseOpen);
              }}
              aria-expanded={collapseOpen}
              type="button"
            >
              <span className="navbar-toggler-bar top-bar"></span>
              <span className="navbar-toggler-bar middle-bar"></span>
              <span className="navbar-toggler-bar bottom-bar"></span>
            </button>
          </div>
          <Collapse className="justify-content-end" isOpen={collapseOpen} navbar>
            <Nav navbar>
              <NavItem>
                <NavLink to="/cart" tag={Link}>
                  <p className="d-lg-none d-xl-none pl-2">Cart</p>
                  <i className="fa" style={{ fontSize: '20px'}}>&#xf07a;</i>
                  <span className='badge badge-danger' id='lblCartCount'>{ cart.length }</span>
                </NavLink>
              </NavItem>
              { auth.token ? loggedUser() : noLogged() }
            </Nav>
          </Collapse>
        </Container>
      </Navbar>
    </>
  );
}

export default IndexNavbar;
