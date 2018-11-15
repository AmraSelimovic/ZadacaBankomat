import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;

public class Fajl {

	public static ArrayList<Racun> readFromFile() {
		String fileName = "racuni.txt";
		ArrayList<Racun> accounts = new ArrayList<Racun>();
		String line = null;
		System.out.println();

		try {

			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:/Users/medn/eclipse-workspace/BankomatZadaca/racuni.txt")));
			
			while ((line = bufferedReader.readLine()) != null) {
				String[] info = line.split(" ");
				Racun acc = new Racun();
				acc.setBrojRacuna(Integer.parseInt(info[0]));
				acc.setImeVlasnika(info[1]);
				acc.setTrenutnoStanjeRacuna(Double.parseDouble(info[2]));
				accounts.add(acc);
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");

		}
		return accounts;
	}

}

