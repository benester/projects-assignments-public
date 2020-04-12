import React, { Component } from "react";
import Login from "../AuthForms/Login";
import "./home.css";
class Home extends Component {
    render() {
        return (
            <div className="Home">
                <br></br>
                <br></br>
                <img src="logo.png" id="LoginLogo" alt="Swindler casino logo"></img>
                <br/>
                <br/>
                <br/>
                <Login/>
            </div>
        );
    }
}
export default Home;
