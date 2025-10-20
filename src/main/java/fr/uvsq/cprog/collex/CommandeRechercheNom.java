package fr.uvsq.cprog.collex;

public class CommandeRechercheNom implements Commande {
  private final Dns dns;
  private final AdresseIP ip;


  public CommandeRechercheNom(Dns dns, AdresseIP ip) {
    this.dns = dns;
    this.ip = ip;
  }


  @Override
  public String execute() {
    DnsItem item = dns.getItem(ip);
    return item != null ? item.getName().toString() : "IP not found: " + ip;
  }
}