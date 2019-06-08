package rs.novisad.crimetime.entity;

public enum CrimeCategory {

	Ubistva, FizickiNapadi, Pljacka;
	
	public static String toString(CrimeCategory e) {
		switch (e) {
		case Ubistva:
			return "Ubistva";
			
		case FizickiNapadi:
			return "FizickiNapadi";
			
		case Pljacka:
			return "Pljacka";
			
		default:
			return "";
		}
	}
}
