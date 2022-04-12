package echec;

import java.util.ArrayList;

import echec.pieces.Pièce;
import echec.pieces.Roi;

public class Partie {
	private Joueur blanc;
	private Joueur noir;
	private Echiquier echiquier;
	private boolean tourDeBlanc;
	private int nbCoupsSansPrises;

	/**
	 * Constructeur d'une Partie
	 * @param typeBlanc Humain ou IA pour le joueur blanc
	 * @param typeNoir Humain ou IA pour le joueur noir
	 */
	public Partie(String typeBlanc, String typeNoir) {
		if (typeBlanc.equals("Humain"))
			blanc = new Joueur("Humain", "BLANC");
		else
			blanc = new Joueur("IA", "BLANC");
		
		if (typeNoir.equals("Humain"))
			noir = new Joueur("Humain", "NOIR");
		else
			noir = new Joueur("IA", "NOIR");
		
		this.echiquier = new Echiquier();
		this.tourDeBlanc = true;
		this.nbCoupsSansPrises = 0;
	}

	/**
	 * Vérifie si c'est au tour du joueur blanc
	 * @return tourDeBlanc
	 */
	public boolean isTourDeBlanc() {
		return tourDeBlanc;
	}

	/**
	 * Passe d'un tour à l'autre
	 * @param b le booléen pour changer le tour
	 */
	public void setTourDeBlanc(boolean b) {
		this.tourDeBlanc = b;
	}

	/**
	 * Retourne l'échiquier
	 * @return échiquier
	 */
	public Echiquier getEchiquier() {
		return echiquier;
	}

	/**
	 * Le joueur saisit son coup et déplace la pièce
	 * @param s le coup saisi
	 */
	public void jouer(String s) {
		if(tourDeBlanc) {
			if(blanc.getType() == "Humain") {
				Coordonnée départ = Coordonnée.stringToInt(s)[0];
				Coordonnée arrivée = Coordonnée.stringToInt(s)[1];
				echiquier.déplacer(départ, arrivée);
			}
			else
				blanc.jouerIA(this);
		}
		else {
			if(noir.getType() == "Humain") {
				Coordonnée départ = Coordonnée.stringToInt(s)[0];
				Coordonnée arrivée = Coordonnée.stringToInt(s)[1];
				echiquier.déplacer(départ, arrivée);
			}
			else
				noir.jouerIA(this);
		}
		if(echiquier.getPriseDernierCoup() == null)
			nbCoupsSansPrises += 1;
		else
			nbCoupsSansPrises = 0;
		if(isTourDeBlanc())
			tourDeBlanc = false;
		else
			tourDeBlanc = true;
	}

	/**
	 * Vérifie si le coup saisi est possible
	 * @param s le coup saisi
	 * @return true si le coup saisi est possible
	 */
    public boolean estPossible(String s) {
    	Coordonnée départ = Coordonnée.stringToInt(s)[0];
    	Coordonnée arrivée = Coordonnée.stringToInt(s)[1];
    	Pièce p = echiquier.getPièce(départ.getLigne(), départ.getColonne());
    	if (p == null)
    		return false;
    	if (!p.estPossible(echiquier, arrivée.getLigne(), arrivée.getColonne())
    			|| (p.getCouleur().equals("NOIR") && tourDeBlanc)
    			|| (p.getCouleur().equals("BLANC") && !tourDeBlanc))
    		return false;
    	return true;
    }

	/**
	 * Vérifie si le coup joué met en échec le roi du joueur actif
	 * @param s le coup saisi
	 * @return b true si le coup met en échec le roi
	 */
	public boolean donneEchec(String s) {
		Coordonnée départ = Coordonnée.stringToInt(s)[0];
		Coordonnée arrivée = Coordonnée.stringToInt(s)[1];
		boolean b = false;

		if(echiquier.getPièce(arrivée.getLigne(), arrivée.getColonne()) != null)
			echiquier.setPriseDonneEchec(echiquier.getPièce(arrivée.getLigne(), arrivée.getColonne()));
		echiquier.déplacer(départ, arrivée);

		if(tourDeBlanc
				&& echiquier.getRoiBlanc().échec(echiquier))
			b = true;

		if(!tourDeBlanc
				&& echiquier.getRoiNoir().échec(echiquier))
			b = true;

		echiquier.annulerDernierCoup(départ, arrivée);
		if(echiquier.getPriseDonneEchec() != null)
			echiquier.setPièce(arrivée.getLigne(), arrivée.getColonne(), echiquier.getPriseDonneEchec());
		echiquier.setPriseDonneEchec(null);
		return b;
	}

	/**
	 * Vérifie que le joueur n'a plus aucun coup à jouer
	 * @return true s'il n'a plus aucun coup possible
	 */
    public boolean pat() {
    	for(int i = 0; i < Echiquier.MAX; ++i) {
    		for(int j = 0; j < Echiquier.MAX; ++j) {
    			Pièce p = echiquier.getPièce(i, j);
    			if(p != null) {
    				if(tourDeBlanc) {
    					if(p.getCouleur() == "BLANC"
    							&& !p.coupsPossibles(echiquier).isEmpty())
    						return false;
    				}
    				else {
    					if(p.getCouleur() == "NOIR"
							&& !p.coupsPossibles(echiquier).isEmpty())
    					return false;
    				}
    			}
    		}
    	}
    	return true;
    }

	/**
	 * Vérifie s'il y a eu 50 coups sans prise ou non
	 * @return true s'il y a eu 50 coups sans prise
	 */
	public boolean cinquanteCoups() {
    	return (nbCoupsSansPrises == 50);
    }

	/**
	 * Vérifie s'il y a insuffisance de matériel ou non
	 * @return true s'il y a insuffisance de matériel
	 */
	public boolean matérielInsuffisant() {
    	ArrayList<Pièce> blancs = echiquier.getPiècesBlanches();
    	ArrayList<Pièce> noirs = echiquier.getPiècesNoires();
    	
    	if(blancs.size() == 1 && noirs.size() == 1)
    		return true;
    	
    	if(blancs.size() == 1 && noirs.size() == 2)
    		if(noirs.get(0).getType() == "CAVALIER" || noirs.get(1).getType() == "CAVALIER"
    				|| noirs.get(0).getType() == "FOU" || noirs.get(0).getType() == "FOU")
    			return true;
    	
    	if(blancs.size() == 2 && noirs.size() == 1)
    		if(blancs.get(0).getType() == "CAVALIER" || blancs.get(1).getType() == "CAVALIER"
    				|| blancs.get(0).getType() == "FOU" || blancs.get(1).getType() == "FOU")
    			return true;
    	
    	if(blancs.size() == 2 && noirs.size() == 2)
    		if((blancs.get(0).getType() == "FOU" || blancs.get(1).getType() == "FOU")
    				&& (noirs.get(0).getType() == "FOU" || noirs.get(0).getType() == "FOU"))
    			return true;
    	
    	return false;
    }

	/**
	 * Vérifie s'il y a échec et mat pour un roi
	 * @param roi le roi à vérifier
	 * @return false s'il n'y a pas échec et mat
	 */
	public boolean échecEtMat(Roi roi) {
    	Coordonnée initial = new Coordonnée(roi.getLigne(), roi.getColonne());
    	if(roi.échec(echiquier)) {
    		for(Coordonnée coups : roi.coupsPossibles(echiquier)) {
    			echiquier.déplacer(initial, coups);
    			if(!roi.échec(echiquier)) {
    				echiquier.annulerDernierCoup(initial, coups);
    				return false;
    			}
    			echiquier.annulerDernierCoup(initial, coups);
    		}
    		if (roi.peutEtreProtégé(echiquier))
    			return false;
    		
    		return true;
    	}
    	return false;
    }

	/**
	 * Vérifie si la partie est finie ou non
	 * @return true si elle est finie
	 */
	public boolean fin() {
    	if(this.pat() || cinquanteCoups() || matérielInsuffisant()
    			|| (échecEtMat(echiquier.getRoiBlanc()) && tourDeBlanc)
    			|| (échecEtMat(echiquier.getRoiNoir()) && !tourDeBlanc))
    		return true;
    	return false;
    }

	/**
	 * Vérifie si un joueur abandonne la partie
	 * @param s le coups saisi
	 * @return true si le coup saisie est vide
	 */
	public boolean abandon(String s) {
		return (s.length() == 0);
	}

	/**
	 * Affiche le résultat de fin de partie selon la situation
	 * @param s le dernier coup saisi
	 * @return fin le résultat
	 */
	public String toStringFin(String s) {
		String fin = "";

		if(s.length() == 0) { // abandon
			fin += "partie finie, abandon des ";
			if(tourDeBlanc)
				fin += "Blancs";
			else
				fin += "Noirs";
		}
		
		if(cinquanteCoups())
			fin += "partie nulle, règle des Cinquante Coups";
		
		if(matérielInsuffisant())
			fin += "partie nulle, matériel insuffisant";
		
		if(pat())
			fin += "partie nulle, PAT";
		
		if(échecEtMat(echiquier.getRoiBlanc()))
			fin += "les Noirs ont gagné par Echec Et Mat";
		
		if(échecEtMat(echiquier.getRoiNoir()))
			fin += "les Blancs ont gagné par Echec Et Mat";
    	
    	return fin;
    }
	
}
