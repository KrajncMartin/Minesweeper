# Minesweeper
Javanski program igre Minesweeper (prva verzija, 05/07/2024)

# Navodila za inštalacijo
Kloniramo repositorij na naš lokalni računalnik s tem, da v cmd (command prompt) vpišemo ukaz:
```
git clone https://github.com/SwissKitten/Minesweeper.git
```
Se pomaknemo v klonirano mapo na računalniku:
```
cd Najnovejša verzija
```
Prevedemo program:
```
javac Minesweeper.java
```
Ter ga zaženemo:
```
java Minesweeper.java
```
*Pri navodilih postavljanja programa se pričakuje, da ima uporabnik naložen tako Git, kot tudi JDK (Java Development Kit).*<br />
*Priložena je tudi prvotna "delujoča" verzija programa:* ***Minesweeper_v1.0.java*** *.*

# Navodila za igranje igre
Ob zagonu se nam prikaže mreža 25x12, na kateri je naključno postavljenih 45 min.
Za igranje vsake poteze moramo vnesti 3 parametre: 
1. Koordinate izbranega polja:
   * številko vrstice [0 - 24] in
   * številko stolpca [0 - 11]
2. Vrsto vnosa:
   * E (explore) ali F (flag)
  
# Avtor
Martin Krajnc
