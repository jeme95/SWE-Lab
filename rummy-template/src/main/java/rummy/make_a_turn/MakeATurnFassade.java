package rummy.make_a_turn;

import org.springframework.stereotype.Component;
import rummy.make_a_turn.impl.Make_a_turn_impl;
import rummy.make_a_turn.port.Make_a_turn_port;
import rummy.make_a_turn.port.Make_a_turn_provided;
import rummy.matchcenter.impl.Match;
import rummy.matchcenter.port.IKarte;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.statemachine.impl.StateMachineImpl;
import rummy.statemachine.port.State;

@Component
public class MakeATurnFassade implements Make_a_turn_provided, Make_a_turn_port {

	/**
	 * @directed true
	 * @link aggregation
	 * @supplierRole make_a_turn_impl
	 */
	private Make_a_turn_impl make_a_turn_impl = new Make_a_turn_impl() ;

	@Override
	public Make_a_turn_provided make_a_turn_provided() {
		// TODO Auto-generated method stub // todo
		return null;
	}

	@Override
	public void offeneKarteZiehen(IMatch match, IPlayer player) {
		
		if (!match.getStateMachine().getState().isSubStateOf(State.S.MussZiehen))
			return;
		this.make_a_turn_impl.offeneKarteZiehen(match, player);
	}

	@Override
	public void verdeckteKarteZiehen(IMatch match, IPlayer player) {
		
		if (!match.getStateMachine().getState().isSubStateOf(State.S.MussZiehen)) {
			return;
		}
		this.make_a_turn_impl.verdeckteKarteZiehen(match, player);
	}

	@Override
	public void karteAblegen(IMatch match, IPlayer player, int indexKarte) {
		if (!match.getStateMachine().getState().isSubStateOf(State.S.VerdecktGezogen))
			return;
		this.make_a_turn_impl.karteAblegen(match, player, indexKarte);

	}
}
