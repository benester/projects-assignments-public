import Observable from "./observable";



class UserModel extends Observable {

    constructor() {
        super();
        this.balance = 0;
        const value = localStorage.getItem("balance");
        if(value)
        this.setBalance(parseInt(value));
    }

    /**
     * sets value to the balance
     * @param {integer} num The amount of balance to be set to the user 
     */  
    setBalance(num)
    {
        if(num >= 0 )
        {
            this.balance = num;
            this.notifyObservers({
                type: "balanceChange",
                balance: this.getBalance()
            });
            localStorage.setItem("balance",this.getBalance());
        }
    }

    /**
     * adds to the value of the balance
     * @param {integer} num The amount of balance to be added to the user 
     */
    increaseBalance(num,whos) {
        let value = num;
        if (num > 0) {
            this.balance += num;
            this.notifyObservers({
                type: "balanceChangePlus",
                balance: this.getBalance(),
                number: parseInt(value),
                who: whos,
            });
            localStorage.setItem("balance",this.getBalance());
        }
    }

    /**
     * Removes value from the balance
     * @param {integer} num amount to decrease balance by(This is not needed for slotmachine and high or low, only for blackjack)
     */
    decreaseBalance(num) {
            let value = num;
            if(!num){
                value = parseInt(localStorage.getItem("tokencost"));
            }
            if(this.getBalance()-value >= 0){
            this.balance -= value
            this.notifyObservers({
                type: "balanceChangeminus",
                balance: this.getBalance(),
                number: parseInt(value),
            });
            localStorage.setItem("balance",this.getBalance());

        }
    }
    /**
    * Will let firebase know how much the user staked in a specific game, and decrease the users balance
    * @param {string} type The name of the game that user just started
    */
    decreaseBalanceAndNotifyFirebase(type)
    {
        let value = parseInt(localStorage.getItem("tokencost"));
        
        if(this.getBalance()-value >= 0){
            this.balance -= value
            this.notifyObservers({
                type: "balanceChangeminus",
                balance: this.getBalance(),
                number: parseInt(value),
                who:type
            });
            localStorage.setItem("balance",this.getBalance());
        }
    }

    /**
     * Returns the user's balance
     * @returns {integer} the users balance
     */
    getBalance() {
        return this.balance;
    }
}

const UserModelInstance = new UserModel();
export default UserModelInstance;