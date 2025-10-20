package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.util.List;
import org.junit.Test;


public class DnsTest {
  @Test
  public void testLoadDatabase() throws IOException {
    Dns dns = new Dns("/dns_data.txt");
    DnsItem item = dns.getItem(new NomMachine("www.uvsq.fr"));
    assertNotNull(item);
    assertEquals("193.51.31.90", item.getIp().getIpAddress());
  }

  @Test
  public void testGetItemByIp() throws IOException {
    Dns dns = new Dns("/dns_data.txt");
    DnsItem item = dns.getItem(new AdresseIP("193.51.31.90"));
    assertNotNull(item);
    assertEquals("www.uvsq.fr", item.getName().getFullName());
    assertNull(dns.getItem(new AdresseIP("10.0.0.1")));
  }

  @Test
  public void testGetItemByName() throws IOException {
    Dns dns = new Dns("/dns_data.txt");
    DnsItem item = dns.getItem(new NomMachine("ecampus.uvsq.fr"));
    assertNotNull(item);
    assertEquals("193.51.25.12", item.getIp().getIpAddress());
    assertNull(dns.getItem(new NomMachine("unknown.domain")));
  }

  @Test
  public void testGetItems() throws IOException {
    Dns dns = new Dns("/dns_data.txt");
    List<DnsItem> items = dns.getItems("uvsq.fr", false);
    assertEquals(3, items.size());
    assertEquals("ecampus", items.get(0).getName().getMachineName());
    items = dns.getItems("uvsq.fr", true);
    assertEquals("193.51.25.12", items.get(0).getIp().getIpAddress());
  }

  @Test
  public void testAddItem() throws IOException {
    Dns dns = new Dns("/dns_data.txt");
    dns.addItem(new AdresseIP("193.51.25.24"), new NomMachine("pikachu.uvsq.fr"));
    DnsItem item = dns.getItem(new AdresseIP("193.51.25.24"));
    assertNotNull(item);
    assertEquals("pikachu.uvsq.fr", item.getName().getFullName());
    assertThrows(IllegalArgumentException.class,
        () -> dns.addItem(new AdresseIP("193.51.25.24"), new NomMachine("other.uvsq.fr")));
  }
}