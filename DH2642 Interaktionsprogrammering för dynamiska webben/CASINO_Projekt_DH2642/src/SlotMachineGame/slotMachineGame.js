import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import DeckModel from "../model/deckModel";
import "./slotMachineGame.css";
import UserModel from "../model/userModel";

class SlotMachineGame extends Component {
    constructor(props) {
        super(props);
        this.state = {
            status: "LOADING",
            deck: null,
            cards: null
        };
    }

    gamestarted() {
        UserModel.decreaseBalanceAndNotifyFirebase("Slot");
    }

    /**
     * @async
     * Initializes a partial deck with cards of our choosing to be used during the course of the game.
     */
    async componentDidMount() {
        let arrOfCards = ["7H,7D,7S,  AH,AD,AS,AC,  0H,0D,0S,0C,  QH,QD,QS,QC,  KH,KD,KS,KC  2H,2D,2S,2C,  3H,3D,3S,3C"];

        await DeckModel.createNewPartialDeck(arrOfCards).then(deck => {
            this.setState({
                status: "DECKLOADED",
                deck: deck
            });
        })
            .catch(() => {
                this.setState({
                    status: "ERROR"
                });
            });
    }

    /**
     * Renders the page content.
     */
    render() {
        let cardPic = "";
        let spinButton = "";
        let spinResults =
            (<div id="spaceing">
                <h2 id="spinResults"> </h2>
                <br />
                <h2 id="winResults"> </h2>
            </div>);
        let error = "";
        let uniqueKeys = ["0", "1", "2"];

        let gameDesc = (
            <div>
                <h1>Slot machine</h1>
                <p id="smdesc">The game about intriguing luck</p>
                <p id="smxs">(but mostly talent)</p>
            </div>
        )
        let howToWin = (
            <span className ="hoverOver">
                <p>How to win</p>
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
                </span>
            </span>
        )

        switch (this.state.status) {
            case "LOADING":
                cardPic = <img src="/loader.gif" id="loader" alt="A loading animation"/>;
                break;
            case "DECKLOADED":
                cardPic =
                    (
                        <div id="hiddenSlotMachineCards">
                            <img src="hidden_card.png" alt="The backside of a playing card" key="0"></img>
                            <img src="hidden_card.png" alt="The backside of a playing card" key="1"></img>
                            <img src="hidden_card.png" alt="The backside of a playing card" key="2"></img>
                        </div>
                    )
                spinButton =
                    (
                        <button id="spinButton" onClick={() => this.spin()}>Spin!</button>
                    )
                break;
            case "CARDSLOADED":
                cardPic =
                    (
                        <div id="slotMachineCards">
                            {this.state.cards.map((card, index) => {
                                if (card !== null) {
                                    return (<img src={card.image} alt={"The " + card.value + " of " + card.suit} key={uniqueKeys[index]}></img>)
                                }
                                else {
                                    return (<img src="hidden_card.png" alt="The backside of a playing card" key={uniqueKeys[index]}></img>)
                                }
                            })
                            }
                        </div>)
                spinButton = (
                    <button id="spinButton" onClick={() => this.spin()}>Spin!</button>
                )
                break;
            case "ERROR":
                error = (
                    <div>
                        <h1>ERROR</h1>
                        <h3>Page could not load due to external server error.</h3>
                    </div>
                )
                break;
            default:
                break;
        }

        return (
            <div className="SlotMachineGame">
                <Link to="/Gamepage"><h4 id="leaveButton">X</h4></Link>
                {gameDesc}
                {cardPic}
                {spinButton}
                <br/>
                {howToWin}
                {spinResults}
                {error}
            </div>
        )
    }

    /**
     * @async
     * Draws three cards from the deck, sorts them into order, displays them, checks if
     * they reward anything and finally reshuffles the cards into the deck.
     */
    async spin() {
        const stake = parseInt(localStorage.getItem("tokencost"));
        if (this.state.status !== "ERROR" && stake !== 0) {
            document.getElementById("spinButton").disabled = "disabled"; //Disables the spinbutton
            this.gamestarted();
            const stake = parseInt(localStorage.getItem("tokencost"));

            
            await this.resetPictureVisuals();
            document.getElementById("spinResults").innerHTML = "...";
            document.getElementById("winResults").innerHTML = "";


            DeckModel.drawCards(3, this.state.deck.deck_id).then(async response => {
                let sortedCards = this.sortCards(response.cards);
                if (this.state.cards !== null) {
                    await sleep(300); //Decides for how long the backsides of the cards are shown when spin is clicked.
                }

                await this.displayCardsWithDelay(sortedCards, 300);
                this.checkForWin(stake);
                await DeckModel.reshuffleCards(this.state.deck.deck_id);
                document.getElementById("spinButton").disabled = null; //Enables the spinbutton
            })
                .catch((e) => {
                    this.setState({
                        status: "ERROR"
                    });
                    console.log(e)
                });
        }
        else {
            alert("Choose your stake first")
        }
    }

    /**
     * Sets the state of the cards three times, one by one with a delay
     * in between to make the page rerender and display one new card each time. 
     * @param {array} sortedCards
     * @param {integer} delay
     */
    async displayCardsWithDelay(sortedCards, delay) {
        this.setState({
            status: "CARDSLOADED",
            cards: [sortedCards[0], null, null]
        });

        await sleep(delay);
        this.setState({
            cards: [sortedCards[0], sortedCards[1], null]
        })

        await sleep(delay);
        this.setState({
            cards: [sortedCards[0], sortedCards[1], sortedCards[2]]
        })
    }

    /**
     * Checks if the drawn cards resulted in a win and prints the result.
     * @param {integer} currentStake holds the relevant stake from when the spin started.
     */
    checkForWin(currentStake) {
        let spinResults = "No win";
        let winResults = "";

        let x = this.state.cards[0];
        let y = this.state.cards[1];
        let z = this.state.cards[2];


        if (x.value === "7" && this.threeOfAKind(x, y, z)) {
            spinResults = "JACKPOT!";
            winResults = "Won: " + 150 * currentStake;
            UserModel.increaseBalance(150 * currentStake,"Slot");
        }
        else if (this.aceStraight(x, y, z)) {
            spinResults = "ACE-STRAIGHT";
            winResults = "Won: " + 30 * currentStake;
            UserModel.increaseBalance(30 * currentStake,"Slot");
        }
        else if (this.sameSuit(x, y, z)) {
            spinResults = "SUIT-FLUSH";
            winResults = "Won: " + 20 * currentStake;
            UserModel.increaseBalance(20 * currentStake,"Slot");
        }
        else if (this.threeOfAKind(x, y, z)) {
            spinResults = "THREE OF A KIND";
            winResults = "Won: " + 10 * currentStake;
            UserModel.increaseBalance(10 * currentStake,"Slot");
        }

        document.getElementById("spinResults").innerHTML = spinResults;
        document.getElementById("winResults").innerHTML = winResults;

    }

    /**
     * Returns a boolean whether or not the drawn cards are three of a kind.
     * @param {object} x represents card 1
     * @param {object} y represents card 2
     * @param {object} z represents card 3
     * @return {bool}
     */
    threeOfAKind(x, y, z) {
        if (x.value === y.value && x.value === z.value) {
            return true;
        }
        return false;
    }

    /**
     * Returns a boolean whether or not the drawn cards are of the same suit.
     * @param {object} x represents card 1
     * @param {object} y represents card 2
     * @param {object} z represents card 3
     * @return {bool}
     */
    sameSuit(x, y, z) {
        if (x.suit === y.suit && x.suit === z.suit) {
            return true;
        }
        return false;
    }

    /**
     * Returns a boolean whether or not the drawn cards make a straight.
     * (ACE-2-3 OR QUEEN-KING-ACE)
     * @param {object} x represents card 1
     * @param {object} y represents card 2
     * @param {object} z represents card 3
     * @return {bool}
     */
    aceStraight(x, y, z) {
        if (x.value === "ACE" && y.value === "2" && z.value === "3") {
            return true;
        }
        else if (x.value === "QUEEN" && y.value === "KING" && z.value === "ACE") {
            return true;
        }
        return false;
    }

    /**
     * Sorts the drawn cards based on their values.
     * @param {object} cards 
     * @return a finished sorted hand of cards
     */
    sortCards(cards) {
        cards.sort(this.sortingLogic);
        return this.acePlacement(cards)
    }

    /**
     * Function used to sort the cards in ascending order.
     * @param {object} a is a card
     * @param {object} b is another card
     * @return the magnitude of the compared cards
     */
    sortingLogic(a, b) {
        let valueList = ["ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"];

        if (valueList.indexOf(a.value) > valueList.indexOf(b.value)) {
            return +1;
        }
        if (valueList.indexOf(a.value) < valueList.indexOf(b.value)) {
            return -1;
        }
        return 0;
    }

    /**
     * Special case sorting where the ACE either is placed in the beginning or the end.
     * @param {array} cards
     * @return a sorted hand of cards
     */
    acePlacement(cards) {
        if (cards[0].value === "ACE" && cards[1].value === "QUEEN" && cards[2].value === "KING") {
            return ([cards[1], cards[2], cards[0]]);
        }
        return cards;
    }

    /**
     * Rerenders the page to shows the backside pictures of all cards.
     */
    resetPictureVisuals() {
        if (this.state.status !== "ERROR") {
            this.setState({
                status: "DECKLOADED"
            });
        }
    }
}

/**
* a function to slow the pace of the dealer. 
* @param {integer} milliseconds the amount of time the sleep function will stay active
*/
const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
}


export default SlotMachineGame;