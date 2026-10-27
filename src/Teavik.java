// Abstraktne ülemklass hoiab raamatute ja ajakirjade ühiseid andmeid.
public abstract class Teavik {
    private final int id;
    private final String pealkiri;
    private boolean laenutatud;

    protected Teavik(int id, String pealkiri) {
        if (id <= 0 || pealkiri == null || pealkiri.isBlank()) {
            throw new IllegalArgumentException("ID peab olema positiivne ja pealkiri täidetud.");
        }
        this.id = id;
        this.pealkiri = pealkiri.trim();
    }

    public int getId() { return id; }
    public String getPealkiri() { return pealkiri; }
    public boolean isLaenutatud() { return laenutatud; }

    // Välja ei muudeta otse: meetod kontrollib kõigepealt lubatud olekut.
    public void laenuta() {
        if (laenutatud) {
            throw new IllegalStateException("See teavik on juba laenutatud.");
        }
        laenutatud = true;
    }

    public void tagasta() {
        if (!laenutatud) {
            throw new IllegalStateException("See teavik ei ole laenutatud.");
        }
        laenutatud = false;
    }

    // Iga alamklass määrab ise laenutusaja. See on abstraktsioon.
    public abstract int getLaenutusPaevad();

    @Override
    public String toString() {
        // Polümorfism: laenutusaeg sõltub objekti tegelikust alamklassist.
        return id + " | " + pealkiri + " | " + getLaenutusPaevad()
                + " päeva | " + (laenutatud ? "laenutatud" : "saadaval");
    }
}
