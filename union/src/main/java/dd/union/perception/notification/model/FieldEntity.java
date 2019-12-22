package dd.union.perception.notification.model;

/**
 * Поле уведомления
 * <p/>
 * Created by slebedev on 08.06.2017.
 */


public class FieldEntity {

    private String path;
    private String valRu;
    private String valEn;
    private String type;
    private NotificationEntity notification;

    public FieldEntity() {
    }

    public FieldEntity(NotificationEntity notification) {
        this.notification = notification;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getValRu() {
        return valRu;
    }

    public void setValRu(String valRu) {
        this.valRu = valRu;
    }

    public String getValEn() {
        return valEn;
    }

    public void setValEn(String valEn) {
        this.valEn = valEn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NotificationEntity getNotification() {
        return notification;
    }

    public void setNotification(NotificationEntity notification) {
        this.notification = notification;
    }
}
