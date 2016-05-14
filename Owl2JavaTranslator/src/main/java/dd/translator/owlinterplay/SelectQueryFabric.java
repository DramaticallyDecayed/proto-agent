package dd.translator.owlinterplay;

import dd.ontologyinterchanger.SelectQueryHolder;

/**
 * Created by Sergey on 14.05.2016.
 */
public final class SelectQueryFabric {
    private SelectQueryFabric() {
    }

    /**
     * @return all classes that are implemented as interfaces
     */
    public static SelectQueryHolder generateQueryForInterfaces() {
        return new SelectQueryHolder(
                "SELECT ?c " +
                        "WHERE { " +
                        "    ?c rdfs:subClassOf+ core2ed:WorldEntity ." +
                        "MINUS{" +
                        "?c rdfs:subClassOf core2ed:WorldEntity ." +
                        "}" +
                        "MINUS{\n" +
                        "?c rdfs:subClassOf+ core2ed:Known ." +
                        "}" +
                        "MINUS{" +
                        "?c rdfs:subClassOf+ core2ed:Percepted ." +
                        "}" +
                        "MINUS{" +
                        "?c rdfs:subClassOf+ core2ed:Computed ." +
                        "}" +
                        "}");
    }

    public static SelectQueryHolder collectClassAttributes(String className) {
        return new SelectQueryHolder(
                "SELECT ?attr ?type " +
                        "WHERE {" +
                        "BIND(:" + className + " AS ?c) ." +
                        "?attr rdfs:domain ?c ." +
                        "?attr a owl:DatatypeProperty ." +
                        "?attr rdfs:range ?type" +
                        "}"
        );
    }
}
