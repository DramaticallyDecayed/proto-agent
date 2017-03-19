package dd.smda.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.smda.datastore.DataStoreAdapter;
import dd.smda.datastore.DataStoreUtils;
import dd.smda.datastore.DataStoreWriter;
import dd.smda.rservice.RServer;
import dd.smda.rservice.SimpleLM;
import dd.smda.sas.objectproperty.BeBatchFor;
import dd.smda.sas.objectproperty.HasBatch;
import dd.smda.sas.objectproperty.HasGraph;
import dd.smda.sas.worldentity.GraphFragment;
import dd.smda.sas.worldentity.GraphFragmentC;
import dd.smda.sas.worldentity.ParameterBatch;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RserveException;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sergey on 26.02.2017.
 */
public class Node_cu_GraphFragment extends dd.smda.sas.computation.node.Node_cu_GraphFragment {

    private static final int BATCH_SIZE = 30;
    private List<ParameterBatch> batches;

    public Node_cu_GraphFragment(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {

        batches = new ArrayList<>();
        if (getHasBatchList() != null && getGraphStructureRuleList() != null) {
            if (!getHasBatchList().isEmpty() && !getGraphStructureRuleList().isEmpty()) {

                String rule = getGraphStructureRuleList().get(0).getHasRuleString();
                String[] ruleParams = rule.split(" ");
                String concentrateParam = ruleParams[0];
                String[] linearParams = Arrays.copyOfRange(ruleParams, 1, ruleParams.length - 1);
                DataStoreWriter dataStoreWriter = new DataStoreWriter();
                dataStoreWriter.startWrite();
                DataStoreAdapter dataStoreAdapter = new DataStoreAdapter();
                dataStoreAdapter.open();
                try {
                    for (HasBatch hasBatch : getHasBatchList()) {
                        for (String linearParam : linearParams) {
                            List<String>[] result = selectParameterValues(concentrateParam, dataStoreAdapter, hasBatch, linearParam);
                            RServer rServer = new RServer();
                            try {
                                List<String> concentrateVals = calculateDependencies(result, rServer);
                                batches.add(hasBatch.getRange());
                                saveResults(dataStoreWriter, hasBatch, linearParam, concentrateVals);
                            } catch (RserveException e) {
                                e.printStackTrace();
                            } catch (REXPMismatchException e) {
                                e.printStackTrace();
                            } finally {
                                rServer.stop();
                            }
                        }
                    }
                    for (ParameterBatch batch : batches) {
                        deletePreviousResults(dataStoreAdapter, batch);
                    }
                    dataStoreAdapter.close();
                    dataStoreWriter.endWrite();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
        return CalculationResult.UNKNOWN;
    }

    private void deletePreviousResults(DataStoreAdapter dataStoreAdapter, ParameterBatch batch) throws Exception {
        String delete = "prefix parametergraph:<http://dramaticallydecayed.com/parameterized-system#>\n" +
                "delete where {\n" +
                "\t?c a parametergraph:GraphFragment .\n" +
                "\tparametergraph:" + DataStoreUtils.getId(batch) + " parametergraph:beBatchFor ?c .\n" +
                "\t?c parametergraph:hasIndependentParameter ?ip .\n" +
                " \t?c parametergraph:hasDependentParameter ?dp .\n" +
                "}";
        dataStoreAdapter.delete(delete);
    }

    private void saveResults(DataStoreWriter dataStoreWriter, HasBatch hasBatch, String linearParam, List<String> concentrateVals) throws IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException {
        for (String concentrateVal : concentrateVals) {

            GraphFragment graphFragment = new GraphFragmentC();

            graphFragment.setHasIndependentParameter(concentrateVal);
            graphFragment.setHasDependentParameter(linearParam);
            graphFragment.setHasID(hasBatch.getRange().getHasID());

            dataStoreWriter.continueWrite(
                    graphFragment,
                    DataStoreUtils.getId(graphFragment, hasBatch.getRange())
            );


            HasGraph hasGraph = new HasGraph();
            hasGraph.setDomain(hasBatch.getDomain());
            hasGraph.setRange(graphFragment);

            dataStoreWriter.continueWrite(
                    hasGraph,
                    DataStoreUtils.getId(hasBatch.getDomain()),
                    DataStoreUtils.getId(graphFragment, hasBatch.getRange())
            );

            BeBatchFor beBatchFor = new BeBatchFor();
            beBatchFor.setDomain(hasBatch.getRange());
            beBatchFor.setRange(graphFragment);

            dataStoreWriter.continueWrite(
                    beBatchFor,
                    DataStoreUtils.getId(hasBatch.getRange()),
                    DataStoreUtils.getId(graphFragment, hasBatch.getRange())
            );

        }
    }

    private List<String> calculateDependencies(List<String>[] result, RServer rServer) throws RserveException, REXPMismatchException {
        rServer.start();
        SimpleLM simpleLM = new SimpleLM(rServer.getConnection());
        simpleLM.loadParameterValues(result);
        return simpleLM.evalLM();
    }

    private List<String>[] selectParameterValues(String concentrateParam, DataStoreAdapter dataStoreAdapter, HasBatch hasBatch, String linearParam) throws Exception {
        String select = "prefix parametergraph:<http://dramaticallydecayed.com/parameterized-system#>\n" +
                "SELECT ?cval ?nval\n" +
                "WHERE {\n" +
                hasBatch.getRange().getHasRuleString() +
                "\t?a parametergraph:describePatient ?p.\n" +
                "\t?a parametergraph:includeParameter ?nparam .\n" +
                "\t?nparam a parametergraph:" + linearParam + " .\n" +
                "\t?nparam parametergraph:hasValue ?nval .\n" +
                "\t?a parametergraph:includeParameter ?cparam .\n" +
                "\t?cparam a parametergraph:" + concentrateParam + " .\n" +
                "\t?cparam parametergraph:hasValue ?cval .\n" +
                "}";

        return dataStoreAdapter.select(select, "cval", "nval");
    }
}
