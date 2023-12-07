package twisk;

import twisk.interfaceGraphique.mondeIG.MondeIG;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.FabriqueNumero;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ClientTwisk {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, InterruptedException {
        Monde monde = new Monde();
        Activite zoo = new Activite("balade au zoo", 3, 1);
        Guichet guichet = new Guichet("accès au toboggan", 2);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);

        zoo.ajouterSuccesseur(guichet);
        guichet.ajouterSuccesseur(tob);

        monde.ajouter(zoo, tob, guichet);

        monde.aCommeEntree(zoo);
        monde.aCommeSortie(tob);

        simulerClientMonde(monde) ;



        simulerClientMonde(monde) ;
    }
    public static void simulerClientMonde(Monde monde) throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
            ClassLoaderPerso classLoader = new ClassLoaderPerso(ClientTwisk.class.getClassLoader()) ;
            Class<?> cl = classLoader.loadClass("twisk.simulation.Simulation") ;
            Constructor<?> cons = cl.getConstructor() ;
            Object o = cons.newInstance() ;
            Method setNbClients = cl.getMethod("setNbClients", int.class) ;
            setNbClients.invoke(o,5) ;
            Method simuler = cl.getMethod("simuler", Monde.class) ;
            simuler.invoke(o,monde) ;
            classLoader = null ;
        }


    }

