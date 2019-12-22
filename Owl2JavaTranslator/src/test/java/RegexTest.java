import org.junit.Test;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sergey on 05.02.2017.
 */
public class RegexTest {

    @Test
    public void test(){
        String str1 = "SELECT ?c WHERE {     ?c rdfs:subClassOf+ core2ed:WorldEntity .MINUS{?c rdfs:subClassOf core2ed:WorldEntity .}MINUS{\n" +
                "?c rdfs:subClassOf+ core2ed:Known .}MINUS{?c rdfs:subClassOf+ core2ed:Percepted .}MINUS{?c rdfs:subClassOf+ core2ed:Computed .}}";

        String str2 = "SELECT ?sc WHERE {OPTIONAL{:ParameterizedConcept rdfs:subClassOf* ?sc .?sc rdfs:subClassOf core2ed:ModelEntity .}OPTIONAL{:ParameterizedConcept rdfs:subClassOf* ?sc .?sc rdfs:subClassOf core2ed:DesignEntity .}}";
        String source = str1;
        String destination = "";
        String insert = "parameterized-system";

        destination = replacePrefixes(source, destination, insert);


        System.out.println(str1);
        System.out.println(str2);
        System.out.println(destination);
    }

    private String replacePrefixes(String source, String destination, String insert) {
        Pattern p = Pattern.compile("\\W\\:");
        Matcher m = p.matcher(source);
        int lastIndex = -1;
        while (m.find()) {
            destination += source.substring(lastIndex+1, m.start() + 1) + insert;
            lastIndex = m.start();
        }
        destination += source.substring(lastIndex + 1);
        return destination;
    }
}
