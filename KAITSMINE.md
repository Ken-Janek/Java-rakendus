- **Teavik:** ühised andmed ja reeglid, näiteks ID, pealkiri ja laenutamise olek.
- **Raamat:** teavik, mida laenutatakse 21 päevaks.
- **Ajakiri:** teavik, mida laenutatakse 7 päevaks.
- **Raamatukogu:** hoiab teavikuid ning otsib ja sorteerib neid.
- **Peaklass:** käivitab programmi, loeb sisendi ja kuvab menüü.

| Küsimus | Vastus ja näide |
| --- | --- |
| Kus on pärilus? | `Raamat extends Teavik`. Raamat pärib Teaviku avalikud meetodid; private välju kasutab ta ülemklassi meetodite kaudu. |
| Kus on kapseldamine? | `private boolean laenutatud`. Seda saab muuta ainult kontrollivate laenuta ja tagasta meetoditega. |
| Kus on abstraktsioon? | `abstract class Teavik` kirjeldab ühist alust. `new Teavik(...)` pole lubatud; loon konkreetse Raamatu või Ajakirja. |
| Kus on polümorfism? | `Teavik t = new Ajakiri(4, "Täheke");` ja `t.getLaenutusPaevad()` annab 7. Java kasutab tegeliku objekti meetodit. |
| Miks HashMap? | ID on võti. `get(id)` leiab vastava teaviku ilma kogu nimekirja käsitsi läbimata. Võtmed on unikaalsed. |
| Miks ArrayList? | Otsingu tulemusi on mugav järjestada, sorteerida ja läbi käia. |
| Miks mitte kõik väljad public? | Siis saaks kontrollidest mööda minna ja objekti vigasesse olekusse viia. |
| Abstraktne klass või interface? | Abstraktne klass sobib siin, sest jagan ka olekut ja konstruktorit. Liides sobib ühise käitumislepingu jaoks, ka eri päritolu klassidele. |
| int, Integer, String? | int on primitiivne täisarv; Integer on täisarvu ümbrisobjekt; String on tekst. HashMap vajab tüübiparameetrina Integer-it, mitte int-i. |
| boolean? | true või false; siin näitab, kas teavik on laenutatud. |
| Viitetüüp? | Näiteks String, Teavik või ArrayList; muutuja sisaldab viidet objektile. |
| == või equals? | Primitiividel võrdleb == väärtust, objektidel viiteid. String.equals võrdleb teksti sisu. Meie Teavik ei kirjuta equals-i üle, seega seal võrreldakse vaikimisi objektide samasust. |
| Mida tähendab final? | Väljale ei saa uut väärtust omistada. final HashMap sisu saab endiselt muuta. |
| Miks @Override? | Näitab, et meetod kirjutab päritud meetodi üle; kompilaator kontrollib signatuuri. |
| Mida teeb super? | Kutsub alamklassi konstruktoris ülemklassi konstruktori, mis seab ID ja pealkirja. |
| Commit ja push? | Commit salvestab kohaliku muudatuste versiooni. Push saadab kohalikud commit'id serverisse, näiteks GitHubi. |

## Laenutamise teekond

1. Kasutaja valib `4` ja sisestab ID `1`.
2. `Integer.parseInt` muudab teksti arvuks. Vigane arv jõuab catch-plokki.
3. `leiaIdJargi(1)` küsib HashMap-ist teaviku. Puuduva ID korral tekib erand.
4. `laenuta()` kontrollib senist olekut ning muudab selle true-ks.
5. `getLaenutusPaevad()` valib tegeliku alamklassi meetodi; Kevade puhul on tulemus 21.
6. Menüü kordub. Andmete muutus kestab kuni programmi sulgemiseni.

## Otsingu tingimuse seletus

`!ainultSaadaval || !teavik.isLaenutatud()` tähendab: kui saadavuse filtrit pole vaja, sobib iga olek; muidu peab teavik olema laenutamata. `&&` nõuab nii sobivat pealkirja kui ka sobivat olekut. Tühi otsing leiab kõik pealkirjad, sest iga tekst sisaldab tühja teksti.

Otsing tagastab uue ArrayList-i, kuid selle sees on samad Teaviku objektid. Nimekirjast eemaldamine ei eemalda teavikut raamatukogust; teaviku laenutamine muudab sama objekti olekut.

## Muudatuste tegemine

### 1. Muuda raamatu laenutusaega

Muuda Raamat.java meetodis `return 21;` väärtuseks `return 28;`. Kompileeri uuesti ja laenuta raamat. Ajakirja aeg jääb 7 päevaks.

### 2. Lisa uus alamklass

Loo src/Dvd.java:

```java
public class Dvd extends Teavik {
    public Dvd(int id, String pealkiri) {
        super(id, pealkiri);
    }

    @Override
    public int getLaenutusPaevad() {
        return 3;
    }
}
```

Lisa Peaklassi teiste algandmete juurde `kogu.lisa(new Dvd(4, "Kevade film"));`. Kompileeri ning laenuta ID 4. Menüü ja Raamatukogu ei vaja muutmist, sest nad kasutavad ühist Teaviku tüüpi.

### 3. Näita ainult laenutatud teavikuid

Lisa Raamatukogu klassi meetod:

```java
public ArrayList<Teavik> leiaLaenutatud() {
    ArrayList<Teavik> tulemused = new ArrayList<>();
    for (Teavik teavik : teavikud.values()) {
        if (teavik.isLaenutatud()) {
            tulemused.add(teavik);
        }
    }
    return tulemused;
}
```

Lisa menüü teksti valik 6 ning switch-i enne default-haru `case "6": kuva(kogu.leiaLaenutatud()); break;`. Muuda ka vale valiku teadet. Laenuta üks teavik ja kontrolli, et valik 6 näitab ainult seda. See näidis ei sorteeri tulemusi.

### 4. Ennusta tulemus enne proovimist

- Asendan Raamatus 21 väärtusega 0: programm kuvab 0 päeva, sest praegu selle tagastusväärtuse kontrolli pole.
- Laenutan sama teaviku kaks korda: teine kord annab veateate; olek jääb true-ks.
- Lisan sama ID-ga kaks teavikut: lisa viskab erandi; olemasolevat teavikut ei kirjutata üle.
- Eemaldan sorteerimise: HashMap-i tulemuste järjekord pole tagatud.
- Asendan pealkirja võrdluses contains-i equals-iga: otsing nõuab kogu pealkirja vastavust.
- Kirjutan `Teavik t = new Raamat(8, "Test");`: t.getLaenutusPaevad() annab 21.
