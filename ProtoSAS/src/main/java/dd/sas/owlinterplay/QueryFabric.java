package dd.sas.owlinterplay;

/**
 * Created by Sergey on 29.05.2016.
 */
public final class QueryFabric {

    private QueryFabric(){}

    public static String prepareActivationString(String nodeName){
        return "CONSTRUCT{ " +
                "   ?nd core2ed:hasDerivative ?d ." +
                "}" +
                "WHERE {" +
                "   BIND(:" + nodeName + " AS ?nd) ." +
                "   ?nd core2ed:implement ?cu ." +
                "   ?cu core2ed:hasDerivative ?d ." +
                "}";
    }
}
