package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

/**
 * Un guichet qui permet de limiter le nombre de clients présent dans son successeur
 */
public class Guichet extends Etape {
    private int nbJetons ;
    private int semaphore ;

    /**
     * Constructeur de guichet
     * @param nom le nom du guichet
     */
    public Guichet(String nom){

        super(nom) ;
        this.semaphore = FabriqueNumero.getInstances().getNumeroSemaphore();
        this.nbJetons = 0 ;
    }
    /**
     * Constructeur de guichet
     * @param nom le nom du guichet
     * @param nb le nombre de jeton du guichet
     */
    public Guichet(String nom,int nb) {

        super(nom) ;
        this.nbJetons = nb ;
        this.semaphore = FabriqueNumero.getInstances().getNumeroSemaphore();
    }

    public int getNbJetons() {
        return nbJetons;
    }

    @Override
    public boolean estUneActivite() {

        return false;
    }

    @Override
    public boolean estUnGuichet() {

        return true ;
    }
    /**
     * Fonction qui retourne le sémaphore lié au guichet
     * @return le sémaphore lié au guichet
     */
    public int getSemaphore() {

        return this.semaphore;}
    /**
     * Fonction qui convertis les données du guichet en code c
     * @return le code c liée au guichet
     */
    public String toC(){

        StringBuilder builder = new StringBuilder("") ;
        /*
        builder.append(
                "nb =rand() % (" +this.getNbSuccesseur()+  ");\n" +
                        "switch (nb) {\n" +
                        "case 0 :  \n" );
            builder.append("P(ids,")
                    .append("SEM_Ticket_"+this.nomToC())
                    .append(");\n");
            ActiviteRestreinte res = (ActiviteRestreinte) this.getSuccesseur();
            builder.append("transfert(")
                    .append(this.nomToC())
                    .append(",")
                    .append(this.getSuccesseur().nomToC())
                    .append(");\n");
            builder.append("delai(")
                    .append(res.getTemps())
                    .append(",")
                    .append(res.getEcartTemps())
                    .append( ");\n");
            builder.append("V(ids,")
                    .append("SEM_Ticket_"+this.nomToC())
                    .append(");\n");
            //Debut switch succeseur
            builder.append( "nb2 =rand() % (" +res.getNbSuccesseur()+  ");\n")
                    .append("switch (nb2) {\n")
                            .append("case 0 :  \n") ;
                                    builder.append("transfert(")
                                            .append(res.nomToC())
                                            .append(",")
                                            .append(res.getSuccesseur().nomToC())
                                            .append(");\n");
                                    builder.append(res.getSuccesseur().toC());
                                    //fin case 0
                                    builder.append("break ;\n") ;
                            if(res.getNbSuccesseur() > 1){
                                builder.append("case 1 :  \n") ;
                                builder.append("transfert(")
                                        .append(res.nomToC())
                                        .append(",")
                                        .append(res.getSuccesseur(1).nomToC())
                                        .append(");\n");
                                builder.append(res.getSuccesseur(1).toC());
                                //fin case 1
                                builder.append("break ;\n") ;
                            }
                            if(res.getNbSuccesseur() > 2){
                                builder.append("case 2 :  \n") ;
                                builder.append("transfert(")
                                        .append(res.nomToC())
                                        .append(",")
                                        .append(res.getSuccesseur(2).nomToC())
                                        .append(");\n");
                                builder.append(res.getSuccesseur(2).toC());
                                //fin case 2
                                builder.append("break ;\n") ;
                            }
                            if(res.getNbSuccesseur() > 3) {
                                builder.append("case 3 :  \n");
                                builder.append("transfert(")
                                        .append(res.nomToC())
                                        .append(",")
                                        .append(res.getSuccesseur(3).nomToC())
                                        .append(");\n");
                                builder.append(res.getSuccesseur(3).toC());
                                //fin case 3
                                builder.append("break ;\n") ;

                            }
                            //Fin switch suc
                            builder.append("}\n") ;
        //fin case 0
        builder.append("break ;\n") ;

        if(this.getNbSuccesseur() > 1 ) {
            builder.append("case 1 :  \n") ;
                builder.append("P(ids,")
                        .append("SEM_Ticket_"+this.nomToC())
                        .append(");\n");
                 res = (ActiviteRestreinte) this.getSuccesseur();
                builder.append("transfert(")
                        .append(this.nomToC())
                        .append(",")
                        .append(this.getSuccesseur().nomToC())
                        .append(");\n");
                builder.append("delai(")
                        .append(res.getTemps())
                        .append(",")
                        .append(res.getEcartTemps())
                        .append( ");\n");
                builder.append("V(ids,")
                        .append("SEM_Ticket_"+this.nomToC())
                        .append(");\n");
                builder.append("transfert(")
                        .append(res.nomToC())
                        .append(",")
                        .append(res.getSuccesseur().nomToC())
                        .append(");\n");
                builder.append(res.getSuccesseur().toC());
            //fin case 1
            builder.append("break ;\n") ;

        }
        if(this.getNbSuccesseur() > 2) {
            builder.append("case 2 :  \n") ;
                builder.append("P(ids,")
                        .append("SEM_Ticket_"+this.nomToC())
                        .append(");\n");
                 res = (ActiviteRestreinte) this.getSuccesseur(2);
                builder.append("transfert(")
                        .append(this.nomToC())
                        .append(",")
                        .append(this.getSuccesseur().nomToC())
                        .append(");\n");
                builder.append("delai(")
                        .append(res.getTemps())
                        .append(",")
                        .append(res.getEcartTemps())
                        .append( ");\n");
                builder.append("V(ids,")
                        .append("SEM_Ticket_"+this.nomToC())
                        .append(");\n");
                builder.append("transfert(")
                        .append(res.nomToC())
                        .append(",")
                        .append(res.getSuccesseur().nomToC())
                        .append(");\n");
                builder.append(res.getSuccesseur().toC());
            //fin case 2
            builder.append("break ;\n") ;

        }
        if (this.getNbSuccesseur() > 3) {
            builder.append("case 3 : { \n") ;
                builder.append("P(ids,")
                        .append("SEM_Ticket_"+this.nomToC())
                        .append(");\n");
                 res = (ActiviteRestreinte) this.getSuccesseur(3);
                builder.append("transfert(")
                        .append(this.nomToC())
                        .append(",")
                        .append(this.getSuccesseur(3).nomToC())
                        .append(");\n");
                builder.append("delai(")
                        .append(res.getTemps())
                        .append(",")
                        .append(res.getEcartTemps())
                        .append( ");\n");
                builder.append("V(ids,")
                        .append("SEM_Ticket_"+this.nomToC())
                        .append(");\n");
                builder.append("transfert(")
                        .append(res.nomToC())
                        .append(",")
                        .append(res.getSuccesseur().nomToC()) // ???
                        .append(");\n");
                builder.append(res.getSuccesseur().toC());
            //Fin case 3
            builder.append("break ;\n")
                    .append("}\n") ;
        }




        builder.append("}\n");
        //____________________________________________________
        */
        builder.append("P(ids,")
                .append("SEM_Ticket_"+this.nomToC())
                .append(");\n");
        ActiviteRestreinte res = (ActiviteRestreinte) this.getSuccesseur();
        builder.append("transfert(")
                .append(this.nomToC())
                .append(",")
                .append(this.getSuccesseur().nomToC())
                .append(");\n");
        builder.append("delai(")
                .append(res.getTemps())
                .append(",")
                .append(res.getEcartTemps())
                .append( ");\n");
        builder.append("V(ids,")
                .append("SEM_Ticket_"+this.nomToC())
                .append(");\n");
        builder.append("transfert(")
                .append(res.nomToC())
                .append(",")
                .append(res.getSuccesseur().nomToC())
                .append(");\n");
        builder.append(res.getSuccesseur().toC());
        return builder.toString() ;
    }
    /**
     * Fonction qui convertis les données du guichet pour construire les constante utile au code C
     * @return le code c liée au guichet
     */
    public String toConstanteC() {

        StringBuilder build = new StringBuilder("") ;
        int sem = this.getSemaphore();
        build.append("#define ") ;
        build.append(this.nomToC());
        build.append(" ");
        build.append(this.getNb()) ;
        build.append("\n");
        build.append("#define ") ;
        build.append("SEM_Ticket_"+this.nomToC()) ;
        build.append(" ");
        build.append(this.getSemaphore()) ;
        build.append("\n");

        return build.toString() ;

    }

    }

