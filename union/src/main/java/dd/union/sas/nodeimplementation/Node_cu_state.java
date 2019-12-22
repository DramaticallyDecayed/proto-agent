package dd.union.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.sas.pipeline.worldmodel.Relation;
import dd.union.ontology.DataStoreService;
import dd.union.perception.notification.model.types.DateType;
import dd.union.perception.notification.model.types.NumberCollectionType;
import dd.union.sas.objectproperty.FormStatement;
import dd.union.sas.objectproperty.State;
import dd.union.sas.worldentity.NF3Statement;
import dd.union.sas.worldentity.NfField;
import dd.union.sas.worldentity.Notification;
import dd.union.sas.worldentity.Statement;
import implementation.Node_cu_state_processing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Node_cu_state extends dd.union.sas.computation.node.Node_cu_state {

    private static Logger logger = LoggerFactory.getLogger(Node_cu_state.class);

    public Node_cu_state(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (getHasNfFieldList() != null && !getHasNfFieldList().isEmpty()) {
            Map<Notification, Map<NF3Statement, List<NfField>>> map
                    = Node_cu_state_processing.customProcess(getHasNfFieldList());
            map.forEach(
                    (x, y) -> {
                        getStateList().addAll(createStates(x, y.keySet()));
                        y.forEach(
                                (m, n) -> getFormStatementList().addAll(createFormStates(m, n))
                        );
                        saveState();
                        getNF3StatementList().addAll(y.keySet());
                    }
            );


            return CalculationResult.POSITIVE;
        }
        return CalculationResult.UNKNOWN;
    }

    private List<FormStatement> createFormStates(NF3Statement statement, List<NfField> fields) {
        List<FormStatement> result = new ArrayList<>();
        for (NfField field : fields) {
            FormStatement formStatement = new FormStatement();
            formStatement.setDomain(field);
            formStatement.setRange(statement);
            result.add(formStatement);
        }
        initStatements(result);
        return result;
    }

    private void initStatements(List<FormStatement> result) {
        result.stream().forEach(x -> initStatement(x.getRange(), x.getDomain()));
    }

    private void initStatement(NF3Statement range, NfField domain) {

        try {
            if (domain.getNffield_path().contains(range.getStatementPath() + "_4")) {
                NumberCollectionType pair = (NumberCollectionType) NodeUtils.getIType(domain);
                range.setChange(pair.getTypedValue().get(0));
                range.setTotal(pair.getTypedValue().get(1));
            } else if (domain.getNffield_path().contains(range.getStatementPath() + "_6")) {
                DateType date = (DateType) NodeUtils.getIType(domain);
                range.setDate(date.getTypedValue());
            } else if (domain.getNffield_path().contains(range.getStatementPath() + "_3_A_1")) {
                if (domain.getNffield_text().equals("NOT APPLICABLE")) {
                    range.setDeployedID(false);
                } else {
                    range.setDeployedID(true);
                }
            } else if (domain.getNffield_path().contains(range.getStatementPath() + "_3_B_1")) {
                if (domain.getNffield_text().equals("NOT APPLICABLE")) {
                    range.setNon_deployedID(false);
                } else {
                    range.setNon_deployedID(true);
                }
            } else if (domain.getNffield_path().contains(range.getStatementPath() + "_1")) {
                if (domain.getNffield_text().contains("DEPLOYED")) {
                    range.setDeployedType(true);
                } else {
                    range.setDeployedType(false);
                }
                if (domain.getNffield_text().contains("NON-DEPLOYED")) {
                    range.setNon_deployedType(true);
                } else {
                    range.setNon_deployedType(false);
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            logger.error("Error while initializing nf3Statement:", e);
        }

    }

    private List<State> createStates(Notification x, Set<NF3Statement> values) {
        List<State> result = new ArrayList<>();
        for (NF3Statement statement : values) {
            State state = new State();
            state.setDomain(x);
            state.setRange(statement);
            result.add(state);
        }
        return result;
    }

    private void saveState() {
        try {
            for (State state : getStateList()) {
                DataStoreService.INSTANCE.dataStoreWriter.write(state.getRange());
                DataStoreService.INSTANCE.dataStoreWriter.write((Relation) state);
            }
        } catch (Exception e) {
            logger.error("Error while saving nf3Statement", e);
        }
    }


    private void fillMap(Map<Statement, NfField> statementNfFieldMap) {

    }
}
