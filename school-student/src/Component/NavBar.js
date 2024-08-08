import React from "react";
import { Link } from "react-router-dom";

function NavBar () {
    return (
            <nav className="navbar navbar-expand-sm bg-primary navbar-light">
                <div className="container-fluid">
                    <ul className="navbar-nav">
                       <li className="nav-item"><Link to="/" className="nav-link">Home</Link></li>
                       <li className="nav-item"><Link to="/school" className="nav-link">School</Link></li>
                       <li className="nav-item"><Link to="/student" className="nav-link">Student</Link></li>   
                    </ul>
                </div>
            </nav>
    )
}
export default NavBar;