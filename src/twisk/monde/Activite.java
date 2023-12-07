package twisk.monde;

import java.util.Iterator;
/**
 * Une activitée du monde
 */
public class Activite extends Etape  {
    private int temps ;
    private int ecartTemps ;


    /**
     * Constructeur d'Activité
     * @param nom le nom de l'activité
     */
    public Activite(String nom){

        super(nom);
        this.temps = 0 ;
        this.ecartTemps = 0 ;
    }
    /**
     * Constructeur d'Activité
     * @param nom le nom de l'activité
     * @param temps le temps de l'activité
     * @param ecartTemps l'ecartTemps de l'activité
     */
    public Activite(String nom, int temps ,int ecartTemps){

        super(nom);
        this.ecartTemps = ecartTemps ;
        this.temps = temps ;

    }


    public boolean estUneActivite(){

        return true ;
    }
    /**
     * retourne le temps d'une activité
     */
    public int getTemps(){

        return this.temps; }
    /**
     * retourne l'ecartTemps d'une activité
     */
    public int getEcartTemps(){

        return this.ecartTemps;
    }
    @Override
    public boolean estUnGuichet() {

        return false;
    }
    /**
     * Fonction qui convertis les données de l'activité en code c
     * @return le code c liée a l'activité
     */
    public String toC(){
        StringBuilder str = new StringBuilder();
        /*
        str.append(
                "nb =rand() % (" +this.getNbSuccesseur()+  ");\n" +
                        "switch (nb) {\n" +
                        "case 0 :  \n" );
                        str.append("delai(")
                                .append(this.getTemps())
                                .append(",")
                                .append(this.getEcartTemps())
                                .append(");\n")
                                .append("transfert(")
                                .append(this.nomToC())
                                .append(",")
                                .append(this.getSuccesseur().nomToC())
                                .append(");\n")
                                .append(this.getSuccesseur().toC())
                                .append("\n")
                                .append("break ;\n") ;
                                if(this.getNbSuccesseur() > 1 ) {
                                    str.append("case 1 :  \n")
                                            .append("delai(")
                                            .append(this.getTemps())
                                            .append(",")
                                            .append(this.getEcartTemps())
                                            .append(");\n")
                                            .append("transfert(")
                                            .append(this.nomToC())
                                            .append(",")
                                            .append(this.getSuccesseur(1).nomToC())
                                            .append(");\n")
                                            .append(this.getSuccesseur(1).toC())
                                            .append("\n")
                                            .append("break ;\n") ;

                                }
                                if(this.getNbSuccesseur() > 2) {
                                    str.append("case 2 :  \n")
                                            .append("delai(")
                                            .append(this.getTemps())
                                            .append(",")
                                            .append(this.getEcartTemps())
                                            .append(");\n")
                                            .append("transfert(")
                                            .append(this.nomToC())
                                            .append(",")
                                            .append(this.getSuccesseur(2).nomToC())
                                            .append(");\n")
                                            .append(this.getSuccesseur(2).toC())
                                            .append("\n")
                                            .append("break ;\n");
                                }
                                if (this.getNbSuccesseur() > 3) {
                                                str.append("case 3 :  \n")
                                                        .append("delai(")
                                                        .append(this.getTemps())
                                                        .append(",")
                                                        .append(this.getEcartTemps())
                                                        .append(");\n")
                                                        .append("transfert(")
                                                        .append(this.nomToC())
                                                        .append(",")
                                                        .append(this.getSuccesseur(3).nomToC())
                                                        .append(");\n")
                                                        .append(this.getSuccesseur(3).toC())
                                                        .append("\n")
                                                        .append("break ;\n") ;

                                }
        str.append("\n}") ;
        */
        str.append("delai(")
                .append(this.getTemps())
                .append(",")
                .append(this.getEcartTemps())
                .append(");\n")
                .append("transfert(")
                .append(this.nomToC())
                .append(",")
                .append(this.getSuccesseur().nomToC())
                .append(");\n")
                .append(this.getSuccesseur().toC());

        return str.toString() ;
    }
    /**
     * Fonction qui convertis les données de l'activité pour construire les constante utile au code C
     * @return le code c liée a l'activité
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
