package fr.uvsq.cprog.collex;

/**
 * Represents a DNS entry mapping an IP address to a machine name.
 */
public class DnsItem {
  private final AdresseIP ip;
  private final NomMachine name;


  public DnsItem(AdresseIP ip, NomMachine name) {
    this.ip = ip;
    this.name = name;
  }

  public AdresseIP getIp() {
    return ip;
  }

  public NomMachine getName() {
    return name;
  }


  @Override
  public String toString() {
    return name + " " + ip;
  }
}