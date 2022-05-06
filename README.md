### beadando1
## A program célja

A program Gyogyszer nyilvántartásra lett kitalálva és az adott dolgokat tárolja:
 

    name -> Gyogyszer neve

    id -> Gyogyszer azonosítója

    veny -> Gyogyszer vényre vagy vény nélkül kapható

 
* name: a Gyogyszerek nevei valóságban is tartalmazhatnak betüket és számokat is így erre nem ad hibát.
* id: különböző Gyóygszertára különböző azonosítókat adnak meg ezáltal csak a hosszra szürtem le de tartalmazhat mint etüket mint számokat, a hossza a bairt szövegnek 3-nál nagyobb egyenlő és 10-nél kisebb egyenlőnek kell lenni.
*	veny: 0-át vvagy 1-et várbe amit a kiiratásnál irok át, 0 ha vény nélkül kapható az adott Gyogyszer, 1 ha vényre kapható.

## A program működése

Első sorban a főmenüt hozza be, ahol 4 opciónk van,

### Főmenü:
1.	List pills  -> Kilistázza az xml fáljban lévő elemeket
2.	Add new pill -> Uj elem hozzáadása az xml fájlhoz
3.	Modify a pill -> Elem módósítása
4.	Delete a pill -> Elem törlése
0.	Exit -> Kilépés a programból

## 2.Add new pill

Hozzá lehet adni egy új elemet.

A hozzá adni kívánt elemet beirás közben ellenörzi hogy létezik e már az adott név vagy azonosító

## 3.Modify a pill

Bekér egy Pill nevet melyet ha tartalmaz az xml fájl akkor feladja az alábbi választásokat:
1.	Modify Id-> Az elöbiekben megadott névhez tartozó elem id-ját lehet modosítani, ellenörzi hogy létezik e már az adott id
2.	Modify Veny -> Az elöbiekben megadott névhez tartozó elem veny értéket lehet modosítani
0.	Cancel -> Kilépés a Főmenübe

## 4.Delete a pill

Át dob az adott választásokra:

1.	Delete about Name-> Ez után a választás után lehet megadni nevet ami alapján ki töröll egy elemet ha megtalálja az xml fájlban az adott nevet
2.	Delete about Id -> Ez után a választás után lehet megadni id-t ami alapján ki töröll egy elemet ha megtalálja az xml fájlban az adott id-t
0.	Cancel -> Kilépés a Főmenübe
