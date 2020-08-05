package Poker;

public class Dealer extends Player{

	    public Deck dealersDeck;
	    /**
	     * Constructor for objects of class Dealer
	     */
	    public Dealer()
	    {
	        this.dealersDeck = new Deck();
	    }
	    public Dealer(String name, int stash)
	    {
	        this.name = name;
	        this.Stash = stash;
	        this.playerHand = new Card[5];
	        this.dealersDeck = new Deck();
	    }
	    
	    /**
	     * An example of a method - replace this comment with your own
	     *
	     * @param  y  a sample parameter for a method
	     * @return    the sum of x and y
	     */
	    public void dealCardToPlayer(Player p)
	    {
	        Card dealCard = dealersDeck.deal();
	        if(p.handSize < 5) 
	        {            
	            p.playerHand[p.handSize] = dealCard;
	            p.handSize = p.handSize + 1;
	        }
	    }
	    public void recieveCard(Card c, boolean v)
	    {
	        
	        if(this.handSize < 5 )
	        {
	            if(v == true)
	                c.show();
	            else if (v == false)
	                c.hide();
	            this.playerHand[handSize] = c;
	            this.handSize++;
	        }
	        
	    }
	    public Card deal()
	    {
	        return dealersDeck.deal();
	    }
	    public void resetDeck()
	    {
	        this.dealersDeck = new Deck();   
	    }
	}

