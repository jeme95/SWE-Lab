package rummy.statemachine.port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface State {
	
	boolean isSubStateOf(State state);

	boolean isSuperStateOf(State state);

	public enum S implements State {
		Initialized, Closed,//
		MustJoin, JoinOrStart, MustStart, Play, CanCallFoo,//
		CanStart(JoinOrStart, MustStart), //
		Join(MustJoin,CanStart),
		MussZiehen, VerdecktGezogen, OffenGezogen, ZugBeendet,
		MussEroeffnen, HatEroeffnet, MussAnlegen, MussJokerTauschen, JokerAufHand,
		MakeAturn(MussZiehen, VerdecktGezogen, OffenGezogen, ZugBeendet),
		MeldingCards(MussEroeffnen, HatEroeffnet, MussAnlegen, MussJokerTauschen, JokerAufHand);

		private List<State> subStates;

		public static final S INITIAL_STATE = Initialized;

		private S(State... subS) {
			this.subStates = new ArrayList<>(Arrays.asList(subS));
		}

		@Override
		public boolean isSuperStateOf(State s) {
			boolean result = (this == null) || (this == s); // self contained
			for (State state : this.subStates) // or
				result |= state.isSuperStateOf(s); // contained in a substate!
			return result;
		}

		@Override
		public boolean isSubStateOf(State state) {
			return state == null ? false : state.isSuperStateOf(this);
		}
	}
	
}
