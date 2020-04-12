import React, { Component } from "react";
import "./bottomBar.css";
import UserModel from "../model/userModel";

/* This class is the Bottombar in the app. */

class BottomBar extends Component {
	constructor(props) {
		super(props);
		UserModel.addObserver(this);

		this.state = {
			stake: 0
		};
	}

	render() {
		return (
			<div id="bottomBar">
				<h2 id={"currentStakeID"}>
					Stake: {this.state.stake + " " + this.getTokenString()}{" "}
				</h2>
				<div className={"stakeSection"}>
					<h3 id={"stakeText"}>{this.props.props}</h3>
					<div id={"stakeSelection"}>
						<button className={"stakeBtn"} onClick={() => this.clickHandler(1)}>
							1
						</button>
						<button className={"stakeBtn"} onClick={() => this.clickHandler(5)}>
							5
						</button>
						<button
							className={"stakeBtn"}
							onClick={() => this.clickHandler(10)}
						>
							10
						</button>
						<button
							className={"stakeBtn"}
							onClick={() => this.clickHandler(25)}
						>
							25
						</button>
						<button
							className={"stakeBtn"}
							onClick={() => this.clickHandler(50)}
						>
							50
						</button>
						<button
							className={"stakeBtn"}
							onClick={() => this.clickHandler(100)}
						>
							100
						</button>
						<button className={"clearBtn"} onClick={() => this.clickHandler(0)}>
							Clear
						</button>
					</div>
				</div>
			</div>
		);
	}

	componentDidMount() {
		localStorage.setItem("tokencost", this.getStake());
		this.changeButtonInteraction();
	}

	componentWillUnmount() {
		localStorage.removeItem("tokencost");
	}

	/**
	 * Handles the clicks on the stakes or the clear button.
	 * When clicked on a stake, it is added to the current stake value.
	 * When clicked on clear, the total stake is set to 0.
	 * @param {integer} num
	 */
	clickHandler(num) {
		const stake = parseInt(localStorage.getItem("tokencost"));

		let newStake = num + stake;
		if (num > 0 && UserModel.getBalance() >= newStake) {
			this.setState({ stake: newStake }, () => {
				this.changeButtonInteraction();
				localStorage.removeItem("tokencost");
				localStorage.setItem("tokencost", this.getStake());
			});
		} else if (num === 0) {
			this.setState({ stake: 0 }, () => {
				localStorage.removeItem("tokencost");
				localStorage.setItem("tokencost", 0);
				this.changeButtonInteraction();
			});
		}
	}

	/**
	 * Controls the availability of the stake buttons.
	 * If the user don't have enough tokens for a stake option,
	 * it is grayed out and disabled.
	 */
	changeButtonInteraction() {
		const buttons = Array.from(document.getElementsByClassName("stakeBtn"));
		buttons.forEach((button) => {
			if (
				parseInt(button.innerHTML) + this.getStake() >
				UserModel.getBalance()
			) {
				button.disabled = "disabled";
			} else {
				button.disabled = false;
			}
		});
	}

	/**
	 * This function will return the stake that the user paid
	 * @returns {integer} the stake that the user paid
	 */
	getStake() {
		return this.state.stake;
	}

	/**
	 * @returns {string} "token" in either singular or plural.
	 */
	getTokenString() {
		if (this.getStake() >= 2 || this.getStake() === 0) {
			return "tokens";
		} else {
			return "token";
		}
	}

	/**
	 * If the user balance changes, ex. from a new fund deposit,
	 * the stake buttons become enabled again.
	 * Also, losing the majority of the user's funds results in the
	 * remaining funds to become the new stake.
	 */
	update() {
		this.changeButtonInteraction();
		if (this.getStake() > UserModel.getBalance()) {
			this.setState({ stake: UserModel.getBalance() }, () => {
				localStorage.removeItem("tokencost");
				localStorage.setItem("tokencost", UserModel.getBalance());
			});
		}
	}
}

export default BottomBar;
