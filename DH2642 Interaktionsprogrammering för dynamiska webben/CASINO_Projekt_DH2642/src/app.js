import React, { Component } from "react";
import { Route } from "react-router-dom";
import {Redirect} from "react-router";
import { Switch } from "react-router";
import Home from "./Home/home.js";
import HighOrLowPage from "./HighOrLowPage/highOrLowPage";
// Possible use in the future.
// eslint-disable-next-line.
import {withFirebase} from "./model/Firebase";
import DeckModel from "./model/deckModel";
import GamePage from "./GamePage/gamePage";
import Userpage from "./AuthForms/Userpage";
import BlackJackPage from "./BlackJackPage/blackJackPage";
import SlotMachinePage from "./SlotMachinePage/slotMachinePage";
import "./app.css";

class App extends Component {
	constructor(props) {
		super(props);
		this.state = {
			title: "Casino Swindler",
			user:this.props.firebase.auth.currentUser,
		};
		//modelInstance.restoreState();
	}
componentDidMount()
{
	this.alistener();
}

	alistener()
	{
		this.props.firebase.auth.onAuthStateChanged((user)=>{
			if(user)
			{
				this.setState({user:{user}});
				localStorage.setItem('user', user.uid);
			}
			else{
			this.setState({user:null});
			localStorage.removeItem('user');
			}
		})
	}


	render() {
		return (
			<div className="App">
			
				{this.state.user ? (
				<Switch>

				<Route
					exact
					path="/HighOrLow"
					render={() => <HighOrLowPage deck={DeckModel} />}
				/>

				<Route
					exact
					path="/Blackjack"
					render={() => <BlackJackPage deck={DeckModel} />}
				/>

				<Route
					exact
					path="/Slotmachine"
					render={() => <SlotMachinePage deck={DeckModel} />}
				/>

				<Route exact path="/Gamepage" render={() => <GamePage />} />

				<Route exact path="/Userpage" render={() => <Userpage />} />				

				<Route exact path="/">
					{this.state.user ? (<Home/>) :  (<Redirect to="/Gamepage" />)} 
				</Route>
							</Switch> ) : (<Home/>)}
			</div>
		);
	}
}

const wholething = withFirebase(App);

export default wholething;