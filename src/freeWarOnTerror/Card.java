package freeWarOnTerror;

/**
 *
 * @author Emil
 */
public class Card {

    private final int ops;
    private final int alignment; //0 is Automatic Event, 1 is Neutral, 2 is US, 3 is Jihadist

    public Card(int ops, int alignment) {
        this.ops = ops;
        this.alignment = alignment;
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
}
