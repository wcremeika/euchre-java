package enums;
public enum Suit {
	HEARTS("DIAMONDS"),
	DIAMONDS("HEARTS"),
	CLUBS("SPADES"),
	SPADES("CLUBS");
	
	private String opposite;
	
	private Suit(String o) {
		this.opposite = o;
	}
	
	public String getOpposite() {
		return this.opposite;
	}
}
