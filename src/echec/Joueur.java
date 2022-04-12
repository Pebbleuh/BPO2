package echec;

import java.util.ArrayList;
import java.util.Random;

import echec.pieces.Pièce;

public class Joueur {
	private String type;
	private String couleur;

	/**
	 * Constructeur d'un Joueur
	 * @param type
	 * @param couleur
	 */
	public Joueur(String type, String couleur) {
		this.type = type;
		this.couleur = couleur;
	}

	/**
	 * Retourne le type de la pièce
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Fait jouer l'IA
	 * @param p la partie actuelle
	 */
    public void jouerIA(Partie p) {  
    	String s = getCoupIA(p);
    	while(p.donneEchec(s)) {
    		s = getCoupIA(p);
    	}
    	Coordonnée départ = Coordonnée.stringToInt(s)[0];
    	Coordonnée arrivée = Coordonnée.stringToInt(s)[1];
    	p.getEchiquier().déplacer(départ, arrivée);
    }

	/**
	 * Choisit un coup aléatoire parmi la liste des coups possibles
	 * @param partie la partie en cours
	 * @return s le coup aléatoire de l'IA
	 */
	public String getCoupIA(Partie partie) {
       	ArrayList<Pièce> pièces = new ArrayList<>();
    	Pièce pi;
    	
    	if (this.couleur == "BLANC") {
    		pièces.addAll(partie.getEchiquier().getPiècesBlanches());
    		Random r = new Random();
        	int nb = r.nextInt(pièces.size());
        	pi = pièces.get(nb); // pièce blanche au hasard

			// tant que l'échiquier n'est pas vide
    		while(pi.coupsPossibles(partie.getEchiquier()).isEmpty()) {
    			nb = r.nextInt(pièces.size());
        		pi = pièces.get(nb);
    		}
    	}
    	else {
    		pièces.addAll(partie.getEchiquier().getPiècesNoires());
    		Random r = new Random();
        	int nb = r.nextInt(pièces.size());
        	pi = pièces.get(nb);
        	
    		while(pi.coupsPossibles(partie.getEchiquier()).isEmpty()) {
    			nb = r.nextInt(pièces.size());
        		pi = pièces.get(nb);
    		}
    	}
    	
    	Coordonnée départ = new Coordonnée(pi.getLigne(), pi.getColonne());
    	Random rand = new Random();
    	// un coup aléatoire dans la liste des coups possibles du joueur noir ou blanc
    	int i = rand.nextInt(pi.coupsPossibles(partie.getEchiquier()).size());
    	Coordonnée arrivée = pi.coupsPossibles(partie.getEchiquier()).get(i);
    	String s = Coordonnée.intToString(départ, arrivée);
    	return s;
    }
    
}
