

class Observable {
	constructor() {
		this._observers = [];
	}

	addObserver(observer) {
		this._observers.push(observer);
	}

	notifyObservers(changevalue) {
		this._observers.map(observers => observers.update(changevalue));
	}

	removeObserver(observer) {
		this._observers = this._observers.filter(obs => obs !== observer);
	}

}

export default Observable;
