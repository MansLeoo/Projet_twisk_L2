#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "def.h"



#define num_sem_guichet 1



#define sasEntree 0
#define sasSortie 1
#define guichet 2
#define boutique 3

#define nb_clients 6



void simulation(int ids) {
/*
    entrer(sasEntree) ;
    delai(6,3);
    transfert(sasEntree,musee);


    delai(3,1);
    transfert(musee,sasSortie);
*/

    entrer(sasEntree) ;
    delai(6,3);
    transfert(sasEntree,guichet);
    P(ids,num_sem_guichet) ;
        transfert(guichet,boutique);
        delai(6,3);
    V(ids,num_sem_guichet) ;
    transfert(boutique,sasSortie);
}





