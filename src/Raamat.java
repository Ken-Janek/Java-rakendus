// Pärilus: Raamat on Teaviku alamklass ja kasutab selle ühiseid reegleid.
// Teaviku private väljadele pääseb ligi ainult ülemklassi meetodite kaudu.
public class Raamat extends Teavik {
    // Konstruktor käivitub new Raamat(...) korral.
    public Raamat(int id, String pealkiri) {
        // super kutsub Teaviku konstruktorit, mis kontrollib ja salvestab andmed.
        super(id, pealkiri);
    }

    // Raamat määrab abstraktse meetodi konkreetse käitumise: laenutusaeg on 21 päeva.
    // Override aitab kompilaatoril kontrollida, et meetodi nimi ja tüübid on õiged.
    @Override
    public int getLaenutusPaevad() {
        return 21;
    }
}
