package twisk.interfaceGraphique.mondeIG;

public class GuichetIG extends EtapeIG {



    private int nbJetons;
    public GuichetIG(String nom,int larg,int haut,int nbJetons) {
        super(nom,larg,haut);
        this.nbJetons = nbJetons ;

    }
    public GuichetIG(int larg,int haut,int nbJetons) {
        super(larg,haut);
        this.nbJetons = nbJetons ;
    }
    @Override
    public boolean isGuichet() {
        return true;
    }

    public int getNbJetons() {
        return nbJetons;
    }

    public void setNbJetons(int nbJetons) {
        this.nbJetons = nbJetons;
    }
}
