package dd.soccer.perception.perceptingobjects;

/**
 * Created by Sergey on 18.10.2015.
 */
public class Player extends ObservableSoccerObject {

    //TODO: no reason to store name - better to store sign IFF (identification, friend or foe)
    private String commandName;
    private Integer number;

    public Player(String commandName, String numberString, String paramsString) {
        if (!commandName.isEmpty()) {
            this.commandName = commandName;
        }

        if (!numberString.isEmpty()) {
            number = Integer.parseInt(numberString);
        }
        String[] paramStringArray = paramsString.split(" ");

        setDistance(Double.parseDouble(paramStringArray[0]));
        setDirection(Double.parseDouble(paramStringArray[1]));
    }

    public String toString() {
        return "Player "
                + " command: " + commandName
                + " number: " + number
                + " distance: " + getDistance()
                + " direction: " + getDirection();
    }
}
