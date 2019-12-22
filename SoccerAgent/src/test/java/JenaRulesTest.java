
import dd.ontologyinterchanger.ModelLoadingUtinls;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.FileUtils;
import org.apache.jena.vocabulary.ReasonerVocabulary;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Sergey on 29.11.2015.
 */
public class JenaRulesTest {

    @Test
    public void testRules() {
        Path model = Paths.get("D:\\code\\IdeaProjects\\ProtoAgent\\SoccerAgent\\src\\main\\resources", "bareKB.rdf");
        OntModel ontModel = ModelLoadingUtinls.loadModelWithImports(
                model.toUri().toString(),
                FileUtils.langN3
        );

        Path rules = Paths.get("D:\\code\\IdeaProjects\\ProtoAgent\\SoccerAgent\\src\\main\\resources", "rules");
        Reasoner reasoner = new GenericRuleReasoner(Rule.rulesFromURL(rules.toUri().toString()));
        reasoner.setParameter(ReasonerVocabulary.PROPruleMode, "forwardRETE");

        InfModel infModel = ModelFactory.createInfModel(reasoner, ontModel);
        System.out.println(infModel.size());
        StmtIterator it = infModel.listStatements();



        System.out.println("=============================================================");

//        while ( it.hasNext() )
//        {
//            Statement stmt = it.nextStatement();
//
//            Resource subject = stmt.getSubject();
//            Property predicate = stmt.getPredicate();
//            RDFNode object = stmt.getObject();
//
//            System.out.println( subject.toString() + " " + predicate.toString() + " " + object.toString() );
//        }
    }
}
