package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;


public class AdresseIPTest {
  @Test
  public void testValidIp() {
    AdresseIP ip = new AdresseIP("192.168.0.1");
    assertEquals("192.168.0.1", ip.getIpAddress());
  }

  @Test
  public void testInvalidIp() {
    assertThrows(IllegalArgumentException.class, () -> new AdresseIP("256.1.2.3"));
    assertThrows(IllegalArgumentException.class, () -> new AdresseIP("1.2.3"));
    assertThrows(IllegalArgumentException.class, () -> new AdresseIP("a.b.c.d"));
  }

  @Test
  public void testEquals() {
    AdresseIP ip1 = new AdresseIP("192.168.0.1");
    AdresseIP ip2 = new AdresseIP("192.168.0.1");
    AdresseIP ip3 = new AdresseIP("10.0.0.1");
    assertEquals(ip1, ip2);
    assertEquals(ip1.hashCode(), ip2.hashCode());
    assertEquals(false, ip1.equals(ip3));
  }
}