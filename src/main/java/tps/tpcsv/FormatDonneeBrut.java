package main.java.tps.tpcsv;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import lombok.Getter;

public class FormatDonneeBrut {
	@CsvBindByName(column = "Nom")
	@Getter private String nom;
	
	@CsvBindByName(column = "Prénom")
	@Getter private String prenom;
	
	@CsvBindByName(column = "Date de naissance")
	@CsvDate("yyyy-MM-dd")
	@Getter private LocalDate dateNaissance;
	
	@CsvBindByName(column = "Genre")
	@Getter private GenderType genre;
	
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

	public int getScore() {
		return score;
	}

	public SubscribeType getSouscription() {
		return souscription;
	}

	@CsvBindByName(column = "Taille")
	@Getter private double taille;
	
	@CsvBindByName(column = "Score")
	@Getter private int score;
	
	@CsvBindByName(column = "Souscription")
	@Getter private SubscribeType souscription;
	
	@Override
	public String toString() {
		return String.format("%s %s %s %s %s %s %s", this.nom, this.prenom, this.dateNaissance, this.genre, this.taille, this.score, this.souscription);
	}
}
