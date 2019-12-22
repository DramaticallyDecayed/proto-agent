package dd.sas.presentation;

/**
 * Created by Sergey on 18.05.2016.
 */
public interface WorldEntity {

    default String getUri() {
        return "";
    }

    default void setUri(String uri) {

    }
}
