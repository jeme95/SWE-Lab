package rummy.matchcenter.impl;

import java.util.ArrayList;
import java.util.List;

import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.MatchManagement;
import rummy.statemachine.port.State;
import rummy.statemachine.port.StateMachinePort;

public class MatchMangementImpl implements MatchManagement {

	private StateMachinePort stateMachinePort;

	public MatchMangementImpl(StateMachinePort smPort) {
		this.stateMachinePort = smPort;
	}

	@Override
	public boolean startGame(IMatch match) {
		
		this.stateMachinePort.getMachine(match.getId()).setState(State.S.Play);
		
		match.setStateMachine(this.stateMachinePort.getMachine(match.getId()));

		return true;
	}

//	@Override
//	public void initialiseMatch(IMatch match) {
//		System.out.println("GAME STARTED");
//		match.createAllKarten(); // Sets erzeugen (2 Sets je 52 Blatt + 2 Joker)
//		match.kartenVerteilen(); // Karten auf Spieler verteilen
//		match.givePlayersIndexes();
//	}
	
	
}
