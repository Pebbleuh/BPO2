package echec.pieces;

import java.util.ArrayList;

import echec.Coordonnée;
import echec.Echiquier;

public class Reine extends Pièce {

	/**
	 * Constructeur d'une Reine
	 * @param couleur
	 * @param ligne
	 * @param colonne
	 */
	public Reine(String couleur, int ligne, int colonne) {
		super(couleur, ligne, colonne);
		this.type = "REINE";
	}

	/**
	 * Ajoute dans une liste tous les coups possibles d'une reine sur l'échiquier
	 * @param e l'échiquier actuel
	 * @return coups la liste des coups possibles
	 */
    @Override
    public ArrayList<Coordonnée> coupsPossibles(Echiquier e) {
    	ArrayList<Coordonnée> coups = new ArrayList<Coordonnée>();
    	
    	// haut
    	for (int i = ligne + 1; i < Echiquier.MAX; ++i) {
    		if (e.getPièce(i, colonne) != null
    				&& this.getCouleur() == e.getPièce(i, colonne).getCouleur())
    			break;
    		coups.add(new Coordonnée(i, colonne));
    		if (e.getPièce(i, colonne) != null)
    			break;
    	}
    	
    	// bas
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
    	
    	//gauche
    	for (int i = colonne - 1; i >= Echiquier.MIN - 1; --i) {
    		if (e.getPièce(ligne, i) != null
    				&& this.getCouleur() == e.getPièce(ligne, i).getCouleur())
    			break;
    		coups.add(new Coordonnée(ligne, i));
    		if (e.getPièce(ligne, i) != null)
    			break;
    	}
    	
    	// nord-ouest
    	for (int i = ligne - 1, j = colonne - 1;
    			i >= Echiquier.MIN - 1 && j >= Echiquier.MIN - 1; --i, --j) {
    		if (e.getPièce(i, j) != null
    				&& this.couleur == e.getPièce(i, j).getCouleur())
    			break;
    		coups.add(new Coordonnée(i, j));
    		if (e.getPièce(i, j) != null)
    			break;
    	}
    	
    	// nord-est
    	for (int i = ligne - 1, j = colonne + 1;
    			i >= Echiquier.MIN - 1 && j < Echiquier.MAX; --i, ++j) {
    		if (e.getPièce(i, j) != null
    				&& this.couleur == e.getPièce(i, j).getCouleur())
    			break;
    		coups.add(new Coordonnée(i, j));
    		if (e.getPièce(i, j) != null)
    			break;
    	}
    	
    	// sud-est
    	for (int i = ligne + 1, j = colonne + 1;
    			i < Echiquier.MAX && j < Echiquier.MAX; ++i, ++j) {
    		if (e.getPièce(i, j) != null
    				&& this.couleur == e.getPièce(i, j).getCouleur())
    			break;
    		coups.add(new Coordonnée(i, j));
    		if (e.getPièce(i, j) != null)
    			break;
    	}
    	
    	// sud-ouest
    	for (int i = ligne + 1, j = colonne - 1;
    			i < Echiquier.MAX && j >= Echiquier.MIN - 1; ++i, --j) {
    		if (e.getPièce(i, j) != null
    				&& this.couleur == e.getPièce(i, j).getCouleur())
    			break;
    		coups.add(new Coordonnée(i, j));
    		if (e.getPièce(i, j) != null)
    			break;
    	}
    	
    	return coups;
    }

	/**
	 * Vérifie qu'un coup est possible pour la reine
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
    	
    	if (!((colonne == y && ligne != x) || (colonne != y && ligne == x)
    			|| (Math.abs(ligne - x) == Math.abs(colonne - y))))
			return false; 

    	if (colonne == y) {
    		// bas
    		if (ligne < x) {
    			for (int i = ligne + 1; i < x; ++i) {
    				if (e.getPièce(i, colonne) != null)
    					return false;
    			}
    		}
	
    		// haut
    		if (ligne > x) {
    			for (int i = ligne - 1; i > x; --i) {
    				if (e.getPièce(i, colonne) != null)
    					return false;
    			}
    		}
    	}	
	
    	if (ligne == x) {
    		// droite
    		if (colonne < y) {
    			for (int i = colonne + 1; i < y; ++i) {
    				if (e.getPièce(ligne, i) != null)
    					return false;
    			}
    		}
	
    		//gauche
    		if (colonne > y) {
    			for (int i = colonne - 1; i > y; --i) {
    				if (e.getPièce(ligne, i) != null)
    					return false;
    			}
    		}
    	}
    	
    	// nord-ouest
    	if (ligne > x && colonne > y) {
    		for (int i = ligne - 1, j = colonne - 1; i > x && j > y; --i, --j) {
    			if (e.getPièce(i, j) != null)
    				return false;
    		}
    	}
    	
    	// nord-est
    	if (ligne > x && colonne < y) {
    		for (int i = ligne - 1, j = colonne + 1; i > x && j < y; --i, ++j) {
    			if (e.getPièce(i, j) != null)
    				return false;
    		}
    	}
    	
    	// sud-est
    	if (ligne < x && colonne < y) {
    		for (int i = ligne + 1, j = colonne + 1; i < x && j < y; ++i, ++j) {
    			if (e.getPièce(i, j) != null)
    				return false;
    		}
    	}
    	
    	// sud-ouest
    	if (ligne < x && colonne > y) {
    		for (int i = ligne + 1, j = colonne - 1; i < x && j > y; ++i, --j) {
    			if (e.getPièce(i, j) != null)
    				return false;
    		}
    	}
        
        return true;
    }

	/**
	 * Représentation visuelle de la reine par un caractère
	 * @return le caractère
	 */
    @Override
    public char getSymbole() {
        if (couleur.equals("BLANC"))
            return 'D';
        else
            return 'd';
    }

}
