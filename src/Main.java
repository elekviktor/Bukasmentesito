import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. feladat
        Tallomas[][] sz = new Tallomas[21][21];
        InputStream be = new FileInputStream("vonat.txt");
        Scanner sc = new Scanner(be);
        int szerelveny = 0;
        int allomas = 0;
        int ora = 0;
        int perc = 0;
        String muvelet = "";
        int i = 0;
        for (int i1 = 0; i1 < 21; i1++) {
            for (int j = 0; j < 21; j++) {
                sz[i1][j] = new Tallomas();
            }
        }
        while (sc.hasNext()) {
            szerelveny = sc.nextInt();
            allomas = sc.nextInt();
            ora = sc.nextInt();
            perc = sc.nextInt();
            muvelet = sc.next();
            for (int i1 = 0; i1 < sz.length; i1++) {
                for (int j = 0; j < sz[i].length; j++) {
                    sz[i][j] = new Tallomas();
                }
            }
            Tallomas tallomas = new Tallomas();
            if (muvelet.equals("E")) {
                sz[szerelveny][allomas].erk.ora = ora;
                sz[szerelveny][allomas].erk.perc = perc;
                sz[szerelveny][allomas].erk.ido = ora * 60 + perc;
            } else {
                sz[szerelveny][allomas].ind.ora = ora;
                sz[szerelveny][allomas].ind.perc = perc;
                sz[szerelveny][allomas].ind.ido = ora * 60 + perc;
            }
        }
        be.close();
        // 2. feladat
        System.out.println("2. feladat");
        System.out.println("Az allomasok szama: " + (allomas + 1));
        System.out.println("A vonatok szama: " + szerelveny);

        // 3. feladat
        System.out.println("3. feladat");
        int maxvarakozas = -1;
        int maxszerelveny = 0;
        int maxallomas = 0;
        for (int i1 = 1; i1 <= szerelveny; i1++) {
            for (int j = 0; j < allomas; j++) {
                int varakozas = sz[i1][j].ind.ido - sz[i1][j].erk.ido;
                if (varakozas > maxvarakozas) {
                    maxszerelveny = i1;
                    maxallomas = j;
                    maxvarakozas = varakozas;
                }
            }
        }
        System.out.println("A(z) " + maxszerelveny + ". vonat a(z) " + maxallomas + ". allomason " + maxvarakozas + " percet allt.");

        // 4. feladat
        System.out.println("4. feladat");
        int vsz = 0;
        int vora = 0;
        int vperc = 0;
        System.out.print("Adja meg egy vonat azonositojat! ");
        vsz = sc.nextInt();
        System.out.print("Adjon meg egy idopontot (ora perc)! ");
        vora = sc.nextInt();
        vperc = sc.nextInt();

        // 5. Feladat
        System.out.println("5. feladat");
        int hossz = sz[vsz][allomas].erk.ido - sz[vsz][0].ind.ido;
        System.out.print("A(z) " + vsz + ". vonat utja ");
        if (hossz > 2 * 60 + 22)
            System.out.print(hossz - (2 * 60 + 22) + " perccel hosszabb volt az eloirtnal.");
        if (hossz == 2 * 60 + 22) System.out.print(" pontosan az eloirt ideig tartott.");
        if (hossz < 2 * 60 + 22)
            System.out.print((2 * 60 + 22) - hossz + " perccel rovidebb volt az eloirtnal.");
        System.out.println();

        // 6. feladat
        StringBuilder konvert = new StringBuilder();
        konvert.append("halad").append(vsz).append(".txt");
        String fajlnev = konvert.toString();
        PrintStream ki = new PrintStream(new FileOutputStream(fajlnev));
        for (int i1 = 1; i1 <= allomas; i1++) {
            ki.println(i1 + ". allomas: " + sz[vsz][i1].erk.ora + ":" + sz[vsz][i1].erk.perc);
        }
        ki.close();

        // 7. feladat
        System.out.println("7. feladat");
        int idopont = vora * 60 + vperc;
        for (int i1 = 1; i1 <= szerelveny; i1++) {
            for (int j = 0; j < allomas; j++) {
                if (sz[i1][0].ind.ido <= idopont && idopont < sz[i1][1].erk.ido) {
                    System.out.println("Az " + i1 + ". vonat a " + j + ". allomason allt.");
                }
                if (sz[i1][j].ind.ido <= idopont && idopont < sz[i1][j + 1].erk.ido) {
                    System.out.println(
                            "Az " + i1 + ". vonat a " + j + ". es a " + (j + 1) + ". allomas kozott jart.");
                }
            }
        }
        sc.close();
    }
}

class Tallomas {
    Tido ind = new Tido();
    Tido erk = new Tido();
}

class Tido {
    int ora = 0;
    int perc = 0;
    int ido = 0;
}