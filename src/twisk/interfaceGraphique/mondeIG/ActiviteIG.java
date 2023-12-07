package twisk.interfaceGraphique.mondeIG;

import twisk.interfaceGraphique.mondeIG.EtapeIG;

public class ActiviteIG extends EtapeIG {

    private int temps ;
    private int ecartTemps;
    private boolean activiteRestreinte ;



    public ActiviteIG(String nom , int larg , int haut, int temps, int ecartTemps){

        super(nom,larg,haut);
        this.temps = temps ;
        this.ecartTemps = ecartTemps ;
    }
    public ActiviteIG(int larg , int haut,int temps,int ecartTemps){

        super(larg,haut);
        this.temps = temps ;
        this.ecartTemps = ecartTemps ;

    }

    public ActiviteIG(int larg , int haut) {
        super(larg,haut);
    }




    /**
     *
     * @return le temps de l'étape
     */
    public int getTemps() {
        return temps;
    }
    /**
     * Fonction qui permet de définir le temps de l'étape
     * @param temps
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }

    /**
     *
     * @return l'écart temps de l'étape
     */
    public int getEcartTemps() {
        return ecartTemps;
    }
    /**
     * Fonction qui permet de définir l'ecartTemps de l'étape
     * @param ecartTemps
     */
    public void setEcartTemps(int ecartTemps) {
        this.ecartTemps = ecartTemps;
    }

    @Override
    public boolean isActivite() {
        return true;
    }
    public boolean isActiviteRestreinte() {
        return activiteRestreinte;
    }

    public void setActiviteRestreinte(boolean activiteRestreinte) {
        this.activiteRestreinte = activiteRestreinte;
    }
}
