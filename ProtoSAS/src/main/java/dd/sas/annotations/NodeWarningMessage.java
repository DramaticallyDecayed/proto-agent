package dd.sas.annotations;

/**
 * Created by Sergey on 21.05.2016.
 */
public enum NodeWarningMessage {
    UNSAFE_CASTING("unsafe casting: object filtering is expected");

    private String value;

    NodeWarningMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
