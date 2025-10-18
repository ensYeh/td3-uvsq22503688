package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP implements Comparable<AdresseIP> {
    private final String valeur;

    public AdresseIP(String valeur) {
        if (!valeur.matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
            throw new IllegalArgumentException("Adresse IP invalide: " + valeur);
        }
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }

    @Override
    public int compareTo(AdresseIP autre) {
        return this.valeur.compareTo(autre.valeur);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseIP)) return false;
        AdresseIP that = (AdresseIP) o;
        return Objects.equals(valeur, that.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur);
    }

    @Override
    public String toString() {
        return valeur;
    }
}
