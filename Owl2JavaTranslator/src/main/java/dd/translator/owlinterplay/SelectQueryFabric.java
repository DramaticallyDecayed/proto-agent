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

    public static SelectQueryHolder collectAllParents(String className) {
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

    public static SelectQueryHolder collectNodes() {
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE { " +
                        "    ?nd a core2ed:Node ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectObjectProperties() {
        return new SelectQueryHolder(
                "SELECT * " +
                        "WHERE {" +
                        "   ?r rdfs:subPropertyOf+ core2ed:world ." +
                        "   ?r a owl:ObjectProperty ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectDomains(String name) {
        return collectPropertyFeature(name, "domain");
    }

    public static SelectQueryHolder collectRanges(String name) {
        return collectPropertyFeature(name, "range");
    }

    private static SelectQueryHolder collectPropertyFeature(String name, String feature) {
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

    public static SelectQueryHolder collectNodeBases(String name) {
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

    public static SelectQueryHolder collectNodeGenericBases(String nodeName) {
        return new SelectQueryHolder(
                "SELECT ?donor ?fbase ?cbase ?type " +
                        "WHERE {" +
                        "   BIND(:" + nodeName + " AS ?nd) . " +
                        "   ?nd core2ed:dependOn ?donor . " +
                        "   ?donor core2ed:hasDerivative ?cbase . " +
                        "   ?nd core2ed:hasBase ?base ." +
                        "   ?cbase rdfs:subClassOf* ?base . " +
                        "   BIND(core2ed:FindPair(?nd, ?cbase) AS ?fbase) ." +
                        "   ?fbase rdf:type ?type ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectNodeDerivatives(String name) {
        return new SelectQueryHolder(
                "SELECT ?derivative ?type " +
                        "WHERE { " +
                        "   BIND(:" + name + " AS ?nd) ." +
                        "   ?nd core2ed:hasDerivative ?derivative ." +
                        "   ?derivative rdf:type ?type ." +
                        "}"
        );
    }

    public static SelectQueryHolder isAssociativeRelation(String relationName) {
        return new SelectQueryHolder(
                "SELECT ?r " +
                        "WHERE{ " +
                        "   BIND(:" + relationName + " AS ?r) ." +
                        "   ?r rdfs:subPropertyOf* core2ed:associative ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectGenerativeInitialNodes() {
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE { " +
                        " ?nd a core2ed:Node ." +
                        " ?nd core2ed:implement ?cu ." +
                        " ?cu a core2ed:GenerativeInitialCU ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectAssociativePlainNodes() {
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE {" +
                        " ?nd a core2ed:Node ." +
                        " ?nd core2ed:implement ?cu ." +
                        " ?cu a core2ed:AssociativePlainCU ." +
                        "}"
        );
    }

    public static SelectQueryHolder inferRealDomain(String donorName, String derivativeName) {
        return inferRealFeature(donorName, derivativeName, "domain");
    }

    public static SelectQueryHolder inferRealRange(String donorName, String derivativeName) {
        return inferRealFeature(donorName, derivativeName, "range");
    }

    private static SelectQueryHolder inferRealFeature(String donorName, String derivativeName, String feature) {
        return new SelectQueryHolder(
                "SELECT ?f " +
                        "WHERE { " +
                        " BIND(:" + donorName + " AS ?nd) ." +
                        " BIND(:" + derivativeName + " AS ?r) ." +
                        " ?r rdfs:" + feature + " ?generic ." +
                        " ?nd core2ed:hasBase ?f. " +
                        " ?f rdfs:subClassOf* ?generic ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectAssociativeRelationRefiningNodes() {
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE { " +
                        "   ?nd a core2ed:Node ." +
                        "   ?nd core2ed:implement ?cu ." +
                        "   ?cu a core2ed:AssociativeRelationRefiningCU ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectAssociativeRefiningNodes() {
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE { " +
                        "   ?nd a core2ed:Node ." +
                        "   ?nd core2ed:implement ?cu ." +
                        "   ?cu a core2ed:AssociativeRefiningCU ." +
                        "}"
        );
    }


    public static SelectQueryHolder collectRelationAxiomParts4Node(String nodeName, String relationName) {
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

    public static SelectQueryHolder collectRelationAxiomParts(String relationName) {
        return new SelectQueryHolder(
                "SELECT ?first ?second " +
                        "WHERE { " +
                        "   BIND(:" + relationName + " AS ?r) ." +
                        "   ?r owl:propertyChainAxiom ?axiom ." +
                        "   ?axiom rdf:first ?first ." +
                        "   ?axiom rdf:rest/rdf:first ?second ." +
                        "}"
        );
    }

    public static SelectQueryHolder findInverseRelationDerivative(String nodeName, String relationName) {
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

    public static SelectQueryHolder findDisjointRelationDerivative(String nodeName, String relationName) {
        return new SelectQueryHolder(
                "SELECT ?dd WHERE {" +
                        "   BIND(:" + nodeName + " AS ?nd) ." +
                        "   ?nd core2ed:hasDerivative :" + relationName + " ." +
                        "   ?dd owl:propertyDisjointWith :" + relationName + " ." +
                        "} "
        );
    }

    public static SelectQueryHolder findInverseRelation(String relationName) {
        return new SelectQueryHolder(
                "SELECT ?r " +
                        "WHERE {" +
                        " BIND(:" + relationName + " AS ?inverser) ." +
                        " ?r owl:inverseOf ?inverser ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectGenerativeCompositeNode() {
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE { " +
                        "   ?nd a core2ed:Node ." +
                        "   ?nd core2ed:implement ?cu ." +
                        "   ?cu a core2ed:GenerativeCompositeCU ." +
                        "}"
        );
    }

    public static SelectQueryHolder findCompositeDerivative(String nodeName, String relationName) {
        return new SelectQueryHolder(
                "SELECT ?r ?base ?second " +
                        "WHERE { " +
                        "   BIND(:" + nodeName + " AS ?nd) ." +
                        "   BIND(:" + relationName + " AS ?r) ." +
                        "   ?r owl:propertyChainAxiom ?axiom ." +
                        "   ?axiom rdf:first ?base ." +
                        "   ?axiom rdf:rest/rdf:first ?second ." +
                        "   ?nd core2ed:hasBase ?base." +
                        "}"
        );
    }

    public static SelectQueryHolder findSecondPartOfCompositeDerivative(String nodeName, String relationName) {
        return new SelectQueryHolder(
                "SELECT ?r ?base " +
                        "WHERE {" +
                        "   BIND(:" + nodeName + " AS ?nd) ." +
                        "   BIND(:" + relationName + " AS ?r) ." +
                        "   ?nd core2ed:hasBase ?base." +
                        "   ?d owl:propertyChainAxiom ?axiom ." +
                        "   ?axiom rdf:first ?base ." +
                        "   ?axiom rdf:rest/rdf:first ?r ." +
                        "}"
        );

    }

    public static SelectQueryHolder retrieveClassDerivative(String nodeName, String relationName) {
        return new SelectQueryHolder(
                "SELECT ?c " +
                        "WHERE {" +
                        "   BIND(:" + nodeName + " AS ?nd) ." +
                        "   BIND(:" + relationName + " AS ?r) ." +
                        "   ?nd core2ed:hasDerivative ?c ." +
                        "   ?c a owl:Class ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectGenerativeComplexNode() {
        return new SelectQueryHolder(
                "SELECT ?nd " +
                        "WHERE {" +
                        "   ?nd a core2ed:Node ." +
                        "   ?nd core2ed:implement ?cu ." +
                        "   ?cu a core2ed:GenerativeComplexCU ." +
                        "}"
        );
    }


    public static SelectQueryHolder collectRalationDomainPairs(String nodeName) {
        return new SelectQueryHolder(
                "SELECT ?r ?b " +
                        "WHERE {" +
                        "   BIND(:" + nodeName + " AS ?nd) ." +
                        "   ?nd core2ed:hasDerivative ?r ." +
                        "   ?r a owl:ObjectProperty ." +
                        "   ?nd core2ed:hasBase ?b ." +
                        "   ?r rdfs:domain ?b." +
                        "}"
        );
    }

    public static SelectQueryHolder retrieveClassDerivative(String nodeName) {
        return new SelectQueryHolder(
                "SELECT ?c " +
                        "WHERE {" +
                        "   BIND(:" + nodeName + " AS ?nd) ." +
                        "   ?nd core2ed:hasDerivative ?c ." +
                        "   ?c a owl:Class ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectAllInitialEntities() {
        return new SelectQueryHolder(
                "SELECT ?c " +
                        "WHERE {" +
                        "   {?c rdfs:subClassOf+ core2ed:Percepted .}" +
                        "   UNION" +
                        "   {?c rdfs:subClassOf+ core2ed:Known .}" +
                        "}"
        );
    }

    public static SelectQueryHolder collectInitialNode() {
        return new SelectQueryHolder(
                "SELECT ?nd ?d " +
                        "WHERE {" +
                        "   ?nd a core2ed:Node ." +
                        "   ?nd core2ed:implement ?cu ." +
                        "   ?cu a core2ed:GenerativeInitialCU ." +
                        "   ?cu core2ed:hasDerivative ?d ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectGenericBases(String nodeName) {
        return new SelectQueryHolder(
                "SELECT ?base ?type " +
                        "WHERE {" +
                        "   BIND(:" + nodeName + " AS ?nd) ." +
                        "   ?nd core2ed:implement ?cu ." +
                        "   ?cu core2ed:hasBase ?base ." +
                        "   ?base rdf:type ?type ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectOutPropertyFlows(String nodeName) {
        return new SelectQueryHolder("SELECT ?d ?p ?r " +
                "WHERE { " +
                "    BIND(:" + nodeName + " AS ?node) ." +
                "   ?node core2ed:outFlow ?outFlow ." +
                "   ?outFlow core2ed:domainPropertyOfFlow ?d ." +
                "   ?outFlow core2ed:rangePropertyOfFlow ?r ." +
                "   ?outFlow core2ed:propertyOfFlow ?p ." +
                "}"
        );
    }

    public static SelectQueryHolder collectOutObjectFlows(String nodeName) {
        return new SelectQueryHolder("SELECT ?obj " +
                "WHERE { " +
                "   BIND(:" + nodeName + " AS ?node) ." +
                "   ?node core2ed:outFlow ?outFlow ." +
                "   ?outFlow core2ed:objectOfFlow ?obj ." +
                "}");
    }

    public static SelectQueryHolder collectDonorFlows(String nodeName, String donorName) {
        return new SelectQueryHolder(
                "SELECT DISTINCT ?methodName " +
                        "WHERE{ " +
                        "   BIND(:" + nodeName + " AS ?node) . " +
                        "   ?node core2ed:outFlow ?outFlow . " +
                        "   ?outFlow core2ed:inFlow ?inFlow . " +
                        "   :" + donorName + " core2ed:outFlow ?inFlow ." +
                        "   OPTIONAL{" +
                        "       ?inFlow core2ed:domainPropertyOfFlow ?d ." +
                        "       ?inFlow core2ed:rangePropertyOfFlow ?r ." +
                        "       ?inFlow core2ed:propertyOfFlow ?p ." +
                        "   }" +
                        "   OPTIONAL{" +
                        "       ?inFlow core2ed:objectOfFlow ?o ." +
                        "   }" +
                        "   BIND(" +
                        "       IF(EXISTS{?inFlow a core2ed:ObjectFlow} ," +
                        "           afn:localname(?o)," +
                        "           fn:concat(afn:localname(?d),\"_\", afn:localname(?p), \"_\",afn:localname(?r)))" +
                        "           AS ?methodName" +
                        "   )" +
                        "}"
        );
    }

    public static SelectQueryHolder collectNodeInFlows(String nodeName){
        return new SelectQueryHolder(
                "SELECT DISTINCT ?inFlow ?entity ?type " +
                        "WHERE {" +
                        "    BIND(:" + nodeName + " AS ?node) ." +
                        "   ?node core2ed:outFlow ?outFlow ." +
                        "   ?outFlow core2ed:inFlow ?inFlow ." +
                        "   {?inFlow core2ed:objectOfFlow ?entity}" +
                        "   UNION" +
                        "   {?inFlow core2ed:propertyOfFlow ?entity}" +
                        "   ?entity rdf:type ?type ." +
                        "}"
        );
    }

}
