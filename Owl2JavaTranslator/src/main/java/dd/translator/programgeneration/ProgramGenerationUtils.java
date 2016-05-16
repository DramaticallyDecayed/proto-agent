package dd.translator.programgeneration;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;

/**
 * Created by Sergey on 15.05.2016.
 */
public final class ProgramGenerationUtils {
    private ProgramGenerationUtils() {

    }

    public static JDefinedClass addGetter(JDefinedClass getterOwner, String getterName, Class getterType) {
        getterOwner.method(JMod.NONE, getterType, "get" + makeFirsLetterUp(getterName));
        return getterOwner;
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
}
