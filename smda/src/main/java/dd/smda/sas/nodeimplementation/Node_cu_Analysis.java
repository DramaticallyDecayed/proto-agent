package dd.smda.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.smda.datastore.DataStoreWriter;
import dd.smda.datastore.DataStoreUtils;
import dd.smda.sas.worldentity.Analysis;

/**
 * Created by Sergey on 19.02.2017.
 */
public class Node_cu_Analysis extends dd.smda.sas.computation.node.Node_cu_Analysis {
    public Node_cu_Analysis(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (getAnalysisList() != null && !getAnalysisList().isEmpty()) {
            DataStoreWriter dataStoreWriter = new DataStoreWriter();
            try {
                dataStoreWriter.startWrite();
                for (Analysis analysis : getAnalysisList()) {
                    dataStoreWriter.continueWrite(analysis, DataStoreUtils.getIdPostfix(analysis));
                }
                dataStoreWriter.endWrite();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return CalculationResult.POSITIVE;
    }
}
