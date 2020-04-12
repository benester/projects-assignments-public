import React, { Component } from 'react';
import NavigationBar from '../NavigationBar/navigationBar';
import SlotMachineGame from "../SlotMachineGame/slotMachineGame";
import BottomBar from '../BottomBar/bottomBar';
import './slotMachinePage.css';

class SlotMachinePage extends Component {
    render() {
        return (
            <div className="SlotMachinePage">
                <NavigationBar />
                <SlotMachineGame />
                <BottomBar props={'Stake per spin'} />
            </div>
        );
    }
}
export default SlotMachinePage;
