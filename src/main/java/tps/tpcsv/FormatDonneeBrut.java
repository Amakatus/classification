package main.java.tps.tpcsv;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class FormatDonneeBrut {

	@CsvBindByName(column = "Nom")
    private String nom;
	
	@CsvBindByName(column = "Prénom")
    private String prenom;
	
	@CsvBindByName(column = "Date de naissance")
	@CsvDate("yyyy-MM-dd")
    private LocalDate dateNaissance;
	
	@CsvBindByName(column = "Genre")
    private Genre genre;
	
	@CsvBindByName(column = "Taille")
    private double taille;
	
	@CsvBindByName(column = "Score")
    private int score;
	
	@CsvBindByName(column = "Souscription")
    private OuiNon souscription; // OuiNon est un type énuméré à créer

    @Override
    public String toString () {
        return "Forename :" + this.nom + "Name :" + this.prenom + "Date of Birth :" + this.dateNaissance + "Type :" + this.genre + "Size :" + this.taille + "Score :" + this.score + "Sub :" + this.souscription;
    }
}