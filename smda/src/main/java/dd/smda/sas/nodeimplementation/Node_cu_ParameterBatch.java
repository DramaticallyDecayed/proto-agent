package dd.smda.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.smda.datastore.DataStoreAdapter;
import dd.smda.datastore.DataStoreUtils;
import dd.smda.datastore.DataStoreWriter;
import dd.smda.perception.CSV2ModelTranslator;
import dd.smda.sas.objectproperty.*;
import dd.smda.sas.worldentity.ParameterBatch;
import dd.smda.sas.worldentity.ParameterBatchC;
import dd.smda.sas.worldentity.Patient;
import dd.smda.sas.worldentity.PatientGroupRule;

/**
 * Created by Sergey on 26.02.2017.
 */
public class Node_cu_ParameterBatch extends dd.smda.sas.computation.node.Node_cu_ParameterBatch {
    private static final String PREFIX = "parametergraph";
    private static final int BATCH_SIZE = 30;

    public Node_cu_ParameterBatch(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (getIncludeParameterList() != null && getBePatientBaseForList() != null) {
            if (!getIncludeParameterList().isEmpty() && !getBePatientBaseForList().isEmpty()) {
                try {
                    DataStoreWriter dataStoreWriter = new DataStoreWriter();
                    DataStoreAdapter dataStoreAdapter = new DataStoreAdapter();
                    dataStoreAdapter.open();
                    for (BePatientBaseFor bePatientBaseFor : getBePatientBaseForList()) {
                        Patient patient = bePatientBaseFor.getDomain();

                        PatientGroupRule patientGroupRule = bePatientBaseFor.getRange();


                        String queryBody = patientGroupRule.getHasQueryString();
                        StringBuilder query = new StringBuilder();
                        query.append("prefix " + PREFIX + ":<" + CSV2ModelTranslator.prefix.get(PREFIX) + ">\n" +
                                "SELECT (COUNT(?p) AS ?" + DataStoreAdapter.COUNTER_VAR_NAME + ") \n" +
                                "WHERE {");
                        query.append(queryBody);
                        query.append("}");

                        int count = dataStoreAdapter.count(query.toString());

                        query.setLength(0);
                        if (count > BATCH_SIZE) {
                            ParameterBatch parameterBatch = new ParameterBatchC();
                            String batchID = DataStoreUtils.getId(parameterBatch, patientGroupRule);

                            query.append("prefix " + PREFIX + ":<" + CSV2ModelTranslator.prefix.get(PREFIX) + ">\n" +
                                    "ASK\n" +
                                    "WHERE {\n" +
                                    "\tBIND(parametergraph:" + batchID + " AS ?b) .\n" +
                                    "\t?b parametergraph:hasSize ?size . \n" +
                                    "\t FILTER(?size + " + BATCH_SIZE +" > \"" + count + "\"^^xsd:integer) .\n" +
                                    "}");
                            if (!dataStoreAdapter.ask(query.toString())) {

                                parameterBatch.setHasSize(count);
                                parameterBatch.setHasID(DataStoreUtils.constructBatchIDPostfix(patientGroupRule));

                                HasBatch hasBatch = new HasBatch();
                                hasBatch.setDomain(patient);
                                hasBatch.setRange(parameterBatch);

                                dataStoreWriter.startWrite();
                                dataStoreWriter.continueWrite(
                                        parameterBatch,
                                        batchID
                                );
                                dataStoreWriter.continueWrite(
                                        hasBatch,
                                        DataStoreUtils.getId(patient),
                                        batchID
                                );
                                dataStoreWriter.endWrite();

                                parameterBatch.setHasRuleString(patientGroupRule.getHasQueryString());

                                getHasBatchList().add(hasBatch);

                                getParameterBatchList().add(parameterBatch);

                            }else{
                                //fixme: only when user not linked with batch
                                parameterBatch.setHasSize(count);
                                parameterBatch.setHasID(DataStoreUtils.constructBatchIDPostfix(patientGroupRule));
                                parameterBatch.setHasRuleString(patientGroupRule.getHasQueryString());
                                HasBatch hasBatch = new HasBatch();
                                hasBatch.setDomain(patient);
                                hasBatch.setRange(parameterBatch);
                                dataStoreWriter.startWrite();
                                dataStoreWriter.continueWrite(
                                        hasBatch,
                                        DataStoreUtils.getId(patient),
                                        batchID
                                );
                                dataStoreWriter.endWrite();
                                getHasBatchList().add(hasBatch);
                                getParameterBatchList().add(parameterBatch);
                            }

                            RestrictBatch restrictBatch = new RestrictBatch();
                            restrictBatch.setDomain(patientGroupRule);
                            restrictBatch.setRange(parameterBatch);
                            getRestrictBatchList().add(restrictBatch);

                        }

                    }
                    dataStoreAdapter.close();
                    return CalculationResult.POSITIVE;
                } catch (Exception e) {
                    e.printStackTrace();
                    return CalculationResult.UNKNOWN;
                }
            }
        }
        return CalculationResult.UNKNOWN;
    }


}
