import java.util.Arrays;
import java.util.Random;

/**
 * @author vannguyen
 *
 * 
 * 1. Access modifiers (private)restrict access to data at different levels.
 * 2. The most challenging part is to keep track of the card in the deck because it is hard and if you mess up your deck of cards class will boom
 *
 */
public class DeckOfCards implements DeckOfCardsInterface {

	//These are the only instance variables you will need.
	private final int DECKSIZE = 52;
	private Card[] cards;
	private int i=0;
	private int z=0;
	private int nextCardIndex;
	
	/**
	 * constructor
	 *
	 */
	
	public DeckOfCards() {

		cards= new Card[DECKSIZE];
		for(Suit s : Suit.values()) {
			for(FaceValue v : FaceValue.values()) {
				//Create new card and add it to your deck.
				cards[i] = new Card(s, v);
				i++;
			}	

		}
	}

	@Override
	/**
	 * shuffle the whole deck
	 *
	 */
	public void shuffle() {
		// TODO Auto-generated method stub
		Random generator = new Random();

		//Attempt to swap each card with a random card in the deck.
		//This isn't a perfect random shuffle but it is a simple one.
		//A better shuffle requires a more complex algorithm.

		for (int i=0; i< cards.length; i++) {
			int j = generator.nextInt(cards.length);
			Card temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}

		//Reset instance variable that keeps track of dealt and remaining cards.
		nextCardIndex = 0;

	}

	@Override
	/**
	 * draw a card from the deck 
	 *
	 */
	public Card draw() {
		// TODO Auto-generated method stub

		if ( nextCardIndex-1 < cards.length ) {
			// return current Card in array
			return cards[nextCardIndex++]; 
		}
		else        
			return null; // return null to indicate that all Cards were dealt   
	} 

	@Override
	/**
	 * the number of card remaining
	 *
	 */
	public int numCardsRemaining() {
	// TODO Auto-generated method stub
		if (nextCardIndex-1<cards.length) {
			int rem=cards.length-nextCardIndex; 
			return rem ;}
		else 
			return 0;
	}

	@Override
	/**
	 * the number of dealt cards 
	 *
	 */
	
	public int numCardsDealt() {
		// TODO Auto-generated method stub
		return nextCardIndex;
	}

	@Override
	/**
	 * print  all the dealt cards out
	 *
	 */
	public Card[] dealtCards() {
		// TODO Auto-generated method stub
		Card[] drawnCards = Arrays.copyOf(cards,nextCardIndex);
		for(int i=0; i<drawnCards.length;i++) {
			System.out.println(drawnCards[i]);
		}
		return drawnCards;
	}

	@Override
	/**
	 * print all the remained card
	 *
	 */
	public Card[] remainingCards() {
		// TODO Auto-generated method stub
		while(nextCardIndex<cards.length)
			System.out.println(cards[nextCardIndex++]);
		return cards;
	}
	/**
	 * to string method
	 *
	 */
	public String toString() {
		if (cards[0]!=  new Card (Suit.Clubs, FaceValue.Two)){
			System.out.println("Shuffeld deck\n\n");
			for (int i = 0; i<cards.length;i++) {
				System.out.println(cards[i]);}
			return " ";
		}
		else {
			System.out.println("Fresh new deck\n\n");

			for (int i = 0; i<cards.length;i++) {
				System.out.println(cards[i]);}
			return " " ;
		}

	}
}
