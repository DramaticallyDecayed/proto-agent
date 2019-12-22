package dd.union.perception.notification.recognition.listeners;

import dd.union.perception.notification.model.FieldEntity;
import dd.union.perception.notification.model.NotificationEntity;
import dd.union.perception.notification.model.types.IType;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slebedev on 13.06.2017.
 */
public final class ListenerUtils {

    private ListenerUtils() {
    }

    private static Pattern bulletPattern = Pattern.compile("\\w+");


    public static String text(CommonTokenStream startFormat3Tokens, ParserRuleContext ctx, int channel) {
        Token semi = ctx.getStop();
        int i = semi.getTokenIndex();
        List<Token> cmtChannel = startFormat3Tokens.getHiddenTokensToRight(i, channel);
        StringBuilder sb = new StringBuilder();
        if (cmtChannel != null) {
            for (Token token : cmtChannel) {
                if (token != null) {
                    sb.append(token.getText());
                }
            }
        }
        return sb.toString().trim();
    }


    public static String normilizeString(String bulletString) {
        Matcher matcher = bulletPattern.matcher(bulletString);
        StringBuilder bullet = null;
        while (matcher.find()) {
            if (bullet == null) {
                bullet = new StringBuilder();
                bullet.append(matcher.group());
            } else {
                bullet.append("_" + matcher.group());
            }
        }
        return bullet.toString();
    }

    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static FieldEntity transform2FieldEntity(NotificationEntity notificationEntity, IType type) {
        FieldEntity fieldEntity = new FieldEntity(notificationEntity);
        fieldEntity.setPath(type.getPath());
        fieldEntity.setValEn(type.getValue());
        fieldEntity.setValRu(type.translate());
        fieldEntity.setType(type.getClass().getName());
        notificationEntity.getFields().add(fieldEntity);
        return fieldEntity;
    }

}
