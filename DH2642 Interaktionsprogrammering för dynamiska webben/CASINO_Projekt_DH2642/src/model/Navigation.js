import React from 'react';
import { Link } from 'react-router-dom';

const Navigation = ({ authUser }) => (
    <div>{authUser ? <NavigationAuth /> : <NavigationNonAuth />}</div>
  );

  
  const NavigationAuth = () => (
    <Switch>

    <Route
        exact
        path="/HighOrLow"
        render={() => <HighOrLowPage deck={DeckModel} />}
    />

    <Route
        exact path="/Blackjack"
        render={() => <BlackJackPage deck={DeckModel} />}
    />

    <Route
        exact path="/Slotmachine"
        render={() => <SlotMachinePage deck={DeckModel} />}
    />

    <Route exact path="/Gamepage" render={() => <GamePage />} />
</Switch>
  );


  
const NavigationNonAuth = () => (
    <Route exact path="/" component={Home} />
  );