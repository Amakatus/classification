package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;


import main.java.tps.tpcsv.ChargementDonneesUtil;
import main.java.tps.tpcsv.FormatDonneeBrut;

class TestChargementDonneesUtil {

	@Test
    public void testCharger () throws IOException {
        List<FormatDonneeBrut> l = ChargementDonneesUtil.charger("/data/personnes.csv");
        System.out.println(l);
    }

}
