package dd.union;

import ch.qos.logback.classic.Level;
import dd.sas.SAS;
import dd.union.ontology.OntologyHandler;
import dd.union.perception.notification.Perception;
import dd.union.sas.Perceptor2SASAdapterImpl;
import dd.union.sas.worldentity.Notification;
import dd.union.sas.worldentity.NotificationC;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UnionAgent {

    public static void setLoggingLevel(ch.qos.logback.classic.Level level) {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(level);
    }


    public static void main(String... args) throws FileNotFoundException {
        setLoggingLevel(Level.ERROR);

        OntologyHandler ontologyHandler = new OntologyHandler();
        ontologyHandler.arm();

        SAS sas = new SAS(ontologyHandler);
        Perceptor2SASAdapterImpl adapter = initPerceptor2SASAdapter(sas);
        sas.cycle();

        Perception perception = new Perception();

        while (true) {
            perception.cycle();
            List<Notification> notifications = new ArrayList<>();
            ConcurrentLinkedQueue<String> texts = perception.getTexts();
            while (!texts.isEmpty()) {
                notifications.add(collect(texts.poll()));
            }
            adapter.setNotificationList(notifications);
            sas.cycle();
        }
    }

    private static Perceptor2SASAdapterImpl initPerceptor2SASAdapter(SAS sas) {
        Perceptor2SASAdapterImpl adapter = new Perceptor2SASAdapterImpl();
        adapter.getLevel().addNode(adapter);
        sas.getLevelHolder().addLevel(adapter.getLevel());
        return adapter;
    }

    private static Notification collect(String text){
        Notification notification = new NotificationC();
        notification.setNf_text(text);
        return notification;
    }
}
