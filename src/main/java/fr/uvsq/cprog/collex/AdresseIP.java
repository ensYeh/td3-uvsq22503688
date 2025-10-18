package fr.uvsq.cprog.collex;

public class AdresseIP {
    private int[] octets;

    public AdresseIP(int o1, int o2, int o3, int o4) {
        if (!isValidOctet(o1) || !isValidOctet(o2) || !isValidOctet(o3) || !isValidOctet(o4)) {
            throw new IllegalArgumentException("Each octet must be between 0 and 255.");
        }
        this.octets = new int[]{o1, o2, o3, o4};
    }

    private boolean isValidOctet(int octet) {
        return octet >= 0 && octet <= 255;
    }

    @Override
    public String toString() {
        return String.format("%d.%d.%d.%d", octets[0], octets[1], octets[2], octets[3]);
    }
}
