package Poker;

public class Card {
	public Suit suit;
    public int value;
    public boolean visible;
    public String name = new String();
    /**
     * Constructor for objects of class Cards
     */
    public Card(Suit suit, int value, boolean visible, String name)
    {
        this.suit = suit;
        {
        if (value >= 1 && value <=14)
            this.value = value;
        }
        this.visible = visible;
        this.name = name;
    }
    public Card()
    {
        this.suit =null;
        this.value = 0;
        this.visible = false;
        this.name = "";
    }
    /**
     * Get the card's suit
     */
    public Suit getSuit()
    {
         return suit;
    }
    /**
     * Get the card's point value
     */
    public int getValue()
    {
        return value;
    }
    /**
     * Get the card's name
     */
    public String getName()
    {
        return name;
    }
    /**
     * Sets the card to be seen, so you see the front of a card
     */
    public void show()
    {
        visible = true;
    }
    /**
     * Sets the card to be hidden, so you don't see it.
     */
    public void hide()
    {
        visible = false;
    }
    /**
    *Displays either side, based on the field's value.
    */
   @Override
   public String toString()
   {
       String cardDescription = new String();
       
       if(visible)
       {
           cardDescription = name + "of" + suit;
       }
       else
       {
           cardDescription = "Hidden Card";
       }
       return cardDescription;
    }
}
