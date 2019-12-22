package dd.union.perception.notification.recognition.listeners;

import dd.union.sas.objectproperty.HasNfField;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.List;

/**
 * Итерфейс для классов, распознающих уведомление
 * <p>
 * Created by slebedev on 13.06.2017.
 */
public interface HiddenChannelListener extends ParseTreeListener {
    void setCommonTokenStream(CommonTokenStream commonTokenStream);
    List<HasNfField> getHasNfFieldsList();
}
