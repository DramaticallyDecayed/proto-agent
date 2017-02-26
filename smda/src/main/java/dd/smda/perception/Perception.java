package dd.smda.perception;

import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.AnalysisC;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Sergey on 19.02.2017.
 */
public class Perception {

    private Map<String, Integer> columnName2ColumnIndex = new HashMap<>();
    private CSVBatch csvBatch;
    private AnalysisParametersBuffer buffer;
    private ConcurrentLinkedQueue<Analysis> analyses;
    private int index;
    private static int BATCH_SIZE = 10;
    private int batchSize;

    public Perception(ConcurrentLinkedQueue<Analysis> analyses) throws FileNotFoundException {
        CSVBatchReader reader = new CSVBatchReader();
        csvBatch = reader.read();
        this.columnName2ColumnIndex = reader.getColumnName2ColumnIndex();
        buffer = new AnalysisParametersBuffer(columnName2ColumnIndex);
        this.analyses = analyses;
    }

    public void cycle(){
        try {
            batchSize = BATCH_SIZE;
            simulateInput(csvBatch);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void simulateInput(CSVBatch csvBatch) throws ParseException {
        for (; batchSize > 0 && index < csvBatch.getData().size(); batchSize--) {
            String[] line = csvBatch.getData().get(index++);
            analyses.offer(constructAnalysis(line));
            buffer.register(line);
        }
    }

    private Analysis constructAnalysis(String[] line) throws ParseException {
        Analysis analysis = new AnalysisC();
        analysis.setHasRegisterNumber(
                line[columnName2ColumnIndex.get("Ном_Лаб_эпиз")]
        );
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(line[columnName2ColumnIndex.get("Дат_врем_забора")]);
        analysis.setHasDate(date);
        return analysis;
    }

    public AnalysisParametersBuffer getBuffer(){
        return buffer;
    }
}
