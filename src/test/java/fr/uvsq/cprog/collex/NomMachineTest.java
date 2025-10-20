package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;


public class NomMachineTest {
  @Test
  public void testValidName() {
    NomMachine name = new NomMachine("www.uvsq.fr");
    assertEquals("www", name.getMachineName());
    assertEquals("uvsq.fr", name.getDomain());
    assertEquals("www.uvsq.fr", name.getFullName());
  }

  @Test
  public void testInvalidName() {
    assertThrows(IllegalArgumentException.class, () -> new NomMachine("invalid..domain"));
    assertThrows(IllegalArgumentException.class, () -> new NomMachine("-invalid.domain"));
  }

  @Test
  public void testEquals() {
    NomMachine name1 = new NomMachine("www.uvsq.fr");
    NomMachine name2 = new NomMachine("www.uvsq.fr");
    NomMachine name3 = new NomMachine("mail.uvsq.fr");
    assertEquals(name1, name2);
    assertEquals(name1.hashCode(), name2.hashCode());
    assertEquals(false, name1.equals(name3));
  }
}