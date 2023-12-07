package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;
/**
 * Une étape du monde
 */
public abstract class Etape implements Iterable {

    private String nom ;
    private int nb ;
    private GestionnairesEtapes gestionnaire ;
    /**
     * Constructur de Etap
     * @param nom Nom de l'étape
     */
    public Etape(String nom) {

        this.nom = nom;
        this.nb = FabriqueNumero.getInstances().getNumeroEtape();
        this.gestionnaire = new GestionnairesEtapes() ;
    }
    /**
     * Fonction qui ajoutes e successeur a l'étape
     * @param e des Etapes
     */
    public void ajouterSuccesseur(Etape... e){

        this.gestionnaire.ajouter(e);


    }
    /**
     * Fonction qui retourne le nombre de successeur
     */
    public int getNbSuccesseur(){

        return this.gestionnaire.nbEtapes() ;
    }
    /**
     * Retourne vrai si il s'agit d'une activité
     */
    public abstract boolean  estUneActivite() ;


    /**
     * Retourne vrai si il s'agit d'un guichet
     */
    public abstract boolean estUnGuichet() ;

    /**
     * Fonction qui retourne le numéro de l'étape
     */
    public int getNb() {

        return nb;
    }

    /**
     * Fonction qui retourne le nom de l'étape
     * @return le nom de l'etape
     */
    public String getNom(){ return nom; }
    /**
     * Fonction qui retourne un iterateur sur le gestionnaireEtapes
     */
    public Iterator<Etape> iterator(){

        return gestionnaire.iterator();
    }
    /**
     * Fonction toString qui retourne le nom de l'étape suivis de ses successeurs
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("") ;
        builder.append(this.nom) ;
        builder.append(" : " + this.gestionnaire.nbEtapes() + " successeur");
        Iterator<Etape> iterator = gestionnaire.iterator();
        while (iterator.hasNext()){
            builder.append(" - " + iterator.next().nom) ;
        }
        return builder.toString();
    }
    /**
     * Fonction qui retourne le nom de l'étape en supprimant les potentiels espaces du nom
     */
    public String nomToC(){

        return this.getNom().replaceAll("\\s+", "");
    }
    /**
     * Fonction qui retourne le premier successeur d'une étape
     */
    public Etape getSuccesseur() {

        return this.gestionnaire.getSucceseur() ;
    }
    /**
     * Fonction qui retourne le premier élément du gestionnaire
     * @return le premier élément de l'arrayList.
     */
    public Etape getSuccesseur(int index){
        return this.gestionnaire.getSucceseur(index) ;
        }
    /**
     * Fonction qui convertis les données d'une etape en code C
     * @return un string correspondant au code c de l'étape
     */
    public abstract String toC() ;
    /**
     * Fonction qui convertis les données d'une etape en code C
     * @return un string correspondant au  constantes en code c de l'étape
     */
    public abstract String toConstanteC();

    public int getNum() {
        return this.nb ;
    }
}
