package dd.translator.owlinterplay;

import dd.ontologyinterchanger.BareModelInterchanger;

/**
 * Created by Sergey on 10.05.2016.
 */
public class TranslatorOntologyHandler {
    private BareModelInterchanger bmi;

    private final static String ONTOLOGY_NAME = "soccer2ed.rdf";

    public TranslatorOntologyHandler() {
        bmi = new BareModelInterchanger(getResourceName());
    }

    public void checkOntologyInference() {
        bmi.runInference();
        System.out.println(bmi.getNewTriples().size());
    }

    private String getResourceName() {
        return getClass().getClassLoader().getResource(ONTOLOGY_NAME).toString();
    }


}
