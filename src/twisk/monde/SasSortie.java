package twisk.monde;
/**
 * Le SasSortie qui définis la sortie du monde
 */
public class SasSortie extends Activite {

    /**
     * Constructeur SasSortie
     */
    public SasSortie(){

        super("SasSortie");
    }
    /**
     * Fonction qui retourne une chaine vide Le SasSortie n'a pas de succeseurs
     * @return Une chaine vide
     */
    @Override
    public String toC() {

        return "";
    }
    /**
     * Fonction qui convertis les données du SasSortie pour construire les constante utile au code C
     * @return le code c liée au SasSortie
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
}
