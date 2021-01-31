package rummy.matchcenter.impl;

import rummy.matchcenter.port.IKarte;

public class Karte implements IKarte {

	public Karte(Wert wert, Type typ, int code) {
		this.wert = wert;
		this.typ = typ;
		this.Code = code;
	}

	
	public Wert getWert() {
		return wert;
	}

	public Type getTyp() {
		return typ;
	}

	public int getCode() {
		return Code;
	}



	public void setCode(int code) {
		this.Code = code;
	}

	enum Type {
		Karo, Herz, Pik, Kreuz, Joker
	}

	enum Wert {
		Ass, Zwei, Drei, Vier, Fünf, Sechs, Sieben, Acht, Neun, Zehn, Bube, Dame, König
	}
	
	public Wert wert;
	public Type typ;
	int Code;
	
}
