import React from 'react';

const FirebaseContext = React.createContext(null);
    /**
     * beskrivning
     * Adds firebase to props, making it possible to use firebase in other components, by importing the parent folder and using withFirebase()
     */
export const withFirebase = Component => props => (
  <FirebaseContext.Consumer>
    {firebase => <Component {...props} firebase={firebase} />}
  </FirebaseContext.Consumer>
);

export default FirebaseContext;