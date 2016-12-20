package model;

import java.util.Stack;

public class Deck {
	private Stack<Card> cards;
	
	public Card topCard() {
		return cards.pop();
	}
}