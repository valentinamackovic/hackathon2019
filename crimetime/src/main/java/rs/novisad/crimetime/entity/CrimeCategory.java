package rs.novisad.crimetime.entity;

public enum CrimeCategory {

	TezaKrivicnaDela, LaksaKrivicnaDela, Prekrsaj;
	
	public static String toString(CrimeCategory e) {
		switch (e) {
		case TezaKrivicnaDela:
			return "TezaKrivicnaDela";
			
		case LaksaKrivicnaDela:
			return "LaksaKrivicnaDela";
			
		case Prekrsaj:
			return "Prekrsaj";
			
		default:
			return "";
		}
	}
}
