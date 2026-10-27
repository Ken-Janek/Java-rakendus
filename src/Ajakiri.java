// Pärilus: ajakiri kasutab Teaviku ühiseid andmeid ja laenutamise reegleid.
public class Ajakiri extends Teavik {
    // Konstruktor loob ajakirja ning annab ID ja pealkirja ülemklassile.
    public Ajakiri(int id, String pealkiri) {
        super(id, pealkiri);
    }

    // Override tähendab ülemklassi meetodi ülekirjutamist.
    // Polümorfism: ajakirjal on raamatu 21 päeva asemel 7-päevane laenutusaeg.
    @Override
    public int getLaenutusPaevad() {
        return 7;
    }
}
