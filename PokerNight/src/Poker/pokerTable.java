package Poker;

public class pokerTable {
	Player player;
	Dealer dealer;
	public int pot;

	// Default constructor
	public pokerTable() {
		pot = 0;
		player = new Player();
		dealer = new Dealer("Dealer", 0);
	}

	public pokerTable(Player p, Dealer d) {
		pot = 0;
		player = new Player();
		dealer = new Dealer("Dealer", player.getStash() * 5);
	}

	public void play() {
		// deal initial code
		player.recieveCard(dealer.deal(), true);
		dealer.recieveCard(dealer.deal(), false);
		player.recieveCard(dealer.deal(), true);
		dealer.recieveCard(dealer.deal(), true);
	}

	public void hitP() {
		player.recieveCard(dealer.deal(), true);
	}

	public void hitD() {
		dealer.recieveCard(dealer.deal(), true);
	}
}
