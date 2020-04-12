import React, { Component } from "react";
import { withRouter } from "react-router";
import { Link } from "react-router-dom";
import userModel from "../model/userModel";
import { Button } from "react-bootstrap";
import "./gameDisplay.css";

class GameDisplay extends Component {
	componentDidMount() {}

	render() {
		let questionMarkTag = (
			<img
				src="questionmark.png"
				id="questionMarkTag"
				alt="Questionmark icon"
			></img>
		);

		return (
			<div className="gameDisplay">
				<h2> Games: </h2>
				<Link to="/blackjack">
					<img src="casino_Blackjack_icon.png" alt="Blackjack icon" id="gamePic"/>
					<br />
					<div className="hoverOver">
						{questionMarkTag}
						<span className="hoverText">
							Blackjack is the American variant of a globally popular banking
							game known as Twenty-One It is a comparing card game between one
							or more players and a dealer, where each player in turn competes
							against the dealer. 
							The objective of the game is to beat the dealer in one of the following ways:
							Get 21 points on the player's first two cards (called a
							"blackjack"), without a dealer blackjack
							<br />
							<br />
							Reach a final score higher than the dealer without exceeding 21
							or
							<br />
							<br />
							Let the dealer draw additional cards until their hand
							exceeds 21 ("busted").
						</span>
					</div>
				</Link>
				<Link to="/highorlow">
					<img src="casino_highOrLow_icon.png" alt="High or low icon" id="gamePic"/>
					<br />
					<div className="hoverOver">
						{questionMarkTag}
						<span className="hoverText">
							The game of High or Low starts of with the dealer drawing two
							cards from the deck and flipping over the first card. The player
							must then guess if the second card has a value that is higher or
							lower than the first card. If the player gets it right, the stake
							is paid back times the odds. If the player gets it wrong, the
							stake is not paid back.
							<br />
							<br />
							Starting a new game shuffles the deck and the game repeats.
						</span>
					</div>
				</Link>
				<Link to="/slotmachine">
					<img src="casino_slot_icon.png" alt="Slot machine icon" id="gamePic"/>
					<br />
					<div className="hoverOver">
						{questionMarkTag}
						<span className="hoverText">
							To win in the slot machine you need to get one of the following combinations:
							<br /><br />
							All cards have the same suit
							<br /><br />
							All cards have the same value
							<br /><br />
							A straight, (ACE-2-3 OR QUEEN-KING-ACE)
							<br /><br />
							The biggest win is if you get three 7s
							<br/>
						</span>
					</div>
				</Link>
				<div id="gamePageFundBox">
					<h3>Add funds</h3>
					<input min="1" type="number" id="gamePageFundInput" onKeyPress={(e) => this.handleKeyPress(e)}/>
					<Button id="gamePageFundsButton" onClick={this.addFunds} >Submit</Button>
					<p id="addedFunds"></p>
				</div> 
			</div>
		);
	}

	/**
	 * Adds funds to the userpage and gives the user visual feedback through a p-element.
	 */
	addFunds() {
		const value = document.getElementById("gamePageFundInput").value;
		if(value > 0){
			userModel.increaseBalance(parseInt(value));
			document.getElementById("addedFunds").innerHTML = "Tokens were successfully added";
			document.getElementById("gamePageFundInput").value = null;
		}
	}

	/**
	 * Checks if a keypress is the 'enter' key, resulting in a function call on addFunds.
	 * @param {event} e 
	 */
	handleKeyPress(e){
		if(e.key==="Enter"){
			this.addFunds();
		}
		else{
			document.getElementById("addedFunds").innerHTML = "";
		}
	}
}

export default withRouter(GameDisplay);
