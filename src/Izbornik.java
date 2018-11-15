import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Izbornik {
	
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws IOException, ClassNotFoundException{

		Racun.racuni = Fajl.readFromFile();
		opcije();

	}

	public static void opcije() throws IOException, ClassNotFoundException{
		System.out.println();
		System.out.println("      DOBRODOSLI U GLAVNI IZBORNIK   ");
		System.out.println("   IZABERITE NEKU OD SLJEDECIH OPCIJA: ");
		System.out.println("1. Kreiranje racuna");
		System.out.println("2. Transfer novca");
		System.out.println("3. Ispis racuna");

		int izbor =0;
		
		do {
			try {
				izbor = inputInt();
				
				if(izbor < 1 || izbor >7 )
					throw new InputMismatchException();
				break;
			}
			catch (Exception e) {
				System.out.println("Pogresan unos. Pokusajte ponovo.");
				input.nextLine();
				continue;
			}
		} while(true);
		
		
		switch (izbor) {
		case 1:
			kreiranjeRacuna();
			break;
		case 2:
			transferNovca();
			break;
		case 3:
			ispisRacuna();
			break;
		}
	}

	public static void kreiranjeRacuna() throws IOException, ClassNotFoundException{
		
		System.out.println();
		System.out.println("   DOBRODOSLI U IZBORNIK KREIRANJE RACUNA   ");
		Racun racun = new Racun();
		
		System.out.println("Unesite broj racuna: ");
		int brojRacuna = provjeriDaLiJeNegativan();


		while (!racun.provjeriZauzetost(brojRacuna)) {
			System.out.println("Upisani ID je zauzet. Pokusajte sa novim unosom!");
			brojRacuna = provjeriDaLiJeNegativan();
		}
	

		System.out.println("Unesite vase ime:");
		String ime = input.next();

		System.out.println("Unesite iznos koji postavljate na racun: ");
		double iznosPostavljanja = provjeriJeLiNegativan();
		
		racun = new Racun(brojRacuna, ime, iznosPostavljanja);
		
		
		String fileName = "racuni.txt";
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter (new File("C:/Users/medn/eclipse-workspace/BankomatZadaca/racuni.txt"),true));

			bufferedWriter.write(racun.getBrojRacuna() + " " + racun.getImeVlasnika() + " " + racun.getTrenutnoStanjeRacuna() );
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");

		}
		
		opcije();
	}

	public static void transferNovca() throws IOException, ClassNotFoundException{

		System.out.println();
		System.out.println("   DOBRODOSLI U IZBORNIK ZA TRANSFER NOVCA   ");
		TransferNovca transfer = new TransferNovca();
		
		System.out.println("Unesite broj racuna sa kog zelite prebaciti novac: ");
		int sourceBroj = provjeriDaLiJeNegativan();
		transfer.setIzvorniRacun(sourceBroj);

		System.out.println("Unesite broj racuna na koji zelite prebaciti novac: ");
		int targetBroj = provjeriDaLiJeNegativan();
		transfer.setTargetRacun(targetBroj);

		System.out.println("Unesite iznos novca koji zelite postaviti: ");
		double novacZaPolog = provjeriJeLiNegativan();
		transfer.setIznosZaPrebacivanje(novacZaPolog);

		if (transfer.provjeriValidnost() && sourceBroj!=targetBroj) {
			transfer.izvrsiTransfer();
			System.out.println("Transfer je uspjesno izvrsen.");
		} else {
			System.out.println("Transfer nije uspjesno izvrsen.");
		}
		
		opcije();
	}

	public static void ispisRacuna() throws IOException, ClassNotFoundException{
		
		System.out.println();
		System.out.println("   DOBRODOSLI U IZBORNIK ZA ISPIS STANJA TRAZENOG RACUNA   ");
		System.out.println("Unesite broj racuna: ");
		int broj = provjeriDaLiJeNegativan();

		Racun.ispisRacuna(broj);


		opcije();

	}
	
	
	// handle exception za integer brojeve
		public static int inputInt() {
			
			int izbor=0;
			do {
				try {
					 izbor = input.nextInt();
					 break;
				} 
				catch (Exception ex) {
					System.out.println("Pogresan unos. Pokusajte ponovo: ");
					input.nextLine();
				}
				
			} while (true);	
			return izbor;
		}
		
	// handle exception za double brojeve
		public static double inputDouble() {
					
			double izbor=0;
				do {
						try {
							 izbor = input.nextDouble();
							 break;
						} 
						catch (Exception ex) {
							System.out.println("Pogresan unos. Pokusajte ponovo: ");
							input.nextLine();
						}
						
					} while (true);	
					return izbor;
				}
		
		// handle exception za negativne integer brojeve
				public static int provjeriDaLiJeNegativan() {
							
					int izbor =0;
					
					do {
						try {
							izbor = inputInt();
							
							if(izbor < 0)
								throw new InputMismatchException();
							break;
						}
						catch (Exception e) {
							System.out.println("Pogresan unos. Pokusajte ponovo:");
							input.nextLine();
							continue;
						}
					} while(true);
					return izbor;
				}
				
				// handle exception za negativne double brojeve
				public static double provjeriJeLiNegativan() {
							
					double izbor=0.0;
					
					do {
						try {
							izbor = inputDouble();
							
							if(izbor <= 0 )
								throw new InputMismatchException();
							break;
						}
						catch (Exception e) {
							System.out.println("Pogresan unos. Pokusajte ponovo:");
							input.nextLine();
							continue;
						}
					} while(true);
					return izbor;
				}

}





