package dd.union.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.union.ontology.DataStoreService;
import dd.union.perception.notification.recognition.listeners.NotificationFormatListener;
import dd.union.perception.notification.recognition.rules.NotificationFormatLexer;
import dd.union.perception.notification.recognition.rules.NotificationFormatParser;
import dd.union.sas.worldentity.Notification;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

public class Node_cu_Notification extends dd.union.sas.computation.node.Node_cu_Notification {

    private static Logger logger = LoggerFactory.getLogger(Node_cu_Notification.class);

    public Node_cu_Notification(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (getNotificationList() != null && !getNotificationList().isEmpty()) {
            for (Notification notification : getNotificationList()) {
                try {
                    walkNotificationFormat(notification);
                    notification.setUri(UUID.randomUUID().toString());
                    DataStoreService.INSTANCE.dataStoreWriter.write(notification);
                } catch (IOException e) {
                    logger.error("Some problem with notification text recognition...", e);
                    return CalculationResult.UNKNOWN;
                } catch (Exception e) {
                    logger.error("Some problem with notification saving...", e);
                    return CalculationResult.UNKNOWN;
                }
                return CalculationResult.POSITIVE;
            }
        }
        return CalculationResult.UNKNOWN;
    }


    private NotificationFormatListener walkNotificationFormat(Notification notification) throws IOException {
        CharStream textInput = new ANTLRInputStream(notification.getNf_text());
        NotificationFormatListener listener = new NotificationFormatListener(notification);
        NotificationFormatLexer notificationFormatLexer = new NotificationFormatLexer(textInput);
        CommonTokenStream notificationTokens = new CommonTokenStream(notificationFormatLexer);
        listener.setCommonTokenStream(notificationTokens);
        NotificationFormatParser notificationFormatParser = new NotificationFormatParser(notificationTokens);
        ParseTree notificationFormatTree = notificationFormatParser.notification();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, notificationFormatTree);
        return listener;
    }

}
