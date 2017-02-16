package dd.smda.perception;

import com.hp.hpl.jena.rdf.model.Model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Sergey on 11.02.2017.
 */
public class CVSBatchReader {

    private final static String DELIMITER = ",";
    private final static String ENCODING = "UTF-8";
    private final static String FILE_NAME = "Urine-csv.csv";


    public static void main(String[] args) throws IOException {
        String csvFile = CVSBatchReader.class.getClassLoader().getResource(FILE_NAME).getPath();
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

        Model model = CSV2ModelTranslator.constructModel(csvBatch);

        ModelWriter.writeModel(model);

    }

}
