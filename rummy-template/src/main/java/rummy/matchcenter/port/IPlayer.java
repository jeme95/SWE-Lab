package rummy.matchcenter.port;

import java.util.ArrayList;
import java.util.List;

import rummy.matchcenter.impl.Karte;

public interface IPlayer {
	List<Karte> getHandKarten();

	IMatch currentMatch();

	String getName();
	
	boolean getIstDran();
	void setIstDran(boolean status);

	
	int getIndex();
	
	void setIndex(int index) ;
	
	boolean getHatGezogen();
	void setHatGezogen(boolean status);

}
