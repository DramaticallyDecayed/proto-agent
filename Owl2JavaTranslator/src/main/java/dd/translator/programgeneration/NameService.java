package dd.translator.programgeneration;

/**
 * Created by Sergey on 25.05.2016.
 */
public final class NameService {

    private final static String PACKAGE_PREFIX = "dd.soccer.sas";
    private final static String NODE_PACKAGE = PACKAGE_PREFIX + "." + "computation.node";
    private final static String WORLD_ENTITY_PACKAGE = PACKAGE_PREFIX + "." + "worldentity";
    private final static String OBJECT_PROPERTY_PACKAGE = PACKAGE_PREFIX + "." + "objectproperty";

    public static String nodeClassName(String name) {
        name = ProgramGenerationUtils.makeFirsLetterUp(name);
        return NODE_PACKAGE  + "." + name;
    }

    public static String worldEntityClassName(String className) {
        return WORLD_ENTITY_PACKAGE + "." + className;
    }

    public static String objectPropertyClassName(String name) {
        name = ProgramGenerationUtils.makeFirsLetterUp(name);
        return OBJECT_PROPERTY_PACKAGE + "." + name;
    }
}
