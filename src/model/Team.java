package model;

public class Team {
	private Player p1;
	private Player p2;
	
	private int score;
	
	public Team(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Player getP1() {
		return p1;
	}
	
	public Player getP2() {
		return p2;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}