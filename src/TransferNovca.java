public class TransferNovca extends Racun {

	private int izvorniRacun;
	private int targetRacun;
	private double iznosZaPrebacivanje;

	
	// konstruktor metoda
	public TransferNovca() {

	}

	//getteri i setteri
	public int getIzvorniRacun() {
		return izvorniRacun;
	}

	public void setIzvorniRacun(int izvorniRacun) {
		this.izvorniRacun = izvorniRacun;
	}

	public int getTargetRacun() {
		return targetRacun;
	}

	public void setTargetRacun(int targetRacun) {
		this.targetRacun = targetRacun;
	}

	public double getIznosZaPrebacivanje() {
		return iznosZaPrebacivanje;
	}

	public void setIznosZaPrebacivanje(double iznosZaPrebacivanje) {
		this.iznosZaPrebacivanje = iznosZaPrebacivanje;
	}
	
	
	
	// metod koji provjerava da li se transfer novca moze izvrsiti
	public boolean provjeriValidnost() {
		boolean izvorniPostoji = false;
		boolean targetPostoji = false;
		boolean dovoljnoNovca = false;

		for (int i = 0; i < racuni.size(); i++) {

			if (racuni.get(i).getBrojRacuna() == izvorniRacun) {

				izvorniPostoji = true;

				if ((racuni.get(i).trenutnoStanjeRacuna - iznosZaPrebacivanje) >= 0)
					dovoljnoNovca = true;

			}

			if (racuni.get(i).getBrojRacuna() == targetRacun)
				targetPostoji = true;
		}

		if (!izvorniPostoji)
			System.out.println("Unijeti broj racuna za slanje ne postoji.");
		if (!targetPostoji)
			System.out.println("Unijeti broj racuna za primanje ne postoji.");
		if (!dovoljnoNovca)
			System.out.println("Nema dovoljno novca za transfer.");
		

		if (!izvorniPostoji || !targetPostoji || !dovoljnoNovca )
			return false;
		
		else 
			return true;

	}

	//metod koji izvrsava transfer ako prethodna provjera rezultira sa true
	public void izvrsiTransfer() {
		for (int i = 0; i < racuni.size(); i++) {

			if (racuni.get(i).getBrojRacuna() == izvorniRacun)
				racuni.get(i).trenutnoStanjeRacuna -= iznosZaPrebacivanje;

			if (racuni.get(i).getBrojRacuna() == targetRacun)
				racuni.get(i).trenutnoStanjeRacuna += iznosZaPrebacivanje;
		}

	}

}



