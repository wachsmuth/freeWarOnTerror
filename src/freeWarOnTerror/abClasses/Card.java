package freeWarOnTerror.abClasses;


import freeWarOnTerror.helpers.CardAlignment;
import static freeWarOnTerror.helpers.CardAlignment.AUTO;
import static freeWarOnTerror.helpers.CardAlignment.JIHAD;
import static freeWarOnTerror.helpers.CardAlignment.NEUTRAL;
import static freeWarOnTerror.helpers.CardAlignment.USA;
import freeWarOnTerror.helpers.CardLookup;
import freeWarOnTerror.helpers.CountryLookup;

/**
 *
 * @author Emil
 */
public abstract class Card {

    private final int ops;
    private final CardAlignment alignment; //0 is Automatic Event, 1 is Neutral, 2 is US, 3 is Jihadist
    private Boolean removedAfterPlay;
    private final String name;
    private final int id;
    private final boolean mark;
    private final CountryLookup[] countries;
    private int reserves = 0;
    private final CardLookup cardLookup;

    public Card(CardLookup c){
        this.name = c.getName();
        this.alignment = c.getAlignment();
        this.removedAfterPlay = c.isRemovedAfterPlay();
        this.id = c.ordinal();
        this.ops = c.getOps();
        this.mark = c.isMark();
        this.countries = c.getLookUps();
        this.cardLookup = c;
    }
    
    /*public Card(String name, int ops, CardAlignment alignment, Boolean removedAfterPlay, boolean mark, int id) {
        this.name = name;
        this.ops = ops;
        this.alignment = alignment;
        this.removedAfterPlay = removedAfterPlay;
        this.id = id;
        this.mark = mark;
        countries = null;
    }

    public Card(String name, int ops, CardAlignment alignment, Boolean removedAfterPlay, boolean mark, int id, CountryLookup... lookups) {
        this.name = name;
        this.ops = ops;
        this.alignment = alignment;
        this.removedAfterPlay = removedAfterPlay;
        this.id = id;
        this.mark = mark;
        this.countries = lookups;
    }*/

//--------------------------------GETTERS-------------------------------------------------------
    public Boolean is(CardLookup c){
        return (c == cardLookup);
    }
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

    public CardAlignment getAlignment() {
        return alignment;
    }

    public Boolean getRemoved() {
        return removedAfterPlay;
    }

    /*public int getId() {
        return id;
    }*/

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
        if (alignment == NEUTRAL) {
            string = string + "Neutral";
        } else if (alignment == USA) {
            string = string + "US";
        } else if (alignment == JIHAD) {
            string = string + "Jihadist";
        } else if (alignment == AUTO) {
            string = string + "Automatic Event";
        }
        string = string + ", " + ops + " ops)";
        return string;
    }
    //--------------------------------ABSTRACT METHODS----------------------------------------------

    public abstract void playEvent();

}
