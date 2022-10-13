package main.java.tps.tpcsv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestChargementDonneesUtil {

    @Test
    public void testCharger () throws IOException {
        List<FormatDonneeBrut> l = ChargementDonneesUtil.charger("src/main/resources/data/personnes.csv");

    }
}
