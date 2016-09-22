package dd.soccer.sas.nodeimplementation;

import dd.sas.computation.CalculationResult;
import dd.sas.computation.Level;
import dd.soccer.sas.objectproperty.MemberOf;
import dd.soccer.sas.worldentity.Player;
import dd.soccer.sas.worldentity.Team;
import dd.soccer.sas.worldentity.TeamPlayer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey on 25.07.2016.
 */
public class Node_cu_memberOf extends dd.soccer.sas.computation.node.Node_cu_memberOf {
    public Node_cu_memberOf(Level level) {
        super(level);
    }

    @Override
    public CalculationResult customProcess() {
        List<Team> teamList = getTeamList();
        List<TeamPlayer> teamPlayers = getTeamPlayerList();

        List<MemberOf> memberOfs = teamPlayers
                .stream()
                .filter(x -> (x instanceof Player) && (x.getTeamName() != null))
                .flatMap(x ->
                        teamList
                                .stream()
                                .filter(y -> y.getTeamName().equals(x.getTeamName()))
                                .map(y -> newDerivative(x, y))
                ).collect(Collectors.toList());

        if(!memberOfs.isEmpty()){
           return CalculationResult.POSITIVE;
        }else{
            return CalculationResult.UNKNOWN;
        }
    }

}
