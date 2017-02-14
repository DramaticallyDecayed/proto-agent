package dd.smda.perception;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Sergey on 11.02.2017.
 */
public class CVSBatchReader {

    private final static String DELIMITER = ",";
    private final static String ENCODING = "UTF-8";
    private final static String FILE_NAME = "Urine-csv.csv";

    private final static Map<String, String> prefix;

    static {
        prefix = new HashMap<>();
        prefix.put("analysis", "http://dramaticallydecayed.com/analysis#");
        prefix.put("hospital", "http://dramaticallydecayed.com/hospital#");
        prefix.put("xsd", "http://www.w3.org/2001/XMLSchema#");
        prefix.put("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        prefix.put("owl", "http://www.w3.org/2002/07/owl#");
        prefix.put("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        prefix.put("foaf", "http://xmlns.com/foaf/0.1/");
    }

    private static Map<Integer, RDFNodeWrapper> column2Scheme = new HashMap<>();
    private static Map<String, Integer> columnName2ColumnIndex = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String csvFile = CVSBatchReader.class.getClassLoader().getResource(FILE_NAME).getPath();
        File file = new File(csvFile);
        Scanner sc = new Scanner(file, ENCODING);
        CSVBatch csvBatch = new CSVBatch();

        if (sc.hasNext()) {
            String[] title = sc.nextLine().split(DELIMITER);

            Map<String, Integer> name2index = new HashMap<>(title.length);
            for (int i = 0; i < title.length; i++) {
                name2index.put(title[i], i);
            }

            csvBatch.setTitle(title);
        }
        //==========================

        while (sc.hasNext()) {
            csvBatch.addDataLine(sc.nextLine().split(DELIMITER));
        }


        Model model = ModelFactory.createDefaultModel();

        for (String key : prefix.keySet()) {
            model.setNsPrefix(key, prefix.get(key));
        }



        String[] title = csvBatch.getTitle();
        for (int i = 0; i < title.length; i++) {
            columnName2ColumnIndex.put(title[i], i);
        }

        RDFNodeWrapper wrapper = new RDFNodeWrapper(
                s -> "patient" + s.replace("/", "_"),
                prefix.get("hospital"),
                columnName2ColumnIndex.get("Рег_номер"),
                new Identifier(prefix.get("hospital"), "Patient")
        );

        createMapping(wrapper, "Креатинин", "Creatinine");
        createMapping(wrapper, "ОАМ Аморфн_фосфаты", "AmorphousPhosphates");

        for (String[] line : csvBatch.getData()) {
            wrapper.addResource(model, line);
        }



        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("./smda/target/model.ttl");
            model.write(fileWriter, "TURTLE");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
        }


    }

    private static void createMapping(RDFNodeWrapper wrapper, String columnName, String parameter) {
        wrapper.addProperty(
                new RDFPropertyWrapper(
                        new Identifier(prefix.get("analysis"), "includeParameter")
                )
        ).addPropertyObject(
                new RDFNodeWrapper(
                        s -> parameter.toLowerCase() + "_" + s,
                        prefix.get("analysis"),
                        columnName2ColumnIndex.get("Ном_Лаб_эпиз"),
                        new Identifier(prefix.get("analysis"), parameter)
                )
        ).addLiteral(
                new RDFLiteralWrapper(
                        new Identifier(prefix.get("xsd"), "float"),
                        new Identifier(prefix.get("analysis"), "hasValue"),
                        columnName2ColumnIndex.get(columnName)
                )
        );
    }


}
