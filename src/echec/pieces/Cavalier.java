package echec.pieces;

import java.util.ArrayList;

import echec.Coordonnée;
import echec.Echiquier;

public class Cavalier extends Pièce {

	/**
	 * Constructeur d'un Cavalier
	 * @param couleur
	 * @param ligne
	 * @param colonne
	 */
	public Cavalier(String couleur, int ligne, int colonne) {
		super(couleur, ligne, colonne);
		this.type = "CAVALIER";
	}

	/**
	 * Vérifie qu'un coup est possible pour le cavalier
	 * @param e l'échiquier actuel
	 * @param x ligne
	 * @param y colonne
	 * @return true si le coup est possible
	 */
	public boolean estPossible(Echiquier e, int x, int y) {
		if(e.getPièce(x, y) != null
				&& e.getPièce(x, y).getCouleur() == this.getCouleur())
			return false;

		// bas + gauche
		if(ligne + 2 == x && colonne - 1 == y)
			return true;

		// bas + droite
		if(ligne + 2 == x && colonne + 1 == y)
			return true;

		// haut + droite
		if(ligne - 2 == x && colonne + 1 == y)
			return true;

		// haut + gauche
		if(ligne - 2 == x && colonne - 1 == y)
			return true;

		// droite + bas
		if(ligne + 1 == x && colonne + 2 == y)
			return true;

		// droite + haut
		if(ligne - 1 == x && colonne + 2 == y)
			return true;

		// gauche + bas
		if(ligne + 1 == x && colonne - 2 == y)
			return true;

		// gauche + haut
		if(ligne - 1 == x && colonne - 2 == y)
			return true;

		return false;
	}

	/**
	 * Ajoute dans une liste tous les coups possibles d'un cavalier sur l'échiquier
	 * @param e l'échiquier actuel
	 * @return coups la liste des coups possibles
	 */
	public ArrayList<Coordonnée> coupsPossibles(Echiquier e){
		ArrayList<Coordonnée> coups = new ArrayList<Coordonnée>();

		// bas + gauche
		if(ligne + 2 < Echiquier.MAX && colonne - 1 >= Echiquier.MIN - 1
				&& (e.getPièce(ligne + 2, colonne - 1) == null
				|| (e.getPièce(ligne + 2, colonne - 1) != null
				&& e.getPièce(ligne + 2, colonne - 1).getCouleur() != this.getCouleur())))
			coups.add(new Coordonnée(ligne + 2, colonne - 1));

		// bas + droite
		if(ligne + 2 < Echiquier.MAX && colonne + 1 < Echiquier.MAX
				&& (e.getPièce(ligne + 2, colonne + 1) == null
				|| (e.getPièce(ligne + 2, colonne + 1) != null
				&& e.getPièce(ligne + 2, colonne + 1).getCouleur() != this.getCouleur())))
			coups.add(new Coordonnée(ligne + 2, colonne + 1));

		// haut + droite
		if(ligne - 2 >= Echiquier.MIN - 1 && colonne + 1 < Echiquier.MAX
				&& (e.getPièce(ligne - 2, colonne + 1) == null
				|| (e.getPièce(ligne - 2, colonne + 1) != null
				&& e.getPièce(ligne - 2, colonne + 1).getCouleur() != this.getCouleur())))
			coups.add(new Coordonnée(ligne - 2, colonne + 1));

		// haut + gauche
		if(ligne - 2 >= Echiquier.MIN - 1 && colonne - 1 >= Echiquier.MIN - 1
				&& (e.getPièce(ligne - 2, colonne - 1) == null
				|| (e.getPièce(ligne - 2, colonne - 1) != null
				&& e.getPièce(ligne - 2, colonne - 1).getCouleur() != this.getCouleur())))
			coups.add(new Coordonnée(ligne - 2, colonne - 1));

		// droite + bas
		if(ligne + 1 < Echiquier.MAX && colonne + 2 < Echiquier.MAX
				&& (e.getPièce(ligne + 1, colonne + 2) == null
				|| (e.getPièce(ligne + 1, colonne + 2) != null
				&& e.getPièce(ligne + 1, colonne + 2).getCouleur() != this.getCouleur())))
			coups.add(new Coordonnée(ligne + 1, colonne + 2));


		// droite + haut
		if(ligne - 1 >= Echiquier.MIN - 1 && colonne + 2 < Echiquier.MAX
				&& (e.getPièce(ligne - 1, colonne + 2) == null
				|| (e.getPièce(ligne - 1, colonne + 2) != null
				&& e.getPièce(ligne - 1, colonne + 2).getCouleur() != this.getCouleur())))
			coups.add(new Coordonnée(ligne - 1, colonne + 2));

		// gauche + bas
		if(ligne + 1 < Echiquier.MAX && colonne - 2 >= Echiquier.MIN - 1
				&& (e.getPièce(ligne + 1, colonne - 2) == null
				|| (e.getPièce(ligne + 1, colonne - 2) != null
				&& e.getPièce(ligne + 1, colonne - 2).getCouleur() != this.getCouleur())))
			coups.add(new Coordonnée(ligne + 1, colonne - 2));

		// gauche + haut
		if(ligne - 1 >= Echiquier.MIN - 1 && colonne - 2 >= Echiquier.MIN - 1
				&& (e.getPièce(ligne - 1, colonne - 2) == null
				|| (e.getPièce(ligne - 1, colonne - 2) != null
				&& e.getPièce(ligne - 1, colonne - 2).getCouleur() != this.getCouleur())))
			coups.add(new Coordonnée(ligne - 1, colonne - 2));

		return coups;
	}

	/**
	 * Représentation visuelle du cavalier par un caractère
	 * @return le caractère
	 */
	public char getSymbole() {
		if(couleur.equals("BLANC"))
			return 'C';
		else
			return 'c';
	}
}