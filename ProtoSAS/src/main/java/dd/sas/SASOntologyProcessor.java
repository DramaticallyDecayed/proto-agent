package dd.sas;

import dd.ontologyinterchanger.QueryExecuter;
import dd.ontologyinterchanger.SelectQueryHolder;
import dd.sas.computation.Level;
import dd.sas.computation.Node;
import dd.sas.owlinterplay.QueryFabric;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;
import java.util.List;
/**
 * Created by Sergey on 30.05.2016.
 */
public class SASOntologyProcessor extends SASProcessor {

    public final static String PACKAGE_PREFIX = "dd.soccer.sas";
    private final static String NODE_CUSTOM_IMPL_PACKAGE = PACKAGE_PREFIX + "." + "nodeimplementation";
    private final static String NODE_DEFAULG_IMPL_PACKAGE = PACKAGE_PREFIX + "." + "computation.node";

    private QueryExecuter queryExecuter;

    public SASOntologyProcessor(LevelHolder levelHolder, QueryExecuter queryExecuter) {
        super(levelHolder);
        this.queryExecuter = queryExecuter;
    }

    @Override
    public void process() {
        System.out.println("--------------Ontology cycle---------------");
        queryExecuter.runInference();
        addNewLevels(getLevelHolder());
        addNewNodes();
        queryExecuter.commitResults();
    }

    private void addNewLevels(LevelHolder levelHolder) {
        SelectQueryHolder sqh = (SelectQueryHolder) queryExecuter
                .executeQueryOnInference(QueryFabric.collectLevelNumbers());
        if (!sqh.isEmpty()) {
            sqh.getDisk("n")
                    .stream()
                    .map(num -> new Level((Integer) num))
                    .map(level -> addLevel2Holder((Level) level))
                    .forEach(level -> levelHolder.addLevel((Level) level));
        }
    }

    private Level addLevel2Holder(Level level) {
        getLevelHolder().addLevel(level);
        return level;
    }

    private void addNewNodes() {
        SelectQueryHolder sqh = (SelectQueryHolder) queryExecuter
                .executeQueryOnInference(QueryFabric.collectNodesWithLevel());
        if (!sqh.isEmpty()) {
            sqh.asStream()
                    .map(result -> createNodeInstance((String) result[0], (String) result[1]))
                    .map(node -> subscribeNode((Node) node))
                    .collect(Collectors.toList());
        }
    }

    private Node subscribeNode(Node node) {
        SelectQueryHolder sqh = (SelectQueryHolder) queryExecuter
                .executeQueryOnInference(QueryFabric.collectNodeDonors(node.name()));
        if (sqh.isEmpty()) {
            Level zero = getLevelHolder().getLevel(0);
            Node adapter = zero.retrieveNode("perceptor2SASAdapter");
            adapter.subscribe(node);
            Class c = adapter.getClass().getSuperclass();
            node.setDonor(adapter, c);
        } else {
            for(String donorName : (List<String>)sqh.getDisk("donor")){
                subscribeNode(node, donorName);
            }
        }
        return node;
    }

    private void subscribeNode(Node node, String donorName){
        SelectQueryHolder sqh = (SelectQueryHolder) queryExecuter
                .executeQuery(QueryFabric.findNodeLevelNumber(donorName));
        Integer levelNum = (Integer)sqh.getTheFirstResult();
        Node donor = getLevelHolder().getLevel(levelNum).retrieveNode(donorName);
        donor.subscribe(node);
        node.setDonor(donor, donor.getClass().getSuperclass());
    }

    private Node addNode2Level(Node node, Level level) {
        level.addNode(node);
        return node;
    }

    private Node createNodeInstance(String levelName, String nodeName) {
        Integer levelNum = Integer.valueOf(levelName.substring(levelName.indexOf("_") + 1));
        Level level = getLevelHolder().getLevel(levelNum);
        Class nodeClass;
        try {
            nodeClass = getClass().getClassLoader().loadClass(customNodeImplClassName(nodeName));
        } catch (ClassNotFoundException e) {
            try {
                nodeClass = getClass().getClassLoader().loadClass(defaultNodeImplClassName(nodeName));
            } catch (ClassNotFoundException t) {
                t.printStackTrace();
                return null;
            }
        }

        try {
            Node node = (Node) nodeClass.getConstructor(new Class[]{Level.class}).newInstance(level);
            addNode2Level(node, level);
            System.out.println(node.name());
            return node;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String customNodeImplClassName(String name) {
        name = makeFirsLetterUp(name);
        return NODE_CUSTOM_IMPL_PACKAGE + "." + name;
    }

    public String defaultNodeImplClassName(String name) {
        name = makeFirsLetterUp(name);
        return NODE_DEFAULG_IMPL_PACKAGE + "." + name;
    }

    public String makeFirsLetterUp(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
