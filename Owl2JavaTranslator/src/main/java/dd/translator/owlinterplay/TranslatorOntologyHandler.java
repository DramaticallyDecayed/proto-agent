package dd.translator.owlinterplay;

import dd.ontologyinterchanger.BareModelInterchanger;
import dd.ontologyinterchanger.SelectQueryHolder;

import java.util.Map;

/**
 * Created by Sergey on 10.05.2016.
 */
public class TranslatorOntologyHandler {
    private BareModelInterchanger bmi;
    private String importOntologyNS;

    private final static String CORE_ONTOLOGY_NAME = "core2ed";
    private final static String ONTOLOGY_FILE_NAME = "soccer2ed.rdf";

    public TranslatorOntologyHandler() {
        bmi = new BareModelInterchanger(getResourceName());
        importOntologyNS = bmi.getPrefixForXMLNS(CORE_ONTOLOGY_NAME);
        prepareForTranslation();
    }

    private void prepareForTranslation() {
        bmi.insertIndividual(importOntologyNS, "ElaborationGeneration", "elaborationGeneration");
        bmi.insertIndividual(importOntologyNS, "NDReification", "ndReification");
        bmi.insertIndividual(importOntologyNS, "ComputedClassGeneration", "computedClassGeneration");
    }

    public void checkOntologyInference() {
        bmi.runInference();
        System.out.println(bmi.getNewTriples().size());
        SelectQueryHolder sqh = (SelectQueryHolder) bmi.excuteQueryOnMain(
                SelectQueryFabric.generateQueryForInterfaces());
        for (Map<String, Object> slice : sqh) {
            System.out.println(slice.get("c"));
            SelectQueryHolder attr = (SelectQueryHolder) bmi.excuteQueryOnMain(
                    SelectQueryFabric.collectClassAttributes((String)slice.get("c")));
            for (Map<String, Object> attrSlice : attr) {
                System.out.println(attrSlice);
            }
        }
    }

    private String getResourceName() {
        return getClass().getClassLoader().getResource(ONTOLOGY_FILE_NAME).toString();
    }


}
