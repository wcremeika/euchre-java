package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import enums.Rank;
import enums.Suit;
import model.Card;
import model.Player;
import model.Team;

public class Main {

	public static void main(String[] args) {
		Player wes = new Player("Wes");
		Player casey = new Player("Casey");
		Team one = new Team();
		one.addPlayer(wes);
		one.addPlayer(casey);
		
		Player alice = new Player("Alice");
		Player billy = new Player("Billy");
		Team two = new Team();
		two.addPlayer(alice);
		two.addPlayer(billy);
		
		GameManager.addTeam(one);
		GameManager.addTeam(two);
		GameManager.dealCards();
	}
}

class GameManager {
	private static List<Team> teams = new ArrayList<Team>();
	private static Suit trump;
	private static Stack<Card> deck = new Stack<Card>();
	
	public static void addTeam(Team t) {
		teams.add(t);
	}

	public static void setTrump(Suit t) {
		trump = t;
		EuchreComparator.setTrump(t);
	}
	
	public static void dealCards() {
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				deck.push(new Card(s,r));
			}
		}
		
//		System.out.println(deck);
		Collections.shuffle(deck);
//		System.out.println(deck);
		int ctr = 3;
		int teamCounter = 0;
		int playerCounter = 0;
		for (int i = 0; i < 20; i+=ctr) {
			for(int j=0; j < ctr;j++) {
				teams.get(teamCounter%teams.size()).getPlayers().get(playerCounter%teams.size()).addCardToHand(deck.pop());
			}
			
			teamCounter = (teamCounter + 1) % 2;
			playerCounter ^= teamCounter;
			ctr = (ctr == 3) ?  2 : 3;
		}
		
		System.out.println(deck);
		System.out.println(teams);
	}
	
	public static int compare(Card c1, Card c2) {
		if (isTrump(c1) && isTrump(c2)) {
			return EuchreComparator.compare(c1, c2);
		}
		else if (c1.getSuit() == trump && c2.getSuit() != trump) {
			return 1;
		}
		else if (c1.getSuit() != trump && c2.getSuit() == trump) {
			return -1;
		}
		else {
			return c1.getRank().compareTo(c2.getRank());
		}
	}
	
	public static boolean isTrump(Card c) {
		Suit opposite = Suit.valueOf(c.getSuit().getOpposite());
		
		return c.getSuit() == trump ||
				(c.getRank() == Rank.JACK && opposite == trump);
	}
}

class EuchreComparator {
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