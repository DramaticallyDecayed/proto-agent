package dd.translator.programgeneration;

import com.sun.codemodel.*;

/**
 * Created by Sergey on 15.05.2016.
 */
public final class ProgramGenerationUtils {
    private ProgramGenerationUtils() {

    }

    public static JDefinedClass addSettersAndGetters4Interface(
            JDefinedClass newInterface,
            String paramName,
            Class paramType) {
        addGetter(newInterface, paramName, paramType, JMod.NONE);
        addSetter(newInterface, paramName, paramType, JMod.NONE);
        return newInterface;
    }

    public static String composeName(String className) {
        return ProgramStructureGenerator.PACKAGE_PREFIX
                + "."
                + SubPackageNameService.findPackageNameFor(className)
                + "."
                + className;
    }

    public static String interfaceName2ClassName(String interfaceName) {
        return interfaceName + "C";
    }

    public static String className2InterfaceName(String className) {
        return className.substring(0, className.length() - 1);
    }

    private static String makeFirsLetterUp(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static JDefinedClass addSettersAndGetters4Class(
            JDefinedClass c,
            String paramName,
            Class paramType) {
        addGetter(c, paramName, paramType, JMod.PUBLIC);
        JMethod setter = addSetter(c, paramName, paramType, JMod.PUBLIC);

        JFieldVar paramField = c.field(JMod.PRIVATE, paramType, paramName);
        setter.body().assign(JExpr._this().ref(paramField), JExpr.ref(paramName));
        return c;
    }

    private static JMethod addSetter(JDefinedClass structure, String paramName, Class paramType, int modifier) {
        JMethod setter = structure.method(modifier, void.class, "set" + makeFirsLetterUp(paramName));
        setter.param(paramType, paramName);
        return setter;
    }

    private static void addGetter(JDefinedClass getterOwner, String getterName, Class getterType, int aPublic) {
        getterOwner.method(aPublic, getterType, "get" + makeFirsLetterUp(getterName));
    }
}
