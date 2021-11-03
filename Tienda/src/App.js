import { BrowserRouter, Route, Switch } from "react-router-dom";

import Index from "pages/HomePage";
import LoginPage from "pages/LoginPage.js";
import ProfilePage from "pages/ProfilePage";
import RegisterPage from "pages/RegisterPage";
import CartPage from "pages/CartPage";
import Navbar from "components/Navbar";
import React from "react";
import DetailProductPage from "pages/DetailProductPage";
import ModalCustom from "components/ModalCustom";
import Products from "pages/Products";
import Orders from "pages/Orders";


function App() {

  React.useEffect(() => {
    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;
    return function cleanup() {
      document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");
    };
  });

    return (
      <BrowserRouter>
        <Navbar />
        <Switch>
            <Route path="/" render={(props) => <Index {...props} />} exact />
            <Route
              path="/profile"
              render={(props) => <ProfilePage {...props} exact />}
            />
            <Route
              path="/login"
              render={(props) => <LoginPage {...props} exact />}
            />
            <Route
              path="/registro"
              render={(props) => <RegisterPage {...props} exact />}
            />
            <Route
              path="/product/:id"
              render={(props) => <DetailProductPage {...props} exact />}
            />
            <Route
              path="/cart"
              render={(props) => <CartPage {...props} exact />}
            />
            <Route
              path="/my/products"
              render={(props) => <Products {...props} exact />}
            />
            <Route
              path="/my/orders"
              render={(props) => <Orders {...props} exact />}
            />
        </Switch>
        <ModalCustom/>
      </BrowserRouter>
    );
  }
  
  export default App;