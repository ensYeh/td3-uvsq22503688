package fr.uvsq.cprog.collex;

public class CommandeQuitter implements Commande {
    @Override
    public String execute() {
        return "Au revoir !";
    }
}
