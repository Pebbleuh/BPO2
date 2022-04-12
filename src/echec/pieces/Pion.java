package echec.pieces;

import java.util.ArrayList;

import echec.Coordonnée;
import echec.Echiquier;

public class Pion extends Pièce {
	private boolean premierCoup;
	// pour vérifier qu'il peut bouger de 2 cases seulement au premier coup

	/**
	 * Constructeur d'un Pion
	 * @param couleur
	 * @param ligne
	 * @param colonne
	 */
	public Pion(String couleur, int ligne, int colonne){
		super(couleur, ligne, colonne);
		this.premierCoup =  true;
		this.type = "PION";
	}

	/**
	 * Vérifie qu'un coup est possible pour le pion
	 * @param e l'échiquier actuel
	 * @param x ligne
	 * @param y colonne
	 * @return true si le coup est possible
	 */
	public boolean estPossible(Echiquier e, int x, int y) {
		// pion blanc
		if(this.couleur == "BLANC") {
			// devant
			if(ligne - 1 == x
					&& e.getPièce(x, colonne) == null)
				return true;

			// devant droite
			if(ligne - 1 == x && colonne + 1 == y
					&& e.getPièce(x, y) != null)
				// l'autre pièce doit être de couleur différente
				if(e.getPièce(x, y).couleur != this.couleur)
					return true;

			// devant gauche
			if(ligne - 1 == x && colonne - 1 == y
					&& e.getPièce(x, y) != null)
				if(e.getPièce(x, y).couleur != this.couleur)
					return true;

			// 2 cases en avant
			if(ligne - 2 == x
					&& premierCoup
					&& e.getPièce(x, colonne) == null
					&& e.getPièce(ligne - 1, colonne) == null)
				return true;
		}
		// pion noir
		else {
			// devant
			if(ligne + 1 == x
					&& e.getPièce(ligne + 1, colonne) == null)
				return true;

			// devant droite
			if(ligne + 1 == x && colonne + 1 == y
					&& e.getPièce(x, y) != null)
				if(e.getPièce(x, y).couleur != this.couleur)
					return true;

			// devant gauche
			if(ligne + 1 == x && colonne - 1 == y
					&& e.getPièce(x, y) != null)
				if(e.getPièce(x, y).couleur != this.couleur)
					return true;

			// 2 cases en avant
			if(ligne + 2 == x
					&& premierCoup
					&& e.getPièce(x, colonne) == null
					&& e.getPièce(ligne + 1, colonne) == null)
				return true;
		}
		return false;
	}

	/**
	 * Ajoute dans une liste tous les coups possibles d'un pion sur l'échiquier
	 * @param e l'échiquier actuel
	 * @return coups la liste des coups possibles
	 */
	public ArrayList<Coordonnée> coupsPossibles(Echiquier e) {
		ArrayList<Coordonnée> coups = new ArrayList<Coordonnée>();

		// pion blanc
		if(this.couleur == "BLANC") {
			// devant
			if(ligne - 1 >= Echiquier.MIN - 1
					&& e.getPièce(ligne - 1, colonne) == null)
				coups.add(new Coordonnée(ligne - 1, colonne));

			// devant droite
			if(ligne - 1 >= Echiquier.MIN - 1 && colonne + 1 < Echiquier.MAX
					&& e.getPièce(ligne - 1, colonne + 1) != null)
				if(e.getPièce(ligne - 1, colonne + 1).couleur != this.couleur)
					coups.add(new Coordonnée(ligne - 1, colonne + 1));

			// devant gauche
			if(ligne - 1 >= Echiquier.MIN - 1 && colonne - 1 >= Echiquier.MIN - 1
					&& e.getPièce(ligne - 1, colonne - 1) != null)
				if(e.getPièce(ligne - 1, colonne - 1).couleur != this.couleur)
					coups.add(new Coordonnée(ligne - 1, colonne - 1));

			// 2 cases en avant
			if(ligne - 2 >= Echiquier.MIN - 1
					&& premierCoup
					&& e.getPièce(ligne - 2, colonne) == null
					&& e.getPièce(ligne - 1, colonne) == null)
				coups.add(new Coordonnée(ligne - 2, colonne));
		}
		// pion noir
		else {
			// devant
			if(ligne + 1 < Echiquier.MAX
					&& e.getPièce(ligne + 1, colonne) == null)
				coups.add(new Coordonnée(ligne + 1, colonne));

			// devant droite
			if(ligne + 1 < Echiquier.MAX && colonne + 1 < Echiquier.MAX
					&& e.getPièce(ligne + 1, colonne + 1) != null)
				if(e.getPièce(ligne + 1, colonne + 1).couleur != this.couleur)
					coups.add(new Coordonnée(ligne + 1, colonne + 1));

			// devant gauche
			if(ligne + 1 < Echiquier.MAX && colonne - 1 >= Echiquier.MIN - 1
					&& e.getPièce(ligne + 1, colonne - 1) != null)
				if(e.getPièce(ligne + 1, colonne - 1).couleur != this.couleur)
					coups.add(new Coordonnée(ligne + 1, colonne - 1));

			// 2 cases en avant
			if(ligne + 2 < Echiquier.MAX
					&& premierCoup
					&& e.getPièce(ligne + 2, colonne) == null
					&& e.getPièce(ligne + 1, colonne) == null)
				coups.add(new Coordonnée(ligne + 2, colonne));
		}
		return coups;
	}

	/**
	 * Représentation visuelle du pion par un caractère
	 * @return le caractère
	 */
	public char getSymbole() {
		if(couleur.equals("BLANC"))
			return 'P';
		else
			return 'p';
	}

	/**
	 * Modifie le booléen du premier coup à false si le pion a déjà bougé une fois
	 * @param b le booléen donné
	 */
	public void setPremierCoup(boolean b) {
		this.premierCoup = b;
	}
}
