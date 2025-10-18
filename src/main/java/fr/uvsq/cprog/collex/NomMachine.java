package fr.uvsq.cprog.collex;

import java.util.Objects;

public class NomMachine implements Comparable<NomMachine> {
    private final String nomComplet;

    public NomMachine(String nomComplet) {
        if (!nomComplet.contains(".")) {
            throw new IllegalArgumentException("Nom de machine invalide: " + nomComplet);
        }
        this.nomComplet = nomComplet;
    }

    public String getNom() {
        return nomComplet.split("\\.")[0];
    }

    public String getDomaine() {
        return nomComplet.substring(nomComplet.indexOf('.') + 1);
    }

    public String getNomComplet() {
        return nomComplet;
    }

    @Override
    public int compareTo(NomMachine autre) {
        return this.nomComplet.compareTo(autre.nomComplet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NomMachine)) return false;
        NomMachine that = (NomMachine) o;
        return Objects.equals(nomComplet, that.nomComplet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomComplet);
    }

    @Override
    public String toString() {
        return nomComplet;
    }
}
