
package rummy.matchcenter.impl;

import java.util.ArrayList;
import java.util.List;

import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;

public class Player implements IPlayer {

	private String name;
	private Match match;
	boolean istDran;
	public int index;
	boolean hatGezogen; 

	List<Karte> handKarten = new ArrayList<Karte>();

	Player(Match match, String name) {
		this.name = name;
		this.match = match;
	}

	@Override
	public IMatch currentMatch() {
		return this.match;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Karte> getHandKarten() {
		return this.handKarten;
	}

	@Override
	public boolean getIstDran() {
		return this.istDran;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return this.index;
	}

	@Override
	public boolean getHatGezogen() {
		return this.hatGezogen;
	}
	
	@Override
	public void setHatGezogen(boolean status) {
		System.out.println("setHatGezogen of "+this.getName() +" wurde aufgerufen, und sein Wert of hatGezogen wird gerade auf "+ status + " gesetzt");
		this.hatGezogen = status ; 
	}
	
	@Override
	public void setIstDran(boolean status) {
		this.istDran = status ; 
	}
	
}
