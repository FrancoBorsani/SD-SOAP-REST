import React from 'react';
import Header from '../template/Header';
import Denuncias from './Denuncias';
import Reclamos from './Reclamos';

class Dashboard extends React.Component {

    render() {
        return(
            <React.Fragment>
                <Header></Header>
                <Denuncias></Denuncias>
                <Reclamos></Reclamos>
            </React.Fragment>
        );
    }
}

export default Dashboard