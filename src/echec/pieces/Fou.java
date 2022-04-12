package echec.pieces;

import java.util.ArrayList;

import echec.Coordonnée;
import echec.Echiquier;

public class Fou extends Pièce {

	/**
	 * Constructeur d'un Fou
	 * @param couleur
	 * @param ligne
	 * @param colonne
	 */
	public Fou(String couleur, int ligne, int colonne) {
		super(couleur, ligne, colonne);
		this.type = "FOU";
	}

	/**
	 * Ajoute dans une liste tous les coups possibles d'un fou sur l'échiquier
	 * @param e l'échiquier actuel
	 * @return coups la liste des coups possibles
	 */
	@Override
	public ArrayList<Coordonnée> coupsPossibles(Echiquier e) {
		ArrayList<Coordonnée> coups = new ArrayList<Coordonnée>();

		// sud-ouest
		for (int i = ligne - 1, j = colonne - 1;
			 i >= Echiquier.MIN - 1 && j >= Echiquier.MIN - 1; --i, --j) {
			if(e.getPièce(i, j) != null
					&& this.couleur == e.getPièce(i, j).getCouleur())
				break;
			coups.add(new Coordonnée(i, j));
			if(e.getPièce(i, j) != null)
				break;
		}

		// sud-est
		for (int i = ligne - 1, j = colonne + 1;
			 i >= Echiquier.MIN - 1 && j < Echiquier.MAX; --i, ++j) {
			if(e.getPièce(i, j) != null
					&& this.couleur == e.getPièce(i, j).getCouleur())
				break;
			coups.add(new Coordonnée(i, j));
			if(e.getPièce(i, j) != null)
				break;
		}

		// nord-est
		for (int i = ligne + 1, j = colonne + 1;
			 i < Echiquier.MAX && j < Echiquier.MAX; ++i, ++j) {
			if(e.getPièce(i, j) != null
					&& this.couleur == e.getPièce(i, j).getCouleur())
				break;
			coups.add(new Coordonnée(i, j));
			if(e.getPièce(i, j) != null)
				break;
		}

		// nord-ouest
		for (int i = ligne + 1, j = colonne - 1;
			 i < Echiquier.MAX && j >= Echiquier.MIN - 1; ++i, --j) {
			if(e.getPièce(i, j) != null
					&& this.couleur == e.getPièce(i, j).getCouleur())
				break;
			coups.add(new Coordonnée(i, j));
			if(e.getPièce(i, j) != null)
				break;
		}
		return coups;
	}

	/**
	 * Vérifie qu'un coup est possible pour le fou
	 * @param e l'échiquier actuel
	 * @param x ligne
	 * @param y colonne
	 * @return true si le coup est possible
	 */
	@Override
	public boolean estPossible(Echiquier e, int x, int y) {
		if(e.getPièce(x, y) != null
				&& e.getPièce(x, y).getCouleur() == this.couleur)
			return false;

		if(!(Math.abs(ligne - x) == Math.abs(colonne - y)))
			return false;

		// nord-ouest
		if(ligne > x && colonne > y) {
			for (int i = ligne - 1, j = colonne - 1; i > x && j > y; --i, --j) {
				if(e.getPièce(i, j) != null)
					return false;
			}
		}

		// nord-est
		if(ligne > x && colonne < y) {
			for (int i = ligne - 1, j = colonne + 1; i > x && j < y; --i, ++j) {
				if(e.getPièce(i, j) != null)
					return false;
			}
		}

		// sud-est
		if(ligne < x && colonne < y) {
			for (int i = ligne + 1, j = colonne + 1; i < x && j < y; ++i, ++j) {
				if(e.getPièce(i, j) != null)
					return false;
			}
		}

		// sud-ouest
		if(ligne < x && colonne > y) {
			for (int i = ligne + 1, j = colonne - 1; i < x && j > y; ++i, --j) {
				if(e.getPièce(i, j) != null)
					return false;
			}
		}
		return true;
	}

	/**
	 * Représentation visuelle du fou par un caractère
	 * @return le caractère
	 */
	@Override
	public char getSymbole() {
		if(couleur.equals("BLANC"))
			return 'F';
		else
			return 'f';
	}
}