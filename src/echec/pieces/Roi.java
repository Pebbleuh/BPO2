package echec.pieces;

import echec.Coordonnée;
import echec.Echiquier;

import java.util.ArrayList;

public class Roi extends Pièce {

    private static Pièce priseVérif = null;
    private static Pièce priseCoupPossible = null;

    /**
     * Constructeur d'un Roi
     * @param couleur
     * @param ligne
     * @param colonne
     */
    public Roi(String couleur, int ligne, int colonne) {
        super(couleur, ligne, colonne);
        this.type = "ROI";
    }

    /**
     * Vérifie qu'un coup est possible pour le roi
     * @param e l'échiquier actuel
     * @param x ligne
     * @param y colonne
     * @return true si le coup est possible
     */
    @Override
    public boolean estPossible(Echiquier e, int x, int y) {
            if (e.getPièce(x, y) != null
                    && e.getPièce(x, y).getCouleur() == this.getCouleur())
                return false;

        // vertical ou horizontal
        if((ligne == (x + 1) && colonne == y)
                || (ligne == (x - 1) && colonne == y)
                || (ligne == x && colonne == (y + 1))
                || (ligne == x && colonne == (y - 1))) {
            return true;
        }

        // diagonale
        if ((ligne == (x + 1) && colonne == (y + 1))
                || (ligne == (x + 1) && colonne == (y - 1))
                || (ligne == (x - 1) && colonne == (y + 1))
                || (ligne == (x - 1) && colonne == (y - 1))) {
            return true;
        }

        return false;
    }

    /**
     * Retourne le caractère représentant le roi
     * @return le caractère
     */
    @Override
    public char getSymbole() {
        return (couleur.equals("BLANC") ? 'R' : 'r');
    }

    /**
     * Retourne tous les coups possibles d'un roi sur l'échiquier
     * @param e l'échiquier en cours
     * @return coups
     */
    @Override
    public ArrayList<Coordonnée> coupsPossibles(Echiquier e) {
        ArrayList<Coordonnée> coups = new ArrayList<>();

        // balayer les lignes, jusqu'à ligne+1 (déplacement d'une case)
        for (int i = ligne-1; i <= ligne+1 && i < Echiquier.MAX; ++i) {
            if (i < 0)
                ++i;
            // balayer les colonnes, jusqu'à colonne+1 (déplacement d'une case)
            for (int j = colonne-1; j <= colonne+1 && j < Echiquier.MAX; ++j) {
                if (j < 0)
                    ++j;

                if(estPossible(e, i, j)) {
                    Coordonnée départ = new Coordonnée(ligne, colonne);
                    Coordonnée arrivée = new Coordonnée(i, j);
                    if(e.getPriseDernierCoup() != null)
                        priseCoupPossible = e.getPriseDernierCoup();
                    e.déplacer(départ, arrivée);
                    if(!échec(e))
                        coups.add(new Coordonnée(i, j));
                    e.annulerDernierCoup(départ, arrivée);
                    e.setPriseDernierCoup(priseCoupPossible);
                    priseCoupPossible = null;
                }
            }
        }
        return coups;
    }

    /**
     * Vérifie si le roi est en échec ou non (la pièce adverse peut se déplacer à sa position)
     * @param e l'échiquier en cours
     * @return false si le roi n'est pas en échec
     */
    public boolean échec(Echiquier e) {
        // balaye le plateau
        for (int i = 0; i < Echiquier.MAX; ++i) {
            for (int j = 0; j < Echiquier.MAX; ++j) {
                Pièce p = e.getPièce(i, j);
                if (p != null && !(ligne == i && colonne == j)) // pas sur la même case que le roi actuel
                    if (p.getCouleur() != this.couleur && p.estPossible(e, ligne, colonne)) {
                        return true; // échec
                    }
            }
        }
        return false;
    }

    /**
     * Vérifie que le roi peut être protégé
     * @param e l'échiquier actuel
     * @return false s'il ne peut pas être protégé
     */
    public boolean peutEtreProtégé(Echiquier e) {
        ArrayList<Pièce> pièces = new ArrayList<>();

        for (int i = 0; i < Echiquier.MAX; ++i) {
            for (int j = 0; j < Echiquier.MAX; ++j) {
                Pièce p = e.getPièce(i, j);

                // si la pièce n'est pas sur la case du roi
                if (!(ligne == i && colonne == j) && p != null)
                    // s'ils ne sont pas de la même couleur
                    if (p.getCouleur() != this.getCouleur())
                        pièces.add(p); // on ajoute la pièce dans les pièces menaces
            }
        }
        for (Pièce p : pièces) {
            ArrayList<Coordonnée> aTester = new ArrayList<>();

            for (Coordonnée coord : aTester) {
                Coordonnée départ = new Coordonnée(p.getLigne(), p.getColonne());
                Coordonnée arrivée = new Coordonnée(coord.getLigne(), coord.getColonne());

                if (e.getPièce(arrivée.getLigne(), arrivée.getColonne()) != null)
                    priseVérif = e.getPièce(arrivée.getLigne(), arrivée.getColonne());
                e.déplacer(départ, arrivée);

                if(!this.échec(e)) {
                    e.annulerDernierCoup(départ, arrivée);
                    e.setPièce(arrivée.getLigne(), arrivée.getColonne(), priseVérif);
                    return true;
                }
                e.annulerDernierCoup(départ, arrivée);
                e.setPièce(arrivée.getLigne(), arrivée.getColonne(), priseVérif);
                priseVérif = null;
            }
        }
        return false;
    }

}
