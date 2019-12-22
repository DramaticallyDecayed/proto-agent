package dd.union.perception.notification.recognition.translators;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by slebedev on 15.06.2017.
 */
public class Vocabulary implements Translation<String> {

    private static Map<String, String> vocabulary = new HashMap<>();

    static {
        vocabulary.put("NONE", "НЕТ");
        vocabulary.put("ANC/SOA", "АНЦ/СНВ");
        vocabulary.put("NOT APPLICABLE", "НЕ ПРИМЕНИМО");
        vocabulary.put("MORE THAN ONE LAUNCH", "БОЛЕЕ ОДНОГО ПУСКА");
        vocabulary.put("YES", "ДА");
        vocabulary.put("PULSE CODE MODULATION/FREQUENCY MODULATION", "КОДОВО-ИМПУЛЬСНАЯ МОДУЛЯЦИЯ/ЧАСТОТНАЯ МОДУЛЯЦИЯ");
        vocabulary.put("NORTHWEST ATLANTIC OCEAN", "CЕВЕРОЗАПАДНЫЙ АТЛАНТИЧЕСКИЙ ОКЕАН");
        vocabulary.put("MORE THAN ONE LAUNCH", "БОЛЬШЕ ЧЕМ ОДИН ПУСК");
    }

    @Override
    public String translate(String value) {
        if (vocabulary.containsKey(value)) {
            return vocabulary.get(value);
        } else {
            return value;
        }
    }
}
