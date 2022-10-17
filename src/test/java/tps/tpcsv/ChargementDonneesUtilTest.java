package test.java.tps.tpcsv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.java.tps.tpcsv.ChargementDonneesUtil;
import main.java.tps.tpcsv.FormatDonneeBrut;
import main.java.tps.tpcsv.GenderType;
import main.java.tps.tpcsv.Personne;

class ChargementDonneesUtilTest {
	static List<FormatDonneeBrut> datas;
	
	@BeforeAll
    static void init(){
		try {
			ChargementDonneesUtilTest.datas = ChargementDonneesUtil.charger("/data/personnes.csv");
		} catch (IOException e) { e.printStackTrace(); }
    }

	@Test
	void testGenereatePersonne() {
		Personne personne = new Personne();
		personne.setPrenomNom("Vincent Chevallier");
		personne.setDateNaissance(LocalDate.of(1978, 05, 18));
		personne.setGenre(GenderType.HOMME);
		personne.setTaille(176);
		personne.setScoreNormalise(0.27);
		personne.setSouscription(true);
		assertEquals(personne.toString(), ChargementDonneesUtil.genereatePersonneFromData(datas.get(0), datas).toString());
	}
	
	@Test
	void testNormalizeScore() {
		assertEquals(0.27, ChargementDonneesUtil.normalizeScore(datas.get(0).getScore(), datas));
		assertEquals(0.28, ChargementDonneesUtil.normalizeScore(datas.get(2).getScore(), datas));
		assertNotEquals(0.73, ChargementDonneesUtil.normalizeScore(datas.get(0).getScore(), datas));
	}
	
	@Test
	void testGetMinMaxScore() {
		double[] minMax = ChargementDonneesUtil.getMinMaxScore(datas);
		double min = minMax[0];
		double max = minMax[1];
		
		boolean minIsMin = true;
		boolean maxIsMax = true;
		double checkScore;
		for(FormatDonneeBrut data : datas) {
			checkScore = data.getScore();
			if(checkScore < min) minIsMin = false;
			else if(checkScore > max) maxIsMax = false;
		}
		assertTrue(minIsMin);
		assertTrue(maxIsMax);
	}
}
