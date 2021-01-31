package rummy.melding_cards;

import org.springframework.stereotype.Component;
import rummy.melding_cards.impl.Melding_cards_impl;
import rummy.melding_cards.port.Melding_cards_provided;
import rummy.statemachine.impl.StateMachineImpl;
import rummy.statemachine.port.State;

@Component
public class MeldingCardsFassade implements Melding_cards_provided {

	private Melding_cards_impl melding_cards_impl;
	private StateMachineImpl stateMachine;

	public void foo() {
		if (!stateMachine.getState().isSubStateOf(State.S.CanCallFoo))
			return;
		this.melding_cards_impl.foo();
	}
}
