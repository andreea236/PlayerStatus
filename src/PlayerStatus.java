

import java.lang.Math;

public class PlayerStatus {
	String nickname;
	int score;
	int lives = 3;
	int health = 100;
	String weaponInHand = "knife";
	// knife = 1000;
	// sniper = 10000;
	// kalashnikov = 20000;
	double positionX = 0;
	double positionY = 0;
	static String gameName;

	public void initPlayer(String nickname) {
		this.nickname = nickname;
	}

	public void initPlayer(String nickname, int lives) {
		this.nickname = nickname;
		this.lives = lives;
	}

	public void initPlayer(String nickname, int lives, int score) {
		this.nickname = nickname;
		this.lives = lives;
		this.score = score;
	}

	public void updateLives() {
		if (lives <= 0)
			return;
		if (health <= 0) {
			health = 100;
			lives--;
		} else if (health > 100) {
			health = 100;
		}
	}

	public int getHealth() {
		return this.health;
	}

	public boolean isDead() {
		return (this.lives <= 0) && (this.health <= 0);
	}

	public int getScore() {
		return this.score;
	}

	public int getLives() {
		return this.lives;
	}

	public void findArtifact(int artifactCode) {
		boolean artifactPerfect = true, artifactPrim = true, artifactCapcana = true;
		// daca artifactCode este numar perfect
		int sumPerfectNr = 0;
		for (int i = 1; i <= artifactCode / 2; i++) {
			if (artifactCode % i == 0)
				sumPerfectNr += i;
		}
		if (sumPerfectNr == artifactCode) {
			score = score + 5000;
			lives = lives + 1;
			health = 100;
			artifactPerfect = false;
			System.out.println(this.getNickname() + " has acquired the perfect artifact.");
		}

		// daca artifactCode este numar prim:
		if (artifactPerfect) {
			boolean prim = true;
			for (int i = 2; i <= artifactCode / 2; ++i) {
				if (artifactCode % i == 0) {
					prim = false;
					break;
				}
			}
			if (prim) {
				score = score + 1000;
				lives = lives + 2;
				health = health + 25;
				artifactPrim = false;
				System.out.println(this.getNickname() + " has acquired the prime artifact.");
			}
		}
		// daca artifactCode este numar par si suma cifrelor sale este divizibila cu 3
		// atunci obiectul respectiv este o capcana
		if (artifactPerfect && artifactPrim) {
			int artifactCopy = artifactCode;
			int sumArtifact = 0;
			do {
				sumArtifact += artifactCopy;
				artifactCopy /= 10;
			} while (artifactCopy != 0);

			if (artifactCode % 2 == 0 && sumArtifact % 3 == 0) {
				score = score - 3000;
				health = health - 25;
				artifactCapcana = false;
				System.out.println(this.getNickname() + " fell into a trap.");
			}
		}

		if (health < 0) {
			lives--;
			health = 100;
		} else if (health > 100)
			health = 100;

		if (!artifactPerfect && !artifactPrim && !artifactCapcana) {
			score = score + artifactCode;
		}
	}

	public boolean setWeaponInHand(String weaponInHand) {

		switch (weaponInHand) {
		case "knife":
			if (score >= 1000) {
				this.weaponInHand = weaponInHand;
				score -= 1000;
			}
			break;
		case "sniper":
			if (score >= 10000) {
				this.weaponInHand = weaponInHand;
				score -= 10000;
			}
			break;
		case "kalashnikov":
			if (score >= 20000) {
				this.weaponInHand = weaponInHand;
				score -= 20000;
			}
			break;
		default:
			return false;
		}
		return true;
	}

	public String getWeaponInHand() {
		return this.weaponInHand;
	}

	public double getPositionX() {
		return this.positionX;
	}

	public double getPositionY() {
		return this.positionY;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public static String getGameName() {
		return gameName;
	}

	protected static void setGameName(String newGameName) {
		gameName = newGameName;
	}

	public void movePlayerTo(double positionX, double positionY) {
		this.setPositionX(positionX);
		this.setPositionY(positionY);
	}

	public String getNickname() {
		return this.nickname;
	}

	public double distanceFromPlayer(PlayerStatus opponent) {
		return Math.sqrt(Math.pow((this.positionX - opponent.positionX), 2)
				+ Math.pow((this.positionY - opponent.positionY), 2));
	}

	public String getBetterWeapon(PlayerStatus opponent) {
		String betterWeapon = "";
		int weaponGradePlayer = 0, weaponGradeOpponent = 0;
		if (this.distanceFromPlayer(opponent) <= 1000) {
			switch (this.getWeaponInHand()) {
			case "knife":
				weaponGradePlayer = 1;
				break;
			case "sniper":
				weaponGradePlayer = 2;
				break;
			case "kalashnikov":
				weaponGradePlayer = 3;
				break;
			}
			switch (opponent.getWeaponInHand()) {
			case "knife":
				weaponGradeOpponent = 1;
				break;
			case "sniper":
				weaponGradeOpponent = 2;
				break;
			case "kalashnikov":
				weaponGradeOpponent = 3;
				break;
			}
			if (weaponGradePlayer > weaponGradeOpponent)
				betterWeapon = this.getWeaponInHand();
			else
				betterWeapon = opponent.getWeaponInHand();
		}

		if (this.distanceFromPlayer(opponent) > 1000) {
			switch (this.getWeaponInHand()) {
			case "knife":
				weaponGradePlayer = 1;
				break;
			case "sniper":
				weaponGradePlayer = 3;
				break;
			case "kalashnikov":
				weaponGradePlayer = 2;
				break;
			}
			switch (opponent.getWeaponInHand()) {
			case "knife":
				weaponGradeOpponent = 1;
				break;
			case "sniper":
				weaponGradeOpponent = 3;
				break;
			case "kalashnikov":
				weaponGradeOpponent = 2;
				break;
			}
			if (weaponGradePlayer > weaponGradeOpponent)
				betterWeapon = this.getWeaponInHand();
			else
				betterWeapon = opponent.getWeaponInHand();
		}
		return betterWeapon;
	}

	public boolean shouldAttackOpponent(PlayerStatus opponent) {
		if (this.getWeaponInHand().equals(opponent.getWeaponInHand())) {
			double probPlayer = (3 * this.health + this.score / 1000) / 4;
			double probOpponent = (3 * opponent.health + opponent.score / 1000) / 4;
			if (probPlayer < probOpponent)
				return false;
		} else {
			if (!getBetterWeapon(opponent).equals(this.getWeaponInHand()))
				return false;
		}
		return true;
	}
}
