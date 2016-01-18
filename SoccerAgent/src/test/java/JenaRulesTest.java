import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.FileUtils;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;
import dd.ontologyinterchanger.ModelLoadingUtinls;
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
