package Poker;

import java.awt.*;
import javax.swing.*;
import java.io.FileNotFoundException;

public class gCard extends Card {
	public ImageIcon cardimage;
	

	// C:/Users/crusa/Downloads/cards/png/
	// C:/Users/crusa/Downloads/cards/png/back.png
	public gCard()
	    {
	        
	    }

	public ImageIcon getImage(Card c) {
		Suit Spades = suit.Spades;
		Suit Clubs = suit.Clubs;
		Suit Diamonds = suit.Diamonds;
		Suit Hearts = suit.Hearts;
		try {

			if (c.visible == false) {
				cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/back.png");
				return cardimage;
			}
			if (c.getSuit() == Clubs) {
				if (c.getValue() == 2)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/2_of_clubs.png");
				if (c.getValue() == 3)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/3_of_clubs.png");
				if (c.getValue() == 4)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/4_of_clubs.png");
				if (c.getValue() == 5)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/5_of_clubs.png");
				if (c.getValue() == 6)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/6_of_clubs.png");
				if (c.getValue() == 7)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/7_of_clubs.png");
				if (c.getValue() == 8)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/8_of_clubs.png");
				if (c.getValue() == 9)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/9_of_clubs.png");
				if(c.getValue() == 10)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/10_of_clubs.png");
	            if(c.getValue() == 11)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/jack_of_clubs.png");
	            if(c.getValue() == 12)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/queen_of_clubs.png");
	            if(c.getValue() == 13)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/king_of_clubs.png");
	            if(c.getValue() == 14)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/ace_of_clubs.png");
				return cardimage;

			} else if (c.getSuit() == Spades) {
				if (c.getValue() == 2)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/2_of_spades.png");
				if (c.getValue() == 3)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/3_of_spades.png");
				if (c.getValue() == 4)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/4_of_spades.png");
				if (c.getValue() == 5)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/5_of_spades.png");
				if (c.getValue() == 6)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/6_of_spades.png");
				if (c.getValue() == 7)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/7_of_spades.png");
				if (c.getValue() == 8)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/8_of_spades.png");
				if (c.getValue() == 9)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/9_of_spades.png");
				if(c.getValue() == 10)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/10_of_spades.png");
	            if(c.getValue() == 11)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/jack_of_spades.png");
	            if(c.getValue() == 12)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/queen_of_spades.png");
	            if(c.getValue() == 13)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/king_of_spades.png");
	            if(c.getValue() == 14)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/ace_of_spades.png");
				return cardimage;
			} else if (c.getSuit() == Diamonds) {
				if (c.getValue() == 2)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/2_of_diamonds.png");
				if (c.getValue() == 3)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/3_of_diamonds.png");
				if (c.getValue() == 4)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/4_of_diamonds.png");
				if (c.getValue() == 5)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/5_of_diamonds.png");
				if (c.getValue() == 6)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/6_of_diamonds.png");
				if (c.getValue() == 7)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/7_of_diamonds.png");
				if (c.getValue() == 8)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/8_of_diamonds.png");
				if (c.getValue() == 9)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/9_of_diamonds.png");
				if(c.getValue() == 10)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/10_of_diamonds.png");
	            if(c.getValue() == 11)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/jack_of_diamonds.png");
	            if(c.getValue() == 12)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/queen_of_diamonds.png");
	            if(c.getValue() == 13)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/king_of_diamonds.png");
	            if(c.getValue() == 14)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/ace_of_diamonds.png");
				return cardimage;
			} else if (c.getSuit() == Hearts) {
				if (c.getValue() == 2)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/2_of_hearts.png");
				if (c.getValue() == 3)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/3_of_hearts.png");
				if (c.getValue() == 4)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/4_of_hearts.png");
				if (c.getValue() == 5)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/5_of_hearts.png");
				if (c.getValue() == 6)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/6_of_hearts.png");
				if (c.getValue() == 7)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/7_of_hearts.png");
				if (c.getValue() == 8)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/8_of_hearts.png");
				if (c.getValue() == 9)
					cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/9_of_hearts.png");
				if(c.getValue() == 10)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/10_of_hearts.png");
	            if(c.getValue() == 11)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/jack_of_hearts.png");
	            if(c.getValue() == 12)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/queen_of_hearts.png");
	            if(c.getValue() == 13)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/king_of_hearts.png");
	            if(c.getValue() == 14)
	                cardimage = new ImageIcon("C:/Users/crusa/Downloads/cards/png/ace_of_hearts.png");
				return cardimage;
			}
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getStackTrace());
			throw e;
		}
		return cardimage;

	}
}
