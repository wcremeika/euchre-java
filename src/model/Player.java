package model;

import java.util.List;

public class Player {
	private String name;
	private List<Card> hand;
	
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Card> getHand() {
		return hand;
	}
	
	public void addCardToHand(Card c) {
		this.hand.add(c);
	}
	
	public Card playCard(Card c) {
		return this.hand.remove(this.hand.indexOf(c));
	}
}