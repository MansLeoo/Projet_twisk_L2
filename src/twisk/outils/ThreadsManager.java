package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class ThreadsManager {
    private static ThreadsManager instance = new ThreadsManager() ;
    private ArrayList<Thread> threads ;
    public ThreadsManager(){
        threads = new ArrayList<>();
    }
    public static ThreadsManager getInstance(){
        return instance ;
    }
    public void lancer(Task task){
        Thread thread = new Thread(task);
        this.threads.add(thread) ;
        thread.start();

    }
    public void detruireTout(){
        for(Thread thread: threads){
            thread.interrupt();
        }
    }
}
