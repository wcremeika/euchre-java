package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Suit;

public class Player {
	private String name;
	private Map<Suit,List<Card>> hand;
	
	public Player(String name) {
		this.name = name;
		this.hand = new HashMap<Suit,List<Card>>();
	}

	public String getName() {
		return name;
	}

	public void addCardToHand(Card c) {
		if (this.hand.containsKey(c.getSuit())) {
			this.hand.get(c.getSuit()).add(c);
		}
		else {
			List<Card> newCardList = new ArrayList<>();
			newCardList.add(c);
			this.hand.put(c.getSuit(), newCardList);
		}
	}
	
	public Card removeCardFromHand(Card c) {
		int random = (int) Math.floor(Math.random()*Suit.values().length);
		Suit s = Suit.values()[random];
		
		while (s == c.getSuit() || !this.hand.containsKey(s)) {
			random = (int) Math.floor(Math.random()*Suit.values().length);
			s = Suit.values()[random];
		}
		
		return this.hand.get(s).remove(0);
	}
	
	public Card playCard(Card c) {
		return this.hand.get(c.getSuit()).remove(this.hand.get(c.getSuit()).indexOf(c));
	}

	@Override
	public String toString() {
		return name + "\n\thand=" + hand + "\n\t";
	}

	
}