package dd.soccer.perception.perceptingobjects;

import commonmodel.ElementState;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey on 28.10.2015.
 */
public class BodyState extends ElementState {

    enum ViewQuality{
        HIGH("high"),
        LOW("normal");
        private String type;
        private ViewQuality(String type){
            this.type = type;
        }
    }

    enum ViewWidth{
        NARROW("narrow"),
        NORMAL("normal"),
        WIDE("wide");
        private String type;
        private ViewWidth(String type){
            this.type = type;
        }
    }

    private static Map<String, ViewQuality> viewQualityMapper;
    private static Map<String, ViewWidth> viewWidthMapper;

    static {
        viewQualityMapper = new HashMap<>();
        for (ViewQuality vq : ViewQuality.values()) {
            viewQualityMapper.put(vq.type, vq);
        }

        viewWidthMapper = new HashMap<>();
        for (ViewWidth vw : ViewWidth.values()) {
            viewWidthMapper.put(vw.type, vw);
        }
    }

    private int stamina;
    private int effort;
    private double speed;
    private int kickCount;
    private int dashCount;
    private int turnCount;
    private int sayCount;
    private ViewQuality viewQuality;
    private ViewWidth viewWidth;


    public ViewQuality getViewQuality() {
        return viewQuality;
    }

    public void setViewQuality(String viewQuality) {
        this.viewQuality = viewQualityMapper.get(viewQuality);
    }

    public void setViewQuality(ViewQuality viewQuality) {
        this.viewQuality = viewQuality;
    }

    public ViewWidth getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(String viewWidth) {
        this.viewWidth = viewWidthMapper.get(viewWidth);
    }

    public void setViewWidth(ViewWidth viewWidth) {
        this.viewWidth = viewWidth;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getKickCount() {
        return kickCount;
    }

    public void setKickCount(int kickCount) {
        this.kickCount = kickCount;
    }

    public int getDashCount() {
        return dashCount;
    }

    public void setDashCount(int dashCount) {
        this.dashCount = dashCount;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public int getSayCount() {
        return sayCount;
    }

    public void setSayCount(int sayCount) {
        this.sayCount = sayCount;
    }


    public String toString(){
        return "EgoState: " +
                "view quality:" + viewQuality +
                " view width:" + viewWidth +
                " speed:" + speed;
    }

}
