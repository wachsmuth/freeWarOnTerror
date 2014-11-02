package freeWarOnTerror.abClasses;

import static freeWarOnTerror.Game.addCardToPlay;

/**
 *
 * @author Emil
 */
public abstract class Card {

    private final int ops;
    private final int alignment; //0 is Automatic Event, 1 is Neutral, 2 is US, 3 is Jihadist
    private Boolean removedAfterPlay;
    private final String name;
    private final int id;
    private final boolean mark;
    private final int[] countries;

    public Card(String name, int ops, int alignment, Boolean removedAfterPlay, boolean mark, int id) {
        this.name = name;
        this.ops = ops;
        this.alignment = alignment;
        this.removedAfterPlay = removedAfterPlay;
        this.id = id;
        this.mark = mark;
        countries = null;
    }
    
    public Card(String name, int ops, int alignment, Boolean removedAfterPlay, boolean mark, int id, int ... countries) {
        this.name = name;
        this.ops = ops;
        this.alignment = alignment;
        this.removedAfterPlay = removedAfterPlay;
        this.id = id;
        this.mark = mark;
        this.countries = countries;
    }

    public abstract void playEvent();

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

    @Override
    public String toString(){
        String string = name;
        string = string + " (";
        if (alignment == 1){
            string = string + "Neutral";
        }
        else if (alignment == 2){
            string = string + "US";
        }
        else if (alignment == 3){
            string = string + "Jihadist";
        }
        else if (alignment == 0){
            string = string + "Automatic Event";
        }
        string = string + ", " + ops + " ops)";
        return string;
    }
    
    public void addToPlay(){
        addCardToPlay(name);
    }

    public int getId() {
        return id;
    }
}
