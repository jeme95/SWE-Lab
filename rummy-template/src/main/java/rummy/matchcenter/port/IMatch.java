package rummy.matchcenter.port;

import java.util.Collection;
import java.util.List;

import rummy.matchcenter.impl.Karte;
import rummy.matchcenter.impl.Player;
import rummy.statemachine.port.StateMachine;

public interface IMatch {

	int MAX = 4;
	int MIN = 2;

	IPlayer getHost();

	boolean enoughPlayers();

	int getId();


	public void kartenVerteilen();
	public List<Karte> getAllKarten();
	public List<Karte> getOffeneKarten();
	public void verdecktZiehen(IPlayer player);
	public void createAllKarten() ; 
	public void karteAblegen(IPlayer player, int indexKarteSelected);
	public Karte getObersteOffenerKarte();
	public void setObersteOffenerKarte(Karte karte);
	public int getIndexCurrentPlayer(); 
	public void givePlayersIndexes(); 
	public void initialiseMatch(); 
//	public void setStateMachine(StateMachine stateMachine);
	public void setStateMachine(StateMachine stateMachine); 
	public StateMachine getStateMachine();
	public List<Player> getPlayers();
	public void setPlayers(List<Player> players);
	public void setIndexCurrentPlayer(int indexCurrentPlayer);


	Collection<? extends IPlayer> allPlayers();

}
