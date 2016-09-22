package pipeline.concrete;

import pipeline.concrete.calculation.*;

/**
 * Created by Sergey on 13.09.2016.
 */
public class NodeModelMain {

    public static void main(String...args){
        Node_See ns = new Node_See();
        Node_Player np = new Node_Player();
        Node_Ego ne = new Node_Ego();
        Node_Ball nb = new Node_Ball();
        Node_Landmark nl = new Node_Landmark();

        ns.setDonor(np);
        ns.setDonor(ne);
        ns.setDonor(nb);
        ns.setDonor(nl);

        ne.calculate();
        np.calculate();
        nb.calculate();
        nl.calculate();

        ns.calculate();
    }

}
