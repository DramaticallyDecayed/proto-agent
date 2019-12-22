package dd.union.perception.notification.model.parsers;

/**
 * Created by slebedev on 15.06.2017.
 */
public interface Parsing<T>  {

    T parse(String value);
}
