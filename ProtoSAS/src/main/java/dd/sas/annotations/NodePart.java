package dd.sas.annotations;

/**
 * Created by Sergey on 20.05.2016.
 */
public enum NodePart {
    BASE("base"),
    DERIVATIVE("derivative"),
    DONOR("donor");

    private String value;

    private NodePart(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
