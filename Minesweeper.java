import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class Minesweeper {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        /*  POMEN ŠT. NA POLJU:
         *  BOMBA = -1
         *  št. bomb okoli polja = #
         *  POMEN KOMAND (input):
         *  [št. vrstice][št. stolpca] E (explore)/ F (flag)
         */

        int[][] mreza = new int[25][12];
        char[][] prikazanaMreza = new char[25][12];
        int stPoteze = 1;
        int stFlagov = 45;
        int stUporabljenihFlagov = 0;
        boolean jeKonc = false;

        
        dodajBombe(mreza);
        dopolniMrezo(mreza);
        inicializiraj(prikazanaMreza);

        
        printajPrikazanoMrezo(prikazanaMreza);
        System.out.println("Vnesi # vrstice zelenega polja:");
        int inputVr = sc.nextInt();
        System.out.println("Vnesi # stolpca zelenega polja:");
        int inputSt = sc.nextInt();
        System.out.printf("Tip vnosa (E - explore / F - flag(Na voljo se: %d zastavic.)):", stFlagov);
        char inputType = sc.next().charAt(0);
        

        while(!jeKonc){
            if(inputType == 'F'){
                stUporabljenihFlagov++;
            }
            jeKonc = konecIgre(stPoteze, inputVr, inputSt, inputType, mreza, prikazanaMreza);
            posodobi(prikazanaMreza, mreza, inputVr, inputSt, inputType);
            if(jeKonc){
                break;
            }
            stPoteze++;

            System.out.println("Vnesi # vrstice zelenega polja:");
            inputVr = sc.nextInt();
            System.out.println("Vnesi # stolpca zelenega polja:");
            inputSt = sc.nextInt();
            System.out.printf("Tip vnosa (E - explore / F - flag(Na voljo se: %d zastavic.)):", stFlagov-stUporabljenihFlagov);
            inputType = sc.next().charAt(0);
        }
    }
    public static boolean konecIgre(int stPoteze, int inputVr, int inputSt, char vnosType, int[][] mreza, char[][] prikMreza){
        boolean pogoj = false;
        if(mreza[inputVr][inputSt] == -1 && vnosType == 'E'){
            if(stPoteze == 1){
                preusmeriBombo(mreza, inputVr, inputSt);
            }
            else{
                zguba();
                return true;
            }
        }
        for(int i = 0; i < 25; i++){
            pogoj = true;
            for(int j = 0; j < 12; j++){
                if(!(mreza[i][j] == -1 && prikMreza[i][j] == 'F')){
                    pogoj = false;
                    break;
                }
            }
            if(!pogoj){
                break;
            }
        }
        if(pogoj){
            zmaga();
        }

        return pogoj;
    }
    
    public static void posodobi(char[][] prikMreza, int[][] mreza, int vrstica, int stolpec, char inputType){
        char naMrezi = Character.forDigit(mreza[vrstica][stolpec], 10);
        char ch = '/';

        if(inputType == 'E'){
            prikMreza[vrstica][stolpec] = naMrezi;
            if((vrstica != 0) && (vrstica != 24) && (stolpec != 0) && (stolpec != 11)){
                for(int i = -1; i < 2; i++){
                    for(int j = -1; j < 2; j++){
                        if((mreza[vrstica + i][stolpec + j] != -1) ){
                            if(!(i == 0 && j == 0)){
                            ch = Character.forDigit((mreza[vrstica + i][stolpec + j]), 10);
                            prikMreza[vrstica + i][stolpec + j] = ch;
                            }
                        }
                    }
                }
            }
            else if(vrstica == 0){
                if(mreza[vrstica + 1][stolpec] != -1){
                    ch = Character.forDigit((mreza[vrstica + 1][stolpec]), 10);
                    prikMreza[vrstica+1][stolpec] = ch;
                }
                if(mreza[vrstica][stolpec - 1] != -1){
                    if(stolpec != 0){
                        ch = Character.forDigit((mreza[vrstica][stolpec - 1]), 10);
                        prikMreza[vrstica][stolpec-1] = ch;
                    }
                }
                if(mreza[vrstica - 1][stolpec - 1] != -1){
                    if(stolpec != 0){
                        ch = Character.forDigit((mreza[vrstica - 1][stolpec - 1]), 10);
                        prikMreza[vrstica-1][stolpec-1] = ch;
                    }
                }
                if(mreza[vrstica][stolpec + 1] != -1){
                    if(stolpec != 11){
                        ch = Character.forDigit((mreza[vrstica][stolpec + 1]), 10);
                        prikMreza[vrstica][stolpec+1] = ch;
                    }
                }
                if(mreza[vrstica + 1][stolpec + 1] != -1){
                    if(stolpec != 11){
                        ch = Character.forDigit((mreza[vrstica + 1][stolpec + 1]), 10);
                        prikMreza[vrstica+1][stolpec+1] = ch;
                    }
                }
            }
            else if(vrstica == 24){
                if(mreza[vrstica - 1][stolpec] != -1){
                    ch = Character.forDigit((mreza[vrstica - 1][stolpec]), 10);
                    prikMreza[vrstica-1][stolpec] = ch;
                }
                if(mreza[vrstica][stolpec-1] != -1){
                    if(stolpec != 0){
                        ch = Character.forDigit((mreza[vrstica][stolpec - 1]), 10);
                        prikMreza[vrstica][stolpec-1] = ch;
                    }
                }
                if(mreza[vrstica-1][stolpec-1] != -1){
                    if(stolpec != 0){
                        ch = Character.forDigit((mreza[vrstica - 1][stolpec - 1]), 10);
                        prikMreza[vrstica-1][stolpec-1] = ch;
                    }
                }
                if(mreza[vrstica][stolpec+1] != -1){
                    if(stolpec != 11){
                        ch = Character.forDigit((mreza[vrstica][stolpec + 1]), 10);
                       prikMreza[vrstica][stolpec+1] = ch;
                    }
                }
                if(mreza[vrstica+1][stolpec+1] != -1){
                    if(stolpec != 11){
                        ch = Character.forDigit((mreza[vrstica+1][stolpec + 1]), 10);
                       prikMreza[vrstica+1][stolpec+1] = ch;
                    }
                }
            }
            else if(stolpec == 0){
                if(mreza[vrstica - 1][stolpec] != -1){
                    ch = Character.forDigit((mreza[vrstica - 1][stolpec]), 10);
                    prikMreza[vrstica-1][stolpec] = ch;
                }
                if(mreza[vrstica - 1][stolpec + 1] != -1){
                    ch = Character.forDigit((mreza[vrstica-1][stolpec + 1]), 10);
                    prikMreza[vrstica-1][stolpec+1] = ch;
                }
                if(mreza[vrstica+1][stolpec] != -1){
                    ch = Character.forDigit((mreza[vrstica + 1][stolpec]), 10);
                    prikMreza[vrstica+1][stolpec] = ch;
                }
                if(mreza[vrstica][stolpec + 1] != -1){
                    ch = Character.forDigit((mreza[vrstica][stolpec + 1]), 10);
                    prikMreza[vrstica][stolpec+1] = ch;
                }
                if(mreza[vrstica + 1][stolpec + 1] != -1){
                    ch = Character.forDigit((mreza[vrstica + 1][stolpec + 1]), 10);
                    prikMreza[vrstica+1][stolpec+1] = ch;
                }
            }
            else if(stolpec == 11){
                if(mreza[vrstica - 1][stolpec] != -1){
                    ch = Character.forDigit((mreza[vrstica - 1][stolpec]), 10);
                    prikMreza[vrstica-1][stolpec] = ch;
                }
                if(mreza[vrstica - 1][stolpec - 1] != -1){
                    ch = Character.forDigit((mreza[vrstica-1][stolpec - 1]), 10);
                    prikMreza[vrstica-1][stolpec+1] = ch;
                }
                if(mreza[vrstica-1][stolpec] != -1){
                    ch = Character.forDigit((mreza[vrstica - 1][stolpec]), 10);
                    prikMreza[vrstica+1][stolpec] = ch;
                }
                if(mreza[vrstica][stolpec + 1] != -1){
                    ch = Character.forDigit((mreza[vrstica][stolpec + 1]), 10);
                    prikMreza[vrstica][stolpec+1] = ch;
                }
                if(mreza[vrstica + 1][stolpec - 1] != -1){
                    ch = Character.forDigit((mreza[vrstica + 1][stolpec - 1]), 10);
                    prikMreza[vrstica+1][stolpec+1] = ch;
                }
            }
        }
        else if(inputType == 'F'){
            prikMreza[vrstica][stolpec] = 'F';
        }
        printajPrikazanoMrezo(prikMreza);
    }
    public static void preusmeriBombo(int[][] mreza, int vrstica, int stolpec){
        Random rd = new Random();
        mreza[vrstica][stolpec] = 0;
        int NovaVrstica = rd.nextInt(25);
        int NovStolpec = rd.nextInt(12);
        while(((vrstica != NovaVrstica) || (stolpec != NovStolpec)) && (mreza[NovaVrstica][NovStolpec] == -1)){
            NovaVrstica = rd.nextInt(25);
            NovStolpec = rd.nextInt(12);
        }
        mreza[NovaVrstica][NovStolpec] = -1;
        dopolniMrezo(mreza);
    }
    public static void inicializiraj (char[][] mreza){
        for(int i = 0; i < 25; i++){
            for(int j = 0; j < 12; j++){
                mreza[i][j] = 'x';
            }
        }
    }
    public static void printajPrikazanoMrezo(char[][] mreza){
        for(int i = 0; i < 25; i++){
            for(int j = 0; j < 12; j++){
                if(j == 11){
                    System.out.printf("%2c\n\n", mreza[i][j]);
                    break;
                }
                System.out.printf("%2c  ", mreza[i][j]);
            }
        }
    }
    public static void printajMrezo(int[][] mreza){
        for(int i = 0; i < 25; i++){
            for(int j = 0; j < 12; j++){
                if(j == 11){
                    System.out.printf("%2d\n\n", mreza[i][j]);
                    break;
                }
                System.out.printf("%2d  ", mreza[i][j]);
            }
        }
    }
    public static void dodajBombe(int[][] mreza){
        Random rd = new Random();
        int vrstica = 0;
        int stolpec = 0;
        boolean zeNot = false;
        int stBomb = 45;
        int notrBomb = 0;

        while(notrBomb != stBomb){
            zeNot = false;
            vrstica = rd.nextInt(25);
            stolpec = rd.nextInt(12);
            if(mreza[vrstica][stolpec] == -1){
                zeNot = true;
            }
            if(!zeNot){
                mreza[vrstica][stolpec] = -1;
                notrBomb++;
            }
        }
    }
    public static void dopolniMrezo(int[][] mreza){
        int stBombOkoli = 0;
        for(int i = 1; i < 24; i++){
            for(int j = 1; j < 11; j++){
                if(mreza[i][j] != -1){
                    stBombOkoli = 0;
                    if(mreza[i-1][j-1] == -1){
                        stBombOkoli++;
                    }
                    if(mreza[i-1][j] == -1){
                        stBombOkoli++;
                    }
                    if(mreza[i-1][j+1] == -1){
                        stBombOkoli++;
                    }
                    if(mreza[i][j-1] == -1){
                        stBombOkoli++;
                    }
                    if(mreza[i][j+1] == -1){
                        stBombOkoli++;
                    }
                    if(mreza[i+1][j-1] == -1){
                        stBombOkoli++;
                    }
                    if(mreza[i+1][j] == -1){
                        stBombOkoli++;
                    }
                    if(mreza[i+1][j+1] == -1){
                        stBombOkoli++;
                    }
                    mreza[i][j] = stBombOkoli;
                }
            }
        }
        for(int i = 1; i < 24; i++){
            if(mreza[i][0] != -1){
                stBombOkoli = 0;
                if(mreza[i-1][0] == -1){
                    stBombOkoli++;
                }
                if(mreza[i+1][0] == -1){
                    stBombOkoli++;
                }
                if(mreza[i-1][1] == -1){
                    stBombOkoli++;
                }
                if(mreza[i+1][1] == -1){
                    stBombOkoli++;
                }
                if(mreza[i][1] == -1){
                    stBombOkoli++;
                }
                mreza[i][0] = stBombOkoli;
            }
            if(mreza[i][11] != -1){
                stBombOkoli = 0;
                if(mreza[i-1][11] == -1){
                    stBombOkoli++;
                }
                if(mreza[i+1][11] == -1){
                    stBombOkoli++;
                }
                if(mreza[i-1][10] == -1){
                    stBombOkoli++;
                }
                if(mreza[i+1][10] == -1){
                    stBombOkoli++;
                }
                if(mreza[i][10] == -1){
                    stBombOkoli++;
                }
                mreza[i][11] = stBombOkoli;
            }
        }
        for(int i = 0; i < 12; i++){
            if(mreza[0][i] != -1){
                stBombOkoli = 0;
                if(mreza[1][i] == -1){
                    stBombOkoli++;
                }
                if(i != 11){
                    if(mreza[0][i+1] == -1){
                        stBombOkoli++;
                    }
                    if(mreza[1][i+1] == -1){
                        stBombOkoli++;
                    }
                }
                if(i != 0){
                    if(mreza[0][i-1] == -1){
                        stBombOkoli++;
                    }
                    if(mreza[1][i-1] == -1){
                        stBombOkoli++;
                    }
                }
                mreza[0][i] = stBombOkoli;
            }
            if(mreza[24][i] != -1){
                stBombOkoli = 0;
                if(mreza[23][i] == -1){
                    stBombOkoli++;
                }
                if(i != 11){
                    if(mreza[24][i+1] == -1){
                        stBombOkoli++;
                    }
                    
                    if(mreza[23][i+1] == -1){
                        stBombOkoli++;
                    }
                }
                if(i != 0){
                    if(mreza[23][i-1] == -1){
                        stBombOkoli++;
                    }
                    if(mreza[24][i-1] == -1){
                        stBombOkoli++;
                    }
                }
                mreza[24][i] = stBombOkoli;
            }
        }
    }

    public static int stejBombe(int[][] mreza){
        int stBomb = 0;
        for(int i = 0; i < 25; i++){
            for(int j = 0; j < 12; j++){
                if(mreza[i][j] == -1){
                    stBomb++;
                }
            }
        }
        return stBomb;
    }
    public static void zguba(){
        System.out.println("Izgubil si.");
    }
    public static void zmaga(){
        System.out.println("Čestitke, zmagal si!");
    }
}
