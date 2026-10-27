import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;

// Raamatukogu haldab andmeid; menüü ja kasutaja sisend asuvad Peaklassis.
public class Raamatukogu {
    // Võti on unikaalne ID. HashMap võimaldab ID järgi kiiresti otsida.
    private final HashMap<Integer, Teavik> teavikud = new HashMap<>();

    // Sama meetod võtab vastu nii Raamatu kui Ajakirja, sest mõlemad on Teavikud.
    // Integer on int-i ümbrisobjekt: kollektsiooni tüübiparameeter peab olema viitetüüp.
    // final keelab kaardi asendamise, kuid kaardi sisu saab lisamisega muuta.
    public void lisa(Teavik teavik) {
        // null tähendab, et viide ei osuta ühelegi objektile.
        if (teavik == null) {
            throw new IllegalArgumentException("Teavik puudub.");
        }
        // Duplikaadi kontroll väldib olemasoleva teaviku kogemata ülekirjutamist.
        if (teavikud.containsKey(teavik.getId())) {
            throw new IllegalArgumentException("Sellise ID-ga teavik on juba olemas.");
        }
        teavikud.put(teavik.getId(), teavik); // Salvestame paari: ID -> objekt.
    }

    // ID järgi otsimine on HashMap-is keskmiselt O(1), st tavaliselt väga kiire.
    public Teavik leiaIdJargi(int id) {
        Teavik teavik = teavikud.get(id);
        // get tagastab null-i, kui võtit pole. Anname kasutajale arusaadava vea.
        if (teavik == null) {
            throw new IllegalArgumentException("Sellise ID-ga teavikut ei leitud.");
        }
        return teavik;
    }

    // otsing on pealkirja osa; ainultSaadaval määrab, kas laenutatud jäetakse välja.
    public ArrayList<Teavik> otsi(String otsing, boolean ainultSaadaval) {
        // Uus nimekiri kaitseb sisemist kollektsiooni ja sobib sorteerimiseks.
        ArrayList<Teavik> tulemused = new ArrayList<>();
        // Väiketähed võimaldavad otsida tähesuurusest sõltumata.
        // Locale.ROOT hoiab teisenduse sõltumatuna arvuti keeleseadest.
        String otsitav = otsing.toLowerCase(Locale.ROOT);
        // values annab kaardi objektid ilma võtmeteta; tsükkel vaatab igaühe läbi.
        for (Teavik teavik : teavikud.values()) {
            boolean pealkiriSobib = teavik.getPealkiri().toLowerCase(Locale.ROOT).contains(otsitav);
            // contains kontrollib teksti osa. Tühja otsingu korral sobib iga pealkiri.
            // Olek sobib, kui filtrit pole vaja VÕI teavik on laenutamata.
            boolean olekSobib = !ainultSaadaval || !teavik.isLaenutatud();
            // && tähendab JA: mõlemad tingimused peavad olema tõesed.
            if (pealkiriSobib && olekSobib) {
                tulemused.add(teavik);
            }
        }
        // HashMap ei taga järjekorda, seega sorteerime tulemused pealkirja järgi.
        // Teavik::getPealkiri ütleb, millist väärtust võrdlemiseks kasutada.
        tulemused.sort(Comparator.comparing(Teavik::getPealkiri, String.CASE_INSENSITIVE_ORDER));
        // Nimekiri on uus, kuid selle sees on viited samadele Teaviku objektidele.
        return tulemused;
    }
}
