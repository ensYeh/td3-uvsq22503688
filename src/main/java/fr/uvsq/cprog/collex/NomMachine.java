package fr.uvsq.cprog.collex;

import java.util.Objects;

public class NomMachine {
    private final String nom;

    public NomMachine(String nom) {
        if (!nom.contains(".")) {
            throw new IllegalArgumentException("Nom de machine invalide : " + nom);
        }
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getDomaine() {
        return nom.substring(nom.indexOf('.') + 1);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof NomMachine && ((NomMachine) o).nom.equals(this.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return nom;
    }
}
