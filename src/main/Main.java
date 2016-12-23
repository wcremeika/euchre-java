package main;
import model.EuchreTable;
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
		
		EuchreTable table = new EuchreTable();
		
		table.addTeam(one);
		table.addTeam(two);
		table.dealCards();
		// choose trump
		table.callTrumpFromKitty();
	}
}