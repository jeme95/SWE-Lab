package rummy.matchcenter.port;

import java.util.Collection;
import java.util.List;

import rummy.matchcenter.impl.Karte;
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
	public void addKarte(IPlayer player);
	public void createAllKarten() ; 
	public void finishTurn(IPlayer player, int indexKarteSelected);
	public Karte getObersteOffenerKarte();
	public int getIndexCurrentPlayer(); 
	public void givePlayersIndexes(); 
	public void initialiseMatch(); 
	public void setStateMachine(StateMachine stateMachine);
	
	Collection<? extends IPlayer> allPlayers();

}
