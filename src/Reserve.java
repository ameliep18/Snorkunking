public class Reserve {

	private int oxygen;

	public Reserve(int oxygen) {
		this.oxygen = oxygen;
	}

	public int getOxygen() {
		return oxygen;
	}

	public int oxygenReserve(int nbLevels) {
		int nbReserve = 1;
		int reserve = 2*nbLevels;

		return reserve;
		/*while (reserve != 0) {
			if (isKeyPressed(VK_KP_DOWN)== true)
                reserve = reserve - (1 + nbChestTransported);
            if (isKeyPressed(VK_KP_UP)== true)
                reserve = reserve - (1 + nbChestTransported);
            if (isKeyPressed(VK_ENTER)== true)
            	reserve = reserve - 1;
		}
		if (reserve == 0) {
		    int nbTreasure = 0;
			nbReserve = nbReserve + 1;
			int score = nbTreasure;
			if (nbReserve <= 3) {
				reserve = 2*nbLevels;
			}
			else {
				//fin du jeu
			}
		}*/
		
	}

	public int oxygenConsuption(int nbTresors, int oxygene) {
		oxygene = 1 - nbTresors;
		return oxygene;
	}

} //ferme la classe
