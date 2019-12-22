package dd.translator.owlinterplay;


import dd.ontologyinterchanger.BareModelInterchanger;
import dd.ontologyinterchanger.SelectQueryHolder;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sergey on 10.05.2016.
 */
public enum TranslatorOntologyHandler {

    INSTANCE;

    private BareModelInterchanger bmi;
    private String importOntologyNS;

    private final static String CORE_ONTOLOGY_NAME = "core2ed";
    //private final static String ONTOLOGY_FILE_NAME = "parameter-graph.ttl";
    // private final static String ONTOLOGY_FILE_NAME = "trafficcontrol.ttl";
    //private final static String ONTOLOGY_FILE_NAME = "arcolim.ttl";
    private final static String ONTOLOGY_FILE_NAME = "airdefence.ttl";
    private final static String SUBSTATUTE_PREFIX = "parameterized-system";

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
        System.out.println("The size of inferred triples: " + bmi.getNewTriples().size());
    }

    public SelectQueryHolder executeQuery(SelectQueryHolder sqh) {
        //String destination = replacePrefixes(sqh.getQueryString(), SUBSTATUTE_PREFIX);
        //sqh = new SelectQueryHolder(destination);
        return (SelectQueryHolder) bmi.excuteQueryOnMain(sqh);
    }

    private String getResourceName() {
        return getClass().getClassLoader().getResource(ONTOLOGY_FILE_NAME).toString();
    }

    private String replacePrefixes(String source, String insert) {
        String destination = "";
        Pattern p = Pattern.compile("\\W\\:");
        Matcher m = p.matcher(source);
        int lastIndex = -1;
        while (m.find()) {
            destination += source.substring(lastIndex + 1, m.start() + 1) + insert;
            lastIndex = m.start();
        }
        destination += source.substring(lastIndex + 1);
        return destination;
    }

}
