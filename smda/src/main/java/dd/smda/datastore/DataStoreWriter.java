package dd.smda.datastore;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Created by Sergey on 21.02.2017.
 */
public class DataStoreWriter {
    private StringBuilder sb = new StringBuilder();
    private DataStoreAdapter dataStoreAdapter = new DataStoreAdapter();

    public void startWrite() {
        dataStoreAdapter.open();
        sb = new StringBuilder();
        sb.append(Class2String.updatePrefix());
    }

    public void continueWrite(Object o, String id) throws IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException {
        sb.append(Class2String.updateBody4Class(o, id));
    }

    public void continueWrite(Object o, String id1, String id2) throws IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException {
        sb.append(Class2String.updateBody4Property(o, id1, id2));
    }

    public void endWrite() throws Exception {
        sb.append(Class2String.updatePostfix());
        dataStoreAdapter.update(sb.toString());
        dataStoreAdapter.close();
    }

    public DataStoreAdapter getDataStoreAdapter(){
        return dataStoreAdapter;
    }
}
