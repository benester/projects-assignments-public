import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter } from "react-router-dom";
import App from "./app";
import "./index.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import Firebase, { FirebaseContext } from './model/Firebase';

ReactDOM.render(
	<FirebaseContext.Provider value = {new Firebase()}>
	<BrowserRouter>
		<App />
	</BrowserRouter>
	</FirebaseContext.Provider>,
	document.getElementById("root")
);


