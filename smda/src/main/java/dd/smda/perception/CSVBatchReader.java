package dd.smda.perception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Sergey on 11.02.2017.
 */
public class CSVBatchReader {

    private final static String DELIMITER = ",";
    private final static String ENCODING = "UTF-8";
    private final static String FILE_NAME = "Urine-csv.csv";

    private Map<String, Integer> columnName2ColumnIndex = new HashMap<>();

    public static void main(String[] args) throws IOException, ParseException {
        CSVBatch csvBatch = new CSVBatchReader().read();
        //Model model = CSV2ModelTranslator.constructModel(csvBatch, columnName2ColumnIndex);
        //ModelWriter.writeModel(model);
    }

    public CSVBatch read() throws FileNotFoundException {
        String csvFile = CSVBatchReader.class.getClassLoader().getResource(FILE_NAME).getPath();
        File file = new File(csvFile);
        Scanner sc = new Scanner(file, ENCODING);
        CSVBatch csvBatch = new CSVBatch();

        if (sc.hasNext()) {
            String[] title = sc.nextLine().split(DELIMITER);
            csvBatch.setTitle(title);
        }

        while (sc.hasNext()) {
            csvBatch.addDataLine(sc.nextLine().split(DELIMITER));
        }

        String[] title = csvBatch.getTitle();
        for (int i = 0; i < title.length; i++) {
            columnName2ColumnIndex.put(title[i], i);
        }
        return csvBatch;
    }

    public Map<String, Integer> getColumnName2ColumnIndex() {
        return columnName2ColumnIndex;
    }
}
