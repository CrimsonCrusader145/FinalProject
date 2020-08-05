package Poker;

import java.util.HashMap;
import java.util.Arrays;

public class Player {
	public String name;
	public int Stash;
	Card[] playerHand = new Card[5];
	int handSize = 0;

	/**
	 * Constructor for objects of class Player
	 */
	public Player() {
		name = "Player";
		this.Stash = 0;
		this.playerHand = new Card[5];
		this.handSize = 0;

	}

	public Player(String name, int Stash, Card[] playerHand) {
		this.name = name;
		this.Stash = Stash;
		this.playerHand = playerHand;
		this.handSize = 0;

	}

	/**
	 * Makes it so that the name is shown.
	 * 
	 * @param name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Allows you to type in the name of the player
	 * 
	 * @return the player's name
	 */
	public String getName() {
		return name;
	}

	public void setStash(int s) {
		this.Stash = s;
	}

	public int getStash() {
		return Stash;
	}

	public void recieveCard(Card c, boolean v) {

		if (this.handSize < 5) {
			if (v == true)
				c.show();
			else if (v == false)
				c.hide();
			this.playerHand[handSize] = c;
			this.handSize++;
		}

	}

	public void clearHand() {
		this.playerHand = new Card[5];
		this.handSize = 0;
	}
}
