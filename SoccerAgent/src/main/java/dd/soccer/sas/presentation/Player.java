package dd.soccer.sas.presentation;

/**
 * Created by Sergey on 25.09.2015.
 */
public class Player extends SpaceObject {


    private int number;

    public Player() {
        super(null, null);
    }

    public Player(int number) {
        super(null, null);
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
