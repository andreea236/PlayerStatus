public class Main {

	public static void main(String[] args) {
		PlayerStatus player1 = new PlayerStatus();
		PlayerStatus player2 = new PlayerStatus();

		player1.initPlayer("PlayerX", 1, 90000);
		player2.initPlayer("PlayerY", 3, 50000);

		System.out.println(player1.getNickname() + " score: " + player1.getScore());
		System.out.println(player2.getNickname() + " score: " + player2.getScore());

		System.out.println(player1.getNickname() + " lives: " + player1.getLives());
		System.out.println(player2.getNickname() + " lives: " + player2.getLives());

		System.out.println(player1.getNickname() + " health: " + player1.getHealth());
		System.out.println(player2.getNickname() + " health: " + player2.getHealth());

		player1.movePlayerTo(20, 30);
		player2.movePlayerTo(-10, 40);

		player1.setWeaponInHand("kalashnikov");
		player2.setWeaponInHand("sniper");

		player1.movePlayerTo(40, 30);
		player2.movePlayerTo(-10, 20);

		System.out.println(player1.getNickname() + " weapon: " + player1.getWeaponInHand());
		System.out.println(player2.getNickname() + " weapon: " + player2.getWeaponInHand());

		System.out.println(player1.getNickname() + " should attack " + player2.getNickname() + " : "
				+ player1.shouldAttackOpponent(player2));
		System.out.println(player2.getNickname() + " should attack " + player1.getNickname() + " : "
				+ player2.shouldAttackOpponent(player1));

		System.out.println(player1.getNickname() + " distance from " + player2.getNickname() + " : "
				+ player1.distanceFromPlayer(player2));

		player1.findArtifact(36);
		player1.findArtifact(36);
		player1.findArtifact(36);
		player1.findArtifact(36);
		player1.findArtifact(36);
		player1.findArtifact(36);
		player1.findArtifact(36);
		player1.findArtifact(36);
		player1.findArtifact(36);

		player2.findArtifact(28);

		player1.updateLives();
		player2.updateLives();

		System.out.println(player1.getNickname() + " score: " + player1.getScore());
		System.out.println(player2.getNickname() + " score: " + player2.getScore());

		System.out.println(player1.getNickname() + " lives: " + player1.getLives());
		System.out.println(player2.getNickname() + " lives: " + player2.getLives());

		System.out.println(player1.getNickname() + " health: " + player1.getHealth());
		System.out.println(player2.getNickname() + " health: " + player2.getHealth());

		if (player1.isDead() && !player2.isDead())
			System.out.println(player2.getNickname() + " won. \nGAME OVER.");

		if (!player1.isDead() && player2.isDead())
			System.out.println(player1.getNickname() + " won. \nGAME OVER.");
	}
}
