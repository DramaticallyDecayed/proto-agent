package dd.union.perception.notification.recognition.processing;


import dd.union.perception.notification.Perception;
import dd.union.perception.notification.model.NotificationEntity;
import dd.union.perception.notification.model.NotificationTree;
import dd.union.perception.notification.recognition.NotificationRecognizer;

import java.io.IOException;

/**
 * Основной сервис по обработке уведомления
 * <p>
 * распознование файла с уведомлением -> сохранение полей уведомления -> подготовка и сохранение модификаторов для таблиц объекто контроля
 * <p>
 * Created by slebedev on 15.06.2017.
 */

public class NotificationProcessingService {

    Entity2TreeTranslator notificationDBLoadingService = new Entity2TreeTranslator();

    public NotificationTree processNotification(String text) throws IOException {
        //NotificationEntity notificationEntity = new NotificationRecognizer().buildNotificationFromDocument(text);
        return null;//notificationDBLoadingService.restore(notificationEntity);
    }

}
