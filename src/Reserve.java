import edu.princeton.cs.introcs.StdDraw;

public class Reserve {

	public static int oxygen;

	public Reserve(Cave cave1, Cave cave2, Cave cave3) {
        int n1 = Cave.NList.get(0);
        int n2 = Cave.NList.get(1);
        int n3 = Cave.NList.get(2);
        int N = n1+n2+n3;
        this.oxygen = 2*N;
	}

	public int getOxygen() {
		return oxygen;
	}

	public int oxygenReserve(int nbLevels) {
		int nbReserve = 1;
		int reserve = 2*nbLevels;
		return reserve;
	}

	public static int oxygenConsumption(int nbCoffres, int oxygen) {
        int drop = 1 + nbCoffres;
        oxygen = oxygen - drop;
        return oxygen;
    }

} //ferme la classe
