package twisk.monde;

/**
 * Le sasEntree qui définis l'entrée du monde
 */
public class SasEntree extends Activite {


    /**
     * Constructeur SasEntree
     */
    public SasEntree(){

        super("SasEntree");
    }
    /**
     * Constructeur SasEntree
     * @param temps le temps de l'activité
     * @param ecartTemps l'ecartTemps de l'activité
     */
    public SasEntree(int temps, int ecartTemps){

        super("SasEntree",temps,ecartTemps);
    }
    /**
     * Fonction qui convertis les données du SasEntree pour construire les constante utile au code C
     * @return le code c liée au SasEntree
     */
    public String toConstanteC(){

        StringBuilder build = new StringBuilder();
        build.append("#define ") ;
        build.append(this.nomToC());
        build.append(" ");
        build.append(this.getNb()) ;
        build.append("\n");
        return build.toString() ;
    }
    /**
     * Fonction qui convertis les données du SasEntree en code c
     * @return le code c liée au SasEntree
     */
    public String toC(){

        StringBuilder str = new StringBuilder();
        str.append("entrer(SasEntree);\n")
                .append("delai(")
                .append(this.getTemps())
                .append(",")
                .append(this.getEcartTemps())
                .append(");\n")
                .append("transfert(SasEntree,")
                .append(this.getSuccesseur().nomToC())
                .append(");\n")
        .append(this.getSuccesseur().toC());
        return str.toString();
    }


}
