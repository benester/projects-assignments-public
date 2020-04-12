import React, { Component } from "react";
import "./Userpage.css";
import NavigationBar from "../NavigationBar/navigationBar";
import {withFirebase} from "../model/Firebase";
class Userpage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            email:'',
            Blackjack:0,
            Slot:0,
            Highlow:0,
            Moneywon:0,
            Moneyspent:0,
            Overall:0,
            uid:'',
            Blackjackcolor:"",
            Slotcolor:"",
            Highlowcolor:"",
            Overallcolor:"",

        };
        this.componentDidMount = this.componentDidMount.bind(this);
        this.changecolors = this.changecolors.bind(this);
    }




    componentDidMount()
    {
        const userinfo = this.props.firebase.auth.currentUser;
        if(!userinfo.isAnonymous){

        this.props.firebase.firestore.collection("users").doc(userinfo.uid).get().then(function(values)
        {
            
            this.setState({
                email:userinfo.email,
                Blackjack:values.data().BlackJack,
                Highlow:values.data().Highlow,
                Slot:values.data().Slot,
                Moneywon:values.data().Moneywon,
                Moneyspent:values.data().Moneyspent,
                Overall:values.data().Moneywon-values.data().Moneyspent,
            })

            this.changecolors();


        }.bind(this)).catch(error =>{
            alert(error.message);
        }
        )

    }
        else
        {
            document.getElementById("aligntext").style.display = "none";

            this.setState({
                email:"User is Anonymous",
                Blackjack:"Anonymous",
                Highlow:"Anonymous",
                Slot:"Anonymous",
                Moneywon:"Anonymous",
                Moneyspent:"Anonymous",
                Overall:"Anonymous",
            })
            document.getElementById("imagechange").src = "https://loremflickr.com/320/140?random=1";
            document.getElementById("imagechange").alt = "A ranodom one from loremflickr"
         }
    }

    changecolors()
    {
        if(this.state.Blackjack < 0)
        {
            this.setState({
                Blackjackcolor:"red",
            })
        }

        else if(this.state.Blackjack > 0)
        {
            this.setState({
                Blackjackcolor:"green",

            })
        }


        if(this.state.Highlow < 0)
        {
            this.setState({
                Highlowcolor:"red",

            })
        }
        else if(this.state.Highlow > 0)
        {
            this.setState({
                Highlowcolor:"green",

            })
        }


        if(this.state.Slot < 0)
        {
            this.setState({
                Slotcolor:"red",

            })
        }
        else if(this.state.Slot > 0)
        {
            this.setState({
                Slotcolor:"green",
            })
        }

        if(parseInt(this.state.Overall) < 0 )
        {
            this.setState({
                Overallcolor:"red",

            })
        }else if(parseInt(this.state.Overall) > 0 )
        {
            this.setState({
                Overallcolor:"green",
            })
        }
    }

    render() {
        return (
        <div className="Wholepageuser">
        <div className="navbar">
         <NavigationBar />
        </div>

        <h2 id="Titleemail">{this.state.email}</h2>
        <div id="iamgeholder">
        <img type="file" src="" id="imagechange" alt=""/>
        </div>
        <div className="Userpage">
        <div className="statusvalues">
            <h2 className="statush2">Stats</h2>


        <div className="alignobjectleft">

            <div>

            <p style={{background:this.state.Blackjackcolor}}>Blackjack: {this.state.Blackjack}</p>
            </div>  


            
           
           
            <div>

            <p style={{background:this.state.Highlowcolor}}>High low: {this.state.Highlow}</p> 

            </div>


            <div>

            <p style={{background:this.state.Slotcolor}}>Slot: {this.state.Slot}</p>

            </div>
        </div>




        <div className="alignobjectright">
        <div>

        <p>Money spent: {this.state.Moneyspent}</p> 

        </div>
        <div>

        <p>Money won: {this.state.Moneywon}</p>

        </div>

        <div>

        <p style={{background:this.state.Overallcolor}}>Overall: {this.state.Overall}</p>

        </div>
                    
        </div>


        </div>
        <p id="aligntext">
        The stats show you your current status in the casino
        <br/>
        If the background color is red, it means that you have lost that many tokens in that game
        <br/>
        Green means that you have gained that many tokens in that specific game
        </p>
        </div>
        </div>
        );
    }
}

const newuserpage = withFirebase(Userpage);

export default newuserpage;
