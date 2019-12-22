package dd.union.perception.notification.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class NotificationEntity {
    private String number;
    private Boolean in;
    private String state;
    private Date timeIn;
    private Date timeOut;
    private String guid;
    private String treaty;
    private Integer format;

    private List<FieldEntity> fields = new ArrayList<>();
    private List<String> references = new ArrayList<>();

    public NotificationEntity() {
    }

    public NotificationEntity(String guid) {
        setGuid(guid);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean isIn() {
        return in;
    }

    public void setIn(Boolean in) {
        this.in = in;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }

    public String getTreaty() {
        return treaty;
    }

    public void setTreaty(String treaty) {
        this.treaty = treaty;
    }

    public List<FieldEntity> getFields() {
        return fields;
    }

    public void setFields(List<FieldEntity> fields) {
        this.fields = fields;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }
}
