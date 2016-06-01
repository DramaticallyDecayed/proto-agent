package dd.sas.owlinterplay;

import dd.ontologyinterchanger.ConstructQuery;
import dd.ontologyinterchanger.SelectQueryHolder;

/**
 * Created by Sergey on 29.05.2016.
 */
public final class QueryFabric {

    private QueryFabric() {
    }

    public static ConstructQuery prepareActivationString(String nodeName) {
        return new ConstructQuery("CONSTRUCT{ " +
                "   ?nd core2ed:hasDerivative ?d ." +
                "}" +
                "WHERE {" +
                "   BIND(:" + nodeName + " AS ?nd) ." +
                "   ?nd core2ed:implement ?cu ." +
                "   ?cu core2ed:hasDerivative ?d ." +
                "}"
        );
    }

    public static SelectQueryHolder collectLevelNumbers(){
        return new SelectQueryHolder(
          "SELECT ?n " +
                  "WHERE {" +
                  "    ?level a core2ed:Level ." +
                  "    ?level core2ed:hasNumber ?n ." +
                  "}"
        );
    }

    public static SelectQueryHolder collectNodesWithLevel(){
        return new SelectQueryHolder(
                "SELECT ?level ?node " +
                        "WHERE {" +
                        "   ?node a core2ed:Node ." +
                        "   ?node core2ed:belongs2Level ?level ." +
                        "}"
        );
    }

    public static SelectQueryHolder collectNodeDonors(String nodeName){
        return new SelectQueryHolder(
                "SELECT ?donor " +
                        "WHERE {" +
                        "   BIND(:" + nodeName + " AS ?node) ." +
                        "   ?node core2ed:dependOn ?donor ." +
                        "}"
        );
    }

    public static SelectQueryHolder findNodeLevelNumber(String nodeName){
        return new SelectQueryHolder(
                "SELECT ?num " +
                        "WHERE{" +
                        "   BIND(:" + nodeName + " AS ?node) ." +
                        "   ?node core2ed:belongs2Level ?level ." +
                        "   ?level core2ed:hasNumber ?num ." +
                        "}"
        );
    }
}
