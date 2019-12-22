package dd.union.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.pipeline.worldmodel.Relation;
import dd.union.drools.DroolAdapter;
import dd.union.graphstate.FinalState;
import dd.union.graphstate.GraphState;
import dd.union.graphstate.InitialState;
import dd.union.ontology.DataStoreService;
import dd.union.sas.objectproperty.*;
import dd.union.sas.worldentity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;
import java.util.stream.Collectors;

public class Node_cu_FullStatement extends dd.union.sas.computation.node.Node_cu_FullStatement {

    private static Logger logger = LoggerFactory.getLogger(Node_cu_FullStatement.class);

    public Node_cu_FullStatement(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (checkList(getInformAboutControlObjectList()) &&
                checkList(getInformAboutLocationList())) {
            try {
                insert(getInformAboutControlObjectList());
                insert(getInformAboutLocationList());
                insertLocationChain();
                insertDispose();
                insertGraphStateHolders();
                System.out.println("-----------------BEFORE FIRE--------------------");
                DroolAdapter.INSTANCE.droolsSystem.fire();
                System.out.println("\n-----------------AFTER FIRE--------------------");


                commitFullStatement();
                commitUserStatements();
            } catch (Exception e) {
                logger.error("Error while full statement construction", e);
                return CalculationResult.UNKNOWN;
            }
            return CalculationResult.POSITIVE;
        }
        return CalculationResult.UNKNOWN;
    }

    private void commitUserStatements() throws Exception {
        state(StateMoveFrom.class);
        state(StateMoveTo.class);
        state(StateItIsMoved.class);
        state(StateDeployed.class);
    }

    private void commitFullStatement() throws Exception {
        List<Relation> initials = extractRelations(InitialState.class);
        List<Relation> finals = extractRelations(FinalState.class);
        for (Relation relation : initials) {
            if (DataStoreService.INSTANCE.dataStoreQuerier.findRelation(relation)[0].isEmpty()) {
                throw new Exception("Have no initial relation: "
                        + relation.getClass().getSimpleName() + "("
                        + ((DeploymentObject)relation.getDomain()).getName()
                        + "; "
                        + ((ControlObject)relation.getRange()).getName()
                        + ")");
            }
        }

        for (Relation relation : initials) {
            DataStoreService.INSTANCE.dataStoreWriter.delete(relation);
        }
        for (Relation relation : finals) {
            DataStoreService.INSTANCE.dataStoreWriter.write(relation);
        }
    }

    private <T extends Relation> void state(Class<T> c) throws Exception {
        List<Relation> relations =
                new ArrayList<>(DroolAdapter.INSTANCE.droolsSystem.getFacts(c));
        for (Relation relation : relations) {
            DataStoreService.INSTANCE.dataStoreWriter.writeFullRelation(relation);
            getFullStatementList().add((FullStatement) relation.getDomain());
        }
    }

    private <T extends GraphState> List<Relation> extractRelations(Class<T> c) {
        return new ArrayList<>(DroolAdapter.INSTANCE.droolsSystem.getFacts(c)).get(0).getRelations();
    }

    private void insertGraphStateHolders() {
        DroolAdapter.INSTANCE.droolsSystem.insert(new InitialState());
        DroolAdapter.INSTANCE.droolsSystem.insert(new FinalState());
    }

    private void insertDispose() {
        List<ControlObject> list = getInformAboutControlObjectList()
                .stream()
                .map(x -> x.getRange())
                .map(Wrapper::new)
                .distinct()
                .map(Wrapper::unwrap)
                .collect(Collectors.toList());


    }

    private void insertLocationChain() throws Exception {
        Stack<Location> locations = new Stack<>();
        List<Location> list = getInformAboutLocationList()
                .stream()
                .map(x -> x.getRange())
                .map(Wrapper::new)
                .distinct()
                .map(Wrapper::unwrap)
                .collect(Collectors.toList());

        for (Location location : list) {
            while (locations.size() >= 1) {
                Belong belong = new Belong();
                belong.setDomain((DeploymentObject) location);
                belong.setRange((DeploymentObject) locations.peek());
                if (DataStoreService.INSTANCE.dataStoreQuerier.findRelation(belong)[0].size() != 0) {
                    DroolAdapter.INSTANCE.droolsSystem.insert(belong);
                    break;
                }
                locations.pop();
            }
            locations.push(location);
        }
    }


    private void insert(List<? extends Relation<NF3Statement, ?>> list) {
        for (Relation<NF3Statement, ?> relation : list) {
            DroolAdapter.INSTANCE.droolsSystem.insert(relation.getDomain());
            DroolAdapter.INSTANCE.droolsSystem.insert(relation);
        }
    }

    private boolean checkList(List list) {
        return list != null && !list.isEmpty();
    }


    private class Wrapper<T extends UnionHLConcept> {
        private final T unionHLConcept;

        public Wrapper(T unionHLConcept) {
            this.unionHLConcept = unionHLConcept;
        }

        public T unwrap() {
            return unionHLConcept;
        }

        public boolean equals(Object other) {
            if (other instanceof Wrapper) {
                return ((Wrapper) other).unionHLConcept.getUri().equals(unionHLConcept.getUri());
            } else {
                return false;
            }
        }

        public int hashCode() {
            return unionHLConcept.getUri().hashCode();
        }

    }
}
