package dd.union.perception.notification.recognition;

import dd.union.perception.notification.model.NotificationEntity;
import dd.union.perception.notification.model.constants.StartFormats;
import dd.union.perception.notification.recognition.listeners.HiddenChannelListener;
import dd.union.perception.notification.recognition.listeners.StartFormat15EntityListener;
import dd.union.perception.notification.recognition.listeners.StartFormat3EntityTreeListener;
import dd.union.perception.notification.recognition.rules.*;
import dd.union.sas.objectproperty.HasNfField;
import dd.union.sas.worldentity.Notification;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.List;
import java.io.IOException;

/**
 * Created by slebedev on 29.06.2017.
 */
public class NotificationRecognizer {

    public List<HasNfField> buildNotificationFromDocument(Notification notification) throws IOException {
        switch (notification.getNf_format()) {
        case StartFormats.FORMAT_3:
            HiddenChannelListener listener3 = new StartFormat3EntityTreeListener(notification);
            walkFormat3(listener3, notification.getNf_text());
            return listener3.getHasNfFieldsList();
        case StartFormats.FORMAT_15:
            HiddenChannelListener listener15 = new StartFormat15EntityListener(notification);
            walkFormat15(listener15, notification.getNf_text());
            return listener15.getHasNfFieldsList();
        default:
            return null;
        }
    }

    private void walkFormat3(HiddenChannelListener listener, String text) throws IOException {
        CharStream textInput = new ANTLRInputStream(text);
        StartFormat3Lexer startFormat3Lexer = new StartFormat3Lexer(textInput);
        CommonTokenStream startFormat3Tokens = new CommonTokenStream(startFormat3Lexer);
        StartFormat3Parser startFormat3Parser = new StartFormat3Parser(startFormat3Tokens);
        ParseTree startFormat3Tree = startFormat3Parser.start_format3();
        ParseTreeWalker walker = new ParseTreeWalker();
        listener.setCommonTokenStream(startFormat3Tokens);
        walker.walk(listener, startFormat3Tree);
    }



    private void walkFormat15(HiddenChannelListener listener, String text) throws IOException {
        CharStream textInput = new ANTLRInputStream(text);
        StartFormat15Lexer startFormat15Lexer = new StartFormat15Lexer(textInput);
        CommonTokenStream startFormat15Tokens = new CommonTokenStream(startFormat15Lexer);
        StartFormat15Parser startFormat15Parser = new StartFormat15Parser(startFormat15Tokens);
        ParseTree startFormat15Tree = startFormat15Parser.start_format15();
        ParseTreeWalker walker = new ParseTreeWalker();
        listener.setCommonTokenStream(startFormat15Tokens);
        walker.walk(listener, startFormat15Tree);
    }




}
