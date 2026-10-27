# Kontrollimine

Kompileeri README juhendi järgi ning proovi neid samme. Iga korduv käivitus alustab algandmetega.

| Tegevus | Oodatav tulemus |
| --- | --- |
| 1 | Eesti Loodus, Kevade ja Rehepapp pealkirja järjekorras |
| 2, siis KEV | Kevade; otsing ei sõltu tähesuurusest |
| 2, siis puuduv | Teade, et sobivaid teavikuid pole |
| 4, siis 1 | Kevade laenutatud 21 päevaks |
| 4, siis 1 uuesti | Viga: juba laenutatud |
| 3 | Kevade ei ole saadaval nimekirjas |
| 5, siis 1 | Kevade tagastatud |
| 5, siis 1 uuesti | Viga: ei ole laenutatud |
| 4, siis 3 | Ajakirja laenutusaeg on 7 päeva |
| 4, siis abc või 999999999999 | ID peab olema täisarv; programm jätkub |
| 4, siis 99 või -1 | ID-d ei leitud; programm jätkub |
| vale menüüvalik | Juhis valida 0–5; programm jätkub |
| 0 | Programm lõpetab |
