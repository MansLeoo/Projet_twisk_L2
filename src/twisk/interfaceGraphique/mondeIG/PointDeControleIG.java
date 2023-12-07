package twisk.interfaceGraphique.mondeIG;


import twisk.interfaceGraphique.outils.FabriqueIdentifiant;

/**
 * Un point de contrôle qui permet de la création d'Arc
 */
public class PointDeControleIG {
    private int posX;
    private int posY ;
    private String id;
    private EtapeIG etape;

    /**
     * Constructeur d'un PointDeControleIG
     * @param etape l'étape liée au point
     * @param posX la coordonée x du point
     * @param posY la coordonée y du point
     */
    public PointDeControleIG(EtapeIG etape ,int posX , int posY ) {
        this.etape = etape;
        this.posX = posX;
        this.posY = posY;
        this.id = FabriqueIdentifiant.getInstances().getIdentififiantControl();
    }

    /**
     * @return l'étape liée au point
     */
    public EtapeIG getEtape() {
        return etape;
    }
    /**
     * @return la pos Y du point
     */
    public int getPosY() {
        return posY;
    }
    /**
     * @return la pos X du point
     */
    public int getPosX() {
        return posX;
    }
    /**
     * @return l"'identifiant du point
     */
    public String getID(){
        return this.id ;
    }

    /**
     * Met a jour la position x du point
     * @param pos la nouvelle position dumpoint
     */
    public void setPosX(int pos) {
        this.posX = pos ;
    }
    /**
     * Met a jour la position Y du point
     * @param pos la nouvelle position dumpoint
     */
    public void setPosY(int pos) {
        this.posY = pos ;
    }
}