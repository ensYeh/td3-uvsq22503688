package fr.uvsq.cprog.collex;

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