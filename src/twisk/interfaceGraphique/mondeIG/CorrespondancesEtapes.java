package twisk.interfaceGraphique.mondeIG;

import twisk.monde.Etape;

import java.util.HashMap;
import java.util.Map;

public class CorrespondancesEtapes {

    private Map<EtapeIG, Etape> correspondances ;

    public CorrespondancesEtapes(){
        this.correspondances = new HashMap<>();
    }

    public Etape get(EtapeIG e){
        return this.correspondances.get(e) ;
    }
    public void ajouter(EtapeIG eIG , Etape e ){
        this.correspondances.put(eIG,e) ;
    }
    public EtapeIG trouverEtapeIG(Etape e) {
        for (Map.Entry<EtapeIG, Etape> entry : correspondances.entrySet()) {
            if (entry.getValue() == e) {
                return entry.getKey();
            }
        }
        return null;
    }
}
