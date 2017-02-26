package dd.smda.perception;

import dd.smda.sas.worldentity.Analysis;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sergey on 19.02.2017.
 */
public class AnalysisParametersBuffer {

    private static Map<String, String[]> analysisRegister = new ConcurrentHashMap<>();
    public static Map<String, Integer> columnName2ColumnIndex;


    public AnalysisParametersBuffer(Map<String, Integer> columnName2ColumnIndex){
        this.columnName2ColumnIndex = new ConcurrentHashMap<>(columnName2ColumnIndex);
    }

    public void register(String[] line) {
        analysisRegister.put(
                line[columnName2ColumnIndex.get("Ном_Лаб_эпиз")]
                        + line[columnName2ColumnIndex.get("Дат_врем_забора")],
                line
        );
    }

    public static String[] getParameters(Analysis analysis) {
        return analysisRegister.get(
                analysis.getHasRegisterNumber()
                        + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(analysis.getHasDate())
        );
    }
}
