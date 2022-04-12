package echec;

import java.util.ArrayList;

public class Coordonnée {
    private int ligne; // ordonnées
    private int colonne; // abscisses

    /**
     * Constructeur d'une Coordonnée
     * @param x ligne
     * @param y colonne
     */
    public Coordonnée(int x, int y) {
        this.ligne = x;
        this.colonne = y;
    }

    /**
     * Retourne la coordonnée (un entier) d'une ligne
     * @return ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Retourne la coordonnée (un entier) d'une colonne
     * @return colonne
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Affiche les coordonnées ligne et colonne
     * @return L'affichage des coordonnées
     */
    public String toString(){
        return "[" + ligne + ", " + colonne + "]";
    }

    /**
     * Convertit le coup du joueur en coordonnées (entiers)
     * @param s le coup
     * @return c les coordonnées
     */
    public static Coordonnée[] stringToInt(String s) {
        ArrayList<Character> tmp = new ArrayList<>();
        tmp.add('8'); tmp.add('7'); tmp.add('6'); tmp.add('5');
        tmp.add('4'); tmp.add('3'); tmp.add('2'); tmp.add('1');

        s = s.replaceAll("\\s+","");
        s = s.toLowerCase();
        Coordonnée départ =
                new Coordonnée(tmp.indexOf(s.charAt(1)),
                        (int) s.charAt(0) - 97);
        Coordonnée arrivée =
                new Coordonnée(tmp.indexOf(s.charAt(3)),
                        (int) s.charAt(2) - 97);
        Coordonnée[] c = { départ, arrivée };
        return c;
    }

    /**
     * Convertit les coordonnées en coup (String)
     * @param départ
     * @param arrivée
     * @return s le coup
     */
    public static String intToString(Coordonnée départ, Coordonnée arrivée) {
        ArrayList<String> lettre = new ArrayList<>();
        lettre.add("a"); lettre.add("b"); lettre.add("c"); lettre.add("d");
        lettre.add("e"); lettre.add("f"); lettre.add("g"); lettre.add("h");

        ArrayList<String> chiffre = new ArrayList<>();
        chiffre.add("8"); chiffre.add("7"); chiffre.add("6"); chiffre.add("5");
        chiffre.add("4"); chiffre.add("3"); chiffre.add("2"); chiffre.add("1");

        String s = lettre.get(départ.getColonne()) + chiffre.get(départ.getLigne());
        s += lettre.get(arrivée.getColonne()) + chiffre.get(arrivée.getLigne());
        return s;
    }

}