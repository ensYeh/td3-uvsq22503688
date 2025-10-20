package fr.uvsq.cprog.collex;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DnsTUI {
  private static final Logger logger = LoggerFactory.getLogger(DnsTUI.class);
  private final Dns dns;
  private final Scanner scanner;

  public DnsTUI(Dns dns) {
    this.dns = dns;
    this.scanner = new Scanner(System.in);
  }

  public Commande nextCommande() {
    System.out.print("> ");
    String input = scanner.nextLine().trim();
    String[] parts = input.split("\\s+");

    try {
      if (input.equals("quit")) {
        return new CommandeQuitter();
      } else if (parts[0].equals("ls")) {
        if (parts.length == 2) {
          return new CommandeLS(dns, parts[1], false);
        } else if (parts.length == 3 && parts[1].equals("-a")) {
          return new CommandeLS(dns, parts[2], true);
        } else {
          return () -> "Invalid ls command";
        }
      } else if (parts[0].equals("add") && parts.length == 3) {
        return new CommandeAdd(dns, new AdresseIP(parts[1]), new NomMachine(parts[2]));
      } else if (parts[0].matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
        return new CommandeRechercheNom(dns, new AdresseIP(parts[0]));
      } else if (parts[0].matches("[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\\.[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?)*")) {
        return new CommandeRechercheIP(dns, new NomMachine(parts[0]));
      }
    } catch (IllegalArgumentException e) {
      logger.warn("Invalid command input: {}", input);
      return () -> "ERREUR : Invalid input: " + e.getMessage();
    }
    return () -> "ERREUR : Unknown command: " + input;
  }


  public void affiche(String result) {
    System.out.println(result);
  }
}