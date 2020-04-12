import React, { Component } from 'react';
import NavigationBar from '../NavigationBar/navigationBar';
import GameDisplay from '../GameDisplay/gameDisplay';
import './gamePage.css';

class GamePage extends Component {
	render() {	
		return (
			<div className="GamePage">
				<NavigationBar />
				<GameDisplay/>
			</div>
		);
	}
}
export default GamePage;
