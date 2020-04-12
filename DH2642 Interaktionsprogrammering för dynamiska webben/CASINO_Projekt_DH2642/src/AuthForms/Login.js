import React, { Component } from "react";
import "./Login.css";
import 'bootstrap/dist/css/bootstrap.css';
import { withRouter } from 'react-router-dom';
import userModel from "../model/userModel";
import {Modal,Button} from "react-bootstrap";
import {withFirebase} from "../model/Firebase";
import 'bootstrap/dist/css/bootstrap.min.css';
class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            email:'',
            password:'',
            isShowing: false,
            message:null,
            showmessage:false,
            showforgot: false,
        };
        this.CreateUser = this.CreateUser.bind(this);
        this.Signin = this.Signin.bind(this);
        this.anony = this.anony.bind(this);
        this.forgotpassowrd = this.forgotpassowrd.bind(this);
    }

    /**
     * beskrivning
     * Opens the modal for error messages
     */
    openModalHandler = () => {
        this.setState({
            isShowing: true
        });
    }

      /**
     * beskrivning
     * closes the modal for Singup
     */
    closeModalHandler = () => {
        this.setState({
            isShowing: false
        });
    }
    
      /**
     * beskrivning
     * Opens the modal for error messages
     */
    showmessages = () => {
        this.setState({
            showmessage: true
        });
    }
    /**
     * beskrivning
     * closes the modal for error messages
     */
    closemessages = () => {
        this.setState({
            showmessage: false
        });
    }
    /**
     * beskrivning
     * opens the modal for password reset 
     */
    forgotpasswordopen = () =>{
        this.setState({
            showforgot:true
        })
    }

    /**
     * beskrivning
     * closes the modal for password reset 
     */
    forgotpasswordclose = () =>{
        this.setState({
            showforgot:false
        })
    }

    /**
     * beskrivning
     * Makes it possible to login anonymously 
     * It also makes sure that balance is 50.
     */
    anony()
    {
        this.props.firebase.auth.signInAnonymously().then(user => {
            const te = this.getBalancefirebase();
            if(te)
            {
                this.props.history.push("/Gamepage")
            }
        });
        
    }

    
 /**
     * beskrivning
     * @param {string} email the users email used at login
     * @param {string} password the users password used at login
     */
       Signin()
    {
        const {email,password} = this.state;
        this.props.firebase.auth.signInWithEmailAndPassword(email, password).then(user => {
           const te = this.getBalancefirebase();
           if(te)
           this.props.history.push("/Gamepage");
        }
        ).catch(error =>{
            this.setState({
                message:error.message,
            })
            this.showmessages()
        })
    }

    
 /**
     * beskrivning
     * @param {string} email the users email used at signup
     * @param {string} password the users password used at signup
     */
    CreateUser()
    {
        
        const email = document.getElementById("emailhold").value;
        const password = document.getElementById("passhold").value;
        this.props.firebase.auth.createUserWithEmailAndPassword(email,password).then((user) => 
            {
                const uid = this.props.firebase.auth.currentUser.uid;

                const accountinfo = {
                    Userid : uid,
                    Balance: 50,
                    BlackJack: 0,
                    Highlow: 0,
                    Slot: 0,
                    Moneyspent:0,
                    Moneywon:0,
                    }
                    this.props.firebase.firestore.collection('users').doc(uid).set(accountinfo).then(() =>
                {
                    const t = this.getBalancefirebase();
                    if(t)
                    this.props.history.push("/Gamepage");
                }).catch(error => {
                    this.setState({
                        message:error.message,
                    })
                    this.showmessages()
                })

            }).catch(error => {
                this.setState({
                    message:error.message,
                })
                this.showmessages()

        });
    }

  /**
     * beskrivning
     * @param {string} emailrequestpassword is the email adress that is used to reset the password.
     */
    forgotpassowrd()
    {
        const emailrequestpassword = document.getElementById("resetpassword").value;
        this.props.firebase.auth.sendPasswordResetEmail(emailrequestpassword).then(info =>
        {
            this.setState({
                message:"Email has been sent",
            })
            this.forgotpasswordclose();
            this.showmessages();
        }).catch(error => {
            this.setState({
                message:error.message,
            })
            this.showmessages()
        }
            
        )
    }


    /**
     * beskrivning
     * @param {string} userid gets the current userit for firebase firestore.
     * it updates the local balance to the one in the firebase account. 
     */
    getBalancefirebase()
    {
        const userid = this.props.firebase.auth.currentUser.uid;
        if(this.props.firebase.auth.currentUser.isAnonymous)
        {
        userModel.setBalance(100);
        return true
        }
        else
        return this.props.firebase.firestore.collection("users").doc(userid).get().then(function(values)
        {
            userModel.setBalance(values.data().Balance);
            return true
        }).catch(error =>{
            alert(error.message, "this account does not have a balance set");
        });
    }

    /**
     * beskrivning
     * Updates the states value every time the input value changes
     * it is connected to login inputs
     */
    onChange = event => {
        this.setState({ [event.target.name]: event.target.value });
    };


    render() {
        const {email,password} = this.state;

        return (
        
            <div id="flexgrid">
            
            {/* <button id="btnLogout" className="hidebtn" onClick={this.Signout}>Logout</button> */}



        <div className="Formholder">
        <span id="logintext"> Log in </span>
        <form>
            <input
                name="email"
                className="logininput"
                value={email}
                onChange={this.onChange}
                type="text"
                placeholder="Email Address"
                autoComplete ="username"
            />
            <br/>
            <input
                name="password"
                className="logininput"
                value={password}
                onChange={this.onChange}
                type="password"
                placeholder="Password"
                autoComplete ="current-password"
            />
            <br/>
            <Button onClick={this.Signin}>
            Sign In
            </Button>
            <br/>
            <br/>

            <Button onClick={this.anony}>
            Sign In Anonymously
            </Button>
        </form>
        <br/>
        <br/>

        <p id="clicktext" onClick={this.openModalHandler}>Create New Account</p>
        <p id="clicktext" onClick={this.forgotpasswordopen}> Reset your password</p>
        </div>

            <div className="wholemodal">
            <Modal aria-labelledby="contained-modal-title-vcenter" centered
            show={this.state.isShowing} onHide={this.closeModalHandler}>
              <Modal.Header closeButton>
              <Modal.Title id="contained-modal-title-vcenter" className="titles">Sign up</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <form>
                <div className="allholders">
                 <label ><b>Email</b></label>
                 <br/>
                 <input type="email" id="emailhold"  placeholder="Enter Email" name="email" autoComplete ="username" required/>
                 </div>
                 <br/>
                <div className="allholders">
                 <label ><b>Password</b></label>
                 <br/>
                 <input type="password" id="passhold" placeholder="Enter Password" name="psw" autoComplete ="new-password" required/>
                 <label><b>Minimum of 6 characters</b></label>
                 </div>
                </form>
              </Modal.Body>
              <Modal.Footer>
                 <div className="buttonholder">
                <Button type="submit" className="btn btn-primary center-block" onClick={this.CreateUser} >Submit</Button>
                </div>
              </Modal.Footer>
            </Modal>
            </div>


            

            <div id="messagemodal">
            <Modal aria-labelledby="contained-modal-title-vcenter" centered
            show={this.state.showmessage} onHide={this.closemessages}>
              <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter" className="titles">{this.state.message}</Modal.Title>
              </Modal.Header>
            </Modal>
            </div>


            <div id="forgotpassword">
            <Modal aria-labelledby="contained-modal-title-vcenter" centered
            show={this.state.showforgot} onHide={this.forgotpasswordclose}>
              <Modal.Header closeButton>
              <Modal.Title className="titles">Reset your password!</Modal.Title>
              </Modal.Header>
              <Modal.Body>
              <div className="allholders">
                  <p>Enter Email</p>
              <input type="email" id="resetpassword" placeholder="Enter Email" name="em" autoComplete ="new-password-request" required/>
              </div>
              </Modal.Body>
              <Modal.Footer>
              <div className="buttonholder">
                <Button className="btn btn-primary center-block" onClick={this.forgotpassowrd} >Submit</Button>
                </div>
              </Modal.Footer>
            </Modal>
            </div>



          </div>
        );
    }
}

const logforms = withRouter(withFirebase(Login));


export default logforms;