package rummy.webui.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import rummy.matchcenter.impl.Player;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.webui.views.container.StateContainer;

public class GameView implements IView {

	private static final String TEMPLATE_NAME = "game";
	private static final String NAME_ATTR = "name";
	private static final String MATCHID_ATTR = "matchId";
	private static final String STATE_ATTR = "state";
	private static final String MATCH_ANZAHL_VERDECKTE = "anzahlVerdeckte";
	private static final String MATCH_ANZAHL_OFFENE = "anzahlOffene";
	private static final String MATCH_STAPEL_OBEN_OFFENE = "stapelObenOffene";
	private static final String MATCH_PLAYERS = "players";
	private static final String PLAYER_TURN = "turn";
	private static final String VERDECKT_CODE = "verdeckt";
	private static final String PLAYER_ABLEGEN_MOEGLICH = "ablegenMoeglich";
	private static final String PLAYER_ZIEHEN_MOEGLICH = "ziehenMoeglich";
	private static final String MATCH_INDEX_CURRENT_PLAYER = "indexCurrentPlayer";

	private IPlayer player;
	private IMatch match;
	private Model model;
	private boolean canStop;
	static int anzahlPlayers = 0;
	private List<Player> players;
	private boolean ablegenMoeglich;
	private boolean ziehenMoeglich;

	public GameView(IMatch match, IPlayer player, boolean canStop) {
		this.player = player;
		this.match = match;
		this.canStop = canStop;
		this.players = new ArrayList(this.match.allPlayers());
		this.ablegenMoeglich = this.player.getIstDran() && this.player.getHatGezogen();
		this.ziehenMoeglich = this.player.getIstDran() && !this.player.getHatGezogen();
	}

	@Override
	public String build(Model model) {

		this.model = model;
		this.model.addAttribute(GameView.STATE_ATTR, new StateContainer(this.canStop, false));
		this.model.addAttribute(GameView.MATCHID_ATTR, String.format("%d", this.match.getId()));
		this.model.addAttribute(GameView.NAME_ATTR, this.player.getName());

		this.model.addAttribute(MATCH_PLAYERS, this.players);
		this.model.addAttribute(MATCH_ANZAHL_VERDECKTE, this.match.getAllKarten().size());
		int size = this.match.getOffeneKarten().size();

		this.model.addAttribute(MATCH_ANZAHL_OFFENE, this.match.getOffeneKarten().size());
		this.model.addAttribute(PLAYER_TURN, this.player.getIstDran());
		this.model.addAttribute(VERDECKT_CODE, "&#127136");
		this.model.addAttribute(PLAYER_ABLEGEN_MOEGLICH, this.ablegenMoeglich);
		this.model.addAttribute(PLAYER_ZIEHEN_MOEGLICH, this.ziehenMoeglich);
		int i = this.match.getObersteOffenerKarte().getCode();
		this.model.addAttribute(MATCH_STAPEL_OBEN_OFFENE, i);
		this.model.addAttribute(MATCH_INDEX_CURRENT_PLAYER, this.match.getIndexCurrentPlayer());

		return GameView.TEMPLATE_NAME;
	}
}
