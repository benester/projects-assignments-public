import React, { Component } from 'react';
import DeckModel from "../model/deckModel";
import { Link } from 'react-router-dom';
import { Prompt } from 'react-router';
import './blackJackGame.css';
import userModel from "../model/userModel";


/**
 * @class
 * @classdesc This class is the blackJack game in the app. Will render the playing cards, buttons and handle the
 * the game logic of the game.
 * @author Benjamin <bgaf@kth.se>
 * @version 1.6
 */
class BlackJackGame extends Component{
    constructor(props){
        super(props);
        this.stake = 0;
        this.moneyPaidOut = false;
        this.state = {
            status: "LOADING",      //either LOADING, ERROR or LOADED 
            dealerCards: [],        //The dealers cards
            playerCards: [],        //The players cards
            splitCards: [],         //The players second hand, in case the player splits
            action : undefined,     //Either undefined or "stay" when the player is done with their turn
            bust : false,           //If the player gets > 21 set this to true 
            dealerDone: false,      //When the dealer is done with the turn
            split : false,          //If the player can split, this will be true
            doubleDown : false,      //If the player can double down, this will be true
            newGame : true,          //Make all the cards turned over in the beginning.
            twoTimes : false         //Will display 2x the stake if the users splits/doubledowns
        }
    }
    /**
    * This function will take the stake from the users balance, and save the stake.
    */
    gamestarted()
    {
        userModel.decreaseBalanceAndNotifyFirebase("BlackJack");
        this.stake = this.getStake();
    }

    componentDidUpdate(){
        //check if the player can split
        this.canPlayerSplit();
       
        //check if the player can double, only if the player has not made a move yet! 
        if(this.state.playerCards.length === 2 && this.state.splitCards.length === 0 && this.state.doubleDown === false){
            this.canPlayerDoubleDown();
        }
    }


    /**
    * This code will run when the page is first opend
    * @async
    */
    async componentDidMount(){
        this.moneyPaidOut = false;
        let deck = await DeckModel.createNewShuffledDeck(4).then(deck=>deck)
            .catch(()=>this.setState({status:"ERROR"}));
        if(deck){
            this.deck_id = deck.deck_id;
            if(deck.remaining >= 4){
            await DeckModel.drawCards(4, deck.deck_id).then(Response =>
                this.setState({
                    dealerCards : Response.cards.slice(0,2),    //only the first two cards go to the dealer
                    playerCards : Response.cards.slice(2),      //The other two cards go to the player(the user). 
                    status:"LOADED",
                    action: undefined,
                    bust : false,
                    dealerDone: false,
                    splitCards : [],
                    split: false,
                    doubleDown : false,
                    twoTimes : false
                }))
            }
            else{
                this.setState({
                status:"ERROR"
                })
            }
        }
    }

    render(){
        let gameboard = "";
        let dealersCards = "";
        let dealerScoreRn = "";
        let playerCards = "";
        let buttons="";
        let winOrLose = "";
        let splitCards = "";
        let twoTimes = "";
        let scoreInGame = "";
        let i = 0;
        switch (this.state.status){
            case "LOADING":
                gameboard = <div> 
                                <img src="/loader.gif" alt="A loading animation"/>
                            </div>;
            break;
            case "LOADED":
                if(this.state.twoTimes === true){
                    twoTimes = "2x ";
                }
                switch(this.state.action){
                    case "stay":
                        /* --------------------------------- When the player is done with their turn -----------------------------------*/
                        //Display dealers cards to the player
                        dealerScoreRn = <p> Dealers Score : {this.calculateScore(this.state.dealerCards)} </p>;
                        dealersCards = 
                        <div className = "cards">{
                            this.state.dealerCards.map((card)=>{
                                return(
                                    <img src={card.image} key={card.code + i++} alt={"The " +card.value +" of "+ card.suit}/>
                                )
                            })}
                        </div>
                        playerCards = 
                        <div className = "cards">{this.state.playerCards.map((card)=>{
                            return(
                                <img src={card.image} key={card.code + i++} alt={"The " +card.value +" of "+ card.suit}/>
                            )
                        })}
                        </div>

                        if(this.state.splitCards.length > 0){
                            splitCards =
                            <div id ="splitCards">
                                {this.state.splitCards.map((card)=>{
                                    return(
                                        <img src={card.image} key={card.code + i++} alt={"The " +card.value +" of "+ card.suit}/>
                                    )
                                })}
                            </div>
                            scoreInGame = 
                            <div id="score">
                                <p>Score 1: {this.calculateScore(this.state.playerCards)}</p>
                                <p id="textSpace"></p>
                                <p>Score 2: {this.calculateScore(this.state.splitCards)}</p>
                            </div>
                        }
                        else{
                            scoreInGame = 
                            <div>
                                <p> Score: {this.calculateScore(this.state.playerCards)}</p>
                                <p> Your Cards:</p>
                            </div>
                        }
                        if(this.state.dealerDone === false){
                            this.dealerBehaviorTree();
                        }
                        if(this.state.dealerDone===true){
                            buttons = <button ref="btn" id="newGameBtn" onClick ={()=>{
                                this.refs.btn.setAttribute("disabled", "disabled");
                                if(this.getStake() > 0){
                                    this.componentDidMount()
                                    this.gamestarted()
                                }
                                else{
                                    alert('Chose a stake first!');
                                    document.getElementById("newGameBtn").disabled = null;
                                }
                            }}>New game</button>;
                            
                            if(this.state.splitCards.length === 0){
                                winOrLose = <h2> {this.didPlayerWinOrLose(this.state.playerCards)} </h2>;
                                this.moneyPaidOut = true;
                            }
                            else{
                                winOrLose = 
                                <div id="winOrLose">
                                    <h2>hand 1: {this.didPlayerWinOrLose(this.state.playerCards)} </h2>
                                    <h2> hand 2: {this.didPlayerWinOrLose(this.state.splitCards)}</h2>
                                </div>
                                this.moneyPaidOut = true;
                            }
                        }
                    break;
                    //Display one of the dealers cards, and hide the other
                    default:
                        /* ---------------------------- Dealer and players cards -------------------------------- */
                        if(this.state.newGame!== true){
                            dealersCards = 
                            <div className = "cards">
                                <img src={this.state.dealerCards.slice(0,1)[0].image} key ={this.state.dealerCards[0] + "dealersCard"} alt={"the " + this.state.dealerCards.slice(0,1)[0].value + " of " + this.state.dealerCards.slice(0,1)[0].suit}/>
                                <img src="hidden_card.png" key={this.state.dealerCards[0] + "hidden"} alt="Dealers hidden card"/>
                            </div>;
                            playerCards = 
                            <div className ="cards">{this.state.playerCards.map((card)=>{
                                return(
                                    <img src={card.image} key={card.code + i++} alt={"The " +card.value +" of "+ card.suit}/>
                                )
                            })}
                            </div>
                        }
                        else{
                            dealersCards = 
                            <div className = "cards">
                                <img src="hidden_card.png" key={"thisHiddenCard"} alt="hidden card"/>
                                <img src="hidden_card.png" key={"thisHiddenCards"} alt="hidden card"/>
                            </div>
                            playerCards = dealersCards;
                        }
                         /* ----------------------------- Score display and display split cards-------------------------- */

                        if(this.state.splitCards.length>0){
                            splitCards =
                            <div id ="splitCards">
                                {this.state.splitCards.map((card)=>{
                                    return(
                                        <img src={card.image} key={card.code + i++} alt={"The " +card.value +" of "+ card.suit}/>
                                    )
                                })}
                            </div>
                            scoreInGame = 
                            <div id="score">
                                <p>Score 1: {this.calculateScore(this.state.playerCards)}</p>
                                <p id="textSpace"></p>
                                <p>Score 2: {this.calculateScore(this.state.splitCards)}</p>
                            </div>
                        }
                        else if(this.state.newGame !== true){
                            scoreInGame = 
                            <div>
                                <p> Score: {this.calculateScore(this.state.playerCards)}</p>
                                <p> Your Cards:</p>
                            </div>
                        }
                        /* ----------------------------- Buttons -------------------------- */
                        if(this.state.bust === false && this.state.newGame !== true){
                            if(this.state.split === true && this.state.splitCards.length===0 && this.state.playerCards.length===2 && this.state.doubleDown === true){
                                buttons = 
                                    <div>
                                        <button onClick={()=>this.doubleDown()} id="double" disabled={this.disableBtnsForLowBalance()}>Double Down</button>
                                        <button onClick={()=>this.splitFunction()}id="split" disabled={this.disableBtnsForLowBalance()}> Split </button>
                                        <button onClick={()=>this.setState({action:"stay"})}id="stay">Stay</button> 
                                        <button onClick={()=>this.hitFunction("player")}id="hit">Hit</button>
                                    </div>
                            }
                            else if(this.state.split === true && this.state.splitCards.length===0 && this.state.playerCards.length===2 && this.state.doubleDown === false){
                                buttons = 
                                    <div>
                                        <button onClick={()=>this.splitFunction()}id="split" disabled={this.disableBtnsForLowBalance()}> Split </button>
                                        <button onClick={()=>this.setState({action:"stay"})}id="stay">Stay</button> 
                                        <button onClick={()=>this.hitFunction("player")}id="hit">Hit</button>
                                    </div>
                            }
                            else if(this.state.split === true && this.state.splitCards.length>0){
                                buttons = 
                                    <div>
                                        <button onClick={()=>this.splitStay("player")} id="stay">Stay</button>
                                        <button onClick={()=>this.hitFunction("player")} id="hit">Hit</button>
                                        <button onClick={()=>this.splitStay("split")} id="splitStay">Stay</button>
                                        <button onClick={()=>this.hitFunction("split")} id="hitSplit">Hit</button>
                                    </div>
                            }
                            else if(this.state.doubleDown === true && this.state.playerCards.length === 2){
                                buttons = 
                                    <div>
                                        <button onClick={()=>this.doubleDown()}id="double" disabled={this.disableBtnsForLowBalance()}>Double Down</button>
                                        <button onClick={()=>this.setState({action:"stay"})}id ="stay">Stay</button> 
                                        <button onClick={()=>this.hitFunction("player")} id="hit">Hit</button>
                                    </div>
                            }
                            else{
                                buttons =
                                    <div>
                                        <button onClick={()=>this.setState({action:"stay"})} id ="stay">Stay</button> 
                                        <button onClick={()=>this.hitFunction("player")} id="hit">Hit</button>
                                    </div>
                            }
                        }
                        else if(this.state.newGame !== true){
                            buttons = <button ref="btn" id="newGameBtn" onClick={()=>{
                                this.refs.btn.setAttribute("disabled", "disabled");
                                if(this.getStake() > 0) {
                                    this.componentDidMount()
                                    this.gamestarted()
                                }
                                else{
                                    alert('Chose a stake first!');
                                    document.getElementById("newGameBtn").disabled = null;
                                }
                            }}>New game</button>
                        } 
                        else{
                            buttons = <button onClick={()=>{
                                if(this.getStake() > 0) {
                                        this.setState({newGame : false})
                                        this.gamestarted()
                                    }
                                    else{
                                        alert('Chose a stake first!');
                                    }
                            }}>Start Game</button>
                        }
                    break;
                }
                gameboard= 
                <div id="gameboard">
                    <div id="dealersCards">
                        <p id="dealersCardsText">Dealers cards:</p>
                        {dealersCards}
                    </div>
                    {dealerScoreRn}
                    <div id="spaceing">{winOrLose}</div>
                    <div id="playerCards">
                        {scoreInGame}
                        {playerCards}
                        {splitCards}
                    </div>
                    {buttons}
                    <br />
                        <span className ="hoverOver">
                            <p>How to play</p>
                            <span className="hoverText">
                                1. Make a bet from 1 token to All In. <br />
                                2. The dealer will give you two cards and show one of his cards. <br />
                                3. You can double your bet if your score is equal to 10 or 11 before you hit or stay. You can split your bet if you get two cards of the same type, i.e 2s 5s Queens etc. 
                                Splitting means that your hand will be split into two separate hands, you can not split more than once. Both will double your stake<br/>
                                4. You have an option to add more cards by choosing 'Hit' but you lose automatically if your value of cards exceeds 21.<br/>
                                5. Click 'Stay' when you are ready to play the hand. <br/>
                                6. The dealer will reveal his hidden card and must always hit if they have 16 or lower, or soft 17. They will stop hitting if they have a hard 17 or more.<br />
                                7. You win when the combined value of your cards is greater than that of the dealer.<br />
                            </span>
                        </span>
                    <p>Current stake: {twoTimes} {this.stake} token(s)</p>
                </div>
            break; 
            case "ERROR" :
                gameboard = <p>error something went wrong here, connection lost..</p>
            break;
            default : 
                gameboard = <p> We dont know what went wrong. Try again soon </p>
        }
        return(
            <div className ="BlackJackGame">
                <Prompt when={this.isGameStartedButNotFinished()} message = "Any unfinished game will result in your stake beeing lost, are you sure you want to leave?" />
                <Link to="/Gamepage"><h4>X</h4></Link>
                <h1> Black jack </h1>
                {gameboard}
            </div>
        )
    }

    /**
     * Logic to handle when the player or dealer "hits"
     * @param {string} player the player that "hits" either the user of the site("player") | ("split") or the dealer ("")
     * This function will change the state, and therefore invoke a reload, it will also disable the hit btn
     * until the next card has loaded
     */
    async hitFunction(player){
        let currentCards = "";

        if(player==="player"){
            currentCards = this.state.playerCards;
            document.getElementById("hit").disabled = "disabled";
        }
        else if(player ==="split"){
            currentCards = this.state.splitCards;
            document.getElementById("hitSplit").disabled = "disabled";
        }
        else{
            currentCards = this.state.dealerCards;
        }

        //Stops the user from beeing dumb
        if(this.calculateScore(currentCards) === 21){
            if (!window.confirm('You are about to hit on 21, are you sure about that?')) {
                document.getElementById("hit").disabled = null;
                return;
            }
        }
        await DeckModel.drawCards(1, this.deck_id).then(response =>{
            currentCards.push(response.cards[0]);
            if(player==="player"){
                document.getElementById("hit").disabled = null;
            }
            else if(player ==="split"){
                document.getElementById("hitSplit").disabled = null;
            }
            if(!this.didPlayerbust(currentCards, player)){
                this.setState({
                    cards: currentCards
                })
            }
        })
    }
    
    
    /**
     * This function will determine if the player can perform a split or not
     * If the player can split, it will change the state of split to true
     * @returns if the player cannot split, do nothing and return nothing.
     */
    canPlayerSplit(){
        //base case, if this is true, the rest of the code should not execute
        if(this.state.split === true || this.state.playerCards.length !== 2){
            return;
        }
        let cards = this.state.playerCards;
        //for the "tens" picture cards
        if(cards[0].value === cards[1].value && this.state.splitCards.length === 0){
            this.setState({
                split : true
            })
        }
    }
    /**
     * This function determines if the player can perform a doubledown or not
     * If the player can double, it will change the state
     */
    canPlayerDoubleDown(){
        let score = this.calculateScore(this.state.playerCards);
        if(score === 10 || score ===11){
            this.setState({
                doubleDown : true
            })
        }
    }

    /**
    *  This function returns the stake the user has chosen
    * @returns {integer} the stake the user has chosen
    */
    getStake(){
        return parseInt(localStorage.getItem("tokencost"));
    }

    /**
    * This function handles the logic of when the player splits.
    * This function changes the state and splits the pair into two seperate decks
    */
    async splitFunction(){
        let cards = this.state.playerCards;
        userModel.decreaseBalance(this.stake);
        //Get two new cards
        let responseCards = [];
        await DeckModel.drawCards(2, this.deck_id).then(Response =>{
            responseCards.push(Response.cards)
        }).catch(e=>{
            this.setState({
                status : "ERROR"
            })
        })
        
        let playercards = cards.slice(0,1) //Keep playercards as an array
        playercards.push(responseCards[0][0])  //Add element to an array
        
        let dealercards = cards.slice(1,2)
        dealercards.push(responseCards[0][1]);

        this.setState({
            split : true, 
            playerCards : playercards,
            splitCards : dealercards,
            twoTimes : true
        })
    }

    /**
    * This function handles the logic of when the player doubleDowns
    */
    async doubleDown(){
        this.disableNonSplit();
        userModel.decreaseBalance(this.stake);
        await this.hitFunction("player");
        this.setState({
            action : "stay",
            twoTimes : true
        })
    }

    /**
    * This function will gray out split / doubledown button(s) if the user has insufficient balance
    * @param {string} btns either "doubleSplit", "split" or "double" indicates which btns to disable
    * @returns {string} either an empty string or the string "disabled"
    */
    disableBtnsForLowBalance(btns){
        if(userModel.getBalance() < this.stake){
            return "disabled"
        }
        return "";
    }

    /**
    * Handles logic when the user stays, while having split
    * @param {string} player either "split" or "player" to determine wich of the hands decided to stay    
    */
    splitStay(player){
        if(player === "split"){
           this.disableSplit();
        }
        else{
            this.disableNonSplit();
        }
        if(this.isSplitBtnsDisabled()){
            this.splitDone();
        }
    }

    /**
     * A function that determines if the player lost the game by busting
     * @param {cards} pCards the cards that the player has. Will change the state and cause a reload
     * @param {string} player, either "player" or "split" if not defined => dealer
     */
    didPlayerbust(pCards , player){
        let playerScore = this.calculateScore(pCards);
        if(playerScore === "busted"){
            if(this.state.splitCards.length===0){
                if(player!== "dealer"){
                    this.setState({
                        bust : true
                    })
                }
            }
            else{
                if(player === "split"){
                    this.disableSplit();
                }
                else if(player === "player"){
                    this.disableNonSplit();
                }
                //dont run this line of code when the dealer is playing. Errors will fly
                if(player!=="dealer" && this.isSplitBtnsDisabled()){
                    this.splitDone();
                }
            }
        }
    }

    /**
    * This function will be called when both hands buttons are disabled, and the player is done with both hands
    * This function will change the state and thus cause a reload
    */
    splitDone(){
        let splitScore = this.calculateScore(this.state.splitCards);
        let playerScore = this.calculateScore(this.state.playerCards);

        if(isNaN(splitScore)&&isNaN(playerScore)){
            this.setState({
                bust : true
            })
        }
        else{
            this.setState({
                action : "stay"
            })
        }
    }

    /**
    * A function that determines if both hands buttons are disabled in a split situation
    * @returns {boolean} true if both hands buttons are disabled, otherwise false
    */
    isSplitBtnsDisabled(){
        if(document.getElementById("hit").disabled && document.getElementById("hitSplit").disabled){
            return true;
        }
        return false;
    }

    /**
     * A function that determines if the dealer lost the game by busting.
     * @param {cards} dCards the card that the dealer has. Will not change state, i.e not cause a reload.
     * @returns {boolean} true if dealer busted, false otherwise. 
     */
    didDealerBust(dCards){
        let dealerScore = this.calculateScore(dCards);
        if(dealerScore === "busted"){
            return true;
        }
        return false;
    }

    /**
     * a function to calculate the score of the player. Will take all the blackjack point rules into account. 
     * @param {cards} cardsOnHand the cards the user or the dealer has on hand 
     * @return {number or string} either a value between 4 and 21 or the string "Busted" if the player busted
     */
    calculateScore(cardsOnHand){
        let Score = 0;
        let aces = 0;
        this.softAces = 0;
        cardsOnHand.forEach(card=>{
            if(isNaN(card.value)){
                if(card.value === "ACE"){
                    aces +=1;
                }
                else{
                    Score += 10;
                }
            }
            else{
                Score += parseInt(card.value);
            }
        })
        if(aces>0){
            for(let a = aces; a >0; a--){
                if(Score + 11 <= 21){
                    Score+=11
                    this.softAces++;
                }
                else{
                    Score+=1;
                }
            }
        }
        if(Score>21 && this.softAces === 0){
            Score = "busted"
        }
        else if(Score > 21){
            for(let a = this.softAces; a > 0; a--){
                Score -= 10;
                this.softAces--;
                if(Score < 22){
                    break;
                }
            }
        }
        return Score;
    }

   /**
    * A function that returns a string on the result of the round. 
    * @param {cards} cards a hand of cards, one of the player's hands, in a split situation, otherwise just the players hand.
    * @returns {string} either player win or lost, with some additional information
    */
    didPlayerWinOrLose(cards){
        let playerScore = this.calculateScore(cards);
        let dealerScore = this.calculateScore(this.state.dealerCards);
        //We call didDealerBust, since this doesnt change the state, like didPlayerBust does
        if(this.didDealerBust(this.state.dealerCards) && !this.didDealerBust(cards)){
            this.IncreaseUserBalance();
            return ("Player won " + this.getWinAmount() + " tokens, dealer busted")
        }
        else if(playerScore >  dealerScore){
            this.IncreaseUserBalance(); 
            return ("Player won " + this.getWinAmount() + " tokens")
        }
        else if(playerScore===dealerScore){
            if(playerScore === 21){
                if(this.gotBlackjack("player") && !this.gotBlackjack("dealer")){
                    this.IncreaseUserBalance();  
                    return ("Player won " + this.getWinAmount() + " tokens, Blackjack!");
                }
                else if (!this.gotBlackjack("player") && this.gotBlackjack("dealer")){
                    return "Player lost, dealer got Blackjack!";
                }
                else{
                    userModel.increaseBalance(this.stake);
                    return "Draw (stake is refunded)"
                }
            }
            else{
                //Returns the stake to the user
                userModel.increaseBalance(this.stake);
                return "Draw (stake is refunded)"
            }
        }
        else {
            return "Player lost" 
        }
    }

    /**
    * This function will add balance to the users account if the player wins
    */
    IncreaseUserBalance(){
        if(this.moneyPaidOut === false){
            if(this.state.doubleDown === true){
                userModel.increaseBalance(4*this.stake,"BlackJack")  //Double the win if the player double down§
            }
            else{
                userModel.increaseBalance( 2 * this.stake,"BlackJack")  
            }
        }
    }
    /**
    * This function will return the amount the player won. Will only be called if the player actually won, so no
    * no controll is needed
    * @returns {integer} the amount of tokens the player won. 
    */
    getWinAmount(){
        if(this.state.doubleDown === true){
            return (4 * this.stake);
        }
        return (2 * this.stake);
    }

    /**
     * A function that determines if a player got blackjack or 21 by several cards
     * @param {string} player either the user ("player") or the dealer ("dealer")
     * @returns {boolean} true if the player got blackjack, else false
     */
    gotBlackjack(player){
        let cards = "";
        if(player ==="player"){
            cards = this.state.playerCards;
        }
        else if(player === "dealer"){
            cards = this.state.dealerCards;
        }
        if(cards.length === 2){
            return true;
        }
        return false;
    }

    /**
     * The behavior tree that the dealer will follow in the game. 
     * This function will change the state when the dealer is done.
     * This dealer will play it's hand like a real Black jack dealer in a casino would.
     * That means, hit if score is less than 17, hit on soft 17, stay on hard 17 and above.
     */
    dealerBehaviorTree(){
        this.softAces = 0;
        let dealersScore = this.calculateScore(this.state.dealerCards);
        //to give the user some time to react to what the dealer is doing
        sleep(1000).then(()=>
        {
            if(this.isSoft17(dealersScore)){
                this.hitFunction("dealer");
            }
            else if(dealersScore < 17){
                this.hitFunction("dealer");
            }
            else if(dealersScore >=17 && dealersScore <= 21){
                this.setState({
                    dealerDone : true
                })
            }
            //this is a catch all else. Might not be needed
            else{
                this.setState({
                    dealerDone : true
                })
            }
        });
    }

    /**
     * Will return true if the dealer has a soft 17 or not
     * @param {Cards} DealerScore the dealers current score
     * @returns {Boolean} true if soft17 else false
     */
    isSoft17(DealerScore){
        if(DealerScore === 17){
            if(this.softAces > 0){ 
                return true
            }
        }
        return false;
    }

    /**
    * This function will set both hit and stay button to disabled for one of the hands in a split situation
    */
    disableSplit(){
    document.getElementById("hitSplit").disabled = "disabled";
    document.getElementById("splitStay").disabled = "disabled";
    }
    /**
    * This function will set both hit and stay button to disabled for the other hand in a split situation
    */
    disableNonSplit(){
        document.getElementById("stay").disabled = "disabled";
        document.getElementById("hit").disabled = "disabled";
    }

    /**
     * Determines if a game has started but not finished yet 
     * @returns {boolean} True if a game is currently undergoing, else false
     */
    isGameStartedButNotFinished(){
        if((this.state.action !== "stay" && this.state.bust === false) && this.state.newGame !== true){
            return true
        }
        return false
    }
}


/**
 * a function to slow the pace of the dealer, by not running code for @milliseconds milliseconds
 * @param {int} milliseconds the amount of time the sleep function will stay active
 * @returns {Promise} to return when the milliseconds are over
 */
const sleep = (milliseconds) => {
        return new Promise(resolve => setTimeout(resolve, milliseconds))
    }

export default BlackJackGame;