import java.util.ArrayList;
import java.util.Scanner;

// Peaklass tegeleb kasutajaga suhtlemisega; andmete reeglid on teistes klassides.
public class Peaklass {
    // Java alustab programmi täitmist main-meetodist. args-i siin ei kasutata.
    public static void main(String[] args) {
        // Loome raamatukogu ja kolm näidisobjekti. Need luuakse igal käivitamisel uuesti.
        Raamatukogu kogu = new Raamatukogu();
        kogu.lisa(new Raamat(1, "Kevade"));
        kogu.lisa(new Raamat(2, "Rehepapp"));
        kogu.lisa(new Ajakiri(3, "Eesti Loodus"));

        // Scanner loeb klaviatuurilt. try sulgeb selle automaatselt ploki lõppedes.
        try (Scanner sisend = new Scanner(System.in)) {
            // Tsükkel kordab menüüd seni, kuni kasutaja valib väljumise.
            boolean tootab = true;
            while (tootab) {
                System.out.println("\nRAAMATUKOGU\n1 - Kõik teavikud\n2 - Otsi pealkirja järgi"
                        + "\n3 - Saadaval teavikud\n4 - Laenuta\n5 - Tagasta\n0 - Välju");
                System.out.print("Valik: ");
                if (!sisend.hasNextLine()) {
                    break; // Lõpetame viisakalt ka siis, kui sisendvoog suletakse.
                }
                // Valik on String, sest menüü jaoks pole arvutamist vaja.
                String valik = sisend.nextLine().trim();
                try {
                    // switch valib sisestatud tekstile vastava tegevuse.
                    switch (valik) {
                        case "1":
                            // Tühi tekst leiab kõik pealkirjad; false lülitab saadavuse filtri välja.
                            kuva(kogu.otsi("", false));
                            break;
                        case "2":
                            // Kasutaja võib sisestada ka ainult osa pealkirjast.
                            System.out.print("Pealkiri või selle osa: ");
                            if (!sisend.hasNextLine()) { return; }
                            kuva(kogu.otsi(sisend.nextLine().trim(), false));
                            break;
                        case "3":
                            // true jätab tulemustesse ainult laenutamata teavikud.
                            kuva(kogu.otsi("", true));
                            break;
                        case "4":
                        case "5":
                            // Laenutamine ja tagastamine kasutavad sama ID küsimise loogikat.
                            System.out.print("Teaviku ID: ");
                            if (!sisend.hasNextLine()) { return; }
                            // Loeme alati terve rea, et vältida nextInt/nextLine segamise vigu.
                            int id = Integer.parseInt(sisend.nextLine().trim());
                            // Ülemklassi tüüpi viide võib osutada Raamatule või Ajakirjale.
                            Teavik teavik = kogu.leiaIdJargi(id);
                            // Stringi sisu võrreldakse equals-iga; == võrdleks objektide viiteid.
                            if (valik.equals("4")) {
                                teavik.laenuta();
                                // Polümorfism: päeva arvu määrab leitud objekti tegelik klass.
                                System.out.println("Laenutatud: " + teavik.getPealkiri()
                                        + ". Laenutusaeg: " + teavik.getLaenutusPaevad() + " päeva.");
                            } else {
                                teavik.tagasta();
                                System.out.println("Tagastatud: " + teavik.getPealkiri());
                            }
                            break;
                        case "0":
                            // break lõpetab switch-i, false lõpetab järgmisel kontrollil while-tsükli.
                            tootab = false;
                            break;
                        default:
                            // Tundmatu menüüvalik ei lõpeta programmi.
                            System.out.println("Vali menüüst number 0–5.");
                    }
                } catch (NumberFormatException e) {
                    // Siia jõuavad tekstina antud ID ja ka int-i jaoks liiga suur arv.
                    // Täpsem erand peab olema enne üldisemat IllegalArgumentException-it.
                    System.out.println("ID peab olema täisarv.");
                } catch (IllegalArgumentException | IllegalStateException e) {
                    // Näiteks puuduv ID või juba laenutatud teavik. Menüü töö jätkub.
                    System.out.println("Viga: " + e.getMessage());
                }
            }
        }
        System.out.println("Head aega!");
    }

    // Abimeetod väldib sama kuvamisloogika kordamist igas menüüharus.
    // private: kasutatakse ainult siin klassis; void: meetod ei tagasta väärtust.
    private static void kuva(ArrayList<Teavik> teavikud) {
        if (teavikud.isEmpty()) {
            System.out.println("Ühtegi sobivat teavikut ei leitud.");
        }
        // for-each käib nimekirja järjest läbi. println kutsub objekti toString-meetodit.
        for (Teavik teavik : teavikud) {
            System.out.println(teavik);
        }
    }
}
