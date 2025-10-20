package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DnsApp {
  private static final Logger logger = LoggerFactory.getLogger(DnsApp.class);


  public static void main(String[] args) {
    try {
      Properties props = new Properties();
      try (InputStream input = DnsApp.class.getResourceAsStream("/dns.properties")) {
        if (input == null) {
          throw new IOException("Properties file not found: dns.properties");
        }
        props.load(input);
      }
      String dataFilePath = props.getProperty("dns.data.file", "/dns_data.txt");
      Dns dns = new Dns(dataFilePath);
      DnsTUI tui = new DnsTUI(dns);
      run(tui);
    } catch (IOException e) {
      logger.error("Failed to initialize DNS application", e);
      System.exit(1);
    }
  }


  public static void run(DnsTUI tui) {
    while (true) {
      Commande cmd = tui.nextCommande();
      String result = cmd.execute();
      tui.affiche(result);
      if (cmd instanceof CommandeQuitter) {
        break;
      }
    }
  }
}