package dd.union.perception.notification.recognition.listeners;

import dd.union.perception.notification.model.types.IdentNumberType;
import dd.union.perception.notification.recognition.rules.NotificationFormatBaseListener;
import dd.union.perception.notification.recognition.rules.NotificationFormatParser;
import dd.union.perception.notification.recognition.rules.StartFormat3Lexer;
import dd.union.sas.worldentity.Notification;
import dd.union.sas.worldentity.NotificationC;
import dd.union.sas.worldentity.Treaty;
import org.antlr.v4.runtime.CommonTokenStream;


/**
 * Created by slebedev on 15.06.2017.
 */
public class NotificationFormatListener extends NotificationFormatBaseListener {

    private CommonTokenStream commonTokenStream;
    private Notification notification;
    private String treaty;

    public NotificationFormatListener(Notification notification){
        this.notification = notification;
    }

    @Override
    public void exitText(NotificationFormatParser.TextContext ctx) {
        String text =  ListenerUtils.text(commonTokenStream, ctx, StartFormat3Lexer.HIDDEN);
        IdentNumberType identNumberType = new IdentNumberType("",text);
        notification.setNf_number(identNumberType.getNumber());
        notification.setNf_format(identNumberType.getFormat());
        notification.setNf_year(identNumberType.getYear());
        treaty = identNumberType.getTreaty();
    }


    public void setCommonTokenStream(CommonTokenStream commonTokenStream) {
        this.commonTokenStream = commonTokenStream;
    }

    public Notification getNotification() {
        return notification;
    }

    public String getTreaty() {
        return treaty;
    }
}
