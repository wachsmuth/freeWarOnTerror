package freeWarOnTerror;

/**
 *
 * @author Emil
 */
public abstract class Card {

    private final int ops;
    private final int alignment; //0 is Automatic Event, 1 is Neutral, 2 is US, 3 is Jihadist
    private Boolean removedAfterPlay;

    public Card(int ops, int alignment, Boolean removedAfterPlay) {
        this.ops = ops;
        this.alignment = alignment;
        this.removedAfterPlay = removedAfterPlay;
    }

    public abstract void play();

    public Boolean getPlayable() {
        return true;
    }
    
    public int getOps(){
        return ops;
    }
    
    public int getAlignment(){
        return alignment;
    }
    
    public Boolean getRemoved(){
        return removedAfterPlay;
    }
    
    public void setRemoved(Boolean removed){
        removedAfterPlay = removed;
    }
}
