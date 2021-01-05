package rummy.make_a_turn;

import org.springframework.stereotype.Component;

import rummy.make_a_turn.impl.Make_a_turn_impl;
import rummy.make_a_turn.port.Make_a_turn_provided;
import rummy.statemachine.port.State;
import rummy.statemachine.port.StateMachine;

@Component
public class MakeATurnFassade implements Make_a_turn_provided  {
	
	private Make_a_turn_impl make_a_turn_impl ;
	private StateMachine stateMachine;							// jeme

	
	public void foo(){
		if (!stateMachine.getState().isSubStateOf(State.S.CanCallFOO))			// jeme
			return;																// jeme
		this.make_a_turn_impl.foo();}

}
