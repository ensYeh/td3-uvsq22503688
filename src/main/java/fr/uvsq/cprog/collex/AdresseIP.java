package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP implements Comparable<AdresseIP> {
    private final String adresse;

    public AdresseIP(String adresse) {
        if (!adresse.matches("\\d{1,3}(\\.\\d{1,3}){3}")) {
            throw new IllegalArgumentException("Adresse IP invalide : " + adresse);
        }
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    @Override
    public int compareTo(AdresseIP other) {
        return this.adresse.compareTo(other.adresse);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof AdresseIP && ((AdresseIP) o).adresse.equals(this.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresse);
    }

    @Override
    public String toString() {
        return adresse;
    }
}
