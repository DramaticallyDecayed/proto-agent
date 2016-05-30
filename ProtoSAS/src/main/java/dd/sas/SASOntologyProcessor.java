package dd.sas;

import dd.ontologyinterchanger.QueryExecuter;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.sas.owlinterplay.QueryFabric;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Sergey on 30.05.2016.
 */
public class SASOntologyProcessor extends SASProcessor {

    public final static String PACKAGE_PREFIX = "dd.soccer.sas";
    private final static String NODE_PACKAGE = PACKAGE_PREFIX + "." + "computation.node";

    private QueryExecuter queryExecuter;

    public SASOntologyProcessor(LevelHolder levelHolder, QueryExecuter queryExecuter) {
        super(levelHolder);
        this.queryExecuter = queryExecuter;
    }

    @Override
    public void process() {
        queryExecuter.runInference();
        addNewLevels(getLevelHolder());
    }

    private void addNewLevels(LevelHolder levelHolder) {
        SelectQueryHolder sqh = (SelectQueryHolder) queryExecuter
                .executeQueryOnInference(QueryFabric.collectLevelNumbers());
        sqh.getDisk("n")
                .stream()
                .map(num -> new Level((Integer) num))
                .map(level -> addNodes((Level) level))
                .forEach(level -> levelHolder.addLevel((Level) level));
    }

    private Level addNodes(Level level) {
        SelectQueryHolder sqh = (SelectQueryHolder) queryExecuter
                .executeQueryOnInference(QueryFabric.collectNodes4Level(level.getName()));
        sqh.getDisk("node")
                .stream()
                .map(name -> createNodeInstance(level, (String) name))
                .forEach(node -> level.addNodeToBeProcessed((Node) node));
        return level;
    }

    private Node createNodeInstance(Level level, String name) {
        try {
            Class nodeClass = getClass().getClassLoader().loadClass(nodeClassName(name));
            Node node = (Node) nodeClass.getConstructor(new Class[]{Level.class}).newInstance(level);
            return node;
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String nodeClassName(String name) {
        name = makeFirsLetterUp(name);
        return NODE_PACKAGE + "." + name;
    }

    public String makeFirsLetterUp(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
