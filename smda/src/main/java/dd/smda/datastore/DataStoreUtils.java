package dd.smda.datastore;

import dd.sas.presentation.WorldEntity;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.ParameterBatch;
import dd.smda.sas.worldentity.Patient;
import dd.smda.sas.worldentity.PatientGroupRule;

import java.util.Arrays;

/**
 * Created by Sergey on 21.02.2017.
 */
public class DataStoreUtils {

    public static String getIdPostfix(Analysis analysis) {
        return analysis.getHasRegisterNumber()
                + analysis.getHasDate().getTime();
    }

    public static String getId(Analysis analysis) {
        return getId(analysis, analysis);
    }

    public static String getId(WorldEntity worldEntity, Analysis analysis) {
        return worldEntity.getClass().getInterfaces()[0].getSimpleName().toLowerCase()
                + "_"
                + getIdPostfix(analysis);
    }

    public static String getId(Patient patient){
        return patient.getClass().getInterfaces()[0].getSimpleName().toLowerCase()
                + "_"
                + patient.getHasRegisterNumber().replace("/","_");
    }

    public static String getId(ParameterBatch parameterBatch, PatientGroupRule patientGroupRule){
        return parameterBatch.getClass().getInterfaces()[0].getSimpleName().toLowerCase()
                + "_"
                + constructBatchIDPostfix(patientGroupRule);
    }

    public static String constructBatchIDPostfix(PatientGroupRule patientGroupRule) {
        String queryBody = patientGroupRule.getHasQueryString();
        String[] ss = queryBody.split(" ");
        return Arrays
                .stream(ss)
                .filter(x -> x.contains("^^"))
                .map(x -> x.substring(0, x.indexOf("^")))
                .map(x -> x.replace("\"", ""))
                .reduce((x1, x2) -> x1 + "_" + x2).get();
    }

}
