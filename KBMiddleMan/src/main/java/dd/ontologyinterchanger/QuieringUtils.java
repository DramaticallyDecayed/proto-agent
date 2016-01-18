package dd.ontologyinterchanger;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import org.topbraid.spin.arq.ARQFactory;

/**
 * Created by Sergey on 22.11.2015.
 */
public class QuieringUtils {

    public static void showModelStatements(Model model) {
        System.out.println("----------------Show statements for model:------------------");
        StmtIterator stmtIterator = model.listStatements();
        while (stmtIterator.hasNext()) {
            System.out.println(stmtIterator.nextStatement());
        }
        System.out.println("--------------------------------------------------------");
    }

    public static void showQueryResults(Model ontModel, String queryString) {
        System.out.println("----------------Query results---------------------------");
        StringBuilder sb = new StringBuilder();
        sb.append(queryString);
        Query query = ARQFactory.get().createQuery(ontModel, sb.toString());
        QueryExecution qe = ARQFactory.get().createQueryExecution(query, ontModel);
        ResultSet results = qe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        qe.close();
        System.out.println("--------------------------------------------------------");
    }

    public static ResultSet getQueryResults(Model ontModel, String queryString) {
        StringBuilder sb = new StringBuilder();
        sb.append(queryString);
        Query query = ARQFactory.get().createQuery(ontModel, sb.toString());
        QueryExecution qe = ARQFactory.get().createQueryExecution(query, ontModel);
        return qe.execSelect();
    }

    public static void askAndUpdate(Model ontModel, String queryString) {
        //FOR QUERY THAT STARTS FROM "SELECT ?i ?sc"!!!
        StringBuilder sb = new StringBuilder();
        sb.append(queryString);
        Query query = ARQFactory.get().createQuery(ontModel, sb.toString());
        QueryExecution qe = ARQFactory.get().createQueryExecution(query, ontModel);
        ResultSet results = qe.execSelect();
        while (results.hasNext()) {
            QuerySolution row = results.nextSolution();
            Statement s = ResourceFactory.createStatement(
                    row.getResource("i"),
                    RDF.type,
                    row.getResource("sc"));
            ontModel.add(s);
        }
    }

}
