package twisk.interfaceGraphique.outils;


public class FabriqueIdentifiant {
    private int noEtape ;
    private int noControl ;
    private static FabriqueIdentifiant instance = new FabriqueIdentifiant() ;


    public static FabriqueIdentifiant getInstances(){
        return instance ;
    }
    private FabriqueIdentifiant(){
        this.noEtape = 0 ;
        this.noControl= 0 ;
    }

    public String getIdentififiant(){
       return  "" + noEtape++ ;
    }
    public String getIdentififiantControl(){
        return  "" + noControl++ ;
    }

}
