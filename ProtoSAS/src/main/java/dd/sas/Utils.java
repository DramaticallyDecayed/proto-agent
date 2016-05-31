package dd.sas;

/**
 * Created by Sergey on 31.05.2016.
 */
public final class Utils {

    private Utils(){}

    public static String makeFirsLetterUp(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
