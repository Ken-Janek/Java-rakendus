// Abstraktne ülemklass hoiab raamatute ja ajakirjade ühiseid andmeid.
public abstract class Teavik {
    // Kapseldamine: private välju ei saa teistest klassidest otse muuta.
    // final tähendab, et ID ja pealkiri määratakse ühe korra konstruktoris.
    private final int id;
    private final String pealkiri;
    // boolean hoiab kahte võimalikku olekut. Algväärtus on vaikimisi false.
    private boolean laenutatud;

    // protected konstruktorit saavad kasutada alamklassid super(...) kaudu.
    protected Teavik(int id, String pealkiri) {
        // || tähendab VÕI. null kontrollitakse enne isBlank-i, et vältida viga.
        if (id <= 0 || pealkiri == null || pealkiri.isBlank()) {
            throw new IllegalArgumentException("ID peab olema positiivne ja pealkiri täidetud.");
        }
        // this.id on objekti väli; id on konstruktorile antud parameeter.
        this.id = id;
        // trim eemaldab pealkirja algusest ja lõpust liigsed tühikud.
        this.pealkiri = pealkiri.trim();
    }

    // Getterid lubavad andmeid lugeda. ID ja pealkirja muutmise settereid pole vaja.
    public int getId() { return id; }
    public String getPealkiri() { return pealkiri; }
    public boolean isLaenutatud() { return laenutatud; }

    // Välja ei muudeta otse: meetod kontrollib kõigepealt lubatud olekut.
    public void laenuta() {
        if (laenutatud) {
            // Erand peatab selle meetodi; menüü püüab erandi kinni ja kuvab teate.
            throw new IllegalStateException("See teavik on juba laenutatud.");
        }
        laenutatud = true; // Oleku muutus on lubatud alles pärast kontrolli.
    }

    // Tagastada saab ainult teavikut, mis on parajasti laenutatud.
    public void tagasta() {
        // ! pöörab tõeväärtuse ümber: !laenutatud tähendab „ei ole laenutatud”.
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
        // toString määrab, milline tekst kuvatakse System.out.println(teavik) korral.
        // Tingimus ? esimene : teine valib teksti vastavalt laenutamise olekule.
        return id + " | " + pealkiri + " | " + getLaenutusPaevad()
                + " päeva | " + (laenutatud ? "laenutatud" : "saadaval");
    }
}
