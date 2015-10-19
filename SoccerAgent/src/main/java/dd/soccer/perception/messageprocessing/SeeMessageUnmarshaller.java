package dd.soccer.perception.messageprocessing;

import dd.soccer.perception.perceptingobjects.*;

/**
 * Created by Sergey on 18.10.2015.
 */
public class SeeMessageUnmarshaller extends MessageUnmarshaller{

    @Override
    protected void unmarshal() {
        while (hasNextElement()) {
            String element = getSeeElement();
            if (element.equals("flag")) {
                createFlag();
            } else if (element.equals("ball")) {
                createBall();
            } else if (element.equals("line")) {
                createLine();
            } else if (element.equals("player")) {
                createPlayer();
            } else if (element.equals("goal")) {
                createGoal();
            }
        }
    }

    private void createLine() {
        getSensorFrame().addObservableObject(new Line(getElementParams(),getElementParams()));
    }

    private void createPlayer(){
        Player player;
        String command = getElementParams();
        if (command.isEmpty()) {
            player = new Player("", "", getElementParams());
        } else {
            String[] pp = command.split(" ");
            if (pp.length > 1) {
                player = new Player(pp[0], pp[1], getElementParams());
            } else {
                player = new Player(pp[0], "", getElementParams());
            }
        }
        getSensorFrame().addObservableObject(player);
    }

    private void createBall(){
        //go up to the end of element name in order to skip ')' and move to parameters
        getElementParams();
        getSensorFrame().addObservableObject(new Ball(getElementParams()));
    }

    private void createGoal(){
        getSensorFrame().addObservableObject(new Goal(getElementParams(),getElementParams()));
    }

    private void createFlag(){
        getSensorFrame().addObservableObject(new Flag(getElementParams(),getElementParams()));
    }
}
