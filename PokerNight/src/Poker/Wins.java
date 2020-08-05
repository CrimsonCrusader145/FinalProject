package Poker;

import java.awt.*;
import javax.swing.*;
import java.io.FileNotFoundException;

public class Wins extends Card {
	public static final int STRAIGHT_FLUSH = 8000000;
	// + valueHighCard()
	public static final int FOUR_OF_A_KIND = 7000000;
	// + Quads Card Rank
	public static final int FULL_HOUSE = 6000000;
	// + SET card rank
	public static final int FLUSH = 5000000;
	// + valueHighCard()
	public static final int STRAIGHT = 4000000;
	// + valueHighCard()
	public static final int SET = 3000000;
	// + Set card value
	public static final int TWO_PAIRS = 2000000;
	// + High2*14^4+ Low2*14^2 + card
	public static final int ONE_PAIR = 1000000;
	// + high*14^2 + high2*14^1 + low

	/***********************************************************
	 * Methods used to determine a certain Poker hand
	 ***********************************************************/

	/*
	 * -------------------------------------------------------- valueHand(): return
	 * value of a hand --------------------------------------------------------
	 */
	public static int valueHand(Card[] h) {
		if (isFlush(h) && isStraight(h))
			return valueStraightFlush(h);
		else if (is4s(h))
			return valueFourOfAKind(h);
		else if (isFullHouse(h))
			return valueFullHouse(h);
		else if (isFlush(h))
			return valueFlush(h);
		else if (isStraight(h))
			return valueStraight(h);
		else if (is3s(h))
			return valueSet(h);
		else if (is22s(h))
			return valueTwoPairs(h);
		else if (is2s(h))
			return valueOnePair(h);
		else
			return valueHighCard(h);
	}

	public static int valueStraightFlush(Card[] h) {
		return STRAIGHT_FLUSH + valueHighCard(h);
	}

	public static int valueFlush(Card[] h) {
		return FLUSH + valueHighCard(h);
	}

	public static int valueStraight(Card[] h) {
		return STRAIGHT + valueHighCard(h);
	}

	public static int valueFourOfAKind(Card[] h) {
		sortByRank(h);

		return FOUR_OF_A_KIND + h[2].value;
	}

	public static int valueFullHouse(Card[] h) {
		sortByRank(h);

		return FULL_HOUSE + h[2].value;
	}

	public static int valueSet(Card[] h) {
		sortByRank(h);

		return SET + h[2].value;
	}

	public static int valueTwoPairs(Card[] h) {
		int val = 0;

		sortByRank(h);

		if (h[0].value == h[1].value && h[2].value == h[3].value)
			val = 14 * 14 * h[2].value + 14 * h[0].value + h[4].value;
		else if (h[0].value == h[1].value && h[3].value == h[4].value)
			val = 14 * 14 * h[3].value + 14 * h[0].value + h[2].value;
		else
			val = 14 * 14 * h[3].value + 14 * h[1].value + h[0].value;

		return TWO_PAIRS + val;
	}

	public static int valueOnePair(Card[] h) {
		int val = 0;

		sortByRank(h);

		if (h[0].value == h[1].value)
			val = 14 * 14 * 14 * h[0].value + +h[2].value + 14 * h[3].value + 14 * 14 * h[4].value;
		else if (h[1].value == h[2].value)
			val = 14 * 14 * 14 * h[1].value + +h[0].value + 14 * h[3].value + 14 * 14 * h[4].value;
		else if (h[2].value == h[3].value)
			val = 14 * 14 * 14 * h[2].value + +h[0].value + 14 * h[1].value + 14 * 14 * h[4].value;
		else
			val = 14 * 14 * 14 * h[3].value + +h[0].value + 14 * h[1].value + 14 * 14 * h[2].value;

		return ONE_PAIR + val;
	}

	/*
	 * ----------------------------------------------------- valueHighCard(): return
	 * value of a high card hand
	 * 
	 * value = 14^4*highestCard + 14^3*2ndHighestCard + 14^2*3rdHighestCard +
	 * 14^1*4thHighestCard + LowestCard
	 * -----------------------------------------------------
	 */
	public static int valueHighCard(Card[] h) {
		int val;

		sortByRank(h);

		val = h[0].value + 14 * h[1].value + 14 * 14 * h[2].value + 14 * 14 * 14 * h[3].value
				+ 14 * 14 * 14 * 14 * h[4].value;

		return val;
	}

	/***********************************************************
	 * Methods used to determine a certain Poker hand
	 ***********************************************************/

	/*
	 * --------------------------------------------- is4s(): true if h has 4 of a
	 * kind false otherwise ---------------------------------------------
	 */
	public static boolean is4s(Card[] h) {
		boolean a1, a2;

		if (h.length != 5)
			return (false);

		sortByRank(h);

		a1 = h[0].value == h[1].value && h[1].value == h[2].value && h[2].value == h[3].value;

		a2 = h[1].value == h[2].value && h[2].value == h[3].value && h[3].value == h[4].value;

		return (a1 || a2);
	}

	/*
	 * ---------------------------------------------------- isFullHouse(): true if h
	 * has Full House false otherwise
	 * ----------------------------------------------------
	 */
	public static boolean isFullHouse(Card[] h) {
		boolean a1, a2;

		if (h.length != 5)
			return (false);

		sortByRank(h);

		a1 = h[0].value == h[1].value && // x x x y y
				h[1].value == h[2].value && h[3].value == h[4].value;

		a2 = h[0].value == h[1].value && // x x y y y
				h[2].value == h[3].value && h[3].value == h[4].value;

		return (a1 || a2);
	}

	/*
	 * ---------------------------------------------------- is3s(): true if h has 3
	 * of a kind false otherwise
	 **** 
	 * Note: use is3s() ONLY if you know the hand does not have 4 of a kind
	 * ----------------------------------------------------
	 */
	public static boolean is3s(Card[] h) {
		boolean a1, a2, a3;

		if (h.length != 5)
			return (false);

		if (is4s(h) || isFullHouse(h))
			return (false); // The hand is not 3 of a kind (but better)

		/*
		 * ---------------------------------------------------------- Now we know the
		 * hand is not 4 of a kind or a full house !
		 * ----------------------------------------------------------
		 */
		sortByRank(h);

		a1 = h[0].value == h[1].value && h[1].value == h[2].value;

		a2 = h[1].value == h[2].value && h[2].value == h[3].value;

		a3 = h[2].value == h[3].value && h[3].value == h[4].value;

		return (a1 || a2 || a3);
	}

	/*
	 * ----------------------------------------------------- is22s(): true if h has
	 * 2 pairs false otherwise
	 **** 
	 * Note: use is22s() ONLY if you know the hand does not have 3 of a kind or
	 * better -----------------------------------------------------
	 */
	public static boolean is22s(Card[] h) {
		boolean a1, a2, a3;

		if (h.length != 5)
			return (false);

		if (is4s(h) || isFullHouse(h) || is3s(h))
			return (false); // The hand is not 2 pairs (but better)

		sortByRank(h);

		a1 = h[0].value == h[1].value && h[2].value == h[3].value;

		a2 = h[0].value == h[1].value && h[3].value == h[4].value;

		a3 = h[1].value == h[2].value && h[3].value == h[4].value;

		return (a1 || a2 || a3);
	}

	/*
	 * ----------------------------------------------------- is2s(): true if h has
	 * one pair false otherwise
	 **** 
	 * Note: use is22s() ONLY if you know the hand does not have 2 pairs or better
	 * -----------------------------------------------------
	 */
	public static boolean is2s(Card[] h) {
		boolean a1, a2, a3, a4;

		if (h.length != 5)
			return (false);

		if (is4s(h) || isFullHouse(h) || is3s(h) || is22s(h))
			return (false); // The hand is not one pair (but better)

		sortByRank(h);

		a1 = h[0].value == h[1].value;
		a2 = h[1].value == h[2].value;
		a3 = h[2].value == h[3].value;
		a4 = h[3].value == h[4].value;

		return (a1 || a2 || a3 || a4);
	}

	/*
	 * --------------------------------------------- isFlush(): true if h has a
	 * flush false otherwise ---------------------------------------------
	 */
	public static boolean isFlush(Card[] h) {
		if (h.length != 5)
			return (false);

		sortBySuit(h);

		return (h[0].suit == h[4].suit); // All Card has same suit
	}

	/*
	 * --------------------------------------------- isStraight(): true if h is a
	 * Straight false otherwise ---------------------------------------------
	 */
	public static boolean isStraight(Card[] h) {
		int i, testRank;

		if (h.length != 5)
			return (false);

		sortByRank(h);

		/*
		 * =========================== Check if hand has an Ace
		 * ===========================
		 */
		if (h[4].value == 14) {
			/*
			 * ================================= Check straight using an Ace
			 * =================================
			 */
			boolean a = h[0].value == 2 && h[1].value == 3 && h[2].value == 4 && h[3].value == 5;
			boolean b = h[0].value == 10 && h[1].value == 11 && h[2].value == 12 && h[3].value == 13;

			return (a || b);
		} else {
			/*
			 * =========================================== General case: check for
			 * increasing values ===========================================
			 */
			testRank = h[0].value + 1;

			for (i = 1; i < 5; i++) {
				if (h[i].value != testRank)
					return (false); // Straight failed...

				testRank++;
			}

			return (true); // Straight found !
		}
	}

	/*
	 * =========================================================== Helper methods
	 * ===========================================================
	 */

	/*
	 * --------------------------------------------- Sort hand by rank:
	 * 
	 * smallest ranked card first ....
	 * 
	 * (Finding a straight is eaiser that way)
	 * ---------------------------------------------
	 */
	public static void sortByRank(Card[] h) {
		int i, j, min_j;

		/*
		 * --------------------------------------------------- The selection sort
		 * algorithm ---------------------------------------------------
		 */
		for (i = 0; i < h.length; i++) {
			/*
			 * --------------------------------------------------- Find array element with
			 * min. value among h[i], h[i+1], ..., h[n-1]
			 * ---------------------------------------------------
			 */
			min_j = i; // Assume elem i (h[i]) is the minimum

			for (j = i + 1; j < h.length; j++) {
				if (h[j].value < h[min_j].value) {
					min_j = j; // We found a smaller minimum, update min_j
				}
			}

			/*
			 * --------------------------------------------------- Swap a[i] and a[min_j]
			 * ---------------------------------------------------
			 */
			Card help = h[i];
			h[i] = h[min_j];
			h[min_j] = help;
		}
	}

	/*
	 * --------------------------------------------- Sort hand by suit:
	 * 
	 * smallest suit card first ....
	 * 
	 * (Finding a flush is eaiser that way)
	 * ---------------------------------------------
	 */
	public static void sortBySuit(Card[] h) {
		int i, j, min_j;

		/*
		 * --------------------------------------------------- The selection sort
		 * algorithm ---------------------------------------------------
		 */
		for (i = 0; i < h.length; i++) {
			/*
			 * --------------------------------------------------- Find array element with
			 * min. value among h[i], h[i+1], ..., h[n-1]
			 * ---------------------------------------------------
			 */
			min_j = i; // Assume elem i (h[i]) is the minimum

			for (j = i + 1; j < h.length; j++) {
				if (h[j].suit.compareTo(h[min_j].suit)<= 0){
					min_j = j; // We found a smaller minimum, update min_j
				}
			}

			/*
			 * --------------------------------------------------- Swap a[i] and a[min_j]
			 * ---------------------------------------------------
			 */
			Card help = h[i];
			h[i] = h[min_j];
			h[min_j] = help;
		}
	}
}
