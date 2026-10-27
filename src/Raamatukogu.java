import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;

// Raamatukogu haldab andmeid; menüü ja kasutaja sisend asuvad Peaklassis.
public class Raamatukogu {
    // Võti on unikaalne ID. HashMap võimaldab ID järgi kiiresti otsida.
    private final HashMap<Integer, Teavik> teavikud = new HashMap<>();

    public void lisa(Teavik teavik) {
        if (teavik == null) {
            throw new IllegalArgumentException("Teavik puudub.");
        }
        if (teavikud.containsKey(teavik.getId())) {
            throw new IllegalArgumentException("Sellise ID-ga teavik on juba olemas.");
        }
        teavikud.put(teavik.getId(), teavik);
    }

    public Teavik leiaIdJargi(int id) {
        Teavik teavik = teavikud.get(id);
        if (teavik == null) {
            throw new IllegalArgumentException("Sellise ID-ga teavikut ei leitud.");
        }
        return teavik;
    }

    public ArrayList<Teavik> otsi(String otsing, boolean ainultSaadaval) {
        // Uus nimekiri kaitseb sisemist kollektsiooni ja sobib sorteerimiseks.
        ArrayList<Teavik> tulemused = new ArrayList<>();
        String otsitav = otsing.toLowerCase(Locale.ROOT);
        for (Teavik teavik : teavikud.values()) {
            boolean pealkiriSobib = teavik.getPealkiri().toLowerCase(Locale.ROOT).contains(otsitav);
            boolean olekSobib = !ainultSaadaval || !teavik.isLaenutatud();
            if (pealkiriSobib && olekSobib) {
                tulemused.add(teavik);
            }
        }
        // HashMap ei taga järjekorda, seega sorteerime tulemused pealkirja järgi.
        tulemused.sort(Comparator.comparing(Teavik::getPealkiri, String.CASE_INSENSITIVE_ORDER));
        return tulemused;
    }
}
