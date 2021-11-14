import React from 'react';
import './assets/css/App.css';
import 'bootstrap/dist/css/bootstrap.css';

import Login from './components/Login';
import Dashboard from './components/Dashboard';
import Reclamos from './components/Reclamos';
import Reclamo from './components/Reclamo';
import Denuncias from './components/Denuncias';
import Denuncia from './components/Denuncia';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

function App() {

  return (
    <React.Fragment>
      <Router>
        <Switch>
          <Route path="/" exact render = { props => (<Login {...props} />)}></Route>
          <Route path="/dashboard" exact render = { props => (<Dashboard {...props} />)}></Route>
          <Route path="/denuncias" exact render = { props => (<Denuncias {...props} />)}></Route>
          <Route path="/denuncia/:id" exact render = { props => (<Denuncia {...props} />)}></Route>
          <Route path="/reclamos" exact render = { props => (<Reclamos {...props} />)}></Route>
          <Route path="/reclamo/:id" exact render = { props => (<Reclamo {...props} />)}></Route>
        </Switch>
      </Router>
    </React.Fragment>
  );
}

export default App;
