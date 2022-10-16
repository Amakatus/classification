package main.java.tps.tpcsv;

import java.time.LocalDate;

public class Personne {
	
	private String prenomNom;  // le prénom et le nom séparés par un espace
    private LocalDate dateNaissance;
    private GenderType genre;
    private int taille;  // taille en centimètres
    private double scoreNormalise;
    private boolean souscription;  // le oui/non devient un boolean

    public Personne(FormatDonneeBrut data) {
    	
    }
    
    public Personne() { }

	public String getPrenomNom() {
		return prenomNom;
	}

	public void setPrenomNom(String prenomNom) {
		this.prenomNom = prenomNom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public GenderType getGenre() {
		return genre;
	}

	public void setGenre(GenderType genre) {
		this.genre = genre;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public double getScoreNormalise() {
		return scoreNormalise;
	}

	public void setScoreNormalise(double scoreNormalise) {
		this.scoreNormalise = scoreNormalise;
	}

	public boolean isSouscription() {
		return souscription;
	}

	public void setSouscription(boolean souscription) {
		this.souscription = souscription;
	}
}
