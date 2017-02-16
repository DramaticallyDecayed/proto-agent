package dd.smda;

import dd.ontologypart.OntologyHandler;
import dd.sas.SAS;
import dd.smda.sas.Perceptor2SASAdapterImpl;


/**
 * Created by Sergey on 06.02.2017.
 */
public class SMDAAgent {

    public static void main(String...args){
        OntologyHandler ontologyHandler = new OntologyHandler();
        ontologyHandler.arm();
        SAS sas = new SAS(ontologyHandler);
        Perceptor2SASAdapterImpl adapter = initPerceptor2SASAdapter(sas);



        while(true){
            adapter.setSystemParameterList();
            adapter.setIntervalRuleList();
            long start = System.nanoTime();
            sas.cycle();
            long end = System.nanoTime();
//            System.out.println("DURATION: " + (end - start));
        }

    }

    private static Perceptor2SASAdapterImpl initPerceptor2SASAdapter(SAS sas) {
        Perceptor2SASAdapterImpl adapter = new Perceptor2SASAdapterImpl();
        adapter.getLevel().addNode(adapter);
        sas.getLevelHolder().addLevel(adapter.getLevel());
        return adapter;
    }

}
