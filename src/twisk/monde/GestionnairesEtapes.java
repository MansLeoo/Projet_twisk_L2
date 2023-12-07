package twisk.monde;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Un GestionnairesEtapes qui stocke un nombre d'étapes
 */
public class GestionnairesEtapes implements Iterable{

    ArrayList<Etape> etapes ;

    /**
     * Constructeur de GestionnairesEtapes
     */
    public GestionnairesEtapes(){
        this.etapes = new ArrayList<>() ;
    }
    /**
     * Fonction qui permet d'ajouter des étapes dans le gestionnaire
     * @param etapes des étapes
     */
    public void ajouter(Etape... etapes){

        for (int i = 0; i < etapes.length; i++) {
            this.etapes.add(etapes[i]);
        }
    }
    /**
     * Fonction qu retourne le nombre d'etapes du gestionnaire
     * @return Le nombre d'etapes du gestionnaire
     */
    public int nbEtapes(){

        return this.etapes.size() ;
    }
    /**
     * Fonction qui créer un iterateur d'étape
     * @return un iterateur d'étape
     */
    @Override
    public Iterator<Etape> iterator() {


        return etapes.iterator();
    }
    /**
     * Fonction qui retourne le premier élément du gestionnaire
     * @return le premier élément de l'arrayList.
     */
    public Etape getSucceseur(){

        return this.etapes.get(0) ;
    }
    /**
     * Fonction qui retourne le premier élément du gestionnaire
     * @return le premier élément de l'arrayList.
     */
    public Etape getSucceseur(int index){
        try {
            Etape etape = this.etapes.get(index) ;
            return etape ;
        }catch(Exception e){
            throw new IndexOutOfBoundsException("L'étape situé à l'index "+index+" n'existe pas") ;
        }

    }
    /**
     * Fonction toString
     * @return le toString() des etapes du gestionnaire
     */
    @Override
    public String toString() {

        Iterator<Etape> iterator = this.iterator();
        StringBuilder builder = new StringBuilder("") ;
        while (iterator.hasNext()) {
            builder.append(iterator.next().toString() + "\n");
        }
        return builder.toString();
    }
}
