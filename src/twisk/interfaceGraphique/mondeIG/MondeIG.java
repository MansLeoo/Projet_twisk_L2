package twisk.interfaceGraphique.mondeIG;



import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

import com.sun.javafx.tk.Toolkit;
import javafx.concurrent.Task;
import twisk.ClientTwisk;
import twisk.interfaceGraphique.exceptions.BiffurcationException;
import twisk.interfaceGraphique.exceptions.MondeException;
import twisk.interfaceGraphique.exceptions.TwiskException;
import twisk.interfaceGraphique.exceptions.ValueException;
import twisk.interfaceGraphique.vues.Observateur;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.ThreadsManager;
import twisk.simulation.GestionnaireClients;
import twisk.simulation.Simulation;

/**
 * Structure du monde sous l'interface graphique
 */
public class MondeIG extends SujetObserve implements Iterable , Observateur {
    /**
     * Liste des étapes
     */
private Map<String, EtapeIG> map ;
    /**
     * Liste des Arc
     */
private ArrayList<ArcIG> lstArc ;
private ArrayList<PointDeControleIG> lstPoint ;
    /**
     * Liste des étapes séléctionner
     */
    private ArrayList<EtapeIG> etapeSelect ;
    /**
     * Liste des Arcs séléctionner
     */
private ArrayList<ArcIG> arcSelect ;
private ArrayList<EtapeIG[]> liaisons ;

private GestionnaireClients gestionnaireClients  ;

private CorrespondancesEtapes correspondance ;
private  int nbClients ;

private boolean isSimu ;
    /**
     * Constructeur du MondeIG
     */
    public MondeIG()  {
        this.map = new HashMap<>()  ;
        ActiviteIG  etape= new ActiviteIG(200,100,6,3) ;
        etape.setEntree(true);
        this.map.put(etape.getIdentifiant(),etape) ;
        this.lstArc = new ArrayList<>() ;
        this.lstPoint = new ArrayList<>(2) ;
        this.etapeSelect = new ArrayList<>() ;
        this.arcSelect = new ArrayList<>() ;
        this.liaisons = new ArrayList<>() ;
        this.gestionnaireClients = null ;
        this.nbClients = 15 ;
        this.isSimu = false ;
    }

    /**
     * Fonction générant un iterateur sur les Etapes du mondeIG
     * @return un itérateur<EtapeIG>
     */
    public Iterator<Entry<String,EtapeIG>> iterator(){
        return map.entrySet().iterator();
    }

    /**
     * Fonction permettant d'ajouter une étape dans le MondeIG
     * @param etape l'étape a ajouter dans le monde IG
     */
    public void ajouter(EtapeIG etape){
        this.map.put(etape.getIdentifiant(),etape) ;
    }

    /**
     * Fonction qui permet de sélectionner une etape.
     * @param etape l'étape a sélectionner
     */
    public void selectionner(EtapeIG etape){
        Iterator<EtapeIG> iterator = this.iteratorEtapeSelect() ;
        boolean present = false ;
        while (iterator.hasNext()){
            if (iterator.next().getIdentifiant().equals(etape.getIdentifiant())){
                this.etapeSelect.remove(etape) ;
                present = true ;
            }
        }
        if (!present) {
            this.etapeSelect.add(etape) ;
        }
        this.notifierObservateur();
    }
    /**
     * Fonction qui permet de sélectionner un arc
     * @param arc l'arc a sélectionner
     */
    public void selectionner(ArcIG arc){
        Iterator<ArcIG> iterator = this.iteratorArcSelect() ;
        boolean present = false ;
        while (iterator.hasNext()){
            if (iterator.next() == arc){
                this.arcSelect.remove(arc) ;
                present = true ;
            }
        }
        if (!present) {
            this.arcSelect.add(arc) ;
        }
        this.notifierObservateur();
    }

    /**
     * Fonction qui permet de créer et d'ajouter un arc dans le MondeIG
     * @param p1 le point de départ de l'arc
     * @param p2 le point d'arrivé de l'arc
     */
    public void ajouter(PointDeControleIG p1 , PointDeControleIG p2){

        this.lstArc.add(new ArcIG(p1,p2)) ;
        this.updateLiaison() ;
    }

    /**
     * Fonction qui permet de sélectionner un point de contrôle, si 2 points sont sélectionner la fonction fait appel à la fonction de création d'un arc.
     * @param p Un Point de Controle
     * @throws TwiskException
     */
    public void select(PointDeControleIG p) throws TwiskException, BiffurcationException {
        this.lstPoint.add(p) ;
        if (lstPoint.size() == 2) {

            if (verificationValide()) {// Vérification Si l'arc est valide
                if(estDansLesLiaisons(lstPoint.get(0).getEtape())){
                    //System.out.println(liaisons.size());
                    this.lstPoint.clear();
                    throw new BiffurcationException("Les biffurcation ne sont pas disponibles") ;
                }
                this.ajouter(lstPoint.get(0), lstPoint.get(1));
            }
            else{
                this.lstPoint.clear();
                throw new TwiskException("Arc non valide") ;
            }
            this.lstPoint.clear();
            this.notifierObservateur();

        }
    }
    private boolean estDansLesLiaisons(EtapeIG etape) {
        for (EtapeIG[] liaison : liaisons) {
            if (liaison[0].getIdentifiant().equals(etape.getIdentifiant()) ) {
                return true;
            }
        }
        return false;
    }
    /**
     * Fonction qui vérifie si les points sélectionnés peuvent construire un arc.
     * C'est-à-dire que l'arc ne sois pas composé de Point déjà utilisé ou que les points ne soient pas liée à la même étape.
     * @return Vrai si les points selectionner du monde sont valide
     */
    private boolean verificationValide() {
        PointDeControleIG p1 = this.lstPoint.get(0) ;
        PointDeControleIG p2 = this.lstPoint.get(1) ;
        if (p1.getEtape().getIdentifiant().equals(p2.getEtape().getIdentifiant())) {
            // Si les 2 points font partie de la même étape
            return false;
        }
        if(rechercheChemin(p2.getEtape(),p1.getEtape())){
            return false ;
        }
        //Vérification si il y a deja une liaison entre les 2 étaoes
        EtapeIG[] verif = {p1.getEtape(), p2.getEtape()} ;
        Iterator<EtapeIG[]> ite = this.iteratorliaison() ;
        while(ite.hasNext()){
            if(Arrays.equals(ite.next() , verif)){
                return false ;
            }
        }
        //Vérification qu'un guichet a bien comme succeseur une activite.
        if(p1.getEtape().isGuichet() && p2.getEtape().isGuichet()){
            return false ;
        }
        return true ;
    }

    /**
     * Fonction générant un iterateur sur les Arcs du mondeIG
     * @return
     */
    public Iterator<ArcIG> iteratorArc(){
        ArrayList<ArcIG> lst = new ArrayList<>() ;
        lst.addAll(this.lstArc) ;
        return lst.iterator() ;
    }

    /**
     * Fonction générant un iterateur sur les Etapes selectionner du monde IG
     * @return
     */
    public Iterator<EtapeIG> iteratorEtapeSelect(){
        ArrayList<EtapeIG> lst = new ArrayList<>();
        lst.addAll(this.etapeSelect) ;
        return lst.iterator() ;
    }

    /**
     * Fonction générant un iterateur sur les Arc selectionner du monde IG
     * @return
     */
    public Iterator<ArcIG> iteratorArcSelect(){
        ArrayList<ArcIG> lst = new ArrayList<>();
        lst.addAll(this.arcSelect) ;
        return lst.iterator() ;
    }

    /**
     * Fonction qui supprime toute les Etapes et tout les arc sélectionner
     */
    public void deleteSelection(){
        Iterator<ArcIG> iteratorArc = this.iteratorArcSelect() ;
        Iterator<EtapeIG> iteratorEtape = this.iteratorEtapeSelect() ;
        while(iteratorArc.hasNext()){
            ArcIG arc = iteratorArc.next() ;
            this.lstArc.remove(arc) ;
            this.etapeSelect.remove(arc) ;
        }
        while(iteratorEtape.hasNext()){
            EtapeIG etape = iteratorEtape.next() ;
            Iterator<PointDeControleIG> itePoint = etape.iterator() ;
            while(itePoint.hasNext()){
                PointDeControleIG p = itePoint.next() ;
                Iterator<ArcIG> iteArc = this.iteratorArc() ; // Création d'iterateur arc / iterer sur les arc et vérifier si un point est présent dans un des arc du monde et dans l'étape supprimer .
                while(iteArc.hasNext()){
                    ArcIG a = iteArc.next() ;
                    if (a.getStartPoint() == p || a.getEndPoint() == p){
                        this.lstArc.remove(a) ;
                    }
                }
            }
            etape.iterator() ;
            this.map.remove(etape.getIdentifiant()) ;
            this.etapeSelect.remove(etape) ;
        }
        this.updateLiaison() ;
        this.notifierObservateur();

    }
    /**
     * Fonction qui enlève la sélection sur les Arc et les Étapes du mondeIG
     */
    public void unselect() {
        this.etapeSelect.clear();
        this.arcSelect.clear();
        this.notifierObservateur();
    }

    /**
     * Vérifie si une unique étape est sélectionnée.
     * @throws TwiskException
     */
    public void verifRename() throws TwiskException {
        if (this.etapeSelect.size() != 1){
            this.etapeSelect.clear();
            throw new TwiskException("Erreur il y a plus d'une activité") ;
        }
    }

    /**
     * Fonction qui permet de renommer l'étape sélectionnée
     * @param nom le nouveau nom de l'étape
     */
    public void renommer(String nom){
        if(nom.length() > 0){
            Iterator<Entry<String, EtapeIG>> iterator = this.iterator() ;
            while(iterator.hasNext()){
                EtapeIG e  = iterator.next().getValue() ;
                EtapeIG etape  = this.etapeSelect.get(0);
                if (e.getIdentifiant().equals(etape.getIdentifiant())) {
                    etape.setNom(nom) ;
                }
            }
        }
    }

    /**
     * @param id identifiant de l'étape
     * @return l'étape correspondant a l'identifiant
     */
    public EtapeIG getEtape(String id){
        return this.map.get(id) ;
    }

    /**
     * Fonction qui permet de replacer une étape
     * @param e l'etape a replacer
     */
    public void replaceEtape(EtapeIG e){
        EtapeIG etape = this.map.get(e.getIdentifiant()) ;
        this.map.replace(etape.getIdentifiant(), etape, e) ;
        this.notifierObservateur();
    }

    /**
     * Fonction qui permet de définir les Étapes sélectionnées en tant que sortie
     */
    public void ajouterSortie() {
        Iterator<EtapeIG> ite = iteratorEtapeSelect() ;
        EtapeIG e = null ;
        ActiviteIG act = null ;
        while (ite.hasNext()) {
            e = ite.next() ;
                if (e.isSortie()) {
                    e.setSortie(false);
                }
                else{
                    e.setSortie(true);
                }
                this.notifierObservateur();
            }
        }

    /**
     * Fonction qui permet de définir les Étapes sélectionnées en tant qu'entrée
     */
    public void ajouterEntree() {
        Iterator<EtapeIG> ite = iteratorEtapeSelect() ;
        EtapeIG e = null ;
        while (ite.hasNext()) {
            e = ite.next() ;
                if (e.isEntree()) {
                    e.setEntree(false);
                }
                else{
                    e.setEntree(true);
                }
                this.notifierObservateur();
            }
        }


    /**
     * Fonction qui change le délai de l'étape sélectionné
     * @param result le nouveau delai
     * @throws ValueException
     */
    public void modifierDelai(String result) throws ValueException {
        EtapeIG etape = this.etapeSelect.get(0) ;

        if(etape.isActivite()) {
            ActiviteIG activite = (ActiviteIG) etape;

            int f =activite.getEcartTemps();
            if (result != null) {

                try {
                    f = Integer.parseInt(result);

                } catch (Exception e) {
                    throw new ValueException("La valeur n'est pas un nombre ");
                }
            }
            if (f >= activite.getTemps()) {
                throw new ValueException("L'écart temps est supérieur au temps de l'étape");
            }
            activite.setEcartTemps(f);
            this.notifierObservateur();
        }
    }
    /**
     * Fonction qui change le temps de l'étape selectionné
     * @param result le nouveau temps
     * @throws ValueException
     */
    public void modifierTemps(String result) throws ValueException {
        EtapeIG etape = this.etapeSelect.get(0);
        if (etape.isActivite()) {
            ActiviteIG activite = (ActiviteIG) etape;
            int f = activite.getTemps();
            if (result != null) {

                try {
                    f = Integer.parseInt(result);

                } catch (Exception e) {
                    throw new ValueException("La valeur est incorrect ");
                }
                if (f <= activite.getEcartTemps()) {
                    throw new ValueException("L'écart temps est supérieur au temps de l'étape");
                }
            }
            activite.setTemps(f);
            ;
            this.notifierObservateur();
        }
    }

    /**
     * @return le nombres d'étapes selectionné
     */
    public int getNbEtapeSelect(){
        return this.etapeSelect.size() ;
    }

    public int getNbGuichetSelect(){
        int nb =0 ;
        Iterator<EtapeIG> iterator = this.iteratorEtapeSelect() ;
        while(iterator.hasNext()){
            EtapeIG etape = iterator.next();
            if(etape.isGuichet()){
                nb++ ;
            }
        }
        return nb ;
    }
    public int getNbActiviteSelect(){

        return getNbEtapeSelect() - getNbGuichetSelect() ;
    }
    public int getNbArcSelect(){
        return this.arcSelect.size() ;
    }

    public void modifierJetons(String result) throws ValueException {
        EtapeIG etape = this.etapeSelect.get(0);
        int f = 0 ;
        if (etape.isGuichet()) {
            GuichetIG guichet = (GuichetIG) etape;
            if (result != null) {

                try {
                    f = Integer.parseInt(result);
                } catch (Exception e) {
                    throw new ValueException("La valeur est incorrect ");
                }

            }
            guichet.setNbJetons(f);
            this.notifierObservateur();
        }
    }
    public void updateLiaison(){
        this.liaisons.clear(); ;
        Iterator<ArcIG> ite = this.lstArc.iterator() ;
        while(ite.hasNext()){
            ArcIG arc = ite.next() ;
            EtapeIG[] liaison = {arc.getStartPoint().getEtape(),arc.getEndPoint().getEtape() } ;
            this.liaisons.add(liaison) ;
        }
    }
    public boolean rechercheChemin(EtapeIG e1 , EtapeIG e2){
        //recherche du chemin entre 2 étapes du monde
                boolean trouve = false ;
                EtapeIG succ = e1 ;
                while(!trouve) {
                    EtapeIG successeur = rechercheSucc(succ) ;
                    if(successeur == null){
                        return false ;
                    }
                    else{
                        succ = successeur ;
                        if(succ.getIdentifiant().equals(e2.getIdentifiant())){
                             return true ;
                        }
                    }
                }
                return false ;
    }
    public Iterator<EtapeIG[]> iteratorliaison(){
        return this.liaisons.iterator() ;
    }
    private void verifierMondeIG() throws MondeException {
        //Vérification si le monde contient bien une entrée et une sortie
        int sortie = 0;
        int entre = 0;
        Iterator<Entry<String, EtapeIG>> ite = this.iterator();
        while (ite.hasNext()) {
            EtapeIG e = ite.next().getValue();
                if (e.isEntree()) {
                    entre ++;
                }
                if (e.isSortie()) {
                    sortie ++;
                }
            }

        if (sortie == 0 || entre == 0) {
            throw new MondeException("Le monde n'a pas une entree et une sortie");
        }
        if (sortie > 1 || entre >1) {
            throw new MondeException("Le monde ne gère pas les bifurcations, il ne peut y avoir qu'une seul entrée et une seul sortie.");
        }
//Définition des activité restreinte
        Iterator<EtapeIG[]> liaison = this.iteratorliaison();
        while (liaison.hasNext()) {
            EtapeIG[] etapes = liaison.next();

            if (etapes[0].isGuichet() && etapes[1].isActivite()) {
                ActiviteIG a = (ActiviteIG) etapes[1];
                a.setActiviteRestreinte(true);
            }
        }
        //recherche du chemin entre l'entrée et la sortie du monde
        Iterator<Entry<String, EtapeIG>> iteetape = this.iterator() ;
        while(iteetape.hasNext()){
            EtapeIG e = iteetape.next().getValue();
            if(e.isEntree() && !e.isSortie()){
                Iterator<EtapeIG[]> iteLiason = this.iteratorliaison();
                boolean sortieE = false ;
                EtapeIG succ = e ;
                while(!sortieE) {
                     EtapeIG successeur = rechercheSucc(succ) ;
                     if(successeur == null){
                         throw new MondeException("Il n'y a pas de chemin entre l'entree et la sortie");
                     }
                     else{
                         succ = successeur ;
                         if(succ.isSortie()){
                             sortieE = true ;
                         }
                     }
                }


            }
        }



    }
private EtapeIG rechercheSucc(EtapeIG e){
    Iterator<EtapeIG[]> iteLiaison = this.iteratorliaison();


    while(iteLiaison.hasNext()) {
        EtapeIG[] liai = iteLiaison.next();
        if (liai[0].getIdentifiant().equals(e.getIdentifiant())) {
            return liai[1] ;
        }
    }
    return null ;
}
    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    private Monde creerMonde(){
            Monde monde = new Monde() ;
             this.correspondance = new CorrespondancesEtapes();
            Iterator<Entry<String, EtapeIG>> ite = this.iterator();
            this.unselect();
            //Ajout des étapes dans le monde
            while (ite.hasNext()){
                EtapeIG e = ite.next().getValue();
                if (e.isActivite()){
                    ActiviteIG a = (ActiviteIG) e ;
                    Activite act =null;
                    if (a.isActiviteRestreinte()){
                         act = new ActiviteRestreinte(a.getNom(),a.getTemps(),a.getEcartTemps()) ;
                    }
                    else {
                         act = new Activite(a.getNom(), a.getTemps(), a.getEcartTemps());
                    }
                    correspondance.ajouter(a,act);
                    monde.ajouter(act);
                    if(a.isSortie()){
                        monde.aCommeSortie(act);
                    }
                    if(a.isEntree()){
                        monde.aCommeEntree(act);
                    }
                }
                if(e.isGuichet()){
                    GuichetIG g = (GuichetIG) e ;
                    Guichet guichet = new Guichet(g.getNom(),g.getNbJetons()) ;
                    correspondance.ajouter(g,guichet);
                    monde.ajouter(guichet);
                    if(g.isSortie()){
                        monde.aCommeSortie(guichet);
                    }
                    if(g.isEntree()){
                        monde.aCommeEntree(guichet);
                    }
                }
            }
            //Définition des Successeur
            Iterator<EtapeIG[]> iter = this.iteratorliaison() ;
            while(iter.hasNext()){
                EtapeIG[] liaison = iter.next();
                Etape e1 = correspondance.get(liaison[0]);
                Etape e2 = correspondance.get(liaison[1]);
                e1.ajouterSuccesseur(e2);
            }
            return monde ;
        }

        public void simuler() throws MondeException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
                this.verifierMondeIG();
                Monde monde = creerMonde() ;
                this.isSimu = true;

            ClassLoaderPerso classLoader = new ClassLoaderPerso(ClientTwisk.class.getClassLoader()) ;
                Class<?> cl = classLoader.loadClass("twisk.simulation.Simulation") ;
                Method getClient = cl.getMethod("getGestionnaire") ;
                Constructor<?> cons = cl.getConstructor() ;
                Object o = cons.newInstance() ;
                MondeIG mG = this ;
                this.gestionnaireClients = (GestionnaireClients) getClient.invoke(o);
                Task<Void> task = new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        Method setNbClients = cl.getMethod("setNbClients", int.class);
                        setNbClients.invoke(o, nbClients);
                        Method ajouterObservateur = cl.getMethod("ajouterObservateur", Observateur.class);
                        ajouterObservateur.invoke(o, mG);
                        Method simuler = cl.getMethod("simuler", Monde.class);

                        simuler.invoke(o, monde);
                        mG.setSimu(false);

                        return null;
                    }
                };
                ThreadsManager.getInstance().lancer(task);
                this.notifierObservateur();
            }

    public boolean isSimu() {
        return isSimu;
    }

    public void setSimu(boolean simu) {
        isSimu = simu;
        notifierObservateur();
        if(simu == false){
            this.gestionnaireClients = null ;
        }
    }

    public CorrespondancesEtapes getCorrespondance() {
        return correspondance;
    }

    public void setCorrespondance(CorrespondancesEtapes correspondance) {
        this.correspondance = correspondance;
    }

    @Override
    public void reagir() {
        this.notifierObservateur();
    }

    public GestionnaireClients getGestionnaireClients() {
        return gestionnaireClients;
    }

    public void setGestionnaireClients(GestionnaireClients gestionnaireClients) {
        this.gestionnaireClients = gestionnaireClients;
    }
}

