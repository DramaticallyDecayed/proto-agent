package dd.smda.datastore;

import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.AnalysisC;
import org.junit.Test;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import java.util.Date;

/**
 * Created by Sergey on 21.02.2017.
 */
public class DataStoreTest {
    private static final String serviceURL = "http://localhost:9999/blazegraph";

    @Test
    public void mainTest() throws Exception {
        final RemoteRepositoryManager repo = new RemoteRepositoryManager(serviceURL, false);
        final String namespace = "smda";
        try {

            Analysis a = new AnalysisC();
            a.setHasDate(new Date());
            a.setHasRegisterNumber("11");
            String update = Class2String.prepare(a, "a_1");

            repo.getRepositoryForNamespace(namespace)
                    .prepareUpdate(update).evaluate();
            final TupleQueryResult result = repo.getRepositoryForNamespace(namespace)
                    .prepareTupleQuery("SELECT * {?s ?p ?o} LIMIT 100")
                    .evaluate();
            try {
                while (result.hasNext()) {
                    final BindingSet bs = result.next();
                    System.out.println(bs);
                }
            } finally {
                result.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            repo.close();
        }
    }

//            CSVBatchReader reader = new CSVBatchReader();
//            CSVBatch csvBatch = reader.read();
//            Model model = CSV2ModelTranslator.constructModel(csvBatch, reader.getColumnName2ColumnIndex());
//
//
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            InputStream is;
//            model.write(os, FileUtils.langTurtle);
//            is = new ByteArrayInputStream(os.toByteArray());
//
//            repo.getRepositoryForNamespace(namespace).add(
//                    new RemoteRepository.AddOp(is, RDFFormat.TURTLE)
//            );

}
