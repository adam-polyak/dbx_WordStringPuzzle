# dbx_WordStringPuzzle

Szólánc

Feladat:

A szólánc olyan szavak sorozata, ahol minden szó egy betűben különbözik az előtte található szótól. A különbség lehet betű törlése, betű beszúrása vagy betű kicserélése más betűre.

Például:

cat cot coat oat hat hot hog dog

Írj olyan programot, amely megadott szavakból előállít egy szóláncot (ha lehetséges)! A létrehozott szóláncban a bemenetként kapott szavak közül mindet fel kell használni.

Input: Egy sor (a standard bemenetről), amelyben szavak vannak szóközzel elválasztva (véletlenszerű sorrendben).
Output: Egy szólánc, amelyet ezekből a szavakból lehet előállítani. A kimenet legyen hibaüzenet, ha a kapott szavakból nem lehetséges szóláncot előállítani.

Példa:

Bemenet: alma körte barack
Kimenet: hiba: a megadott szavakból nem lehetséges szóláncot építeni!

Bemenet: coat hat hot dog cat hog cot oat
Kimenet: cat cot coat oat hat hot hog dog

Bemenet: A01 1000 ABC 101 A0C 1001 ABD AB
Kimenet: 1000 1001 101 A01 A0C ABC AB ABD

Opcionális feladat (ha jut idő rá):
Standard bemenet helyett fájlból olvassa fel a szóláncban felhasználandó szavakat.
