package dd.union.sas;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Node;
import dd.union.sas.nodeimplementation.Node_cu_Notification;
import dd.union.sas.worldentity.Notification;
import dd.union.sas.worldentity.UnionPercepted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Perceptor2SASAdapterImpl extends Perceptor2SASAdapter {
    public final static String NAME = "perceptor2SASAdapter";
    List<Notification> notifications = new ArrayList<>();
    private Map<String, Node> subscribers = new HashMap<>();


    public void setNotificationList(List<Notification> notificationList) {
        notifications = new ArrayList<>(notificationList);
        notificationList.clear();
        if (subscribers.get(Node_cu_Notification.NAME) != null && !notifications.isEmpty()) {
            subscribers.get(Node_cu_Notification.NAME).processNode();
        }
    }

    @Override
    public List<UnionPercepted> getUnionPerceptedList() {
        throw new UnsupportedOperationException("It is a fake entity to support URI property");
    }

    @Override
    public List<Notification> getNotificationList() {
        return notifications;
    }

    @Override
    public void dropDerivative() {
        notifications.clear();
    }

    @Override
    public CalculationResult customProcess() {
        return null;
    }

    @Override
    public void subscribe(Node node) {
        subscribers.put(node.name(), node);
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void pushAsDonor(Node acceptor) {

    }
}
