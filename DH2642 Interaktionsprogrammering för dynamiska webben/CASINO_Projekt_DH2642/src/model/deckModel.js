import Observable from "./observable";
import { ENDPOINT } from "./apiConfig.js";

/**
 * Model class that handles the decks used for each casino game.
 */
class DeckModel extends Observable {
	constructor() {
		super();
		this.deck = undefined;
		this.drawnCards = [];
	}

	/**
	 * Creates a new deck consisting of one or many decks. The deck is shuffled when created.
	 * Please note that we only get one deck with 52 times the number of decks you called for.
	 * @async
	 * @example createNewShuffledDeck(3) returns one deck with 156 cards with every card having two more of its kind (due to it being 3 decks).
	 * @param {integer} numOfDecks decides the number of decks to be used on creation
	 * @returns {cards} a deck of cards, with @param numOfDecks decks
	 */
	async createNewShuffledDeck(numOfDecks) {
		let url = ENDPOINT.concat(
			"/new/shuffle/?deck_count=".concat(numOfDecks)
		);

		return fetch(url)
			.then(errorHandling)
			.then(response => {
				this.deck = response.json();
				return this.deck;
			});
	}

	/**
	 *  Creates a new partial deck to be used in for instance the slot machine.
	 *  @async
	 *  @param {cards} arrayOfCards the cards to be used in the partial deck
	 * 	@example the arrayOfCards[] array is for instance filled with 6 cards: ["AH", "2H", "3H", "4H", "5H"]
	 * ace of hearts, 2s of hearts etc...
	 * @returns {cards} a partial deck of cards
	 */
	async createNewPartialDeck(arrayOfCards) {
		let url = ENDPOINT.concat("/new/shuffle/?cards=".concat(arrayOfCards));

		return fetch(url)
			.then(errorHandling)
			.then(response => {
				this.deck = response.json();
				return this.deck;
			});
	}

	/**
	 * Draw one or more cards from the existing deck
	 * @async
	 * @param {integer} numOfCards the amount of cards to be drawn
	 * @param {string} deck_id the deck to take the cards from. 
	 * @returns {cards} returns the amount of cards specified in the param numOfCards
	 */
	async drawCards(numOfCards, deck_id) {
		if (!this.isDeckEmpty()) {
			let url = ENDPOINT.concat("/")
				.concat(deck_id)
				.concat("/draw/?count=")
				.concat(numOfCards);
			return fetch(url)
				.then(errorHandling)
				.then(response => {
					return response.json()
				})
				.catch((e) => console.log("error"))
		}
	}

	/**
	 * Used instead of creating a new deck when we want to reuse the existing one. The whole deck will
	 * @async
	 * @param {string}deck_id the id of the deck to reshuffle. The api takes care of the shuffling.
	 */
	async reshuffleCards(deck_id) {
		if (!this.isDeckEmpty()) {
			let url = ENDPOINT.concat("/")
				.concat(deck_id)
				.concat("/shuffle/");

			return fetch(url)
				.then(errorHandling)
				.then(response => {
					this.deck = response.json();
					return this.deck;
				});
		}
	}

	/**
	 * This function will return the most recently used deck of cards
	 * @returns {cards} a deck of cards
	 */
	getDeck() {
		return this.deck;
	}

	/**
	 * A function to determine if a deck has been created previously or not
	 * @returns {boolean} if the deck is empty, true, if the deck is not empty, false
	 */
	isDeckEmpty() {
		return this.deck === undefined;
	}
}

/**
 * A function to handle any error casued when accessing the api
 * @param {http status} response The response we get from the api
 * @throws {Error} if the response code is not ok. 
 * @returns {http status} the status that was sent back by the api. 
 */
function errorHandling(response) {
	if (response.ok) return response;
	throw Error(response.statusText);
}

const DeckModelInstance = new DeckModel();
export default DeckModelInstance;
