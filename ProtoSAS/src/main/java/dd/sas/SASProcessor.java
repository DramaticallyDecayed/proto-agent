package dd.sas;

import dd.sas.computation.Processable;

/**
 * Created by Sergey on 29.05.2016.
 */
public abstract class SASProcessor implements Processable {
    private final LevelHolder levelHolder;

    public SASProcessor(LevelHolder levelHolder) {
        this.levelHolder = levelHolder;
    }

    public LevelHolder getLevelHolder(){
        return levelHolder;
    }

    @Override
    public abstract boolean process();
}
