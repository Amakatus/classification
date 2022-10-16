package main.java.tps.tpcsv;

import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		List<FormatDonneeBrut> datas = ChargementDonneesUtil.charger("/data/personnes.csv");
		System.out.println(datas);
		Personne personne = new Personne(datas.get(0));
		System.out.println(personne);
	}
}
