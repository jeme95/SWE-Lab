package rummy.make_a_turn.impl;

import rummy.matchcenter.impl.Karte;
import rummy.matchcenter.impl.Match;
import rummy.matchcenter.port.IKarte;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.statemachine.port.State;

public class Make_a_turn_impl {

	public void foo() {
		// leere Funktion
	}

	public void offeneKarteZiehen(IMatch match, IPlayer player) {
		// todo
	}

	public void verdeckteKarteZiehen(IMatch match, IPlayer player) {
		match.verdecktZiehen(player);
	}

	public void karteAblegen(IMatch match, IPlayer player, int indexKarteSelected) {
		match.karteAblegen(player, indexKarteSelected);

	}

}
