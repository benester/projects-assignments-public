import React, { Component } from 'react';
import NavigationBar from '../NavigationBar/navigationBar';
import BottomBar from '../BottomBar/bottomBar';
import BlackJackGame from '../BlackJackGame/blackJackGame'
import './blackJackPage.css';

class BlackJackPage extends Component {
    render() {
        return (
            <div className="BlackJackPage">
                <NavigationBar />
                <BlackJackGame />
                <BottomBar props={'Stake per hand'} />
            </div>
        );
    }
}
export default BlackJackPage;
