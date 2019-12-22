package dd.union.sas.nodeimplementation;


import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.union.ontology.DataStoreService;
import dd.union.perception.notification.model.types.ILocation;
import dd.union.sas.objectproperty.Belong;
import dd.union.sas.objectproperty.EncodeLocation;
import dd.union.sas.objectproperty.FormStatement;
import dd.union.sas.objectproperty.InformAboutLocation;
import dd.union.sas.worldentity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Node_cu_encodeLocation extends dd.union.sas.computation.node.Node_cu_encodeLocation {

    private static Logger logger = LoggerFactory.getLogger(Node_cu_encodeLocation.class);

    public Node_cu_encodeLocation(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        if (getFormStatementList() != null && !getFormStatementList().isEmpty()) {
            Map<NF3Statement, List<NfField>> map = getStatementMap(getFormStatementList());
            for (NF3Statement statement : map.keySet()) {
                try {
                    List<String> paths = pathToField(statement.getClass(), Location.class);
                    Collections.sort(paths);
                    List<String[][]> locationNames = new ArrayList<>();
                    for (String path : paths) {
                        Optional<NfField> locationFiled = getField(
                                createConcretePath(path, statement.getStatementPath()),
                                map.get(statement)
                        );
                        if (locationFiled.isPresent()) {
                            ILocation locationType = (ILocation) NodeUtils.getIType(locationFiled.get());
                            locationNames.add(locationType.getNames());
                            for (DeploymentObject location : getLocations(locationType.getNames())) {
                                encodeLocation(locationFiled.get(), location);
                                informLocation(statement, location);
                            }
                        }
                    }
                    validate(locationNames);
                } catch (Exception e) {
                    logger.error("Some error while linking location: ", e);
                    return CalculationResult.UNKNOWN;
                }
            }
            try {
                initDeploymentObjects();
            } catch (Exception e) {
                logger.error("Some error while location initialization: ", e);
                return CalculationResult.UNKNOWN;
            }
            return CalculationResult.POSITIVE;
        }
        return CalculationResult.UNKNOWN;
    }

    private void informLocation(NF3Statement statement, DeploymentObject location) {
        InformAboutLocation informAboutLocation = new InformAboutLocation();
        informAboutLocation.setDomain(statement);
        informAboutLocation.setRange(location);
        getInformAboutLocationList().add(informAboutLocation);
    }

    private void encodeLocation(NfField locationFiled, DeploymentObject location) {
        EncodeLocation encodeLocation = new EncodeLocation();
        encodeLocation.setDomain(locationFiled);
        encodeLocation.setRange(location);
        getEncodeLocationList().add(encodeLocation);
    }

    public static Map<NF3Statement, List<NfField>> getStatementMap(List<FormStatement> formStatements) {
        Map<NF3Statement, List<NfField>> map = new HashMap<>();
        for (FormStatement formStatement : formStatements) {
            if (!map.containsKey(formStatement.getRange())) {
                map.put(formStatement.getRange(), new ArrayList<>());
            }
            map.get(formStatement.getRange()).add(formStatement.getDomain());
        }
        return map;
    }

    public static String createConcretePath(String path, String placeholder) {
        return path.replace("*", placeholder);
    }

    public static List<NfField> getFields(final String concretePath, List<NfField> fields) {
        return fields
                .stream()
                .filter(x -> x.getNffield_path().contains(concretePath))
                .collect(Collectors.toList());
    }

    public static Optional<NfField> getField(final String concretePath, List<NfField> fields) {
        return fields
                .stream()
                .filter(x -> x.getNffield_path().contains(concretePath))
                .findFirst();
    }

    public static List<String> pathToField(Class statement, Class c) throws Exception {
        return DataStoreService.INSTANCE.dataStoreQuerier.findRelation(
                query("path",
                        statement.getInterfaces()[0],
                        c),
                new String[]{"path"}
        )[0];
    }

    public static String query(String var, Class statement, Class pathTo) {
        String query = "SELECT ?" + var +
                " WHERE { " +
                " ?template a :NfContentTemplate ." +
                " ?template :templateFor :" + statement.getSimpleName() + " ." +
                " ?template :hasContentPath ?contentPath." +
                " ?contentPath :pathTo :" + pathTo.getSimpleName() + " ." +
                " ?contentPath :pathString ?path ." +
                "}";
        return query;
    }


    private void validate(List<String[][]> locationNames) throws Exception {
        DeploymentObject parent = new DeploymentObjectC();
        parent.setName(locationNames.get(0)[0][0]);
        List<Belong> belongs = new ArrayList<>();
        constructLocationChain(locationNames,
                parent,
                belongs);

        if (!validateLocationChain(belongs)) {
            throw new Exception("Location chain is not validated!");
        }
    }

    private void initDeploymentObjects() throws Exception {
        for (InformAboutLocation informAboutLocation : getInformAboutLocationList()) {
            String uri =
                    DataStoreService.INSTANCE.dataStoreQuerier
                            .findEntity(informAboutLocation.getRange())[0].get(0);
            informAboutLocation.getRange().setUri(removePrefix(uri));
        }
    }

    private boolean validateLocationChain(List<Belong> belongs) throws Exception {
        if (!belongs.isEmpty()) {
            String uri = DataStoreService.INSTANCE.dataStoreQuerier
                    .findRelation(belongs.toArray(new Belong[belongs.size()]))
                    [0].get(0);
            return uri != null ? true : false;
        }
        return true;
    }

    private List<DeploymentObject> getLocations(
            String[][] locationNames
    ) {
        List<DeploymentObject> deploymentObjects = new ArrayList<>();
        for (String[] names : locationNames) {
            for (String name : names) {
                DeploymentObject location = new DeploymentObjectC();
                location.setName(name);
                deploymentObjects.add(location);
            }
        }
        return deploymentObjects;
    }


    private void constructLocationChain(
            List<String[][]> locationNames,
            DeploymentObject parent,
            List<Belong> belongs
    ) {
        for (String[][] batchOfNames : locationNames) {
            for (String[] names : batchOfNames) {
                Belong belong = null;
                for (String name : names) {
                    DeploymentObject location = new DeploymentObjectC();
                    location.setName(name);
                    if (parent != null && !location.getName().equals(parent.getName())) {
                        belong = new Belong();
                        belong.setDomain(location);
                        belong.setRange(parent);
                        belongs.add(belong);
                    }
                }
                if (belong != null) {
                    parent = belong.getDomain();
                }
            }
        }
    }

    public static String removePrefix(String iri) {
        return iri.split("#")[1];
    }
}
