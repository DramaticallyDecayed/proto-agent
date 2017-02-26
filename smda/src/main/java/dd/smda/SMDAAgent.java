package dd.smda;

import dd.ontologypart.OntologyHandler;
import dd.sas.SAS;
import dd.smda.perception.Perception;
import dd.smda.sas.Perceptor2SASAdapterImpl;
import dd.smda.sas.worldentity.Analysis;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Sergey on 06.02.2017.
 */
public class SMDAAgent {

    public static void main(String... args) throws FileNotFoundException {
        OntologyHandler ontologyHandler = new OntologyHandler();
        ontologyHandler.arm();

        SAS sas = new SAS(ontologyHandler);
        Perceptor2SASAdapterImpl adapter = initPerceptor2SASAdapter(sas);

        ConcurrentLinkedQueue<Analysis> analyses = new ConcurrentLinkedQueue<>();
        Perception perception = new Perception(analyses);

        while (true) {
            perception.cycle();
            List<Analysis> analysisList = new ArrayList<>();
            while (!analyses.isEmpty()) {
                analysisList.add(analyses.poll());
            }

            adapter.setGroupRuleList();
            adapter.setGraphStructureRuleList();

            adapter.setAnalysisList(analysisList);
            sas.cycle();
        }

    }

    private static Perceptor2SASAdapterImpl initPerceptor2SASAdapter(SAS sas) {
        Perceptor2SASAdapterImpl adapter = new Perceptor2SASAdapterImpl();
        adapter.getLevel().addNode(adapter);
        sas.getLevelHolder().addLevel(adapter.getLevel());
        return adapter;
    }

}
