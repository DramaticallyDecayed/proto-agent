package dd.union.perception.notification.model.types;

import dd.union.perception.notification.recognition.translators.Vocabulary;

/**
 * Хранение строкового значение, подлежащего переводу
 *
 * Created by slebedev on 15.06.2017.
 */
public class VocabularyStringType extends StringType {

    public VocabularyStringType(String path, String value) {
        super(path, value);
    }

    public VocabularyStringType(String path, String value, boolean empty) {
        super(path, value, empty);
    }

    @Override
    public String translate() {
        return new Vocabulary().translate(getValue());
    }
}
