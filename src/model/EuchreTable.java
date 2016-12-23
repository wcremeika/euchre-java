package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import enums.Rank;
import enums.Suit;
import utils.EuchreComparator;

public class EuchreTable extends Table {
	private Suit trump;
	private List<Team> teams = new ArrayList<Team>();
	private Stack<Card> deck = new Stack<Card>();
	
	private final int PASS = 0;
	
	public void addTeam(Team t) {
		teams.add(t);
		players.addAll(t.getPlayers());
	}

	public void setTrump(Suit t) {
		trump = t;
		EuchreComparator.setTrump(t);
	}
	
	public void dealCards() {
		createAndShuffleDeck();
		int[] cardOptions = {3,2};
		int cardCounter = cardOptions[0];
		
		for (int dealPosition = dealerPosition+1; deck.size() > 4; dealPosition=(dealPosition+1) % players.size()) {
			Player currentPlayer = players.get(dealPosition);
			
			for(int j=0; j < cardCounter;j++) {
				currentPlayer.addCardToHand(deck.pop());
			}
			
			cardCounter = (dealPosition != 0) ? cardOptions[cardCounter == 2 ? 0 : 1] : cardCounter;
		}
	}
	
	private void createAndShuffleDeck() {
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				deck.push(new Card(s,r));
			}
		}
		
		Collections.shuffle(deck);
	}
	
	public void callTrumpFromKitty() {
		Card potentialTrump = deck.pop();
		int currentPosition = lastPlayerToCall();
		
		if (currentPosition == players.size()) {
			playersCallTrump(potentialTrump.getSuit());
		}
		else {
			System.out.println("pickup\n");
			Player dealer = players.get(dealerPosition);
			dealer.addCardToHand(potentialTrump);
			this.deck.push(dealer.removeCardFromHand(potentialTrump));
			this.trump = potentialTrump.getSuit();
		}
	}
	
	public void playersCallTrump(Suit dismissedTrump) {
		System.out.println("no one picked up");
		int currentPosition = lastPlayerToCall();
		Suit newTrump = Suit.values()[(int) Math.floor(Math.random() * 4)];
		
		while (newTrump == dismissedTrump) {
			newTrump = Suit.values()[(int) Math.floor(Math.random() * 4)];
		}
		
		System.out.println(newTrump);
		int trumpTeam = 0;
		
		for (Team team : teams) {
			if (team.hasPlayer(players.get(currentPosition%4))) {
				trumpTeam = this.teams.indexOf(team);
			}
		}
		
		System.out.println(trumpTeam);
	}
	
	public int lastPlayerToCall() {
		int currentPosition = 0;
		int playerChoice = (int) Math.floor(Math.random() * 2);
		
		while(playerChoice == PASS && currentPosition < players.size()) {
			currentPosition++;
			playerChoice = (int) Math.floor(Math.random() * 2); 
		}
		
		return currentPosition;
	}
	
	public int compare(Card c1, Card c2) {
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
	
	public boolean isTrump(Card c) {
		Suit opposite = Suit.valueOf(c.getSuit().getOpposite());
		
		return c.getSuit() == trump ||
				(c.getRank() == Rank.JACK && opposite == trump);
	}
}

enum PlayerChoice {
	PASS,
	PICKUP;
}