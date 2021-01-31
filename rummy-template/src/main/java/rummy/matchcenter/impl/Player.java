
package rummy.matchcenter.impl;

import java.util.ArrayList;
import java.util.List;

import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;

public class Player implements IPlayer {

	private String name;
	
	/**
	 * @directed true
	 * @link aggregation
	 * @supplierRole match
	 */
	private Match match;
	boolean istDran;
	public int index;
	boolean hatGezogen; 
	
	/**
	 * @clientCardinality
	 * @clientRole player
	 * @directed true
	 * @link composition
	 * @supplierCardinality 0..14
	 * @supplierRole handKarten
	 */
	
	@SuppressWarnings("unused")
	private rummy.matchcenter.impl.Karte lnkKarte;
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
		this.hatGezogen = status ; 
	}
	
	@Override
	public void setIstDran(boolean status) {
		this.istDran = status ; 
	}
}
