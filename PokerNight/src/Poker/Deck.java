package Poker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Deck {
	public ArrayList<Card> cards = new ArrayList<>();
	public Random rand = new Random();

	/**
	 * Constructor for objects of class Deck
	 */
	public Deck() {
		for (Suit suit : Suit.values()) {
			for (int i = 2; i < 15; i++) {
				if (i < 11) {
					cards.add(new Card(suit, i, true, Integer.toString(i)));
				} else if (i == 11) {
					cards.add(new Card(suit, 11, true, "Jack"));
				} else if (i == 12) {
					cards.add(new Card(suit, 12, true, "Queen"));
				} else if (i == 13) {
					cards.add(new Card(suit, 13, true, "King"));
				} else {
					cards.add(new Card(suit, 14, true, "Ace"));
				}

			}
		}
		shuffle(cards);
	}

	/**
	 * Deals one card from your deck
	 * 
	 * @return Card from deck
	 */
	public Card deal() {
		return cards.remove((int) (Math.random() * (cards.size() - 1) + 1));
	}

	/**
	 * 
	 */
	protected ArrayList<Card> getCards() {
		return cards;
	}

	public void shuffle(ArrayList<Card> cards) {
		Collections.shuffle(cards);
	}

	@Override
	public String toString() {
		String cardString = "";
		for (Card card : cards) {
			cardString += card.toString() + "\n";
		}
		return cardString;
	}
}
