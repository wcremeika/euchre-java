package utils;

import java.util.ArrayList;
import java.util.List;

import enums.Rank;
import enums.Suit;
import model.Card;

public class EuchreComparator {
	private static Suit trump;
	private static List<Card> trumpCards = new ArrayList<Card>();
	
	public static void setTrump(Suit t) {
		trump = t;
		Rank[] ranks = Rank.values();
		
 		for (int i=0; i < ranks.length; i++) {
 			if (ranks[i] != Rank.JACK) {
 				trumpCards.add(new Card(trump,ranks[i]));
 			}
		}
 		
 		trumpCards.add(new Card(Suit.valueOf(trump.getOpposite()), Rank.JACK));
 		trumpCards.add(new Card(trump, Rank.JACK));
 		
 		for (int i=0; i < trumpCards.size(); i++) {
 			System.out.println(trumpCards.get(i));
 		}
	}
	
	public static int compare(Card c1, Card c2) {
		int idx1 = trumpCards.indexOf(c1);
		int idx2 = trumpCards.indexOf(c2);
		
		if (idx1 > idx2) {
			return 1;
		}
		
		return 0;
	}
}