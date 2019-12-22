package dd.translator.owlinterplay;

import java.util.Date;

/**
 * Created by Sergey on 15.05.2016.
 */
public final class OwlTranlationUtils {

    private OwlTranlationUtils() {
    }

    public static Class decodeStringType(String type) throws OwlInterplayException {
        if (type.contains("float")) {
            return Double.class;
        } if (type.contains("double")) {
            return Double.class;
        }else if (type.contains("integer")) {
            return Integer.class;
        } else if (type.contains("boolean")) {
            return Boolean.class;
        } else if (type.contains("string")) {
            return String.class;
        }  else if (type.contains("date")) {
            return Date.class;
        }
        throw new OwlInterplayException("Unknown OWL type: " + type);
    }

}
