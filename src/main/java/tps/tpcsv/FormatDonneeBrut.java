package tps.tpcsv;

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
	private GenderType genre;

	@CsvBindByName(column = "Taille")
	private double taille;
	
	@CsvBindByName(column = "Score")
	private double score;
	
	@CsvBindByName(column = "Souscription")
	private SubscribeType souscription;
	
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public GenderType getGenre() {
		return genre;
	}

	public double getTaille() {
		return taille;
	}

	public double getScore() {
		return score;
	}

	public SubscribeType getSouscription() {
		return souscription;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %s %s %s %s", this.nom, this.prenom, this.dateNaissance, this.genre, this.taille, this.score, this.souscription);
	}
}
