package dd.smda.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.smda.datastore.Class2String;
import dd.smda.datastore.DataStoreUtils;
import dd.smda.datastore.DataStoreWriter;
import dd.smda.perception.AnalysisParametersBuffer;
import dd.smda.perception.Field2ClassNameMapper;
import dd.smda.sas.objectproperty.IncludeParameter;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.SystemParameter;
import dd.smda.sas.worldentity.SystemParameterC;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 19.02.2017.
 */
public class Node_cu_includeParameter extends dd.smda.sas.computation.node.Node_cu_includeParameter {
    public Node_cu_includeParameter(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        analysisRegister.clear();
        if (!getAnalysisList().isEmpty()) {
            Map<String, Integer> map = AnalysisParametersBuffer.columnName2ColumnIndex;

            try {
                DataStoreWriter dataStoreWriter = new DataStoreWriter();
                dataStoreWriter.startWrite();
                for (Analysis analysis : getAnalysisList()) {
                    String[] params = AnalysisParametersBuffer.getParameters(analysis);

                    SystemParameter systemParameter;
                    try {
                        for (String paramName : map.keySet()) {
                            String className = Field2ClassNameMapper.field2ClassNameMapping.get(paramName);
                            if (className != null) {
                                Class<SystemParameter> c = (Class<SystemParameter>) Class.forName
                                        (
                                                "dd.smda.sas.worldentity." + className + "C"
                                        );
                                systemParameter = c.newInstance();
                                if (params[map.get(paramName)].isEmpty()) {
                                    systemParameter.setHasValue("NA");
                                } else {
                                    if(!paramName.equals("ОАМ_Цвет") && params[map.get(paramName)].equals("ж")){
                                        System.out.println();
                                    }
                                    systemParameter.setHasValue(params[map.get(paramName)]);
                                }

                                IncludeParameter ip = new IncludeParameter();
                                ip.setDomain(analysis);
                                ip.setRange(systemParameter);


                                dataStoreWriter.continueWrite(
                                        systemParameter,
                                        DataStoreUtils.getId(systemParameter, analysis)
                                );

                                dataStoreWriter.continueWrite(
                                        ip,
                                        DataStoreUtils.getId(analysis),
                                        DataStoreUtils.getId(systemParameter, analysis)
                                );

                                registerAnalysis(analysis);

                                //getSystemParameterList().add(systemParameter);
                                //getIncludeParameterList().add(ip);
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
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

    private Map<String, Analysis> analysisRegister = new HashMap<>();

    private void registerAnalysis(Analysis analysis) {
        String id = DataStoreUtils.getId(analysis);
        if(!analysisRegister.containsKey(id)) {
            IncludeParameter ip = new IncludeParameter();
            ip.setDomain(analysis);
            ip.setRange(new SystemParameterC());
            getIncludeParameterList().add(ip);
        }
    }

}
