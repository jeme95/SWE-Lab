package rummy.melding_cards;

import org.springframework.stereotype.Component;

import rummy.melding_cards.impl.Melding_cards_impl;
import rummy.melding_cards.port.Melding_cards_provided;

@Component
public class MeldingCardsFassade implements Melding_cards_provided{
	
	private Melding_cards_impl melding_cards_impl ; 
	
	public void foo(){this.melding_cards_impl.foo();}

	
}
