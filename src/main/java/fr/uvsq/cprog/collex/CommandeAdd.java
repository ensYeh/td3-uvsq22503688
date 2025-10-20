package fr.uvsq.cprog.collex;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommandeAdd implements Commande {
  private static final Logger logger = LoggerFactory.getLogger(CommandeAdd.class);
  private final Dns dns;
  private final AdresseIP ip;
  private final NomMachine name;


  public CommandeAdd(Dns dns, AdresseIP ip, NomMachine name) {
    this.dns = dns;
    this.ip = ip;
    this.name = name;
  }


  @Override
  public String execute() {
    try {
      dns.addItem(ip, name);
      return "Added: " + name + " " + ip;
    } catch (IllegalArgumentException e) {
      return "ERREUR : " + e.getMessage();
    } catch (IOException e) {
      logger.error("Failed to update DNS data file", e);
      return "ERREUR : Failed to update DNS data file";
    }
  }
}