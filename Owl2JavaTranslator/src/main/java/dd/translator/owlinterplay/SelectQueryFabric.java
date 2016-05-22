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

    public static SelectQueryHolder collectEffectiveEntities() {
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

    public static SelectQueryHolder collectNodes(){
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE { " +
                        "    ?nd a core2ed:Node ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectObjectProperties(){
        return new SelectQueryHolder(
                "SELECT * " +
                        "WHERE {" +
                        "   ?r rdfs:subPropertyOf+ core2ed:world ." +
                        "   ?r a owl:ObjectProperty ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectDomains(String name){
        return collectPropertyFeature(name, "domain");
    }

    public static SelectQueryHolder collectRanges(String name){
        return collectPropertyFeature(name, "range");
    }

    private static SelectQueryHolder collectPropertyFeature(String name, String feature){
        return new SelectQueryHolder(
                "SELECT ?f " +
                        "WHERE {" +
                        "BIND(:" + name + " AS ?r) ." +
                        "   ?r rdfs:subPropertyOf+ core2ed:world ." +
                        "   ?r a owl:ObjectProperty ." +
                        "   ?r rdfs:" + feature + " ?f ." +
                        "} "
        );
    }

    public static SelectQueryHolder collectNodeBases(String name){
        return new SelectQueryHolder(
                "SELECT ?donor ?base ?type " +
                        "WHERE {" +
                        "   BIND(:" + name + " AS ?nd) ." +
                        "   ?nd core2ed:hasBase ?base ." +
                        "   ?nd core2ed:dependOn ?donor ." +
                        "   ?donor core2ed:hasDerivative ?base ." +
                        "   ?base rdf:type ?type ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectNodeDerivatives(String name){
        return new SelectQueryHolder(
                "SELECT ?derivative ?type " +
                        "WHERE { " +
                        "   BIND(:" + name + " AS ?nd) ." +
                        "   ?nd core2ed:hasDerivative ?derivative ." +
                        "   ?derivative rdf:type ?type ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectGenerativeInitialNodes(){
        return new SelectQueryHolder(
          "SELECT ?nd " +
                  "WHERE { " +
                  " ?nd a core2ed:Node ." +
                  " ?nd core2ed:implement ?cu ." +
                  " ?cu a core2ed:GenerativeInitialCU ." +
                  "}"
        );
    }

    public static SelectQueryHolder collectAssociativePlainNodes(){
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE {" +
                        " ?nd a core2ed:Node ." +
                        " ?nd core2ed:implement ?cu ." +
                        " ?cu a core2ed:AssociativePlainCU ." +
                        "}"
        );
    }

    public static SelectQueryHolder inferRealDomain(String donorName, String derivativeName){
        return inferRealFeature(donorName,  derivativeName, "domain");
    }

    public static SelectQueryHolder inferRealRange(String donorName, String derivativeName){
        return inferRealFeature(donorName,  derivativeName, "range");
    }

    private static SelectQueryHolder inferRealFeature(String donorName, String derivativeName, String feature){
        return new SelectQueryHolder(
          "SELECT ?f " +
                  "WHERE { " +
                  " BIND(:" + donorName +" AS ?nd) ." +
                  " BIND(:" + derivativeName + " AS ?r) ." +
                  " ?r rdfs:" + feature + " ?generic ." +
                  " ?nd core2ed:hasBase ?f. " +
                  " ?f rdfs:subClassOf* ?generic ." +
                  "}"
        );
    }

    public static SelectQueryHolder collectAssociativeRelationRefiningNodes(){
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE { " +
                        "   ?nd a core2ed:Node ." +
                        "   ?nd core2ed:implement ?cu ." +
                        "   ?cu a core2ed:AssociativeRelationRefiningCU ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectAssociativeRefiningNodes(){
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE { " +
                        "   ?nd a core2ed:Node ." +
                        "   ?nd core2ed:implement ?cu ." +
                        "   ?cu a core2ed:AssociativeRefiningCU ." +
                        "}"
        );
    }


    public static SelectQueryHolder collectRelationAxiomParts(String nodeName, String relationName){
        return new SelectQueryHolder(
                "SELECT ?first ?second " +
                        "WHERE { " +
                        "   BIND(:" + nodeName + " AS ?nd)" +
                        "   BIND(:" + relationName + " AS ?r) ." +
                        "   ?r owl:propertyChainAxiom ?axiom ." +
                        "   ?nd core2ed:hasBase ?b1 ." +
                        "   ?nd core2ed:hasBase ?b2 ." +
                        "   ?axiom rdf:first ?first ." +
                        "   ?axiom rdf:rest/rdf:first ?second ." +
                        "   FILTER(?b1 = ?first && ?b2 = ?second) ." +
                        "}"
        );
    }

    public static SelectQueryHolder findInverseRelationDerivative(String nodeName, String relationName){
        return new SelectQueryHolder(
                "SELECT ?r ?inverser " +
                        "WHERE {" +
                        "   BIND(:" + nodeName + " AS ?nd) ." +
                        "   BIND(:" + relationName + " AS ?r) ." +
                        "   ?nd core2ed:hasDerivative ?inverser ." +
                        "   ?r owl:inverseOf ?inverser ." +
                        "}"
        );
    }

    public static SelectQueryHolder findInverseRelation(String relationName){
        return new SelectQueryHolder(
          "SELECT ?r " +
                  "WHERE {" +
                  " BIND(:" + relationName + " AS ?inverser) ." +
                  " ?r owl:inverseOf ?inverser ." +
                  "}"
        );

    }


}
