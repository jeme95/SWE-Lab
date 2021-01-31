package rummy.matchcenter.impl;

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
		match.initialiseMatch();
		return true;
	}

}
