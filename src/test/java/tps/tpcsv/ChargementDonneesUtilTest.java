package test.java.tps.tpcsv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.java.tps.tpcsv.ChargementDonneesUtil;
import main.java.tps.tpcsv.FormatDonneeBrut;
import main.java.tps.tpcsv.GenderType;
import main.java.tps.tpcsv.Personne;

class ChargementDonneesUtilTest {

	@Test
	void testGenereatePersonne() {
		Personne personne = new Personne();
		personne.setPrenomNom("Vincent Chevallier");
		personne.setDateNaissance(LocalDate.of(1978, 05, 18));
		personne.setGenre(GenderType.HOMME);
		personne.setTaille(176);
		personne.setScoreNormalise(0.27);
		personne.setSouscription(true);
		try {
			List<FormatDonneeBrut> datas = ChargementDonneesUtil.charger("/data/personnes.csv");
			assertEquals(personne.toString(), ChargementDonneesUtil.genereatePersonneFromData(datas.get(0), datas).toString());
		} catch (IOException e) { e.printStackTrace(); }
	}

}
