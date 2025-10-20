package fr.uvsq.cprog.collex;

import java.util.List;


public class CommandeLS implements Commande {
  private final Dns dns;
  private final String domain;
  private final boolean sortByIp;

  public CommandeLS(Dns dns, String domain, boolean sortByIp) {
    this.dns = dns;
    this.domain = domain;
    this.sortByIp = sortByIp;
  }


  @Override
  public String execute() {
    List<DnsItem> items = dns.getItems(domain, sortByIp);
    if (items.isEmpty()) {
      return "No entries found for domain: " + domain;
    }
    StringBuilder result = new StringBuilder();
    for (DnsItem item : items) {
      result.append(item.getIp()).append(" ").append(item.getName()).append("\n");
    }
    return result.toString();
  }
}