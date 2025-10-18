package fr.uvsq.cprog.collex;

public class NomMachine {
    private String nom;

    public NomMachine(String nom) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Nom de machine cannot be null or empty.");
        }
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
