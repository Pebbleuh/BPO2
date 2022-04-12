package echec.pieces;

import echec.Coordonnée;
import echec.Echiquier;

import java.util.ArrayList;

public class Tour extends Pièce {

    /**
     * Constructeur d'une Tour
     * @param couleur
     * @param ligne
     * @param colonne
     */
    public Tour(String couleur, int ligne, int colonne) {
        super(couleur, ligne, colonne);
        this.type = "TOUR";
    }

    /**
     * Vérifie qu'un coup est possible pour la tour
     * @param e l'échiquier actuel
     * @param x ligne
     * @param y colonne
     * @return true si le coup est possible
     */
    @Override
    public boolean estPossible(Echiquier e, int x, int y) {
        if (e.getPièce(ligne, colonne) != null
                && e.getPièce(ligne, colonne).getCouleur() == this.getCouleur())
            return false;
        // la colonne ou la ligne précisée doit être différent de la case initiale
        if(!((colonne == y && ligne != x) || (colonne != y && ligne == x)))
            return false;

        // vérifier qu'elle peut se déplacer verticalement
        if (colonne == y) {
            // bas
            if (ligne < x) {
                // vérifier qu'il n'y a pas de pièces dans son chemin
                for (int i = ligne + 1; i < x; ++i) {
                    if(e.getPièce(i, colonne) != null)
                        return false;
                }
            }
            // haut
            if(ligne > x) {
                for (int i = ligne - 1; i > x; --i) {
                    if(e.getPièce(i, colonne) != null)
                        return false;
                }
            }
        }

        // vérifier qu'elle peut se déplacer horizontalement
        if (ligne == x) {
            // droite
            if(colonne < y) {
                for (int i = colonne + 1; i < y; ++i) {
                    if(e.getPièce(ligne, i) != null)
                        return false;
                }
            }
            //gauche
            if(colonne > y) {
                for (int i = colonne - 1; i > y; --i) {
                    if (e.getPièce(ligne, i) != null)
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * Ajoute dans une liste tous les coups possibles d'une tour sur l'échiquier
     * @param e l'échiquier actuel
     * @return coups la liste des coups possibles
     */
    @Override
    public ArrayList<Coordonnée> coupsPossibles(Echiquier e) {
        ArrayList<Coordonnée> coups = new ArrayList<>();

        // on balaye tout l'échiquier
        // bas
        for (int i = ligne + 1; i < Echiquier.MAX; ++i) {
            if (e.getPièce(i, colonne) != null
                    && this.getCouleur() == e.getPièce(i, colonne).getCouleur())
                break;
            coups.add(new Coordonnée(i, colonne)); // ajoute la coordonnée
            if (e.getPièce(i, colonne) != null) // pièce sur le chemin
                break;
        }
        // haut
        for (int i = ligne - 1; i >= Echiquier.MIN - 1; --i) {
            if (e.getPièce(i, colonne) != null
                    && this.getCouleur() == e.getPièce(i, colonne).getCouleur())
                break;
                coups.add(new Coordonnée(i, colonne));
            if (e.getPièce(i, colonne) != null)
                break;
        }
        // droite
        for (int i = colonne + 1; i < Echiquier.MAX; ++i) {
            if (e.getPièce(ligne, i) != null
                    && this.getCouleur() == e.getPièce(ligne, i).getCouleur())
                break;
                coups.add(new Coordonnée(ligne, i));
            if (e.getPièce(ligne, i) != null)
                break;
        }
        // gauche
        for (int i = colonne - 1; i >= Echiquier.MIN - 1; --i) {
            if (e.getPièce(ligne, i) != null
                    && this.getCouleur() == e.getPièce(ligne, i).getCouleur())
                break;
            coups.add(new Coordonnée(ligne, i));
            if (e.getPièce(ligne, i) != null)
                break;
        }
        return coups;
    }

    /**
     * Représentation visuelle de la tour par un caractère
     * @return le caractère
     */
    @Override
    public char getSymbole() {
        if (couleur.equals("BLANC"))
            return 'T';
        else
            return 't';
    }
}
