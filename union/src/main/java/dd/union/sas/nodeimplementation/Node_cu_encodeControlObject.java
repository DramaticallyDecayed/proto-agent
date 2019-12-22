package dd.union.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.union.ontology.DataStoreService;
import dd.union.perception.notification.model.constants.Emptyness;
import dd.union.perception.notification.model.types.StringType;
import dd.union.sas.objectproperty.EncodeControlObject;
import dd.union.sas.objectproperty.InformAboutControlObject;
import dd.union.sas.worldentity.ControlObject;
import dd.union.sas.worldentity.ControlObjectC;
import dd.union.sas.worldentity.NF3Statement;
import dd.union.sas.worldentity.NfField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Node_cu_encodeControlObject extends dd.union.sas.computation.node.Node_cu_encodeControlObject {

    private static Logger logger = LoggerFactory.getLogger(Node_cu_encodeControlObject.class);

    public Node_cu_encodeControlObject(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (getFormStatementList() != null && !getFormStatementList().isEmpty()) {
            Map<NF3Statement, List<NfField>> map =
                    Node_cu_encodeLocation.getStatementMap(getFormStatementList());
            for (NF3Statement statement : map.keySet()) {
                try {
                    List<String> paths =
                            Node_cu_encodeLocation.pathToField(statement.getClass(), ControlObject.class);
                    for (String path : paths) {
                        List<NfField> locationFields = Node_cu_encodeLocation.getFields(
                                Node_cu_encodeLocation.createConcretePath(path, statement.getStatementPath()),
                                map.get(statement)
                        );
                        locationFields = locationFields
                                .stream()
                                .filter(x -> !x.getNffield_text().isEmpty())
                                .collect(Collectors.toList());
                        for (NfField nfField : locationFields) {
                            StringType stringType = (StringType) NodeUtils.getIType(nfField);
                            if (!stringType.getValue().equals(Emptyness.NOT_APPLICABLE)) {
                                ControlObject controlObject = retrieveControlObjects(stringType);
                                informControlObject(statement, controlObject);
                                encodeControlObject(nfField, controlObject);
                            }
                        }
                    }

                } catch (Exception e) {
                    logger.error("Some error while linking control object: ", e);
                    return CalculationResult.UNKNOWN;
                }
            }
            return CalculationResult.POSITIVE;
        }
        return CalculationResult.UNKNOWN;
    }

    private void informControlObject(NF3Statement statement, ControlObject controlObject) {
        InformAboutControlObject informAboutControlObject = new InformAboutControlObject();
        informAboutControlObject.setDomain(statement);
        informAboutControlObject.setRange(controlObject);
        getInformAboutControlObjectList().add(informAboutControlObject);
    }

    private void encodeControlObject(NfField controlObjectField, ControlObject controlObject) {
        EncodeControlObject encodeControlObject = new EncodeControlObject();
        encodeControlObject.setDomain(controlObjectField);
        encodeControlObject.setRange(controlObject);
        getEncodeControlObjectList().add(encodeControlObject);
    }

    private ControlObject retrieveControlObjects(StringType stringType) throws Exception {
        ControlObject controlObject = new ControlObjectC();
        controlObject.setName(stringType.getValue());
        controlObject.setUri(
                Node_cu_encodeLocation.removePrefix(
                        DataStoreService.INSTANCE.dataStoreQuerier.findEntity(controlObject)[0].get(0)
                )
        );
        return controlObject;
    }

}
