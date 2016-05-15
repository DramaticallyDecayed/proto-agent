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
    public static SelectQueryHolder collectInterfaces() {
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
    //TODO: very similar to collectInterfaces -> somehow optimize?
    public static SelectQueryHolder collectElaborations4Interface(String interfaceName){
        return new SelectQueryHolder(
                "SELECT ?ec " +
                        "WHERE {" +
                        "BIND(:" + interfaceName + " AS ?c) ." +
                        "?c core2ed:elaboratedWith ?ec ." +
                        "    ?c rdfs:subClassOf+ core2ed:WorldEntity ." +
                        "MINUS{" +
                        "?c rdfs:subClassOf core2ed:WorldEntity ." +
                        "}" +
                        "MINUS{" +
                        "?c rdfs:subClassOf+ core2ed:Known ." +
                        "}" +
                        "MINUS{" +
                        "?c rdfs:subClassOf+ core2ed:Percepted ." +
                        "}" +
                        "MINUS{" +
                        "?c rdfs:subClassOf+ core2ed:Computed ." +
                        "}" +
                        "}"
        );
    }

    public static SelectQueryHolder findCoreClass(String className) {
        return new SelectQueryHolder(
                "SELECT ?sc " +
                        "WHERE {" +
                        "OPTIONAL{" +
                        ":" + className + " rdfs:subClassOf* ?sc ." +
                        "?sc rdfs:subClassOf core2ed:ModelEntity ." +
                        "}" +
                        "OPTIONAL{" +
                        ":" + className + " rdfs:subClassOf* ?sc ." +
                        "?sc rdfs:subClassOf core2ed:DesignEntity ." +
                        "}" +
                        "}"
        );
    }
}
