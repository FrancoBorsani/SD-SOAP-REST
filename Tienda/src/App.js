import { BrowserRouter, Route, Switch } from "react-router-dom";

import HomePage from "pages/HomePage";
import HomePage_beta from "pages/HomePage_beta";
import LoginPage from "pages/LoginPage.js";
import ProfilePage from "pages/ProfilePage";
import RegisterPage from "pages/RegisterPage";
import CartPage from "pages/CartPage";
import React from "react";
import DetailProductPage from "pages/DetailProductPage";
import Products from "pages/Products";
import Orders from "pages/Orders";
import ProductCreatePage from "pages/ProductCreatePage";

import { ClientRoutes } from 'helpers/enums'
import ProductModifyPage from "pages/ProductModifyPage";
import NotFound from "components/NotFound";
import WizardPage from "pages/WizardPage";
import Claims from "pages/Claims";
import ClaimCreatePage from "pages/ClaimCreatePage";
import DetailOrderPage from "pages/DetailOrderPage";


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
      <Switch>
        <Route path={ClientRoutes.HOME} component={HomePage} exact />
        <Route path={ClientRoutes.PROFILE} component={ProfilePage} exact />
        <Route path={ClientRoutes.LOGIN} component={LoginPage} exact />
        <Route path={ClientRoutes.REGISTER} component={RegisterPage} exact />
        <Route path={ClientRoutes.PRODUCT_DETAIL} component={DetailProductPage} exact />
        <Route path={ClientRoutes.CART} component={CartPage} exact />
        <Route path={ClientRoutes.PRODUCTS_LIST} component={Products} exact />
        <Route path={ClientRoutes.PRODUCT_CREATE} component={ProductCreatePage} exact />
        <Route path={ClientRoutes.PRODUCT_MODIFY} component={ProductModifyPage} exact />
        <Route path={ClientRoutes.ORDERS_LIST} component={Orders} exact />
        <Route path={ClientRoutes.ORDER_DETAIL} component={DetailOrderPage} exact />
        <Route path={ClientRoutes.CHECKOUT} component={WizardPage} exact />
        <Route path={ClientRoutes.CLAIMS_LIST} component={Claims} exact />
        <Route path={ClientRoutes.CLAIMS_CREATE} component={ClaimCreatePage} exact />
        <Route component={NotFound} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
