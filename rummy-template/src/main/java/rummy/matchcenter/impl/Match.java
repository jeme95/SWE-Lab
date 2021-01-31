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

	
/*#rummy.matchcenter.impl.Karte lnkKarte*/


/**
	 * @directed true
	 * @link aggregation
	 * @supplierRole host
	 */
	private Player host;
	
	/**
	 * @directed true
	 * @link composition
	 * @supplierRole stateMachine
	 */
	public StateMachine stateMachine;

	@Override
	public StateMachine getStateMachine() {
		return stateMachine;
	}

	@Override
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
	public List<Player> players = new ArrayList<>();

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

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
	/**
	 * @clientCardinality
	 * @clientRole match
	 * @directed true
	 * @link composition
	 * @supplierCardinality 0..110
	 * @supplierRole allKarten
	 */
	public List<Karte> allKarten = new ArrayList<Karte>();
	
	
	/**
	 * @clientCardinality
	 * @clientRole match
	 * @directed true
	 * @link composition
	 * @supplierCardinality 0..110
	 * @supplierRole offeneKarten
	 */
	public List<Karte> offeneKarten = new ArrayList<Karte>();
	
	/**
	 * @directed true
	 * @link composition
	 * @supplierRole obersteOffenerKarte
	 */
	public Karte obersteOffenerKarte = new Karte(Wert.Ass, Type.Joker, 127219);
	public int indexCurrentPlayer;

	@Override
	public void initialiseMatch() {

		this.createAllKarten();
		this.kartenVerteilen();
		this.givePlayersIndexes();
		this.initilisePlayers();
		this.stateMachine.setState(State.S.VerdecktGezogen);

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

	public void setIndexCurrentPlayer(int indexCurrentPlayer) {
		this.indexCurrentPlayer = indexCurrentPlayer;
	}

	@Override
	public Karte getObersteOffenerKarte() {
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
				while (isInArray(code, notValidValues))
					code++;
				Karte karte = new Karte(werte[j], t, code);
				Karte karte2 = new Karte(werte[j], t, code);

				this.allKarten.add(karte);
				this.allKarten.add(karte2);
				code++;

			}
		}
		for (int i = 0; i < 4; i++) {
			Karte joker = new Karte(werte[0], Type.Joker, 127199);
			this.allKarten.add(joker);
		}
		Collections.shuffle(this.allKarten);
		Collections.shuffle(this.allKarten);
	}

	@Override
	public void kartenVerteilen() {
		for (Player player : this.players) {
			for (int j = 0; j < 13; j++) {
				Karte karte = this.allKarten.remove(0);
				player.handKarten.add(karte);
			}
		}

		Karte k = this.allKarten.remove(0);
		this.players.get(0).handKarten.add(k);
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
	public void verdecktZiehen(IPlayer player) {
		if (player.getHatGezogen() == false) {

			Karte karte = this.getAllKarten().remove(0);
			player.getHandKarten().add(karte);
			player.setHatGezogen(true);
		}
		this.stateMachine.setState(State.S.VerdecktGezogen);
	}

	public void karteAblegen(IPlayer player, int indexKarteSelected) {
		
		if (player.getIstDran()) {
			Karte karte = player.getHandKarten().remove(indexKarteSelected);

			this.offeneKarten.add(karte);
			this.obersteOffenerKarte = karte;
			player.setIstDran(false);

			int nextPlayer = (player.getIndex() + 1) % (this.players.size());

			this.players.get(nextPlayer).setIstDran(true);
			this.players.get(nextPlayer).setHatGezogen(false);

			this.indexCurrentPlayer = nextPlayer;

		}

		this.stateMachine.setState(State.S.ZugBeendet);
		this.stateMachine.setState(State.S.MussZiehen);

	}

}