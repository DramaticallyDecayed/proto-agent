package dd.soccer.sas.presentation;

/**
 * Created by Sergey on 25.09.2015.
 */
public class Player extends SpaceObject {
    public static final String NAME = "Player";

    private int number;

    public Player() {
        super(NAME, null, null);
    }

    public Player(int number) {
        super(NAME, null, null);
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
