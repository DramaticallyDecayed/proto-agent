package dd.smda.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.smda.datastore.DataStoreUtils;
import dd.smda.datastore.DataStoreWriter;
import dd.smda.perception.AnalysisParametersBuffer;

import dd.smda.sas.objectproperty.DescribePatient;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.Patient;
import dd.smda.sas.worldentity.PatientC;

import java.util.Map;

/**
 * Created by Sergey on 25.02.2017.
 */
public class Node_cu_describePatient extends dd.smda.sas.computation.node.Node_cu_describePatient {
    public Node_cu_describePatient(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {

        if (!getAnalysisList().isEmpty()) {
            Map<String, Integer> map = AnalysisParametersBuffer.columnName2ColumnIndex;

            try {
                DataStoreWriter dataStoreWriter = new DataStoreWriter();
                dataStoreWriter.startWrite();
                for (Analysis analysis : getAnalysisList()) {
                    String[] params = AnalysisParametersBuffer.getParameters(analysis);

                    Patient patient;
                    try {

                        patient = new PatientC();
                        getPatientList().add(patient);

                        patient.setHasAge(Integer.valueOf(params[map.get("Возр")]));
                        patient.setHasRegisterNumber(params[map.get("Рег_номер")]);
                        patient.setHasDiagnosis(params[map.get("Диагноз")]);
                        patient.setHasSex(params[map.get("Пол")]);

                        DescribePatient dp = new DescribePatient();
                        dp.setDomain(analysis);
                        dp.setRange(patient);

                        //getDescribePatientList().add(dp);


                        dataStoreWriter.continueWrite(
                                patient,
                                DataStoreUtils.getId(patient)
                        );

                        dataStoreWriter.continueWrite(
                                dp,
                                DataStoreUtils.getId(analysis),
                                DataStoreUtils.getId(patient)
                        );

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                dataStoreWriter.endWrite();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return CalculationResult.POSITIVE;
        }
        return CalculationResult.UNKNOWN;
    }
}
