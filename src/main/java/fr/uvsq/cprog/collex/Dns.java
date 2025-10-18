package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dns {
    private static final Logger logger = LoggerFactory.getLogger(Dns.class);

    private final Map<AdresseIP, DnsItem> parAdresse = new HashMap<>();
    private final Map<NomMachine, DnsItem> parNom = new HashMap<>();
    private final Path cheminFichier;

    public Dns(String fichierConfig) throws IOException {
        Properties props = new Properties();
        props.load(Files.newInputStream(Paths.get(fichierConfig)));

        String fichierDns = props.getProperty("dns.file");
        if (fichierDns == null) {
            throw new IllegalArgumentException("Le fichier de propriétés doit contenir 'dns.file'");
        }

        this.cheminFichier = Paths.get(fichierDns);
        chargerBase();
    }

    private void chargerBase() throws IOException {
        if (!Files.exists(cheminFichier)) {
            logger.warn("Fichier DNS inexistant, création d'un nouveau : {}", cheminFichier);
            Files.createFile(cheminFichier);
            return;
        }

        List<String> lignes = Files.readAllLines(cheminFichier);
        for (String ligne : lignes) {
            if (ligne.isBlank()) continue;
            String[] parts = ligne.trim().split("\\s+");
            if (parts.length != 2) {
                logger.warn("Ligne ignorée (format invalide) : {}", ligne);
                continue;
            }

            NomMachine nom = new NomMachine(parts[0]);
            AdresseIP ip = new AdresseIP(parts[1]);
            DnsItem item = new DnsItem(ip, nom);
            parAdresse.put(ip, item);
            parNom.put(nom, item);
        }
        logger.info("Base DNS chargée : {} éléments", parAdresse.size());
    }

    public DnsItem getItem(AdresseIP adresse) {
        return parAdresse.get(adresse);
    }

    public DnsItem getItem(NomMachine nom) {
        return parNom.get(nom);
    }


    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> resultat = new ArrayList<>();
        for (DnsItem item : parNom.values()) {
            if (item.getNomMachine().getDomaine().equalsIgnoreCase(domaine)) {
                resultat.add(item);
            }
        }
        resultat.sort(Comparator.comparing(i -> i.getNomMachine().getNomComplet()));
        return resultat;
    }


    public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
        if (parAdresse.containsKey(ip)) {
            throw new IllegalArgumentException("ERREUR : L'adresse existe déjà !");
        }
        if (parNom.containsKey(nom)) {
            throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
        }

        DnsItem item = new DnsItem(ip, nom);
        parAdresse.put(ip, item);
        parNom.put(nom, item);

        List<String> lignes = new ArrayList<>(Files.readAllLines(cheminFichier));
        lignes.add(nom.getNomComplet() + " " + ip.getValeur());
        Files.write(cheminFichier, lignes);

        logger.info("Nouvelle entrée ajoutée : {} {}", nom, ip);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        parNom.values().forEach(item -> sb.append(item).append("\n"));
        return sb.toString();
    }
}
