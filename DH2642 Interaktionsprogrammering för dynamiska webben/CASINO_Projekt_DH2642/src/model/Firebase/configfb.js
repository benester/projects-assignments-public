//Check drive for config
// use "npm i firebase" in the project map to get firebase
import {config} from "../apiConfig";
import firebase from 'firebase';
import userModel from "../userModel";
import app from "firebase/app";
import 'firebase/auth';
import 'firebase/firestore';

class Firebase{
    constructor()
    {
        userModel.addObserver(this);
        app.initializeApp(config);
        this.auth = app.auth();
        this.firestore = app.firestore();
        this.storage = app.storage();
    }

    /**
    * Will update the users standing in all the games when they spend money/win money in a game
    * @param {string} payload Will get information from oberservable and update firebase with balance spent and from what game
    */
    update(payload){

       if(!this.auth.currentUser.isAnonymous) {
        if(payload.type === "balanceChange")
        this.UpdateValuebalance(userModel.getBalance());
        if(payload.type === "balanceChangeminus")
        {
            this.UpdateValuebalance(userModel.getBalance());
             const userid = this.auth.currentUser.uid;
            this.firestore.collection('users').doc(userid).update({
                Moneyspent: firebase.firestore.FieldValue.increment(parseInt(payload.number))
        }).then(() => {
        })

        }
        if(payload.type ==="balanceChangePlus")
        {  
            this.UpdateValuebalance(userModel.getBalance());
             const userid = this.auth.currentUser.uid;
            this.firestore.collection('users').doc(userid).update({
            Moneywon: firebase.firestore.FieldValue.increment(parseInt(payload.number))
        }).then(() => {
        })
        }

        if(payload.who && payload.type === "balanceChangeminus" )
        {
            this.UpdateValuebalance(userModel.getBalance());
            const userid = this.auth.currentUser.uid;

            if(payload.who === "BlackJack"){
                this.firestore.collection('users').doc(userid).update({
                    BlackJack: firebase.firestore.FieldValue.increment(-parseInt(payload.number))
                }).then(() => {
                })
            }
            if(payload.who === "Slot"){
                this.firestore.collection('users').doc(userid).update({
                    Slot: firebase.firestore.FieldValue.increment(-parseInt(payload.number))
                }).then(() => {
                })
            }
            

            if(payload.who === "Highlow"){
                this.firestore.collection('users').doc(userid).update({
                    Highlow: firebase.firestore.FieldValue.increment(-parseInt(payload.number))
                }).then(() => {
                })
            }

        }


        if(payload.who && payload.type === "balanceChangePlus" )
        {
            this.UpdateValuebalance(userModel.getBalance());
            const userid = this.auth.currentUser.uid;

            if(payload.who === "BlackJack"){
                this.firestore.collection('users').doc(userid).update({
                    BlackJack: firebase.firestore.FieldValue.increment(parseInt(payload.number))
                }).then(() => {
                })
            }
            if(payload.who === "Slot"){
                this.firestore.collection('users').doc(userid).update({
                    Slot: firebase.firestore.FieldValue.increment(parseInt(payload.number))
                }).then(() => {
                })
            }
            

            if(payload.who === "Highlow"){
                this.firestore.collection('users').doc(userid).update({
                    Highlow: firebase.firestore.FieldValue.increment(parseInt(payload.number))
                }).then(() => {
                })
            }

        }

    }
    }
    
    /**
    * Will add the balance to the users account
    * @param {integer} amount The amount of tokens to be added to the users account 
    */
     Addbalance (amount)
    {
        const userid = this.auth.currentUser.uid;
        this.firestore.collection('users').doc(userid).update({
            Balance: firebase.firestore.FieldValue.increment(amount),
            Moneywon: firebase.firestore.FieldValue.increment(amount)
        }).then(() => {
        })
    }

    /**
    * Will set the users balance to a given value
    * @param {integer} givenvalue the amount that the users balance is to be set to 
    */
    UpdateValuebalance(givenvalue)
    {
        if(!this.auth.currentUser.isAnonymous)
        {
        const userid = this.auth.currentUser.uid;
        this.firestore.collection('users').doc(userid).update({
            Balance: givenvalue
        }).then(
        )
        }
    }

    /**
     * Will signout the user.
     */
    Signout()
    {
        this.auth.signOut().then(function(){
            userModel.setBalance(0);
            localStorage.removeItem("tokencost")
        }).catch(error =>{

        })
    }

    /**
     * Will create a user and send it to firebase
     * @param {string} email the users email used at signup
     * @param {string} password the users password used at signup
     */
    CreateUser(email,password)
    {
        this.auth.createUserWithEmailAndPassword(email,password).then((user) => 
            {
                const uid = user.user.uid;

                const accountinfo = {
                    Userid : uid,
                    Balance: 50,
                    BlackJack: 0,
                    Highlow: 10,
                    Slot: 100
                    }
                    this.firestore.collection('users').doc(uid).set(accountinfo).then(() =>
                {
                    window.location.href = "/gamepage";
                    this.getBalancefirebase();
                })
                


            })

    }
    
}
export default Firebase;