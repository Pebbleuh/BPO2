package echec;

import echec.pieces.*;

import java.util.ArrayList;

public class Echiquier {

    public static final int MIN = 1;
    public static final int MAX = 8;

    private Pièce[][] echiquier;
    private Roi roiBlanc;
    private Roi roiNoir;
    private Pièce priseDernierCoup;
    private Pièce priseDonneEchec;

    /**
     * Constructeur d'un Echiquier
     */
    public Echiquier() {
        this.echiquier = new Pièce[MAX][MAX];
        this.priseDernierCoup = null;
        this.setPriseDonneEchec(null);

        this.roiBlanc = new Roi("BLANC", 7,4);
        this.roiNoir = new Roi("NOIR", 0,4);

        for(int i = 0; i < MAX; i++){
            for(int j = 0; j < MAX; j++){
                echiquier[i][j] = null;
            }
        }
        Pièce.setEchiquier(this);
    }

    /**
     * Modifie la pièce aux coordonnées x et y
     * @param x ligne
     * @param y colonne
     * @param p la nouvelle pièce
     */
    public void setPièce(int x, int y, Pièce p) {
        this.echiquier[x][y] = p;
    }

    /**
     * Retourne la pièce aux coordonnées x et y
     * @param x ligne
     * @param y colonne
     * @return la pièce
     */
    public Pièce getPièce(int x, int y) {
        return this.echiquier[x][y];
    }

    /**
     * Retourne le roi blanc
     * @return roiBlanc
     */
    public Roi getRoiBlanc() {
        return roiBlanc;
    }

    /**
     * Retourne le roi noir
     * @return roiNoir
     */
    public Roi getRoiNoir() {
        return roiNoir;
    }

    /**
     * Retourne la capture du coup entré
     * qui met potentiellement le roi
     * du joueur actif en echec
     * @return priseDonneEchec
     */
    public Pièce getPriseDonneEchec() {
        return priseDonneEchec;
    }

    /**
     * Met à jour la dernière capture
     * lorsqu'on vérifie si le coup entré
     * met le roi du joueur actif en échec
     * @param priseDonneEchec la nouvelle capture
     */
    public void setPriseDonneEchec(Pièce priseDonneEchec) {
        this.priseDonneEchec = priseDonneEchec;
    }

    /**
     * Retourne la dernière capture
     * @return priseDernierCoup
     */
    public Pièce getPriseDernierCoup() {
        return priseDernierCoup;
    }

    /**
     * Met à jour la dernière capture
     * @param p la nouvelle capture
     */
    public void setPriseDernierCoup(Pièce p) {
        this.priseDernierCoup = p;
    }

    /**
     * Déplace la pièce d'une position de départ à la position d'arrivée
     * @param départ
     * @param arrivée
     */
    public void déplacer(Coordonnée départ, Coordonnée arrivée) {
        int x = départ.getLigne(), ligne = arrivée.getLigne();
        int y = départ.getColonne(), colonne = arrivée.getColonne();

        priseDernierCoup = null;
        if(getPièce(ligne, colonne) != null) // case occupée
            priseDernierCoup = this.echiquier[ligne][colonne];

        this.echiquier[x][y].déplacer(this, départ, arrivée);
    }

    /**
     * Annule le dernier coup joué (quand on vérifie si le roi est en échec, échec et mat)
     * Plus simple de déplacer le roi pour vérifier donc il faut annuler le coup
     * @param départ les coordonnées de départ du dernier coup
     * @param arrivée les coordonnées d'arrivée du dernier coup
     */
    public void annulerDernierCoup(Coordonnée départ, Coordonnée arrivée) {
        int x = arrivée.getLigne();
        int y = arrivée.getColonne();

        this.echiquier[x][y].déplacer(this, arrivée, départ);
        if (priseDernierCoup != null)
            setPièce(départ.getLigne(), départ.getColonne(), priseDernierCoup);
        priseDernierCoup = null;
    }

    /**
     * Retourne toutes les pièces blanches du plateau
     * @return p la liste des pièces blanches
     */
    public ArrayList<Pièce> getPiècesBlanches(){
        ArrayList<Pièce> p = new ArrayList<>();
        for(int i = 0; i < MAX; ++i) {
            for(int j = 0; j < MAX; ++j) {
                if(getPièce(i, j) != null
                        && getPièce(i, j).getCouleur() == "BLANC")
                    p.add(getPièce(i, j));
            }
        }
        return p;
    }

    /**
     * Retourne toutes les pièces noires du plateau
     * @return p la liste des pièces noires
     */
    public ArrayList<Pièce> getPiècesNoires(){
        ArrayList<Pièce> p = new ArrayList<>();
        for(int i = 0; i < MAX; ++i) {
            for(int j = 0; j < MAX; ++j) {
                if(getPièce(i, j) != null
                        && getPièce(i, j).getCouleur() == "NOIR")
                    p.add(getPièce(i, j));
            }
        }
        return p;
    }

    /**
     * Affiche le plateau et les pièces sous forme de grille
     * @return s la grille
     */
    public String toString() {
        String lettres = ("    a   b   c   d   e   f   g   h    \n");
        String trait = "   --- --- --- --- --- --- --- ---   \n";
        String s = lettres + trait;
        // ligne
        for (int i = MIN; i <= MAX; ++i) {
            s += MAX - i+1 + " | ";
            // colonne
            for (int j = MIN; j <= MAX; ++j) {
                if(this.echiquier[i-1][j-1] != null)
                    s += this.echiquier[i-1][j-1].getSymbole();
                else
                    s += " ";
                s += " | ";
            }
            s += (MAX-i+1) + "\n";
            s += trait;
        }
        s += lettres;
        return s;
    }

}
