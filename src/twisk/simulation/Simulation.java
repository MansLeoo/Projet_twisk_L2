package twisk.simulation;

import twisk.interfaceGraphique.mondeIG.SujetObserve;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.outils.FabriqueNumero;
import twisk.outils.KitC;
import twisk.outils.ThreadsManager;

import java.io.*;
import java.util.Iterator;

/**
 * Class permettant de faire une simulation d'un monde.
 */
public class Simulation extends SujetObserve {
    private int nbClient;
    private GestionnaireClients gestionnaire ;
    public Simulation() {
        /**
         * Constructeur de Simulation
         */
        this.nbClient = 0;
        this.gestionnaire = new GestionnaireClients() ;
    }

    public GestionnaireClients getGestionnaire() {
        return gestionnaire;
    }

    public native int[] start_simulation(int nbEtapes, int nbGuichets, int nbClients, int[] tabJetonsGuichets) ;
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients) ;
    public native void nettoyage() ;
    /**
     * @param monde Le monde qui va être simuler
     * Fonction permettant de lancer une simulation du monde passé en paramètre
     */
    public void simuler(Monde monde) throws IOException, InterruptedException {
        try {
            //System.out.println(monde.toString());
            //System.out.println(monde.toC());
            // Initialisation de la simulation
            String codeC = monde.toC();
            KitC kit = new KitC();
            kit.creerEnvironnement();
            this.creerFichier(codeC);
            this.compiler();
            this.construireLaLibrairie();
            System.load("/tmp/twisk/libTwisk.so" + FabriqueNumero.getInstances().getNumeroLib());

            //Lancement de la simulation
            int[] tabJetonsGuichet = creationTabJeton(monde);
            int nb_clients = nbClient;
            //System.out.println("Nombre jeton  e1 : "+tabJetonsGuichet[0] + "Nb etape : " + monde.nbEtapes() + "Nb guichet : " + monde.nbGuichet());

            // client
            this.gestionnaire.setClients(start_simulation(monde.nbEtapes(), monde.nbGuichet(), nb_clients, tabJetonsGuichet));


            //System.out.println("Nombres d'étapes : " + monde.nbEtapes());
            // Affichages des clients
            //this.gestionnaire.afficherLstCLient();
            //System.out.println("\n");

            // Affichage de la position des clients
            boolean stop = true;
            int f = 0;
            while (stop) {
                int[] clients = ou_sont_les_clients(monde.nbEtapes(), nb_clients);
                Etape e = monde.getSasEntree();
                for (int i = 0; i < monde.nbEtapes(); i++) {

                    //System.out.println("etape [" + e.getNum() + "] (" + e.getNom() + ") " + clients[e.getNum() * (nb_clients + 1)] + "  clients : ");

                    for (int j = 1; j < clients[e.getNum() * (nb_clients + 1)] + 1; j++) {
                        this.gestionnaire.allerA(clients[j + e.getNum() * (nb_clients + 1)], e, j);
                        //System.out.println(clients[j + e.getNum() * (nb_clients + 1)]);
                    }

                    if (clients[1 * (nb_clients + 1)] == nb_clients) {
                        stop = false;
                    }
                    if (i < monde.nbEtapes() - 1) {
                        e = e.getSuccesseur();
                    }
                }

                //System.out.println("------------------------------");
                Thread.sleep(1000);
                this.notifierObservateur();

            }
        }catch (InterruptedException e){

        }
        nettoyage();
        FabriqueNumero.getInstances().reset();
        ThreadsManager.getInstance().detruireTout();

    }

    private int[] creationTabJeton(Monde monde) {
        /**
         * Fonction generant le tableau de jetons en parcourant les guichet du monde.
         * @param Monde le monde lié a la simulation
         * @return le tableau de jeton
         */
        int[] tab = new int[monde.nbEtapes()] ; // création du tableau de taille de nb_etape
        Iterator<Etape> iterator = monde.iterator() ;
        int i = 0 ;
        //parcours du monde
        while (iterator.hasNext()){
            Etape e = iterator.next();
            if (e.estUnGuichet()){
                Guichet g = (Guichet) e ;
                tab[i++] = g.getNbJetons() ;
            }
        }
        return tab ;
    }

    private void creerFichier(String codeC) throws IOException {
        /**
         * @param codeC une chaîne de caractere correspondant a un code C.
         * Fonction Generant un fichier client.c dans le répertoire /tmp/twisk/
         * @throws IOException si il y a un problème lors de la création du fichier
         */
        //Création du fichier
        File file = new File("/tmp/twisk/client.c");
        if (!file.exists()) {
            file.createNewFile();
        }
        // écriture du code C dans le fichier
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(codeC);
        bw.close();
        fw.close();
    }

    private void compiler() {
        /**
         * Fonction compilant le fichier client.c situer dans le répertoire /tmp/twisk/
         */
        Runtime runtime = Runtime.getRuntime();
        String commande = "gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o";
        try {
            Process p = runtime.exec(commande); // execution de la commande
            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine()) != null) {
                //System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                //System.out.println(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void construireLaLibrairie() {
        /**
         * Fonction permettant de generer la librairie dans le répertoire /tmp/twisk/
         */
        Runtime runtime = Runtime.getRuntime();
        String commande = "gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/codeNatif.o /tmp/twisk/client.o -o /tmp/twisk/libTwisk.so"+FabriqueNumero.getInstances().getNumeroLib();
        try {
            Process p = runtime.exec(commande);
            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String ligne;
            while ((ligne = output.readLine()) != null) {
                //System.out.println(ligne);
            }
            while ((ligne = error.readLine()) != null) {
                //System.out.println(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setNbClients(int nb){
        /**
         * @param nb le nombre de clients
         * Fonction permettant d'initialiser le nombre de clients présent lors de la simulation
         */
        this.nbClient = nb ;
    }
    }
