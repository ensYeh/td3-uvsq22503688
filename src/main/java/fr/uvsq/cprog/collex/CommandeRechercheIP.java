package fr.uvsq.cprog.collex;

public class CommandeRechercheIP implements Commande {
  private final Dns dns;
  private final NomMachine name;


  public CommandeRechercheIP(Dns dns, NomMachine name) {
    this.dns = dns;
    this.name = name;
  }

  @Override
  public String execute() {
    DnsItem item = dns.getItem(name);
    return item != null ? item.getIp().toString() : "Machine name not found: " + name;
  }
}