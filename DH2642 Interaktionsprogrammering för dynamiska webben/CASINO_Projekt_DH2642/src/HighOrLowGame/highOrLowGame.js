import React, { Component } from "react";
import { Prompt } from "react-router";
import { Link } from "react-router-dom";
import deckModel from "../model/deckModel";
import userModel from "../model/userModel";
import "./highOrLowGame.css";

class HighOrLowGame extends Component {
	constructor() {
		super();

		this.state = {
			status: "LOADING",
			deck: undefined,
			deckid: undefined,
			currentcards: undefined,
			odds: undefined,
			result: undefined,
			stake: 0
		};
	}

	/**
	 * Calls the usermodel to tell which game is being played.
	 */
	gamestarted() {
		userModel.decreaseBalanceAndNotifyFirebase("Highlow");
	}

	render() {
		let title = "High or Low";
		let undertitle;

		let oddsLow;
		let oddsHigh;

		let cardUp;
		let cardDown;

		let buttonField;

		let howToPlay = (
			<span className="hoverOver">
				<p>How to play</p>
				<span className="hoverText">
					The game of High or Low starts of with the dealer drawing two cards
					from the deck and flipping over the first card. The player must then
					guess if the second card has a value that is higher or lower than the
					first card. If the player gets it right, the stake is paid back times
					the odds. If the player gets it wrong, the stake is not paid back.
					<br />
					<br />
					Starting a new game shuffles the deck and the game repeats.
				</span>
			</span>
		);

		switch (this.state.status) {
			case "LOADING":
				undertitle = <img src="/loader.gif" alt="a loading animation"/>;
				break;
			case "LOADEDPREGAME":
				undertitle = <p>The game about predictions!</p>;
				cardUp = <img src="hidden_card.png" alt="Backside of card." />;
				cardDown = <img src="hidden_card.png" alt="Backside of card." />;
				buttonField = (
					<div id="buttonField">
						<button
							id="newgamebtn"
							onClick={() => {
								this.newGame();
							}}
						>
							New game
						</button>
					</div>
				);
				break;
			case "LOADED":
				undertitle = this.generateRandomUndertitle();
				oddsLow = <span id="odds">{this.state.odds[0].toFixed(2)}</span>;
				oddsHigh = <span id="odds">{this.state.odds[1].toFixed(2)}</span>;

				cardUp = (
					<img
						src={this.state.currentcards[0].image}
						alt={
							this.state.currentcards[0].value +
							" of " +
							this.state.currentcards[0].suit
						}
					/>
				);
				cardDown = <img src="hidden_card.png" alt="Backside of card." />;
				buttonField = (
					<div id="buttonField">
						<button id="Higher" onClick={() => this.predict("HIGHER")}>
							Higher
						</button>
						<img src="greater_icon.png" alt="'Greater than' icon." />
						<img src="less_icon.png" alt="'Less than' icon." />
						<button onClick={() => this.predict("LOWER")}>Lower</button>
					</div>
				);
				break;
			case "LOADEDPOSTGAME":
				undertitle = <h4>{this.state.result}</h4>;
				cardUp = (
					<img
						src={this.state.currentcards[0].image}
						alt={
							this.state.currentcards[0].value +
							" of " +
							this.state.currentcards[0].suit
						}
					/>
				);
				cardDown = (
					<img
						src={this.state.currentcards[1].image}
						alt={
							this.state.currentcards[1].value +
							" of " +
							this.state.currentcards[1].suit
						}
					/>
				);
				buttonField = (
					<div id="buttonField">
						<button
							id="newgamebtn"
							onClick={() => {
								this.newGame();
							}}
						>
							New game
						</button>
					</div>
				);
				break;
			case "ERROR":
				undertitle = <h4>Something went wrong :(</h4>;
				cardUp = "";
				cardDown = "";
				buttonField = (
					<div id="buttonField">
						<button onClick={() => window.location.reload()}>Reconnect</button>
					</div>
				);
				break;
			default:
				break;
		}

		return (
			<div id="hlContainer">
				<Prompt
					when={this.state.status === "LOADED"}
					message="Your game is unfinished, if you leave now, your stake will be lost, are you sure you want to leave?"
				/>
				<div id="hlHeader">
					<Link to="/Gamepage">
						<h4 id="leaveButton">X</h4>
					</Link>
					<h1>{title}</h1>
					{undertitle}
				</div>
				<div id="hlMain">
					{cardUp}
					{cardDown}
				</div>
				<div id="hlFooter">
					<p>HIGH PAYS {oddsHigh}</p>
					{buttonField}
					<p>LOW PAYS {oddsLow}</p>
					{howToPlay}
					<p>Current stake: {this.state.stake} token(s)</p>
				</div>
			</div>
		);
	}

	/**
	 * Generate a new deck when the component mounts.
	 */
	async componentDidMount() {
		await deckModel
			.createNewShuffledDeck(2)
			.then((deck) => {
				// State sets to a PREGAME stage where both cards a face down.
				this.setState({
					status: "LOADEDPREGAME",
					deck: deck,
					deckid: deck.deck_id
				});
			})
			.catch((error) => {
				console.log(error);
				this.setState({
					status: "ERROR"
				});
			});
	}
	/**
	 * Return the stake the user has chosen
	 * @returns {integer} the stake the user has chosen
	 */
	getStake() {
		return parseInt(localStorage.getItem("tokencost"));
	}

	/**
	 * Generate a new game, this shuffles the deck, draws two cards from the deck
	 * and calculates the odds of game.
	 */
	async newGame() {
		if (this.getStake() > 0) {
			document.getElementById("newgamebtn").disabled = "disabled";
			this.shuffle();

			let drawnCards;
			await deckModel
				.drawCards(2, this.state.deckid)
				.then((response) => (drawnCards = response.cards));

			this.calculateOdds(drawnCards[0]);

			// We reroll the game if the face up card is either ACE or KING.
			if (drawnCards[0].value === "ACE" || drawnCards[0].value === "KING") {
				this.newGame();
				return;
			}
			document.getElementById("newgamebtn").disabled = null;
			this.setState({
				status: "LOADED",
				currentcards: drawnCards,
				stake: this.getStake()
			});
		} else {
			alert("Choose a stake first!");
		}
		this.gamestarted();
	}

	/**
	 * Shuffle the current deck.
	 */
	shuffle() {
		deckModel.reshuffleCards(this.state.deckid);
	}

	/**
	 * Calculates the odds by the given card that has its face up.
	 * @param {Object} faceupCard is the card that is shown to the player, odds is based of this card.
	 */
	calculateOdds(faceupCard) {
		let cardVal = faceupCard.value;
		if (cardVal === "QUEEN") cardVal = 12;
		else if (cardVal === "JACK") cardVal = 11;

		let oddsLow = this.state.deck.remaining / ((cardVal - 1) * 8);
		let oddsHigh = this.state.deck.remaining / ((13 - cardVal) * 8);

		this.setState({
			odds: [oddsLow, oddsHigh]
		});
	}

	/**
	 * Checks what the outcome of the round was. Win = Payout, Lose = No payout, Draw = Stake payout.
	 * @param {String} prediction is what the user predicted.
	 */
	predict(prediction) {
		let leftCardVal = this.getCardValue(this.state.currentcards[0]);
		let rightCardVal = this.getCardValue(this.state.currentcards[1]);

		// If the card value is equal, its a draw
		if (leftCardVal === rightCardVal) prediction = "DRAW";

		let result;
		switch (prediction) {
			case "HIGHER":
				if (rightCardVal > leftCardVal) {
					userModel.increaseBalance(
						Math.floor(this.state.odds[1] * this.state.stake),
						"Highlow"
					);
					result = "You win! " + this.state.odds[1].toFixed(2) + "x win!";
				} else result = "You lost!";
				break;
			case "LOWER":
				if (rightCardVal < leftCardVal) {
					userModel.increaseBalance(
						Math.floor(this.state.odds[0] * this.state.stake),
						"Highlow"
					);
					result = "You win! " + this.state.odds[0].toFixed(2) + "x win!";
				} else result = "You lost!";
				break;
			default:
				result = "Draw! Stake payed back.";
				userModel.increaseBalance(this.state.stake, "Highlow");
				break;
		}

		this.setState({
			status: "LOADEDPOSTGAME",
			result: result
		});
	}

	/**
	 * Gets an integer from any given card, this integer is the value of the card.
	 * Used to get the value from Jacks, Queens and Kings.
	 * @param {Object} card is the given card.
	 * @returns {Integer} cardVal, the extracted value.
	 */
	getCardValue(card) {
		let cardVal = card.value;
		if (cardVal === "KING") cardVal = 13;
		else if (cardVal === "QUEEN") cardVal = 12;
		else if (cardVal === "JACK") cardVal = 11;
		// When a card value was of 10 it was interpreted as a string, not an integer.
		// Thus rendering it non-comparable to an integer.
		else if (cardVal === "10") cardVal = 10;
		else if (cardVal === "ACE") cardVal = 1;

		return cardVal;
	}

	/**
	 * Generates a random undertitle, makes for some sort of fun reading while playing the game.
	 * @returns {String} undertitle
	 */
	generateRandomUndertitle() {
		let undertitle;

		switch (Math.floor(Math.random() * Math.floor(10))) {
			case 0:
				undertitle = "Feeling lucky?";
				break;
			case 1:
				undertitle = "Going risky or going safe?";
				break;
			case 2:
				undertitle = "Swinder Casino is the most trusted!";
				break;
			case 3:
				undertitle = "If Swindler devs read this, vi von!";
				break;
			case 4:
				undertitle = "You should try out Blackjack or Slot Mania!";
				break;
			default:
				undertitle = "The game about predictions!";
				break;
		}

		return <p>{undertitle}</p>;
	}
}
export default HighOrLowGame;

/*
	STAGES OF THE GAME (SIMPLE OVERVIEW)
	
	1. Create a new deck (consisting of 2 decks).
	2. User places a bet.
	3. Shuffle deck.
	4. Draw two cards, one face up, one face down.
	5. Calculate odds.
	6. User predicts the face down card to be higher or lower than the face
		up card.
	7. User wins or loses.
	8. Reshuffle deck, goto: 2
*/
