package rummy.matchcenter.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.List;

import rummy.matchcenter.impl.Karte.Wert;
import rummy.matchcenter.impl.Karte.Type;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
//import rummy.statemachine.port.StateMachinePort;
import rummy.statemachine.port.State;
import rummy.statemachine.port.StateMachine;
import rummy.statemachine.StateMachineCenter;


public class Match implements IMatch {

	/**
	 * @directed true
	 * @link aggregation
	 * @supplierRole host
	 */

	private Player host;
	private StateMachine stateMachine;
//	private StateMachinePort stateMachinePort;


	public StateMachine getStateMachine() {
		return stateMachine;
	}

	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	/**
	 * @clientCardinality
	 * @clientRole match
	 * @directed true
	 * @link composition
	 * @supplierCardinality 1..MAX
	 * @supplierRole players
	 */
	@SuppressWarnings("unused")
	private rummy.matchcenter.impl.Player lnkPlayer;
	private List<Player> players = new ArrayList<>();

	@SuppressWarnings("unused")
	private int numberOfSeries;
	private int id;

	public static boolean isInArray(int n, int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == n)
				return true;

		}
		return false;
	}

	List<Karte> allKarten = new ArrayList<Karte>();
	List<Karte> offeneKarten = new ArrayList<Karte>();
	Karte obersteOffenerKarte;
	int indexCurrentPlayer;

	@Override
	public void initialiseMatch() {
//		this.stateMachine = 
		System.out.println("GAME STARTED");
		this.createAllKarten(); // Sets erzeugen (2 Sets je 52 Blatt + 2 Joker)
		this.kartenVerteilen(); // Karten auf Spieler verteilen
		this.givePlayersIndexes();
		this.initilisePlayers();

	}

	public void initilisePlayers() {

		for (IPlayer player : this.players) {
			player.setHatGezogen(false);
			player.setIstDran(false);
		}

		this.indexCurrentPlayer = 0;
		this.players.get(0).istDran = true;
		this.players.get(0).setHatGezogen(true);
	}

	@Override
	public int getIndexCurrentPlayer() {
		return this.indexCurrentPlayer;
	}

	@Override
	public Karte getObersteOffenerKarte() {
//		int size = this.getOffeneKarten().size()-1; 
//		return this.getOffeneKarten().get(size);
		return this.obersteOffenerKarte;
	}

	public void setObersteOffenerKarte(Karte karte) {
		this.obersteOffenerKarte = karte;
	}

	public List<Karte> getOffeneKarten() {
		return this.offeneKarten;
	}

	public List<Karte> getAllKarten() {
		return this.allKarten;
	}

	Match(int num, int idx) {
		this.numberOfSeries = num;
		this.id = idx;

	}

	@Override
	public IPlayer getHost() {
		return this.host;
	}

	@Override
	public boolean enoughPlayers() {
		return this.players.size() >= IMatch.MIN;
	}

	int addPlayer(Player player) {
		this.players.add(player);
		return this.players.size();
	}

	void setHost(Player host) {
		this.host = host;
		this.players.add(host);
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public Collection<? extends IPlayer> allPlayers() {
		return new ArrayList<>(this.players);
	}

	@Override
	public void createAllKarten() {
		Type[] Types = { Type.Karo, Type.Herz, Type.Pik, Type.Kreuz };
		Wert[] werte = { Wert.Ass, Wert.Zwei, Wert.Drei, Wert.Vier, Wert.Fünf, Wert.Sechs, Wert.Sieben, Wert.Acht,
				Wert.Neun, Wert.Zehn, Wert.Bube, Wert.Dame, Wert.König };
		int[] notValidValues = { 127148, 127151, 127152, 127164, 127167, 127168, 127180, 127183, 127184, 127196 };

		for (int i = 0, code = 127137; i <= 3; i++) {
			Type t = Types[i];
			for (int j = 0; j < 13; j++) {
				while (isInArray(code, notValidValues)) // überspringe alle ungültigen werte
					code++;
				Karte karte = new Karte(werte[j], t, code);
				Karte karte2 = new Karte(werte[j], t, code);

				this.allKarten.add(karte);
				this.allKarten.add(karte2);
				code++;

			}
		}
		for (int i = 0; i < 4; i++) {
			Karte joker = new Karte(werte[0], Type.Joker, 127199); // code of joker
			this.allKarten.add(joker);
		}
		Collections.shuffle(this.allKarten);
		Collections.shuffle(this.allKarten);

		// eine initale Karte dem offenen Stapel hinzufügen
		Karte initialOffeneKarte = new Karte(Wert.Ass, Type.Joker, 127219);
		this.offeneKarten.add(initialOffeneKarte);
		this.obersteOffenerKarte = initialOffeneKarte;

	}

	@Override
	public void kartenVerteilen() {
//System.out.println("karten Verteilen aufgerufen");
		for (Player player : this.players) { // jeder Spieler bekommt 13 Karten
			for (int j = 0; j < 13; j++) {
				Karte karte = this.allKarten.remove(0);
				player.handKarten.add(karte);
			}
		}
		
		Karte k = this.allKarten.remove(0);
		this.players.get(0).handKarten.add(k);
		/*
		 * Karte k = this.allKarten.remove(0); // Host bekommt 14 => +1
		 * this.players.get(0).handKarten.add(k);
		 */

		for (Player player : this.players) {
			player.istDran = false;

		}

	}

	public void givePlayersIndexes() {
		int i = 0;
		for (Player player : this.players) {
			player.index = i;
			i++;
		}
	}

	@Override
	public void addKarte(IPlayer player) {
		if (player.getHatGezogen() == false) {

			Karte karte = this.getAllKarten().remove(0);
			player.getHandKarten().add(karte);
			player.setHatGezogen(true);
		}
		this.stateMachine.setState(State.S.CanCallFOO);							// *******************************
	}

	public void finishTurn(IPlayer player, int indexKarteSelected) {
//		System.out.println("\n vorher:");
		for (IPlayer player2 : this.players) {
//	System.out.println(player2.getName() +" ist dran ? "+player2.getIstDran() );
//			System.out.println(player2.getName() + " hat gezogen? " + player2.getHatGezogen());
//			System.out.println();
		}

//		System.out.println("\n\n\n Player " +player.getName()+ " mit Index " +player.getIndex()+ " hat finishTurn aufgerufen");
		if (player.getIstDran()) {
			Karte karte = player.getHandKarten().remove(indexKarteSelected);

			this.offeneKarten.add(karte);
			this.obersteOffenerKarte = karte;
//			System.out.println(player.getName() + " ist NICHT mehr dran");
			player.setIstDran(false);
//			System.out.println("player.getIndex() = "+player.getIndex());
//			System.out.println("player.getIndex() +1  = "+player.getIndex() + 1);

			int nextPlayer = (player.getIndex() + 1) % (this.players.size()); // kritisch
//			System.out.println("size = "+this.players.size());
//			System.out.println("nextPlayer ist "+nextPlayer);

//			System.out.println("next player is: " + this.players.get(nextPlayer).getName());
			// give the next player the turn
			this.players.get(nextPlayer).setIstDran(true);
			this.players.get(nextPlayer).setHatGezogen(false);
//			System.out.println("hatGezogen of nextPlayer, also of " + this.players.get(nextPlayer).getName() + " ist "
//					+ this.players.get(nextPlayer).getHatGezogen());

//			System.out.println(this.players.get(nextPlayer).getName() + " ist nun dran ");
			this.indexCurrentPlayer = nextPlayer;
//			System.out.println("indexCurrentPlayer = "+indexCurrentPlayer);
//			System.out.println("finishTurn beendet");
			

		}

		System.out.println("\n nachher:");
//		for (IPlayer player2 : this.players) {
////	System.out.println(player2.getName() +" ist dran ? "+player2.getIstDran() );
//			System.out.println(player2.getName() + " hat gezogen? " + player2.getHatGezogen());
//			System.out.println();
//		}
		this.stateMachine.setState(State.S.CanCallFOO);							// *******************************		
	}

}
