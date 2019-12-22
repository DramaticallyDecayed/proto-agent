package dd.union.sas.nodeimplementation;

import dd.union.sas.worldentity.NfField;

import java.lang.reflect.InvocationTargetException;

public final class NodeUtils {

    private NodeUtils(){}

    public static Object getIType(NfField locationFiled) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = Node_cu_encodeLocation
                .class
                .getClassLoader()
                .loadClass(locationFiled.getNffield_type());
        return c.getConstructor(String.class, String.class)
                .newInstance(null, locationFiled.getNffield_text());
    }

}
