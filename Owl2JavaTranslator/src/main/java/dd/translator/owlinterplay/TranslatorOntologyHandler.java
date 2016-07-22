package dd.translator.owlinterplay;

import dd.ontologyinterchanger.BareModelInterchanger;
import dd.ontologyinterchanger.SelectQueryHolder;

/**
 * Created by Sergey on 10.05.2016.
 */
public enum TranslatorOntologyHandler{

    INSTANCE;

    private BareModelInterchanger bmi;
    private String importOntologyNS;

    private final static String CORE_ONTOLOGY_NAME = "core2ed";
    private final static String ONTOLOGY_FILE_NAME = "soccer2ed.rdf";

    TranslatorOntologyHandler() {
        bmi = new BareModelInterchanger(getResourceName());
        importOntologyNS = bmi.getPrefixForXMLNS(CORE_ONTOLOGY_NAME);
        prepareForTranslation();
    }

    /**
     * Activate design stages
     */
    private void prepareForTranslation() {
        bmi.insertIndividual(importOntologyNS, "ElaborationGeneration", "elaborationGeneration");
        bmi.insertIndividual(importOntologyNS, "NDActivation", "ndActivation");
        bmi.insertIndividual(importOntologyNS, "ComputedClassGeneration", "computedClassGeneration");
    }

    public void checkOntologyInference() {
        bmi.runInference();
        System.out.println(bmi.getNewTriples().size());
    }

    public SelectQueryHolder executeQuery(SelectQueryHolder sqh){
        return (SelectQueryHolder) bmi.excuteQueryOnMain(sqh);
    }

    private String getResourceName() {
        return getClass().getClassLoader().getResource(ONTOLOGY_FILE_NAME).toString();
    }


}
