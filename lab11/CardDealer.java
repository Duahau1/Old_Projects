
public class CardDealer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeckOfCards deck = new DeckOfCards();
		System.out.println(deck +"\n");
		deck.shuffle();
		System.out.println(deck);
		Card x,y;
		for (int i=0;i<5;i++) {
			System.out.println("\rDrawing Cards...");	
				// p1
				x=	deck.draw();
				System.out.println("Playe 1: "+x);
				// p2
				y=deck.draw();
				System.out.println("Player 2: "+ y);
				if (x.compareTo(y)>0) {
					System.out.println("Player 1 wins !");
				}
				else System.out.println("Player 2 wins !");
			
		}
		System.out.println(deck.numCardsDealt());
		System.out.println(deck.numCardsRemaining());
		
		deck.dealtCards();
		deck.remainingCards();
	
	}

}
