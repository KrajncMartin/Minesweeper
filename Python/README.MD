# Minesweeper
Python program igre Minesweeper (prva verzija, 11/07/2024)

# Različica programa
Python verzija programa trenutno (15:15, 11/07/2024) še ni na nivoju sestrskega javanskega programa. Tu manjka veliko več funkcionalnosti kot v "glavni" različici mojega Minesweeper programa. V načrtu je, da ta program preseže zmožnosti one različice, saj imam namen vpeljati tudi nek GUI (graphical user interface), predvidoma s tkinter knjižnico.

# Navodila za inštalacijo
Kloniramo repositorij na naš lokalni računalnik s tem, da v cmd (command prompt) vpišemo ukaz:
```
git clone https://github.com/KrajncMartin/Minesweeper.git
```
Se pomaknemo v klonirano mapo na računalniku:
```
cd Python
```
Ter zaženemo program:
```
Minesweeper.py
```
*Pri navodilih postavljanja programa se pričakuje, da ima uporabnik naložen tako Git, kot tudi Python.*<br />

# Navodila za igranje igre
Pred tabo bo prikazana mreža velikosti 12x25, na kateri je nakljucno vnesenih 45 min.
Tvoja naloga (ce jo le sprejmeš) je, da z zastavicami oznaciš polja za katera meniš, da so mine.
Vsako novo polje, ki odkriješ (ki ni mina) bo odprlo polja, ki se ga dotikajo (spet, ce niso mine), na katerih bo pisalo število min, ki se jih dotikajo.
Ce pa boš slucajno želel odkriti polje, ki skriva mino, boš sprožil le-to mino in igra se bo koncala (brez skrbi, pri prvi potezi je to nemogoce).
Pri vsaki potezi boš vnesel 3 parametre: vrstico, stolpec in tip vnosa. Vsak vnos parametra naj bo locen s presledkom.
Mreža je indeksirana tako, da lažje vidiš koordinate izbranega polja.
Pri tipu vnosa boš vnesel bodisi E za 'explore', oziroma odkrivanje polj, bodisi F za 'flag', torej za postavljanje zastavice.
Prva poteza privzame tip vnosa za E.<br />
*Ta navodila se nam prav tako prikažejo ob zagonu programa.*

  
# Avtor
Martin Krajnc
