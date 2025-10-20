package fr.uvsq.cprog.collex;

import java.util.regex.Pattern;


public class AdresseIP {
  private final String ipAddress;


  public AdresseIP(String ipAddress) {
    if (!isValidIp(ipAddress)) {
      throw new IllegalArgumentException("Invalid IP address: " + ipAddress);
    }
    this.ipAddress = ipAddress;
  }


  private boolean isValidIp(String ip) {
    String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
        + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    return ip != null && Pattern.matches(ipPattern, ip);
  }


  public String getIpAddress() {
    return ipAddress;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof AdresseIP)) {
      return false;
    }
    return ipAddress.equals(((AdresseIP) other).ipAddress);
  }


  @Override
  public int hashCode() {
    return ipAddress.hashCode();
  }


  @Override
  public String toString() {
    return ipAddress;
  }
}