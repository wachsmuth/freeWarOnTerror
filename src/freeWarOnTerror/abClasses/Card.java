package freeWarOnTerror.abClasses;

import freeWarOnTerror.helpers.CountryLookup;

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
    private final CountryLookup[] countries;
    private int reserves = 0;

    public Card(String name, int ops, int alignment, Boolean removedAfterPlay, boolean mark, int id) {
        this.name = name;
        this.ops = ops;
        this.alignment = alignment;
        this.removedAfterPlay = removedAfterPlay;
        this.id = id;
        this.mark = mark;
        countries = null;
    }

    public Card(String name, int ops, int alignment, Boolean removedAfterPlay, boolean mark, int id, CountryLookup... lookups) {
        this.name = name;
        this.ops = ops;
        this.alignment = alignment;
        this.removedAfterPlay = removedAfterPlay;
        this.id = id;
        this.mark = mark;
        this.countries = lookups;
    }

//--------------------------------GETTERS-------------------------------------------------------
    public Boolean getPlayable() {
        return true;
    }

    public int getRealOps() {
        return ops;
    }

    public int getOps() {
        if (ops + reserves > 3){
            return 3;
        }
        return ops + reserves;
    }

    public int getAlignment() {
        return alignment;
    }

    public Boolean getRemoved() {
        return removedAfterPlay;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isMark() {
        return mark;
    }

    public CountryLookup[] getCountries() {
        return countries;
    }
//--------------------------------SETTERS-------------------------------------------------------

    public void modifyReserves(int change) {
        reserves += change;
    }

    public void setRemoved(Boolean removed) {
        removedAfterPlay = removed;
    }

//--------------------------------OVERRIDES-----------------------------------------------------
    @Override
    public String toString() {
        String string = name;
        string = string + " (";
        if (alignment == 1) {
            string = string + "Neutral";
        } else if (alignment == 2) {
            string = string + "US";
        } else if (alignment == 3) {
            string = string + "Jihadist";
        } else if (alignment == 0) {
            string = string + "Automatic Event";
        }
        string = string + ", " + ops + " ops)";
        return string;
    }
    //--------------------------------ABSTRACT METHODS----------------------------------------------

    public abstract void playEvent();

}
