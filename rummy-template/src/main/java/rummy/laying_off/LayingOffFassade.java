package rummy.laying_off;

import org.springframework.stereotype.Component;

import rummy.laying_off.impl.Laying_off_impl;
import rummy.laying_off.port.Laying_off_provided;
import rummy.statemachine.port.State;
import rummy.statemachine.port.StateMachine;

@Component
public class LayingOffFassade implements Laying_off_provided{
	
	private Laying_off_impl laying_off_impl ; 
	private StateMachine stateMachine;									// jeme
	
	public void foo(){
		if (!stateMachine.getState().isSubStateOf(State.S.CanCallFOO))					// jeme
			return;																		// jeme
		this.laying_off_impl.foo();}

}
