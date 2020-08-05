package Poker;

import java.util.concurrent.TimeUnit;
import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class gameScene extends JFrame implements ActionListener {

	// tracks games turns
	static int turns = 0;

	// base frame
	public JFrame frame;

	// JPanels for direction
	JPanel north = new JPanel();
	JPanel south = new JPanel();
	JPanel east = new JPanel();
	JPanel west = new JPanel();
	JPanel center = new JPanel();

	// JButtons
	JButton betBtn = new JButton();
	JButton checkBtn = new JButton();
	JButton foldBtn = new JButton();
	JButton newgameBtn = new JButton();
	JTextField inputBet = new JTextField(10);

	// Player
	JLabel playername = new JLabel();
	JLabel pStash = new JLabel();
	JLabel pBet = new JLabel();

	// Dealer
	JLabel dStash = new JLabel();
	JLabel dBet = new JLabel();
	JLabel dealer = new JLabel();

	// North
	JLabel text1 = new JLabel();
	JLabel text2 = new JLabel();
	JLabel text3 = new JLabel();
	JLabel moneyPot = new JLabel();
	JLabel outcome = new JLabel();
	JLabel winner = new JLabel();

	// Center
	JPanel playerCards = new JPanel();
	JPanel dealerCards = new JPanel();
	JLabel pCard1 = new JLabel();
	JLabel pCard2 = new JLabel();
	JLabel pCard3 = new JLabel();
	JLabel pCard4 = new JLabel();
	JLabel pCard5 = new JLabel();
	JLabel dCard1 = new JLabel();
	JLabel dCard2 = new JLabel();
	JLabel dCard3 = new JLabel();
	JLabel dCard4 = new JLabel();
	JLabel dCard5 = new JLabel();

	// Needed variables
	gCard g = new gCard();
	Card cur;
	Icon temp;

	// GameSetUp
	static pokerTable game = new pokerTable();

	public static void main(String argv[]) throws FileNotFoundException {
		System.out.println("Welcome to Poker!! \n In this Card game, "
				+ "players will recieve a new card every turn where they can "
				+ "opt to either bet more money, check(bet 0) or fold(give up). \n "
				+ "Once all remaining players have 5 cards, the pot of all the bets." + "\n\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Player Name (str):");
		String name = scan.nextLine();
		System.out.println("Enter Player Stash (int): ");
		int stash = scan.nextInt();
		scan.close();

		// set values from user input
		game.player.setName(name);
		game.player.setStash(stash);
		game.dealer.setStash(stash * 5);

		// set up game scene with correct values
		gameScene gs = new gameScene(game);

		// ready to play game
		System.out.println("starting game...");
		System.out.println("\n\n");
		System.out.println("Place initial Ante(bet) in the text field at the bottom of the server.");
	}

	/**
	 * Constructor for objects of class GameScanner
	 */
	public gameScene(pokerTable g) throws FileNotFoundException {
		JFrame board = new JFrame("Blackjack Game Board");
		Container content = board.getContentPane();
		content.setLayout(new BorderLayout());
		north.setLayout(new FlowLayout());
		east.setLayout(new GridLayout(3, 1));
		west.setLayout(new GridLayout(3, 1));
		south.setLayout(new FlowLayout());
		center.setLayout(new GridLayout(2, 1));
		content.add(north, BorderLayout.NORTH);
		content.add(center, BorderLayout.CENTER);
		content.add(west, BorderLayout.WEST);
		content.add(east, BorderLayout.EAST);
		content.add(south, BorderLayout.SOUTH);

		playername.setText(g.player.getName());
		pStash.setText("STASH: " + String.valueOf(g.player.getStash()));
		pBet.setText("Bet: ");

		dealer.setText(g.dealer.getName());
		dStash.setText("STASH: " + String.valueOf(g.dealer.getStash()));
		dBet.setText("Bet: ");

		text1.setText("Money Pot: ");
		moneyPot.setText("$$$");
		text2.setText("CurrentOutcomes: ");
		outcome.setText("Initial bets ");
		text3.setText("   Game Winner: ");
		winner.setText("");

		playerCards.setLayout(new FlowLayout());
		dealerCards.setLayout(new FlowLayout());

		north.add(text1);
		north.add(moneyPot);
		north.add(text2);
		north.add(outcome);
		north.add(text3);
		north.add(winner);

		west.add(playername);
		west.add(pStash);
		west.add(pBet);

		east.add(dealer);
		east.add(dStash);
		east.add(dBet);

		betBtn.setText("ANTE");
		betBtn.addActionListener(this);
		betBtn.setEnabled(true);
		checkBtn.setText("CHECK");
		checkBtn.addActionListener(this);
		checkBtn.setEnabled(false);
		foldBtn.setText("FOLD");
		foldBtn.addActionListener(this);
		foldBtn.setEnabled(false);
		newgameBtn.setText("NEW GAME");
		newgameBtn.addActionListener(this);
		newgameBtn.setEnabled(false);

		south.add(inputBet);
		south.add(betBtn);
		south.add(checkBtn);
		south.add(foldBtn);
		south.add(newgameBtn);
		center.add(playerCards);
		center.add(dealerCards);
		board.setSize(1800, 750);
		board.setVisible(true);
	}

	public void reset() {
		// reset turns - enable bet only
		turns = 0;
		betBtn.setEnabled(true);
		newgameBtn.setEnabled(false);

		// remove all cards from card panes so the correct ones can be added in the new
		// game
		playerCards.remove(pCard1);
		playerCards.remove(pCard2);
		playerCards.remove(pCard3);
		playerCards.remove(pCard4);
		playerCards.remove(pCard5);
		dealerCards.remove(dCard1);
		dealerCards.remove(dCard2);
		dealerCards.remove(dCard3);
		dealerCards.remove(dCard4);
		dealerCards.remove(dCard5);
		center.add(playerCards);
		center.add(dealerCards);

		// reset the players hands
		game.player.clearHand();
		game.dealer.clearHand();

		// reset deck
		game.dealer.resetDeck();

		// update gameScene fields
		outcome.setText("New Game Started -- Taking Initial Bets");
		pBet.setText("Awaiting Bet...");
		dBet.setText("Awaiting Bet...");
		moneyPot.setText("$$$");
		winner.setText("");

		// update game.pot
		game.pot = 0;

	}

	public void actionPerformed(ActionEvent e) {

		// bet placed, need to advance
		if (e.getSource() == betBtn) {
			int valid = 0;
			String s = inputBet.getText();
			int b = Integer.parseInt(s);
			try {
				validate(b, game.player.Stash);
			} catch (Exception ex) {
				System.out.println("Exception Occured : " + ex);
				inputBet.setText("");
				pBet.setText("Invalid bet");
				valid = 1;
				System.out.println("place a new bet");
			}
			if (turns == 0 && valid == 0) {
				pBet.setText(String.valueOf(b));
				dBet.setText(String.valueOf(b));
				game.player.Stash = game.player.Stash - b;
				game.dealer.Stash = game.dealer.Stash - b;
				pStash.setText("STASH: " + String.valueOf(game.player.getStash()));
				dStash.setText("STASH: " + String.valueOf(game.dealer.getStash()));
				System.out.println("Dealing Initial Cards... \n\n");
				game.play();
				checkBtn.setEnabled(true);
				foldBtn.setEnabled(true);
				// show first two cards of player & dealer
				showFirst2();
				inputBet.setText("");
				turns++;
			} else if (turns > 0 && valid == 0) {
				pBet.setText(String.valueOf(b));
				dBet.setText(String.valueOf(b));
				game.player.Stash = game.player.Stash - b;
				game.dealer.Stash = game.dealer.Stash - b;
				pStash.setText("STASH: " + String.valueOf(game.player.getStash()));
				dStash.setText("STASH: " + String.valueOf(game.dealer.getStash()));
				game.hitP();
				game.hitD();
				if (turns + 2 == 3) {
					show3();
				} else if (turns + 2 == 4) {
					show4();
				} else if (turns + 2 == 5) {
					show5();
				}
				inputBet.setText("");
				turns++;
			}

			game.pot = game.pot + b * 2;
			moneyPot.setText(String.valueOf(game.pot));
			outcome.setText("Bet Placed");

		} else if (e.getSource() == checkBtn) {
			game.hitP();
			game.hitD();
			if (turns + 2 == 3) {
				show3();
			} else if (turns + 2 == 4) {
				show4();
			} else if (turns + 2 == 5) {
				show5();
			}
			inputBet.setText("");
			center.revalidate();
			center.repaint();
			turns++;
			outcome.setText("Player Checked -- No raise/bet mode");

		} else if (e.getSource() == foldBtn) {
			// need to give player the loss and reset
			outcome.setText("Player folds -- Dealer wins");
			betBtn.setEnabled(false);
			checkBtn.setEnabled(false);
			foldBtn.setEnabled(false);
			newgameBtn.setEnabled(true);
			game.dealer.setStash(game.pot + game.dealer.Stash);
			dStash.setText("STASH: " + String.valueOf(game.dealer.getStash()));
		}

		else if (e.getSource() == newgameBtn) {
			try {
				center.removeAll();
				center.revalidate();
				center.repaint();
				reset();
			} catch (Exception ex) {
				System.out.println(Thread.currentThread().getStackTrace());
			}
		}
		// need to score
		if (turns == 4) {
			betBtn.setEnabled(false);
			checkBtn.setEnabled(false);
			foldBtn.setEnabled(false);
			newgameBtn.setEnabled(true);

			// show dealers hidden card
			dealerCards.remove(dCard1);
			game.dealer.playerHand[0].show();
			dCard1.setIcon(g.getImage(game.dealer.playerHand[0]));
			dealerCards.add(dCard1);

			System.out.println("Max hand size reached-- scoring hands");
			outcome.setText("Hands full -- Scoring Game");
			int test1 = Wins.valueHand(game.player.playerHand);
			System.out.println("player hand score: " + test1);
			int test2 = Wins.valueHand(game.dealer.playerHand);
			System.out.println("dealer hand score: " + test2);

			// compare test values
			if (test1 < test2) {
				winner.setText("Dealer");
				game.dealer.setStash(game.pot + game.dealer.Stash);
				dStash.setText("STASH: " + String.valueOf(game.dealer.getStash()));
			} else if (test1 > test2) {
				winner.setText("Player");
				game.player.setStash(game.pot + game.player.Stash);
				pStash.setText("STASH: " + String.valueOf(game.player.getStash()));
			} else if (test1 == test2) {
				winner.setText("TIE :0");
			}
		}
	}

	private void showFirst2() {
		// TODO Auto-generated method stub
		cur = game.player.playerHand[0];
		temp = g.getImage(cur);
		pCard1.setIcon(temp);
		cur = game.player.playerHand[1];
		temp = g.getImage(cur);
		pCard2.setIcon(temp);
		cur = game.dealer.playerHand[0];
		temp = g.getImage(cur);
		dCard1.setIcon(temp);
		cur = game.dealer.playerHand[1];
		temp = g.getImage(cur);
		dCard2.setIcon(temp);
		playerCards.add(pCard1);
		playerCards.add(pCard2);
		dealerCards.add(dCard1);
		dealerCards.add(dCard2);
	}

	private void show3() {
		cur = game.player.playerHand[2];
		temp = g.getImage(cur);
		pCard3.setIcon(temp);
		cur = game.dealer.playerHand[2];
		temp = g.getImage(cur);
		dCard3.setIcon(temp);
		playerCards.add(pCard3);
		dealerCards.add(dCard3);
	}

	private void show4() {
		cur = game.player.playerHand[3];
		temp = g.getImage(cur);
		pCard4.setIcon(temp);
		cur = game.dealer.playerHand[3];
		temp = g.getImage(cur);
		dCard4.setIcon(temp);
		playerCards.add(pCard4);
		dealerCards.add(dCard4);
	}

	private void show5() {
		cur = game.player.playerHand[4];
		temp = g.getImage(cur);
		pCard5.setIcon(temp);
		cur = game.dealer.playerHand[4];
		temp = g.getImage(cur);
		dCard5.setIcon(temp);
		playerCards.add(pCard5);
		dealerCards.add(dCard5);
	}

	public void validate(int n, int s) throws IllegalBet {

		if (n > s) {
			throw new IllegalBet("Illegal Bet -- Bet Larger Than Stash");
		} else if (n < 0) {
			throw new IllegalBet("Illegal Bet -- Bet Is Negative");
		} else {
			System.out.println("Valid Bet");
		}
	}
}
