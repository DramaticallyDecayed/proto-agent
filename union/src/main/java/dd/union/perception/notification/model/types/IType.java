package dd.union.perception.notification.model.types;

/**
 * Обобщенный интерфейс для типов, используемых для представления основного содержимого уведомления по формату 3
 *
 * Created by slebedev on 06.07.2017.
 */
public interface IType {
    String getPath();

    boolean isEmpty();

    String translate();

    String getValue();

    IType buildIn(AbstractTypeList<IType> list);
}
