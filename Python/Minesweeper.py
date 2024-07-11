import random

mreza = []
prikMreza = []
stFlagov = 45
stUporabljenihFlagov = 0
konec = False

def printajMrezo(mrezaF):
    max_width = max(len(str(j)) for i in mrezaF for j in i)
    for i in mrezaF:
        formatted_row = ' '.join(f'{j:>{max_width}}' for j in i)
        print(formatted_row)
                
def inicializiraj(mrezaF):
    for i in range(25):
        mrezaF.append([])
        for j in range(12):
             mrezaF[i].append(0)
             
def inicializirajPrik(mrezaF):
    for i in range(26):
        mrezaF.append([])
        if(i == 0):
            for j in range(13):
                if(j == 0):
                    mrezaF[i].append(' ')
                else:
                    mrezaF[i].append(str(j))
        else:
            for j in range(13):
                if(j == 0):
                    mrezaF[i].append(str(i))
                else:
                    mrezaF[i].append('x')
            
def dodajBombe(mrezaF):
    vrstica = 0
    stolpec = 0
    zeNot = False
    stBomb = 45
    notrBomb = 0
    while(notrBomb != stBomb):
        zeNot = False
        vrstica = random.randint(0, 24)
        stolpec = random.randint(0, 11)
        if(mrezaF[vrstica][stolpec] == -1):
            zeNot = True
        if(not zeNot):
            mrezaF[vrstica][stolpec] = -1
            notrBomb += 1

def dopolniMrezo(mrezaF):
    stBombOkoli = 0
    for i in range(1, 24):
        for j in range(1, 11):
            if(mrezaF[i][j] != -1):
                stBombOkoli = 0
                if(mrezaF[i-1][j-1] == -1):
                    stBombOkoli += 1
                if(mrezaF[i-1][j] == -1):
                    stBombOkoli += 1
                if(mrezaF[i-1][j+1] == -1):
                    stBombOkoli += 1
                if(mrezaF[i][j-1] == -1):
                    stBombOkoli += 1
                if(mrezaF[i][j+1] == -1):
                    stBombOkoli += 1
                if(mrezaF[i+1][j-1] == -1):
                    stBombOkoli += 1
                if(mrezaF[i+1][j] == -1):
                    stBombOkoli += 1
                if(mrezaF[i+1][j+1] == -1):
                    stBombOkoli += 1
                mrezaF[i][j] = stBombOkoli
                
    for i in range (1, 24):
            if(mreza[i][0] != -1):
                stBombOkoli = 0
                if(mreza[i-1][0] == -1):
                    stBombOkoli += 1
                if(mreza[i+1][0] == -1):
                    stBombOkoli += 1
                if(mreza[i-1][1] == -1):
                    stBombOkoli += 1
                if(mreza[i+1][1] == -1):
                    stBombOkoli += 1
                if(mreza[i][1] == -1):
                    stBombOkoli += 1
                mreza[i][0] = stBombOkoli
            if(mreza[i][11] != -1):
                stBombOkoli = 0
                if(mreza[i-1][11] == -1):
                    stBombOkoli += 1
                if(mreza[i+1][11] == -1):
                    stBombOkoli += 1
                if(mreza[i-1][10] == -1):
                    stBombOkoli += 1
                if(mreza[i+1][10] == -1):
                    stBombOkoli += 1
                if(mreza[i][10] == -1):
                    stBombOkoli += 1
                mreza[i][11] = stBombOkoli
                    
    for i in range (12):
        if(mreza[0][i] != -1):
            stBombOkoli = 0
            if(mreza[1][i] == -1):
                stBombOkoli += 1
            if(i != 11):
                if(mreza[0][i+1] == -1):
                    stBombOkoli += 1
                if(mreza[1][i+1] == -1):
                    stBombOkoli += 1
            if(i != 0):
                if(mreza[0][i-1] == -1):
                    stBombOkoli += 1
                if(mreza[1][i-1] == -1):
                    stBombOkoli += 1
                mreza[0][i] = stBombOkoli
        if(mreza[24][i] != -1):
            stBombOkoli = 0
            if(mreza[23][i] == -1):
                stBombOkoli += 1
            if(i != 11):
                if(mreza[24][i+1] == -1):
                    stBombOkoli += 1
                if(mreza[23][i+1] == -1):
                    stBombOkoli += 1
            if(i != 0):
                if(mreza[23][i-1] == -1):
                    stBombOkoli += 1
                if(mreza[24][i-1] == -1):
                    stBombOkoli += 1
            mreza[24][i] = stBombOkoli
            
def redirect(mrezaF, vrsticaF, stolpecF):
    mrezaF[vrsticaF][stolpecF] = 0
    novaV = random.randint(0, 24)
    novS = random.randint(0, 11)
    while True:
        if((novaV != vrsticaF) or (novS != stolpecF)):
            if(mreza[novaV][novS] != -1):
                break
    mrezaF[novaV][novS] = -1

def zacetekIgre():
    print("Copyright © 2024 Martin Krajnc, All Rights Reserved.")
    print()
    print("MINESWEEPER")
    print("Dobrodošel v igro Minesweeper, ustvarjatelja Martina Krajnca, prvotno zasnovana 04/07/2024 v Ljubljani v javanskem programskem jeziku.")
    print()
    print("Pred tabo bo prikazana mreža velikosti 12x25, na kateri je nakljucno vnesenih 45 min.")
    print("Tvoja naloga (ce jo le sprejmeš) je, da z zastavicami oznaciš polja za katera meniš, da so mine.")
    print("Vsako novo polje, ki odkriješ (ki ni mina) bo odprlo polja, ki se ga dotikajo (spet, ce niso mine), na katerih bo pisalo število min, ki se jih dotikajo.")
    print("Ce pa boš slucajno želel odkriti polje, ki skriva mino, boš sprožil le-to mino in igra se bo koncala (brez skrbi, pri prvi potezi je to nemogoce).")
    print("Pri vsaki potezi boš vnesel 3 parametre: vrstico, stolpec in tip vnosa. Vsak vnos parametra naj bo locen s presledkom.")
    print("Mreža je indeksirana tako, da lažje vidiš koordinate izbranega polja.")
    print("Pri tipu vnosa boš vnesel bodisi E za 'explore', oziroma odkrivanje polj, bodisi F za 'flag', torej za postavljanje zastavice.")
    print("Prva poteza privzame tip vnosa za E.")
    print()
    inp = input("Torej, ali razumeš navodila? Želiš igrati? (Da / Ne): ")
    print()
    return inp

def zmaga():
    print("Cestitke, pravilno si oznacil vse mine, ZMAGAL SI!")
    
def poraz(prikMrezaF, vrsticaF, stolpecF):
    print()
    print("Na mestu ["+str(vrsticaF+1)+"]""["+str(stolpecF+1)+"] je bila mina, IZGUBIL SI.")

def konecIgre(mrezaF, prikMrezaF, vrsticaF, stolpecF, inputTypeF):
    pogoj = False
    st = 0
    
    if((inputTypeF == 'E') and (mrezaF[vrsticaF][stolpecF] == -1)):
        poraz(prikMrezaF, vrsticaF, stolpecF)
        return True
    
    for i in range (25):
        for j in range (12):
            if((mreza[i][j] == -1) and (prikMreza[i+1][j+1] == 'F')):
                st += 1
    if(st == 45):
        pogoj = True
    if(pogoj):
        zmaga()
    return pogoj

def posodobi(mrezaF, prikMrezaF, vrsticaF, stolpecF, inputTypeF):
    if(inputType == 'F'):
        if(prikMrezaF[vrsticaF][stolpecF] == 'F'):
            prikMrezaF[vrsticaF][stolpecF] = 'x'
        else:
            prikMrezaF[vrsticaF][stolpecF] = 'F'

    elif(inputType == 'E'):
        prikMrezaF[vrsticaF][stolpecF] = str(mrezaF[vrsticaF-1][stolpecF-1])
        
    printajMrezo(prikMrezaF)

inicializiraj(mreza)
inicializirajPrik(prikMreza)
dodajBombe(mreza)
dopolniMrezo(mreza)

if (zacetekIgre() == 'Da'):
    printajMrezo(prikMreza)
    
    inputType = 'E'
    vnVrstica, vnStolpec = input("Vnesi vrstico in stolpec želenega polja: ").split()
    vrstica = int(vnVrstica)
    stolpec = int(vnStolpec)
    
    if(mreza[vrstica-1][stolpec-1] == -1):
        redirect(mreza, vrstica-1, stolpec-1)
        dopolniMrezo(mreza)
    posodobi(mreza, prikMreza, vrstica, stolpec, inputType)
    
    while(not konec):
        print("Imaš še",(stFlagov - stUporabljenihFlagov),"zastavic.")
        vnVrstica, vnStolpec, inputType = input("Vnesi vrstico in stolpec želenega polja ter tip vnosa(E / F): ").split()
        vrstica = int(vnVrstica)
        stolpec = int(vnStolpec)
        if(inputType == 'F'):
            stUporabljenihFlagov += 1
        konec = konecIgre(mreza, prikMreza, vrstica-1, stolpec-1, inputType)
        posodobi(mreza, prikMreza, vrstica, stolpec, inputType)
        if(konec):
            break

