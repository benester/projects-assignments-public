import React, { Component } from "react";
import NavigationBar from "../NavigationBar/navigationBar";
import HighOrLowGame from "../HighOrLowGame/highOrLowGame";
import BottomBar from "../BottomBar/bottomBar";
import "./highOrLowPage.css";

class HighOrLowPage extends Component {
	render() {
		return (
			<div className="HighOrLowPage">
				<NavigationBar />
				<HighOrLowGame />
				<BottomBar props={"Stake per round"} />
			</div>
		);
	}
}
export default HighOrLowPage;
