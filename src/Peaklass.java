import java.util.ArrayList;
import java.util.Scanner;

public class Peaklass {
    public static void main(String[] args) {
        Raamatukogu kogu = new Raamatukogu();
        kogu.lisa(new Raamat(1, "Kevade"));
        kogu.lisa(new Raamat(2, "Rehepapp"));
        kogu.lisa(new Ajakiri(3, "Eesti Loodus"));

        try (Scanner sisend = new Scanner(System.in)) {
            boolean tootab = true;
            while (tootab) {
                System.out.println("\nRAAMATUKOGU\n1 - Kõik teavikud\n2 - Otsi pealkirja järgi"
                        + "\n3 - Saadaval teavikud\n4 - Laenuta\n5 - Tagasta\n0 - Välju");
                System.out.print("Valik: ");
                if (!sisend.hasNextLine()) {
                    break; // Lõpetame viisakalt ka siis, kui sisendvoog suletakse.
                }
                String valik = sisend.nextLine().trim();
                try {
                    switch (valik) {
                        case "1":
                            kuva(kogu.otsi("", false));
                            break;
                        case "2":
                            System.out.print("Pealkiri või selle osa: ");
                            if (!sisend.hasNextLine()) { return; }
                            kuva(kogu.otsi(sisend.nextLine().trim(), false));
                            break;
                        case "3":
                            kuva(kogu.otsi("", true));
                            break;
                        case "4":
                        case "5":
                            System.out.print("Teaviku ID: ");
                            if (!sisend.hasNextLine()) { return; }
                            // Loeme alati terve rea, et vältida nextInt/nextLine segamise vigu.
                            int id = Integer.parseInt(sisend.nextLine().trim());
                            Teavik teavik = kogu.leiaIdJargi(id);
                            if (valik.equals("4")) {
                                teavik.laenuta();
                                System.out.println("Laenutatud: " + teavik.getPealkiri()
                                        + ". Laenutusaeg: " + teavik.getLaenutusPaevad() + " päeva.");
                            } else {
                                teavik.tagasta();
                                System.out.println("Tagastatud: " + teavik.getPealkiri());
                            }
                            break;
                        case "0":
                            tootab = false;
                            break;
                        default:
                            System.out.println("Vali menüüst number 0–5.");
                    }
                } catch (NumberFormatException e) {
                    // Täpsem erand peab olema enne üldisemat IllegalArgumentException-it.
                    System.out.println("ID peab olema täisarv.");
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println("Viga: " + e.getMessage());
                }
            }
        }
        System.out.println("Head aega!");
    }

    private static void kuva(ArrayList<Teavik> teavikud) {
        if (teavikud.isEmpty()) {
            System.out.println("Ühtegi sobivat teavikut ei leitud.");
        }
        for (Teavik teavik : teavikud) {
            System.out.println(teavik);
        }
    }
}
