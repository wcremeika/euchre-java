package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private List<Card> hand;
	
	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<Card>();
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

	@Override
	public String toString() {
		return "Player [name=" + name + ", hand=" + hand + "]\n";
	}
}