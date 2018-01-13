public class Reserve {

	private int oxygen;
	public static int reserve;

	public Reserve(Cave cave) {
	    //this.oxygen = oxygen;
	    Reserve.reserve =  2*Cave.NList.get(cave.getIdCave()-1);
	}

	public int getOxygen() {
		return oxygen;
	}

	public int oxygenReserve(int nbLevels) {
		int nbReserve = 1;
		int reserve = 2*nbLevels;
		return reserve;
	}

	public static int oxygenConsuption(int nbCoffres, int oxygene) {
		oxygene = oxygene - (1 + nbCoffres);
		return oxygene;
	}

} //ferme la classe
