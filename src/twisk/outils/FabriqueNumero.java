package twisk.outils;

/**
 * La fabrique de numéro qui permet de générer les numéros d'étapes et les numéros de sémaphore.
 */
public class FabriqueNumero {
    private int cptEtape ;
    private int cptSemaphore ;
    private int cptLib ;

    private int cptLibUse ;
    private static FabriqueNumero instance = new FabriqueNumero() ;

    /**
     * @return l'instance de FabriqueNumero
     */
    public static FabriqueNumero getInstances(){

        return instance ;
    }
    private FabriqueNumero(){
        this.cptEtape = 0 ; this.cptSemaphore = 1 ; this.cptLib = 0 ; this.cptLibUse = 0 ;
    }
    /**
     * @return le numéro de sémaphore
     */
public int getNumeroSemaphore(){


    return  this.cptSemaphore++ ;
}
    /**
     * @return le numéro d'étape
     */
public int getNumeroEtape(){


    return this.cptEtape++ ;
}

    /**
     * @return le numéro de la lib
     */
    public int getNumeroLib(){
        this.cptLibUse++ ;
        if (this.cptLibUse == 2) {
            cptLibUse = 0 ;
            return this.cptLib++;
        }
            return this.cptLib ;

}
    /**
     * Reset la Fabrique de Numéro
     */
public void reset(){

    this.cptEtape = 0 ;
    this.cptSemaphore = 1 ;
}

}
