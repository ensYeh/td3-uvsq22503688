package fr.uvsq.cprog.collex;

import java.util.regex.Pattern;


public class NomMachine {
  private final String machineName;
  private final String domain;

  public NomMachine(String name) {
    if (!isValidName(name)) {
      throw new IllegalArgumentException("Invalid machine name: " + name);
    }
    String[] parts = name.split("\\.", 2);
    this.machineName = parts[0];
    this.domain = parts.length > 1 ? parts[1] : "";
  }


  private boolean isValidName(String name) {
    String namePattern = "^[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\\.[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?)*$";
    return name != null && Pattern.matches(namePattern, name);
  }


  public String getMachineName() {
    return machineName;
  }


  public String getDomain() {
    return domain;
  }


  public String getFullName() {
    return domain.isEmpty() ? machineName : machineName + "." + domain;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof NomMachine)) {
      return false;
    }
    NomMachine otherName = (NomMachine) other;
    return machineName.equals(otherName.machineName) && domain.equals(otherName.domain);
  }

  @Override
  public int hashCode() {
    return 31 * machineName.hashCode() + domain.hashCode();
  }

  @Override
  public String toString() {
    return getFullName();
  }
}