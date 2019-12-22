package dd.union.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.union.perception.notification.recognition.NotificationRecognizer;
import dd.union.sas.objectproperty.HasNfField;
import dd.union.sas.worldentity.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Node_cu_hasNfField extends dd.union.sas.computation.node.Node_cu_hasNfField {
    private static Logger logger = LoggerFactory.getLogger(Node_cu_hasNfField.class);

    public Node_cu_hasNfField(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (getNotificationList() != null && !getNotificationList().isEmpty()) {
            NotificationRecognizer notificationRecognizer = new NotificationRecognizer();
            try {
                for (Notification notification : getNotificationList()) {
                    List<HasNfField> list =
                            notificationRecognizer.buildNotificationFromDocument(notification);
                    getHasNfFieldList().addAll(list);
                }
                return CalculationResult.POSITIVE;
            } catch (IOException e) {
                logger.error("Some error while recognigion...", e);
                return CalculationResult.UNKNOWN;
            }

        }
        return CalculationResult.UNKNOWN;
    }
}
