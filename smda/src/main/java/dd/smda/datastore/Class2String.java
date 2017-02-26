package dd.smda.datastore;

import dd.sas.Utils;
import dd.smda.perception.CSV2ModelTranslator;
import dd.smda.sas.worldentity.Analysis;
import dd.smda.sas.worldentity.AnalysisC;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sergey on 21.02.2017.
 */
public class Class2String {

    private static final String PREFIX = "analysis";
    private static final String XSD = "xsd";

    public static void main(String... args) throws IllegalAccessException, IntrospectionException, InvocationTargetException, ParseException {

        Analysis a = new AnalysisC();
        a.setHasDate(new Date());
        a.setHasRegisterNumber("11");
        prepare(a, "a_1");
    }

    public static String updatePrefix() {
        return "prefix " + PREFIX + ":<" + CSV2ModelTranslator.prefix.get(PREFIX) + ">\n"
                + "INSERT DATA {\n";
    }

    public static String updateBody4Class(Object o, String id) throws IntrospectionException, InvocationTargetException, IllegalAccessException, ParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("\t" +
                PREFIX + ":" + id
                + " a "
                + PREFIX + ":" + o.getClass().getInterfaces()[0].getSimpleName()
                + ".\n"
        );
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            Method getter = new PropertyDescriptor(field.getName(), o.getClass()).getReadMethod();
            sb.append("\t" +
                    PREFIX + ":" + id
                    + " " + PREFIX + ":" + field.getName()
                    + " " + transform(field.getType().getSimpleName(), getter.invoke(o))
                    + ".\n"
            );
        }
        return sb.toString();
    }

    public static String updateBody4Property(Object p, String id1, String id2) throws IntrospectionException, InvocationTargetException, IllegalAccessException, ParseException {
        return "\t" + PREFIX + ":" + id1
                + " " + PREFIX + ":" + Utils.makeFirsLetterDown(p.getClass().getSimpleName())
                + " " + PREFIX + ":" + id2 + ".\n";
    }

    public static String updatePostfix() {
        return "}";
    }

    //TODO: refactoring is needed!
    public static String prepare(Object o, String id)
            throws IllegalAccessException, IntrospectionException, InvocationTargetException, ParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("prefix " + PREFIX + ":<" + CSV2ModelTranslator.prefix.get(PREFIX) + ">\n"
                + "INSERT DATA {\n");
        sb.append("\t" +
                PREFIX + ":" + id
                + " a "
                + PREFIX + ":" + o.getClass().getInterfaces()[0].getSimpleName()
                + ".\n"
        );
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            Method getter = new PropertyDescriptor(field.getName(), o.getClass()).getReadMethod();
            sb.append("\t" +
                    PREFIX + ":" + id
                    + " " + PREFIX + ":" + field.getName()
                    + " " + transform(field.getType().getSimpleName(), getter.invoke(o))
                    + ".\n"
            );
        }
        sb.append("}");
        return sb.toString();
    }

    private static String transform(String type, Object object) throws ParseException {
        switch (type) {
            case "Integer":
                return "\""
                        + object
                        + "\"^^" + XSD + ":integer";
            case "Date":
                return "\""
                        + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(((Date) object))
                        + "\"^^" + XSD + ":dateTime";

            default:
                return "\""
                        + object.toString()
                        + "\"^^" + XSD + ":string";
        }

    }

}
