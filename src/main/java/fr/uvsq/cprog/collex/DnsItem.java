package fr.uvsq.cprog.collex;

public class DnsItem {
    private final AdresseIP adresse;
    private final NomMachine nomMachine;

    public DnsItem(AdresseIP adresse, NomMachine nomMachine) {
        this.adresse = adresse;
        this.nomMachine = nomMachine;
    }

    public AdresseIP getAdresse() {
        return adresse;
    }

    public NomMachine getNomMachine() {
        return nomMachine;
    }

    @Override
    public String toString() {
        return adresse + " " + nomMachine;
    }
}
