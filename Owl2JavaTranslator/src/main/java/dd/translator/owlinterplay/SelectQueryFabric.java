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
    public static SelectQueryHolder collectVirtualEntities() {
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

    //TODO: very similar to collectVirtualEntities -> somehow optimize?
    public static SelectQueryHolder collectElaborations4Interface(String interfaceName) {
        return new SelectQueryHolder(
                "SELECT ?c {" +
                        ":" + interfaceName + " core2ed:elaboratedWith ?c" +
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

    public static SelectQueryHolder collectAllParents(String className){
        return new SelectQueryHolder(
                "SELECT DISTINCT ?pc " +
                        "WHERE {" +
                        "?coreClasses rdfs:subClassOf core2ed:WorldEntity ." +
                        ":" + className + " rdfs:subClassOf+ ?pc ." +
                        "?pc rdfs:subClassOf+ ?coreClasses." +
                        "}"
        );
    }

    public static SelectQueryHolder collectEffecitiveEntities() {
        return new SelectQueryHolder(
                "SELECT * " +
                        "WHERE {" +
                        "    ?c rdfs:subClassOf+ core2ed:WorldEntity ." +
                        "{" +
                        "?c rdfs:subClassOf+ core2ed:Known ." +
                        "}" +
                        "UNION{" +
                        "?c rdfs:subClassOf+ core2ed:Percepted ." +
                        "}" +
                        "UNION{" +
                        "?c rdfs:subClassOf+ core2ed:Computed ." +
                        "}" +
                        "}"
        );
    }
}
