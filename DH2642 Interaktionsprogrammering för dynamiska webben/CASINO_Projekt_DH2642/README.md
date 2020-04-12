# Casino Project DH2642 - Project Group 12

## Get started (for those who do not know yet)

1. First, make sure that you have npm (Node Packet Manager) installed on your system (follow the instructions at [Installing Node](https://docs.npmjs.com/getting-started/installing-node).

2. In the terminal, locate yourself in the root of the repository. Then, run `npm install` through the terminal. Let it install all the dependencies.

3. Since we take use of Google's application called Firebase you'll also need to install another dependency: `npm install firebase`.

4. We encrypt user data with sha3 before we send anything to our Firebase server, therefore you need to install another dependency: `npm install js-sha3`.

5. Run `npm start` through the terminal. This will start the webserver and the application should pop up in your browser ready for use. Alternatively you can open in through [Local Host](http://localhost:3000). Whenever you make changes in your code and save, the browser should update automatically, so you don't have to click refresh anymore.

## Members of Group 12

- [Alexander Jonsson](https://www.kth.se/profile/alexjons/), [alexjons@kth.se](alexjons@kth.se)
- [Alexander Palm](https://www.kth.se/profile/alepa/), [alepa@kth.se](alepa@kth.se)
- [Benjamin Gafvelin](https://www.kth.se/profile/bgaf/), [bgaf@kth.se](bgaf@kth.se)
- [Georgek Aroush](https://www.kth.se/profile/aroush/), [aroush@kth.se](aroush@kth.se)

## The project itself

### API

The following APIs will be used:

- [Deck of Cards](https://deckofcardsapi.com/)

### Fundamental idea

In this project we will make a basic casino website, where the logged in user is able to gamble away ”tokens” that is a virtual and free form of currency on the website. The currently planned games are:

- [High or Low](https://smartcasinoguide.com/high-low-card-game/)
- [Blackjack](https://sv.wikipedia.org/wiki/Black_Jack)
- [Cardslot (slot machine with cards)](https://en.wikipedia.org/wiki/Slot_machine)

### What is done right now? / Current state of the application

As of right now, all of the games are working. Any user is allowed to create an account, add funds and play games that are to their liking.
