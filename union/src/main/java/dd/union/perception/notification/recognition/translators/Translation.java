package dd.union.perception.notification.recognition.translators;

/**
 * Created by slebedev on 15.06.2017.
 */
public interface Translation<T> {

    String translate(T value);

}