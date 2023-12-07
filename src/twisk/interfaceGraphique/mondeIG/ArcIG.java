package twisk.interfaceGraphique.mondeIG;

/**
 * Un arc du MondeIG qi répresente une liaison entre 2 étapes
 */
public class ArcIG {
    private PointDeControleIG p1 ;
    private PointDeControleIG p2 ;

    /**
     * Constructeur d'un Arc
     * @param p1 point de départ
     * @param p2 point d'arrivé
     */
    public ArcIG(PointDeControleIG p1 , PointDeControleIG p2){
        this.p1 = p1 ;
        this.p2 = p2 ;
    }

    /**
     *
     * @return Le point de départ
     */
    public PointDeControleIG getStartPoint(){
        return this.p1 ;
    }

    /**
     *
     * @return La coordonée x du point de départ
     */
    public int getStartPointX(){
        return this.p1.getPosX() ;
    }
    /**
     *
     * @return La coordonée y du point de départ
     */
    public int getStartPointY(){
        return this.p1.getPosY() ;
    }
    /**
     *
     * @return La coordonée x du point d'arrivé
     */
    public int getEndPointX(){
        return this.p2.getPosX() ;
    }
    /**
     *
     * @return La coordonée y du point d'arrivé
     */
    public int getEndPointY(){
        return this.p2.getPosY() ;
    }

    /**
     *
     * @return Le point d'arrivé
     */
    public PointDeControleIG getEndPoint(){
        return this.p2 ;
    }
}
