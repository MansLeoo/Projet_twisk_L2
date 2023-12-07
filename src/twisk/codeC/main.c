#include "def.h"
#include <stdbool.h>

#define nb_clients 6
#define nbEtapes 4
#define nbGuichet 1

int main(int argc, char** argv){





        int tabJetonsGuichet[2] = {2,1} ;
        printf("Nombre jeton : %i",tabJetonsGuichet[0]);
    int* pid = start_simulation(nbEtapes,nbGuichet,nb_clients,tabJetonsGuichet); /*int* tabGuichet = i contient le nombre de jetons disponible pour la sémaphore i+1 */
    printf("les clients :     ") ;
    //int get_client(int etape,int j,int nbEtapes,int nbClients)

    for(int i = 0 ; i < nb_clients ; i++){
        printf("%i,", pid[i]);
    }
    printf("\n");
    printf("nb : %i \n",nbEtapes) ;
    bool stop = true ;
    int f = 0 ;
    while(stop){
        int* test = ou_sont_les_clients( nbEtapes,  nb_clients);


        for(int i = 0 ; i < nbEtapes ; i++){
            printf("\n etape [%i] il y a %i clients : ",i,test[i*(nb_clients+1)]);

            for(int j = 1 ; j< test[i*(nb_clients+1)] + 1 ; j++ ){
                printf("%i,", test[j+i*(nb_clients+1)]);
            }

        if( test[1*(nb_clients+1)] == nb_clients ){
            stop=false ;
        }
        }
        sleep(1);

        printf("\n------------------------------");


    }
    nettoyage();
    return 0;

}
