import React from 'react';

class Header extends React.Component {
    
    state = {
        filter:""
    }

    render() {
        return(
            <nav className="navbar navbar-dark bg-dark">
                <a className="navbar-brand">Helpdesk</a>
                <form className="form-inline">
                    <input value={this.filter} onChange={this.handleChange} name="filter" className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" style={{ marginRight: "10px" }}/>
                </form>
            </nav>
        );
    }
}

export default Header;