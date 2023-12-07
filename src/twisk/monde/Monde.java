package twisk.monde;

import java.util.Iterator;
/**
 * Le Monde dans lequel il y a n étape
 */
public class Monde implements Iterable {
    GestionnairesEtapes etapes ;
    SasSortie sortie ;
    SasEntree entre ;
    /**
     * Constructeur de Monde
     */
    public Monde(){
        SasEntree entre = new SasEntree(6,3) ;
        SasSortie sortie = new SasSortie() ;
        this.etapes = new GestionnairesEtapes() ;
        this.entre = entre ;
        this.sortie = sortie ;
        this.ajouter(entre,sortie);


    }
    /**
     * Fonction qui definis les sorties du monde
     * @param e Les étapes qui seront définies comme des sorties
     */
    public void aCommeSortie(Etape... e){

        for (int i = 0; i < e.length; i++) {
            e[i].ajouterSuccesseur(this.sortie);

        }
    }
    public Etape getEtape(int index){
        int i = 0 ;
        Etape e ;
        Iterator<Etape> ite = this.iterator() ;
        while (ite.hasNext() ) {
            e = ite.next();
            if (i == index){
                return e ;

            }
            i++ ;

        }
        return null ;
    }
    /**
     * Fonction qui definis les entrées du monde
     * @param e Les étapes qui seront définies comme des entrées
     */
    public void aCommeEntree(Etape... e){

        this.entre.ajouterSuccesseur(e);
    }
    /**
     * Fonction qui ajoute des étapes dans le monde
     * @param e les étapes a ajouté dans le monde
     */
    public void ajouter(Etape... e){


        this.etapes.ajouter(e);
    }
    /**
     * Fonction qui retourne le nombres d'étapes dans le monde
     * @return le nombre d'étapes du monde
     */
    public int nbEtapes(){


        return this.etapes.nbEtapes() ;
    }
    /**
     * Fonction qui retourne le nombres de guichet dans le monde
     * @return le nombre de guichet du monde
     */
    public int nbGuichet(){

        int compt = 0 ;
        Iterator<Etape> iterator = etapes.iterator();
        while(iterator.hasNext()){
            Etape e = iterator.next() ;
            if (e.estUnGuichet()){
                compt++ ;
            }
        }
        return compt ;
    }
    /**
     * Fonction qui retourne un iterateur d'étape
     */
    @Override
    public Iterator<Etape> iterator() {


        return this.etapes.iterator();
    }
    /**
     * @return une chaîne de caractere représentant le monde
     */
    @Override
    public String toString() {

        return  etapes.toString()  ;
    }
    /**
     * Fonction qui convertis les données du monde en language c
     * @return un fichier c contenant les données du monde
     */
    public String toC(){

        //Include du fichier.c
        String base = "#include <stdio.h>\n" +
                "#include <stdlib.h>\n" +
                "#include <unistd.h>\n" +
                "#include \"def.h\"\n" +
                "#include <time.h>\n" ;




        String fonc = "\nvoid simulation(int ids) { \n" ;
        //int nb ; int nb2 ; srand(time(NULL));


        // code C des etapes du monde
        StringBuilder str = new StringBuilder();
        str.append(getSasEntree().toC());
        str.append("\n }") ;
        return base + this.toConstanteC()  +  fonc  + str.toString() ;
    }
    /**
     * Fonction qui retourne le SasEntree du monde
     * @return le SasEntree du monde
     */
    public  SasEntree getSasEntree(){

        return this.entre;}
    /**
     * Fonction qui retourne le SasSortie du monde
     * @return le SasSortie du monde
     */
    public  SasSortie getSasSortie(){

        return this.sortie;
    }
    public String toConstanteC(){
        StringBuilder builder = new StringBuilder();
        Iterator<Etape> iterator = this.iterator() ;
        //builder.append(getSasEntree().toConstanteC()) ;
        //builder.append(getSasSortie().toConstanteC()) ;
        while(iterator.hasNext()){
            builder.append(iterator.next().toConstanteC()) ;
        }
        return builder.toString();
    }
}
