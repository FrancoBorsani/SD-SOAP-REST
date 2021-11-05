import ModalCustom from 'components/ModalCustom';
import Navbar from 'components/Navbar';
import React from 'react';

const Layout =({children}) =>{
    return(
        <>
        <Navbar/>
        <ModalCustom/>
        <main className="container clear-filter">
            {children}
        </main>
        </>
    )
}

export default Layout;