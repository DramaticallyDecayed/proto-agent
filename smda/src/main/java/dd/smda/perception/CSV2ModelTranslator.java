package dd.smda.perception;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 16.02.2017.
 */
public class CSV2ModelTranslator {

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

    public static Model constructModel(CSVBatch csvBatch) throws IOException {
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

        createFloatMapping(wrapper, "Креатинин", "Creatinine");
        createFloatMapping(wrapper, "ОАМ Аморфн_фосфаты", "AmorphousPhosphates");
        createFloatMapping(wrapper, "ОАМ_Бактерии", "Bacteria");
        createFloatMapping(wrapper, "ОАМ_Белок", "Protein");
        createFloatMapping(wrapper, "ОАМ Белок_анализатор", "ProteinAnalyzer");
        createFloatMapping(wrapper, "ОАМ_Билирубин", "Bilirubin");
        createFloatMapping(wrapper, "ОАМ_Глюкоз", "Glucose");
        createFloatMapping(wrapper, "ОАМ_Кетон_тела", "KetoneBodies");
        createFloatMapping(wrapper, "ОАМ_Кисл-сть_приб", "AcidityApprox");
        createFloatMapping(wrapper, "ОАМ_Кристал_мочев_кисл", "UricAcidCrystals");
        createFloatMapping(wrapper, "ОАМ_Лейкоциты", "WhiteBloodCells");
        createFloatMapping(wrapper, "ОАМ_Оксалаты", "Oxalate");
        createFloatMapping(wrapper, "ОАМ_Удел_плот", "SpecificGravity");
        createFloatMapping(wrapper, "ОАМ_Ураты", "Urata");
        createFloatMapping(wrapper, "ОАМ_Уробилиноген", "Urobilinogen");
        createFloatMapping(wrapper, "ОАМ_Эритр", "RedBloodCells");

        createStringMapping(wrapper, "ОАМ_Цвет", "Color");

        for (String[] line : csvBatch.getData()) {
            wrapper.addResource(model, line);
        }

        return model;
    }

    private static void createFloatMapping(RDFNodeWrapper wrapper, String columnName, String parameter) {
        createMapping(wrapper, columnName, parameter, "float");
    }

    private static void createStringMapping(RDFNodeWrapper wrapper, String columnName, String parameter) {
        createMapping(wrapper, columnName, parameter, "string");
    }

    private static void createMapping(
            RDFNodeWrapper wrapper,
            String columnName,
            String parameter,
            String type
    ) {
        RDFNodeWrapper analysis = wrapper.addProperty(
                new RDFPropertyWrapper(
                        new Identifier(prefix.get("hospital"), "hasAnalysis"))
        ).addPropertyObject(
                new RDFNodeWrapper(
                        s -> "analysis" + "_" + s,
                        prefix.get("analysis"),
                        columnName2ColumnIndex.get("Ном_Лаб_эпиз"),
                        new Identifier(prefix.get("analysis"), "Analysis")
                )
        );

        analysis.addProperty(
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
                        new Identifier(prefix.get("xsd"), type),
                        new Identifier(prefix.get("analysis"), "hasValue"),
                        columnName2ColumnIndex.get(columnName)
                )
        );

        analysis.addLiteral(
                new RDFLiteralWrapper(
                        new Identifier(prefix.get("xsd"), "dateTime"),
                        new Identifier(prefix.get("analysis"), "hasDate"),
                        columnName2ColumnIndex.get("Дат_врем_забора")
                )
        );
    }

}
