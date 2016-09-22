package dd.soccer.common;

/**
 * Created by Sergey on 25.07.2016.
 */
public enum Constants {

    BALL_SIZE(0.085d),
    PLAYER_SIZE(0.3d),
    KICKABLE_MARGIN(0.7d),
    POSSESSING_AREA(BALL_SIZE.value + PLAYER_SIZE.value + KICKABLE_MARGIN.value);


    private double value;

    Constants(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
