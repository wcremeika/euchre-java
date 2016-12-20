package model;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private List<Player> players = new ArrayList<Player>();
	
	private int score;
	
	public Team() {
		
	}
	
	public Team(List<Player> p) {
		this.players = p;
	}

	public List<Player> getPlayers() {
		return this.players;
	}
	
	public void addPlayer(Player p) {
		this.players.add(p);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Team [players=" + players + ", score=" + score + "]\n";
	}
}