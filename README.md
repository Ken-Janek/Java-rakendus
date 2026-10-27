# Väike raamatukogu

Lihtne Java käsurearakendus raamatute ja ajakirjade laenutamiseks. Saab näha kõiki teavikuid, otsida pealkirja osa järgi, filtreerida saadaval teavikuid ning laenutada ja tagastada ID alusel. Tulemused kuvatakse pealkirja järjekorras.

Andmed on ainult mälus: taaskäivitamisel taastuvad kolm näidisteavikut. Programm näitab laenutusaja pikkust; see ei arvuta tagastamiskuupäeva ega halda lugejaid.

## Käivitamine

Vaja on JDK 17 või uuemat (`java -version` ja `javac -version`). Väliseid teeke ega andmebaasi pole vaja. Kontrollitud JDK 25-ga.

Windowsis ava projekti kaustas terminal ja käivita:

```powershell
.\kaivita.bat
```

Või käivita käsitsi projekti kaustas:

```powershell
mkdir out -Force
javac -encoding UTF-8 -d out src/*.java
java -cp out Peaklass
```

Näide: vali `1`, seejärel `4`, sisesta ID `1`. Vali `3`: Kevade puudub saadaval teavikute nimekirjast. Vali `5` ja ID `1`, et see tagastada. `0` lõpetab programmi.

## Ülesehitus ja nõuded

| Nõue | Kus ja miks |
| --- | --- |
| Viis enda klassi | Teavik, Raamat, Ajakiri, Raamatukogu, Peaklass |
| Pärilus | Raamat ja Ajakiri laiendavad Teavikut |
| Abstraktsioon | Abstraktne Teavik nõuab getLaenutusPaevad meetodit |
| Kapseldamine | Teaviku väljad on private; laenuta/tagasta kontrollivad olekut |
| Polümorfism | Teavik-tüüpi viite getLaenutusPaevad tagastab raamatu puhul 21, ajakirja puhul 7 |
| HashMap | Integer ID seotakse Teavikuga; ID järgi otsimine on keskmiselt O(1) |
| ArrayList | Otsingu tulemuste järjestatud nimekiri, mida saab sorteerida |
| Otsing ja filter | Raamatukogu.otsi; pealkirja osa ja saadavus |
| Vigane sisend | Vale menüüvalik, mittetäisarvuline või tundmatu ID ja lubamatu korduv tegevus |

Kaitsmiseks vaata [KAITSMINE.md](KAITSMINE.md). Kontrollimise juhend on [KONTROLL.md](KONTROLL.md).

## Git ja GitHub

Kohalik ajalugu: `git log --oneline`. Commit salvestab muudatused kohalikult, push saadab need GitHubi.

GitHubi repositooriumi pole selle projekti jaoks veel loodud. Enne esitamist loo GitHubis tühi repositoorium (ilma README-ta), seejärel asenda allolev aadress oma repositooriumi aadressiga:

```powershell
git remote add origin https://github.com/KASUTAJA/REPOSITOOrium.git
git push -u origin HEAD
```

Esita õpetajale loodud repositooriumi link. AI aitas koodi ja õppematerjali koostada; kaitsmisel tuleb koodi ise mõista ja muuta.
