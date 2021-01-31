package rummy.make_a_turn.port;

import rummy.matchcenter.impl.Match;
import rummy.matchcenter.port.IKarte;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;

public interface Make_a_turn_provided {
	
	public void offeneKarteZiehen(IMatch match, IPlayer player) ;
		
	public void verdeckteKarteZiehen(IMatch match, IPlayer player);		
	
	public void karteAblegen(IMatch match,IPlayer player, int indexKarte);		
	
	
}
