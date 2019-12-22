package dd.union.perception.notification.recognition.processing;

import dd.union.perception.notification.model.FieldEntity;
import dd.union.perception.notification.model.NotificationEntity;
import dd.union.perception.notification.model.NotificationTree;
import dd.union.perception.notification.model.constants.Emptyness;
import dd.union.perception.notification.model.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Stack;

/**
 * Прототип сервиса выгрузки уведомления из базы
 * <p/>
 * Created by slebedev on 04.07.2017.
 */
public class Entity2TreeTranslator {

    private static Logger logger = LoggerFactory.getLogger(Entity2TreeTranslator.class);


    public NotificationTree restore(NotificationEntity notificationEntity) {
        List<FieldEntity> fields = notificationEntity.getFields();
        return restoreNotificationTree(fields);
    }

    private NotificationTree restoreNotificationTree(List<FieldEntity> fields) {
        try {
            NotificationTree notificationTree = new NotificationTree();
            AbstractTypeList<IType> list = new AbstractTypeList<>();
            for (FieldEntity field : fields) {

                if (field.getPath().split("_").length > 1) {
                    Class c = Class.forName(field.getType());
                    Constructor<IType> constructor;
                    if (c.equals(AbstractTypeList.class)) {
                        constructor = c.getConstructor(String.class);
                        list.add(constructor.newInstance(field.getPath()));
                    } else {
                        if (field.getValEn().equals(Emptyness.NOT_APPLICABLE)) {
                            constructor = c.getConstructor(String.class, String.class, Boolean.TYPE);
                            list.add(constructor.newInstance(field.getPath(), field.getValEn(), true));
                        } else {
                            constructor = c.getConstructor(String.class, String.class);
                            list.add(constructor.newInstance(field.getPath(), field.getValEn()));
                        }
                    }
                    continue;
                }

                switch (field.getPath()) {
                    case "1":
                        notificationTree.setIdentNumber(new IdentNumberType(field.getPath(), field.getValEn()));
                        break;
                    case "2":
                        if (field.getValEn().equals(Emptyness.NONE)) {
                            notificationTree.setReferences(new StringType(field.getPath(), field.getValEn()));
                        } else {
                            notificationTree.setReferences(new ReferenceType(field.getPath(), field.getValEn()));
                        }
                        break;
                    case "3":
                        notificationTree.setContent(new AbstractTypeList<>(field.getPath()));
                        break;
                    case "4":
                        notificationTree.setRemarks(new StringType(field.getPath(), field.getValEn()));
                        break;
                    case "5":
                        notificationTree.setEnd(new IdentNumberType(field.getPath(), field.getValEn()));
                        break;
                    default:
                        throw new NoSuchFieldException("Неизвестное поле " + field.getPath());
                }
            }
            composeNotificationTree(notificationTree, list);
            return notificationTree;
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        } catch (InstantiationException e) {
            logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private void composeNotificationTree(NotificationTree notificationTree, AbstractTypeList<IType> list) {
        list.sort();
        Stack<IType> parent = new Stack<>();
        for (IType type : list) {
            int currentDeep = type.getPath().split("_").length;
            while (true) {
                if (parent.isEmpty()) {
                    notificationTree.getContent().add(type);
                    parent.push(type);
                    break;
                }
                int previousDeep = parent.peek().getPath().split("_").length;
                if (currentDeep > previousDeep) {
                    ((AbstractTypeList<IType>) parent.peek()).add(type);
                    parent.push(type);
                    break;
                } else if (currentDeep == previousDeep) {
                    parent.pop();
                    if (parent.empty()) {
                        continue;
                    }
                    ((AbstractTypeList<IType>) parent.peek()).add(type);
                    parent.push(type);
                    break;
                } else {
                    parent.pop();
                }
            }

        }
    }

}
