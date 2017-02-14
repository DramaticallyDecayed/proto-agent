package dd.smda.perception;

/**
 * Created by Sergey on 14.02.2017.
 */
class Identifier {
    private final String prefix;
    private final String name;

    Identifier(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    public String toString(){
        return prefix + name;
    }
}
