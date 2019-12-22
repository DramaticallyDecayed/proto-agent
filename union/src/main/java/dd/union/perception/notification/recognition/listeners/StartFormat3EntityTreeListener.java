package dd.union.perception.notification.recognition.listeners;

import dd.union.perception.notification.model.NotificationEntity;
import dd.union.perception.notification.model.constants.Emptyness;
import dd.union.perception.notification.model.types.*;
import dd.union.perception.notification.recognition.rules.StartFormat3BaseListener;
import dd.union.perception.notification.recognition.rules.StartFormat3Lexer;
import dd.union.perception.notification.recognition.rules.StartFormat3Parser;
import dd.union.sas.objectproperty.HasNfField;
import dd.union.sas.worldentity.NfField;
import dd.union.sas.worldentity.NfFieldC;
import dd.union.sas.worldentity.Notification;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Распознает уведомление по формату 3. На выходе объект базы данных.
 * <p/>
 * Помогает уменшить число переходов между различными форматами представления уведомлений
 * <p/>
 * Created by slebedev on 20.07.2017.
 */
public class StartFormat3EntityTreeListener extends StartFormat3BaseListener implements HiddenChannelListener {

    private CommonTokenStream commonTokenStream;
    private Stack<String> bulletStack = new Stack<>();

    private String text;
    private String path;
    private boolean submarine;

    private Notification notification;

    private List<HasNfField> relations = new ArrayList<>();

    public StartFormat3EntityTreeListener(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void enterBullet(StartFormat3Parser.BulletContext ctx) {
        bulletStack.push(ctx.getText());
    }

    @Override
    public void enterDeployed_bullet(StartFormat3Parser.Deployed_bulletContext ctx) {
        bulletStack.push(ctx.getText().replace(" DEPLOYED", ""));
        createHasField4AbstractList(ListenerUtils.normilizeString(bulletStack.toString()));
    }

    @Override
    public void enterNon_deployed_bullet(StartFormat3Parser.Non_deployed_bulletContext ctx) {
        bulletStack.push(ctx.getText().replace(" NON-DEPLOYED", ""));
        createHasField4AbstractList(ListenerUtils.normilizeString(bulletStack.toString()));

    }

    @Override
    public void exitText(StartFormat3Parser.TextContext ctx) {
        if (!bulletStack.empty()) {
            text = ListenerUtils.text(commonTokenStream, ctx, StartFormat3Lexer.HIDDEN);
            path = ListenerUtils.normilizeString(bulletStack.toString());
            bulletStack.pop();
        }
    }

    @Override
    public void enterContent(StartFormat3Parser.ContentContext ctx) {
        createHasField4AbstractList(ListenerUtils.normilizeString(bulletStack.toString()));
    }

    @Override
    public void enterLocation_description(StartFormat3Parser.Location_descriptionContext ctx) {
        createHasField4AbstractList(ListenerUtils.normilizeString(bulletStack.toString()));
    }

    @Override
    public void exitData_changed_bullet(StartFormat3Parser.Data_changed_bulletContext ctx) {
        createHasField4AbstractList(ListenerUtils.normilizeString(bulletStack.toString()));
    }

    @Override
    public void exitPlace(StartFormat3Parser.PlaceContext ctx) {
        createHasField(path, text, LocationType.class);
    }

    @Override
    public void exitIdent_item(StartFormat3Parser.Ident_itemContext ctx) {
        createHasField(path, text, IdentNumberType.class);
    }

    @Override
    public void exitIdent_reference(StartFormat3Parser.Ident_referenceContext ctx) {
        if (!text.equals(Emptyness.NONE)) {
            createHasField(path, text, ReferenceType.class);
        } else {
            createHasField(path, text, VocabularyStringType.class, true);
        }
    }

    @Override
    public void exitEnd_item(StartFormat3Parser.End_itemContext ctx) {
        createHasField(path, text, IdentNumberType.class);
    }

    @Override
    public void exitSubmarine(StartFormat3Parser.SubmarineContext ctx) {
        if (!text.equals(Emptyness.NOT_APPLICABLE)) {
            createHasField(path, text, StringType.class);
            submarine = true;
        } else {
            createHasField(path, text, VocabularyStringType.class, true);
        }
    }

    @Override
    public void exitLauncher(StartFormat3Parser.LauncherContext ctx) {
        if (!text.equals(Emptyness.NOT_APPLICABLE)) {
            if (!submarine) {
                createHasField(path, text, LocationType.class);
            } else {
                createHasField(path, text, NumberCollectionType.class);
            }
        } else {
            createHasField(path, text, VocabularyStringType.class, true);
        }
    }

    @Override
    public void exitIdentifier_unit(StartFormat3Parser.Identifier_unitContext ctx) {
        if (!text.equals(Emptyness.NOT_APPLICABLE)) {
            createHasField(path, text, StringType.class);
        } else {
            createHasField(path, text, VocabularyStringType.class, true);
        }
    }

    @Override
    public void exitUnit_description(StartFormat3Parser.Unit_descriptionContext ctx) {
        createHasField(path, text, StringType.class);
    }

    @Override
    public void exitRemarks_item(StartFormat3Parser.Remarks_itemContext ctx) {
        if (!text.equals(Emptyness.NONE)) {
            createHasField(path, text, StringType.class);
        } else {
            createHasField(path, text, VocabularyStringType.class, true);
        }
    }

    @Override
    public void exitFacility_description(StartFormat3Parser.Facility_descriptionContext ctx) {
        if (!text.equals(Emptyness.NOT_APPLICABLE)) {
            createHasField(path, text, LocationType.class);
        } else {
            createHasField(path, text, VocabularyStringType.class, true);
        }
    }

    @Override
    public void exitDate_change(StartFormat3Parser.Date_changeContext ctx) {
        createHasField(path, text, DateType.class);
    }

    @Override
    public void exitData_changed(StartFormat3Parser.Data_changedContext ctx) {
        if (!bulletStack.empty()) {
            bulletStack.pop();
        }
    }

    @Override
    public void exitDeployed(StartFormat3Parser.DeployedContext ctx) {
        if (!bulletStack.empty()) {
            bulletStack.pop();
        }
    }

    @Override
    public void exitNon_deployed(StartFormat3Parser.Non_deployedContext ctx) {
        if (!bulletStack.empty()) {
            bulletStack.pop();
        }
    }

    @Override
    public void exitLocation(StartFormat3Parser.LocationContext ctx) {
        if (!bulletStack.empty()) {
            bulletStack.pop();
        }
    }

    @Override
    public void exitUnique_identifier(StartFormat3Parser.Unique_identifierContext ctx) {
        createHasField4AbstractList(ListenerUtils.normilizeString(bulletStack.toString()));
        if (!bulletStack.empty()) {
            bulletStack.pop();
        }
    }

    @Override
    public void exitChange_description(StartFormat3Parser.Change_descriptionContext ctx) {
        createHasField(path, text, NumberCollectionType.class);
    }

    @Override
    public void exitContent(StartFormat3Parser.ContentContext ctx) {
        if (!bulletStack.empty()) {
            bulletStack.pop();
        }
    }

    @Override
    public void setCommonTokenStream(CommonTokenStream commonTokenStream) {
        this.commonTokenStream = commonTokenStream;
    }

    @Override
    public List<HasNfField> getHasNfFieldsList() {
        return relations;
    }

    private void createHasField(String path, String text, Class type, boolean isEmpty) {
        HasNfField hasNfField = new HasNfField();
        hasNfField.setDomain(notification);
        hasNfField.setRange(createNfField(path, text, type, isEmpty));
        relations.add(hasNfField);
    }

    private void createHasField(String path, String text, Class type) {
        HasNfField hasNfField = new HasNfField();
        hasNfField.setDomain(notification);
        hasNfField.setRange(createNfField(path, text, type));
        relations.add(hasNfField);
    }

    private void createHasField4AbstractList(String path) {
        HasNfField hasNfField = new HasNfField();
        hasNfField.setDomain(notification);
        hasNfField.setRange(createNfField4AbstractList(path));
        relations.add(hasNfField);
    }

    private NfField createNfField(String path, String text, Class type, boolean isEmpty) {
        NfField nfField = createNfField(path, text, type);
        nfField.setIsEmpty(isEmpty);
        return nfField;
    }

    private NfField createNfField(String path, String text, Class type) {
        NfField nfField = new NfFieldC();
        nfField.setNffield_path(path);
        nfField.setNffield_text(text);
        nfField.setNffield_type(type.getName());
        return nfField;
    }

    private NfField createNfField4AbstractList(String path) {
        NfField nfField = new NfFieldC();
        nfField.setNffield_path(path);
        nfField.setNffield_text("");
        nfField.setNffield_type(AbstractTypeList.class.getName());
        return nfField;
    }
}
