package twisk.interfaceGraphique.mondeIG;


import twisk.interfaceGraphique.outils.FabriqueIdentifiant;

import javax.imageio.ImageTranscoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * Une étape dans le MondeIG
 */
public abstract class EtapeIG implements Iterable  {
    private String nom;
    private String identifiant;
    private int posX;
    private int posY;
    private int largeur;
    private int hauteur;
    private boolean entree ;
    private boolean sortie ;
    private ArrayList<PointDeControleIG> pointDeControles;

    /**
     * Constructeur d'une étape
     * @param nom nom de l'étape
     * @param larg largeur de l'étape
     * @param haut hauteur de l'étape
     */
    public EtapeIG(String nom, int larg, int haut) {

        final int NBCORD = 500 ;
        this.nom = nom;
        this.largeur = larg;
        this.hauteur = haut ;
        this.identifiant = FabriqueIdentifiant.getInstances().getIdentififiant();
        Random rand = new Random() ;
        this.posX = rand.nextInt(NBCORD) ;
        this.posY = rand.nextInt(NBCORD) ;
        this.pointDeControles = creationPoint() ;
        this.entree = false ;
        this.sortie = false ;
    }

    /**
     * Constructeur d'une étape
     * @param larg largeur de l'étape
     * @param haut hauteur de l'étape
     */
    public EtapeIG(int larg, int haut) {
        final int NBCORD = 500 ;
        this.largeur = larg;
        this.hauteur = haut ;
        this.identifiant = FabriqueIdentifiant.getInstances().getIdentififiant();
        this.nom = "Etape" + this.identifiant ;
        Random rand = new Random() ;
        this.posX = rand.nextInt(NBCORD) ;
        this.posY = rand.nextInt(NBCORD) ;
        this.pointDeControles = creationPoint() ;
        this.entree = false ;
        this.sortie = false ;
    }



    /**
     * Fonction qui créait les PointDeControleIG liées a l'étape.
     * @return la liste des PointDeControleIG liée a l'étape
     */
    private ArrayList<PointDeControleIG> creationPoint(){
        PointDeControleIG p1 = new PointDeControleIG(this,this.posX,this.posY+this.hauteur/2) ;
        PointDeControleIG p2 = new PointDeControleIG(this,this.posX + this.largeur,this.posY+this.hauteur/2) ;
        PointDeControleIG p3 = new PointDeControleIG(this,this.posX + this.largeur/2,this.posY) ;
        PointDeControleIG p4 = new PointDeControleIG(this,this.posX + this.largeur/2,this.posY+this.hauteur) ;
        ArrayList<PointDeControleIG> points = new ArrayList<>(4) ;
        points.add(p1) ;
        points.add(p2) ;
        points.add(p3) ;
        points.add(p4) ;
        return points ;
    }

    /**
     *
     * @return La posX de l'étape
     */
    public int getPosX() {
        return posX;
    }

    /**
     *
     * @return La posY de l'étape
     */
    public int getPosY() {
        return posY;
    }

    /**
     *
     * @return l'identifiant de l'étape
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     *
     * @return la Largeurde l'étape
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     *
     * @return le nom de l'étape
     */
    public String getNom() {
        return nom;
    }

    public void setPosX(int posX) {
        this.posX = posX;
        changePoint();
    }
    public void setPosY(int posY) {
        this.posY = posY;
        changePoint();
    }

    /**
     *
     * @return le hauteur de l'étape
     */
    public int getHauteur() {
        return hauteur;
    }


    @Override
    public Iterator<PointDeControleIG> iterator() {
        return this.pointDeControles.iterator() ;
    }

    public void setNom(String nom) {
        this.nom = nom ;
    }

    public void changePoint(){
        this.pointDeControles.get(0).setPosX(this.posX);
        this.pointDeControles.get(0).setPosY(this.posY+this.hauteur/2);

        this.pointDeControles.get(1).setPosX(this.posX + this.largeur);
        this.pointDeControles.get(1).setPosY(this.posY+this.hauteur/2);

        this.pointDeControles.get(2).setPosX(this.posX + this.largeur/2);
        this.pointDeControles.get(2).setPosY(this.posY);

        this.pointDeControles.get(3).setPosX(this.posX + this.largeur/2);
        this.pointDeControles.get(3).setPosY(this.posY+this.hauteur);

    }
    public  boolean isGuichet() {
        return false ;
    }

    public  boolean isActivite() {
        return false ;
    }
    public boolean isActiviteRestreinte() {
        return false ;
    }
    /**
     * @return Vrai si l'etape est une entree
     */
    public boolean isEntree() {
        return entree;
    }

    /**
     * Fonction qui permet de définir l'étape comme une entrée
     * @param entree
     */
    public void setEntree(boolean entree) {
        this.entree = entree;
    }

    /**
     * @return Vrai si l'etape est une sortie
     */
    public boolean isSortie() {
        return sortie;
    }
    /**
     * Fonction qui permet de définir l'étape comme une sortie
     * @param sortie
     */
    public void setSortie(boolean sortie) {
        this.sortie = sortie;
    }

}