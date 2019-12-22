package dd.union.perception.notification.recognition.listeners;

import dd.union.perception.notification.model.NotificationEntity;
import dd.union.perception.notification.model.constants.Emptyness;
import dd.union.perception.notification.model.types.*;
import dd.union.perception.notification.recognition.rules.StartFormat15BaseListener;
import dd.union.perception.notification.recognition.rules.StartFormat15Parser;
import dd.union.perception.notification.recognition.rules.StartFormat3Lexer;
import dd.union.sas.objectproperty.HasNfField;
import dd.union.sas.worldentity.Notification;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.List;
import java.util.Stack;

/**
 * Распознает уведомление по формату 15. На выходе объект базы данных.
 * <p/>
 * Помогает уменшить число переходов между различными форматами представления уведомлений
 * <p/>
 * Created by slebedev on 20.07.2017.
 */
public class StartFormat15EntityListener extends StartFormat15BaseListener implements HiddenChannelListener {

    private CommonTokenStream commonTokenStream;
    private Stack<String> bulletStack = new Stack<>();

    private String text;
    private String path;

    private Notification notification;
    private NotificationEntity notificationEntity = new NotificationEntity();

    public StartFormat15EntityListener(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void enterBullet(StartFormat15Parser.BulletContext ctx) {
        bulletStack.push(ctx.getText());
    }

    @Override
    public void enterContent(StartFormat15Parser.ContentContext ctx) {
        ListenerUtils.transform2FieldEntity(
                notificationEntity, new AbstractTypeList<>(ListenerUtils.normilizeString(bulletStack.toString()))
        );
    }

    @Override
    public void exitText(StartFormat15Parser.TextContext ctx) {
        if (!bulletStack.empty()) {
            text = ListenerUtils.text(commonTokenStream, ctx, StartFormat3Lexer.HIDDEN);
            path = ListenerUtils.normilizeString(bulletStack.toString());
        }
    }

    @Override
    public void exitConvex_polyhedron(StartFormat15Parser.Convex_polyhedronContext ctx) {
        ListenerUtils.transform2FieldEntity(notificationEntity, new PointType(path, text));
    }

    @Override
    public void exitCircle_center(StartFormat15Parser.Circle_centerContext ctx) {
        ListenerUtils.transform2FieldEntity(notificationEntity, new PointType(path, text));
    }

    @Override
    public void exitCircle_radius(StartFormat15Parser.Circle_radiusContext ctx) {
        ListenerUtils.transform2FieldEntity(notificationEntity, new NumberType(path, text));
    }

    @Override
    public void exitPlanned_date(StartFormat15Parser.Planned_dateContext ctx) {
        ListenerUtils.transform2FieldEntity(notificationEntity, new DateType(path, text));
    }

    @Override
    public void exitLaunch_area(StartFormat15Parser.Launch_areaContext ctx) {
        ListenerUtils.transform2FieldEntity(notificationEntity, new VocabularyStringType(path, text));
    }

    @Override
    public void exitSingle_or_multiple(StartFormat15Parser.Single_or_multipleContext ctx) {
        ListenerUtils.transform2FieldEntity(notificationEntity, new VocabularyStringType(path, text));
    }

    @Override
    public void exitImpact_area_reentry(StartFormat15Parser.Impact_area_reentryContext ctx) {
        ListenerUtils.transform2FieldEntity(notificationEntity, new AbstractTypeList<>(path));
        ListenerUtils.transform2FieldEntity(notificationEntity, new VocabularyStringType(path + "_0", text));
    }

    @Override
    public void exitIdent_reference(StartFormat15Parser.Ident_referenceContext ctx) {
        if (!text.equals(Emptyness.NONE)) {
            ListenerUtils.transform2FieldEntity(notificationEntity, new ReferenceType(path, text));
        } else {
            ListenerUtils.transform2FieldEntity(notificationEntity, new VocabularyStringType(path, text, true));
        }
    }

    @Override
    public void exitIdent_item(StartFormat15Parser.Ident_itemContext ctx) {
        IdentNumberType identNumberType = new IdentNumberType(path, text);
        ListenerUtils.transform2FieldEntity(notificationEntity, identNumberType);
        notificationEntity.setNumber(identNumberType.getFullNumber());
    }

    @Override
    public void exitEnd_item(StartFormat15Parser.End_itemContext ctx) {
        ListenerUtils.transform2FieldEntity(notificationEntity, new IdentNumberType(path, text));
    }

    @Override
    public void exitMain_item_element(StartFormat15Parser.Main_item_elementContext ctx) {
        if (!bulletStack.empty()) {
            bulletStack.pop();
        }
    }

    @Override
    public void exitContent_item(StartFormat15Parser.Content_itemContext ctx) {
        if (!bulletStack.empty()) {
            bulletStack.pop();
        }
    }

    @Override
    public void exitImpact_area_items(StartFormat15Parser.Impact_area_itemsContext ctx) {
        if (!bulletStack.empty()) {
            bulletStack.pop();
        }
    }

    @Override
    public void enterFrequences(StartFormat15Parser.FrequencesContext ctx) {
        ListenerUtils.transform2FieldEntity(
                notificationEntity, new AbstractTypeList<>(ListenerUtils.normilizeString(bulletStack.toString()))
        );
    }

    @Override
    public void exitTable_line(StartFormat15Parser.Table_lineContext ctx) {
        if (!bulletStack.empty()) {
            bulletStack.pop();
        }
        ListenerUtils.transform2FieldEntity(notificationEntity, new StringNumberPair(path, text));
    }

    @Override
    public void exitRemarks_item(StartFormat15Parser.Remarks_itemContext ctx) {
        if (!text.equals(Emptyness.NONE)) {
            ListenerUtils.transform2FieldEntity(notificationEntity, new StringType(path, text));
        } else {
            ListenerUtils.transform2FieldEntity(notificationEntity, new VocabularyStringType(path, text, true));
        }
    }

    @Override
    public void setCommonTokenStream(CommonTokenStream commonTokenStream) {
        this.commonTokenStream = commonTokenStream;
    }

    @Override
    public List<HasNfField> getHasNfFieldsList() {
        return null;
    }
}
