/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror.abClasses;

import freeWarOnTerror.Cell;
import static freeWarOnTerror.Game.getCardsInPlay;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.Plot;
import static freeWarOnTerror.helpers.AppendToString.appendString;
import freeWarOnTerror.helpers.CountryLookup;
import freeWarOnTerror.helpers.Governance;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
public abstract class Country extends Location {

    private final ArrayList<Country> hello = new ArrayList<>();
    private final String name;
    private Boolean needsTesting = true;
    private Boolean cadre = false;
    private Boolean ctr = false;
    private final CountryLookup lookUp;

    public Country(CountryLookup c) {
        this.name = c.getName();
        this.lookUp = c;
    }

//--------------------------------GETTERS-------------------------------------------------------
    public boolean is(CountryLookup c) {
        return (this.lookUp == c);
    }

    public int getRecruit() {
        return getGovernance().getValue();
    }

    public Boolean needsTesting() {
        return needsTesting;
    }

    public ArrayList<Country> getAdjacentCountries() {
        return hello;
    }

    public List<MuslimCountry> getAdjacentMuslimCountries() {
        List<MuslimCountry> reList = new ArrayList<>();
        for (Country c : hello) {
            if (c instanceof MuslimCountry) {
                reList.add((MuslimCountry) c);
            }
        }
        return reList;
    }

    public Boolean isCountryAdjacent(Country country) {
        for (Country c : hello) {
            if (c == country) {
                return true;
            }
        }
        return false;
    }

    public Boolean canDeployTo(int ops) {
        return false;
    }

    public boolean canDeployFrom() {
        return false;
    }

    public boolean canRegimeChangeFrom() {
        return troopAmount() > 6;
    }

    public boolean canDisrupt(int ops) {
       return ((hasCells() || cadre) && ops >= getGovernance().getValue());
    }

    public Boolean canRecruit() {
        return cadre || hasCells();
    }

    public boolean canPlot() {
        return getGovernance().getValue() < 4 && hasIdleCells();
    }

    public void placePlot(Plot p) {
        p.move(this);
    }

    public Plot getRandomPlot() {
        return getPlots().get((int) Math.floor(Math.random() * plotAmount()));
    }

    public Boolean getCadre() {
        return cadre;
    }

    public Boolean getCTR() {
        return ctr;
    }

    public Boolean getNeedsTesting() {
        return needsTesting;
    }

    protected String moveablesString() {
        String string = "";
        if (troopAmount() > 1) {
            string += troopAmount() + " troops ";
        } else if (troopAmount() == 1) {
            string += troopAmount() + " troop ";
        }
        if (cellAmount() > 1) {
            string += cellAmount() + " cells ";
        } else if (cellAmount() == 1) {
            string += cellAmount() + " cell ";
        }
        if (plotAmount() > 1) {
            string += plotAmount() + " plots ";
        }
        if (plotAmount() == 1) {
            string += plotAmount() + " plot ";
        }
        return appendString(string);
    }

    protected String eventsToString() {
        String events = "";
        for (Card c : getCardsInPlay()) {
            for (CountryLookup country : c.getCountries()) {
                if (this.is(country)) {
                    events += (c.getName() + " ");
                }
            }
        }
        return events;
    }
//--------------------------------SETTERS-------------------------------------------------------

    @Override
    public void add(Moveable m) {
        if (m instanceof Cell) {
            testCountry();
        }
        super.add(m);
    }

    public void addAdjacentCountry(Country c) {
        hello.add(c);
    }

    public void addAdjacentCountries(ArrayList<Country> countries) {
        for (Country c : countries) {
            if (c != this) {
                hello.add(c);
            }
        }
    }

    public void removeAdjacentCountry(Country c) {
        hello.remove(c);
    }

    public void noLongerNeedsTesting() {
        needsTesting = false;
    }
/*
    public void killCell() { //this method not finished
        Boolean activeCells = false;
        Cell anActiveCell = null;
        if (activeCells) {
        }
    } */

    public void killAllCells() {
        if (hasCells()) {
            for (int i = 0; i < cellAmount(); i++) {
                getCells().get(0).kill();
            }
            setCadre(true);
        }
    }

    public void removeRandomPlot() {
        getRandomPlot().remove();
    }

    public void setCadre(Boolean cadre) {
        this.cadre = cadre;
    }

    public void setCTR(Boolean ctr) {
        this.ctr = ctr;
    }
//--------------------------------OVERRIDES-----------------------------------------------------

    @Override
    public String toString() {
        String string = appendString(name);
        return string;
    }

    @Override
    public String getName() {
        return name;
    }
//--------------------------------ABSTRACT METHODS----------------------------------------------

    public abstract Boolean canWarOfIdeas(int ops);

    public abstract void warOfIdeas();

    public abstract void resolvePlots();

    public abstract void testCountry();

    public abstract Governance getGovernance();

}
