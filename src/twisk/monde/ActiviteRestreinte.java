package twisk.monde;

/**
 * Une ActiviteRestreinte est une activité qui succède un guichet
 */
public class ActiviteRestreinte extends Activite {
    /**
     * Constructeur d'ActiviteRestreinte
     * @param nom le nom de l'activité
     */
    public ActiviteRestreinte(String nom) {
        super(nom);
    }
    /**
     * Constructeur d'ActiviteRestreinte
     * @param nom le nom de l'activité
     * @param temps le temps de l'activité
     * @param ecartTemps l'ecartTemps de l'activité
     */
    public ActiviteRestreinte(String nom, int temps ,int ecartTemps) {

        super(nom, temps, ecartTemps);
    }
    /**
     * Fonction qui convertis les données d l'ActiviteRestreinte en code c (gérer dans le ToC d'un guichet)
     * @return une chaine vide
     */
    public String toC(){

        return "" ;
    }
    /**
     * Fonction qui convertis les données de l'ActiviteRestreinte pour construire les constante utile au code C
     * @return le code c liée a l'ActiviteRestreinte
     */
    public String toConstanteC(){

        StringBuilder build = new StringBuilder();
        build.append("#define ") ;
        build.append(this.nomToC());
        build.append(" ");
        build.append(this.getNb()) ;
        build.append("\n");
        //build.append(this.getSuccesseur().toConstanteC()) ;
        return build.toString() ;
    }
}
