package dd.smda.perception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 19.02.2017.
 */
public class Field2ClassNameMapper {
    public static final Map<String, String> field2ClassNameMapping;

    static {
        field2ClassNameMapping = new HashMap<>();
        field2ClassNameMapping.put("Креатинин", "Creatinine");
        field2ClassNameMapping.put("ОАМ Аморфн_фосфаты", "AmorphousPhosphates");
        field2ClassNameMapping.put("ОАМ_Бактерии", "Bacteria");
        field2ClassNameMapping.put("ОАМ_Белок", "Protein");
        field2ClassNameMapping.put("ОАМ Белок_анализатор", "ProteinAnalyzer");
        field2ClassNameMapping.put("ОАМ_Билирубин", "Bilirubin");
        field2ClassNameMapping.put("ОАМ_Глюкоз", "Glucose");
        field2ClassNameMapping.put("ОАМ_Кетон_тела", "KetoneBodies");
        field2ClassNameMapping.put("ОАМ_Кисл-сть_приб", "AcidityApprox");
        field2ClassNameMapping.put("ОАМ_Кристал_мочев_кисл", "UricAcidCrystals");
        field2ClassNameMapping.put("ОАМ_Лейкоциты", "WhiteBloodCells");
        field2ClassNameMapping.put("ОАМ_Оксалаты", "Oxalate");
        field2ClassNameMapping.put("ОАМ_Удел_плот", "SpecificGravity");
        field2ClassNameMapping.put("ОАМ_Ураты", "Urata");
        field2ClassNameMapping.put("ОАМ_Уробилиноген", "Urobilinogen");
        field2ClassNameMapping.put("ОАМ_Эритр", "RedBloodCells");
        field2ClassNameMapping.put("ОАМ_Цвет", "UrineColor");
    }

}
