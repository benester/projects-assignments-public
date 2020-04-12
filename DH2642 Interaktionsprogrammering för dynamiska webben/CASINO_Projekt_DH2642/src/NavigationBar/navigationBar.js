import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";
import userModel from "../model/userModel";
import { withFirebase } from "../model/Firebase";
import { Modal, Button } from "react-bootstrap";
import "./navigationBar.css";

var styles2 = {
	color: "white"
};

class NavigationBar extends Component {
	constructor(props) {
		super(props);
		userModel.addObserver(this);

		this.state = {
			balance: userModel.getBalance(),
			userNavStatus: "CLOSED",
			avatarID: 1, // Instead of a number this should do a fetch to the database
			isShowing: false
		};
		this.updateSidebarSize = this.updateSidebarSize.bind(this);
	}

	openModalHandler = () => {
		this.setState({
			isShowing: true
		});
	};

	closeModalHandler = () => {
		this.setState({
			isShowing: false
		});
	};

	addFunds() {
		const value = document.getElementById("addfundinput").value;
		userModel.increaseBalance(parseInt(value));
		this.closeModalHandler();
	}

	update() {
		this.setState({
			balance: userModel.getBalance()
		});
	}

	/**
	* Sends the user to their userpage
	*/
	myaccount = () => {
		this.props.history.push("/Userpage");
	};

	render() {
		let avatarElem;

		if (this.state.userNavStatus === "CLOSED")
			avatarElem = (
				<img
					onClick={() => this.openUserNav()}
					id="avatar"
					src={"avatar_" + this.state.avatarID + ".gif"}
					alt="Your avatar icon."
				/>
			);
		else
			avatarElem = (
				<img
					onClick={() => this.closeUserNav()}
					id="avatar"
					src={"avatar_" + this.state.avatarID + ".gif"}
					alt="Default avatar icon."
				/>
			);

		return (
			<div id="navigationBar">
				{this.modal}
				<Link to="/gamepage">
					<img
						id="logo"
						src="logo.png"
						height="40px"
						width="auto"
						alt="Swindler casino logo"
					/>
				</Link>
				<div id="textMiddle">
					<p>Balance: {this.state.balance} </p>
					<Link to="/gamepage"> Game page </Link>
					<Link to="/blackjack"> Black Jack </Link>
					<Link to="/highorlow"> High or Low </Link>
					<Link to="/slotmachine">Slot Machine</Link>

					<div id="messagemodal">
						<Modal
							aria-labelledby="contained-modal-title-vcenter"
							centered
							show={this.state.isShowing}
							onHide={this.closeModalHandler}
						>
							<Modal.Header closeButton>
								<Modal.Title
									id="contained-modal-title-vcenter"
									className="titles"
								>
									Add funds
								</Modal.Title>
							</Modal.Header>
							<Modal.Body>
								<div className="fundaddholder">
									<input
										min="1"
										type="number"
										id="addfundinput"
										onKeyPress={(e) => {
											if (e.key === "Enter") {
												this.addFunds();
											}
										}}
									/>
								</div>
							</Modal.Body>
							<Modal.Footer>
								<Button onClick={() => this.addFunds()}>Submit</Button>
							</Modal.Footer>
						</Modal>
					</div>
				</div>
				{avatarElem}
				<div id="sideNav">
					<p
						className="sideNavItem"
						id="sideNavCloseBtn"
						// eslint-disable-next-line
						href="javascript:void(0)"
						onClick={() => this.closeUserNav()}
					>
						X
					</p>
					{/* <a className="sideNavItem">User page</a> */}

					<Link to="/gamepage" className="mobileFriendly">
						{" "}
						Game page
					</Link>
					<Link to="/blackjack" className="mobileFriendly">
						{" "}
						Black jack
					</Link>
					<Link to="/highorlow" className="mobileFriendly">
						{" "}
						High or Low{" "}
					</Link>
					<Link to="/slotmachine" className="mobileFriendly">
						{" "}
						Slot Machine
					</Link>
					<p
						className="sideNavItem"
						id="thisisgonea"
						onClick={this.myaccount}
						style={styles2}
					>
						My account
					</p>
					<p className="sideNavItem" onClick={this.openModalHandler}>
						Add funds
					</p>
					<p className="sideNavItem" onClick={() => this.cycleAvatars()}>
						Cycle avatars
					</p>
					<p
						className="sideNavItem"
						onClick={() => this.props.firebase.Signout()}
						style={styles2}
					>
						Sign out
					</p>
				</div>
			</div>
		);
	}

	componentDidMount() {
		window.addEventListener("resize", this.updateSidebarSize);
		// When component mounts we want to instantly fix the user sidebar height and top offset
		this.updateSidebarSize();
	}

	componentWillUnmount() {
		window.removeEventListener("resize", this.updateSidebarSize);
	}

	/**
	 * Updates the sidebar size, called when window is resized or on open/close.
	 */
	updateSidebarSize() {
		const isis = this.props.firebase.auth.currentUser.isAnonymous;
		if (isis) {
			document.getElementById("thisisgonea").style.display = "none";
		}

		if (window.innerWidth <= 940) {
			document.getElementById("sideNav").style.height = "280px";
		} else {
			document.getElementById("sideNav").style.height = "210px";
		}

		// User sidebar height offset
		document.getElementById("sideNav").style.top = "63px";
	}

	/**
	 * Called when we want to open the sidebar.
	 */
	openUserNav() {
		document.getElementById("sideNav").style.width = "250px";
		document.getElementById("sideNav").style.borderWidth = "2px";
		this.updateSidebarSize();

		this.setState({
			userNavStatus: "OPEN"
		});
	}

	/**
	 * Called when we want to open the sidebar.
	 */
	closeUserNav() {
		document.getElementById("sideNav").style.width = "0px";
		document.getElementById("sideNav").style.borderWidth = "0px";
		this.updateSidebarSize();

		this.setState({
			userNavStatus: "CLOSED"
		});
	}

	/**
	 * Cycles the avatar ID when called.
	 */
	cycleAvatars() {
		let avatarID = this.state.avatarID;
		avatarID++;

		if (avatarID > 6) avatarID = 1;

		this.setState({
			avatarID: avatarID
		});
	}
}

const navbar = withRouter(withFirebase(NavigationBar));

export default navbar;
