/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror.abClasses;

import freeWarOnTerror.Cell;
import static freeWarOnTerror.Game.getCardsInPlay;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.Plot;
import static freeWarOnTerror.helpers.AppendToString.appendString;
import freeWarOnTerror.helpers.CountryLookup;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public abstract class Country extends Location {

    private final ArrayList<Country> adjacentCountries = new ArrayList<>();
    private final String name;
    private Boolean needsTesting = true;
    private Boolean cadre = false;
    private Boolean ctr = false;
    private final int id;
    private final int countryID;

    public Country(CountryLookup c){
        this.name = c.getName();
        this.countryID = c.ordinal();
        this.id = 0;
    }
    public Country(String name, int id) {
        this.name = name;
        this.id = id;
        countryID = 0;
    }
//--------------------------------GETTERS-------------------------------------------------------

    public int getCountryID(){
        return countryID;
    }
    
    public int getRecruit() {
        return getGovernance();
    }

    public int getAlignment() {
        return 0;
    }

    public Boolean needsTesting() {
        return needsTesting;
    }

    public ArrayList<Country> getAdjacentCountries() {
        return adjacentCountries;
    }

    public Boolean isCountryAdjacent(Country country) {
        for (Country c : adjacentCountries) {
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
    
    public boolean canRegimeChangeFrom(){
        return troopAmount() > 6;
    }

    public boolean canDisrupt(int ops) {
        return (hasTroops() || getAlignment() == 1 || this instanceof NonMuslimCountry) && (hasCells() || cadre) && ops >= getGovernance();
    }

    public Boolean canRecruit() {
        return cadre || hasCells();
    }

    public boolean canPlot() { 
        return getGovernance() < 4 && hasIdleCells();
    }
    
    public void placePlot(Plot p){
        p.move(this);
    }
    
    public Plot getRandomPlot(){
        return getPlots().get((int) Math.floor(Math.random()*plotAmount()));
    }

    public Boolean getCadre() {
        return cadre;
    }

    public Boolean getCTR() {
        return ctr;
    }

    public int getID() {
        return id;
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
            if (c.getCountries().length > 0) {
                for (int i : c.getCountries()) {
                    if (i == id) {
                        events += (c.getName() + " ");
                    }
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

    public void setGovernance(int governance) {
    }

    public void setAlignment(int alignment) {
    }

    public void setGovernanceAndAlignment(int governance, int alignment) {
    }

    public void addAdjacentCountry(Country c) {
        adjacentCountries.add(c);
    }

    public void addAdjacentCountries(ArrayList<Country> countries) {
        for (Country c : countries) {
            if (c != this) {
                adjacentCountries.add(c);
            }
        }
    }

    public void removeAdjacentCountry(Country c) {
        adjacentCountries.remove(c);
    }

    public void noLongerNeedsTesting() {
        needsTesting = false;
    }

    public void killCell() { //this method not finished
        Boolean activeCells = false;
        Cell anActiveCell = null;
        if (activeCells) {
        }
    }

    public void killAllCells() {
        if (hasCells()) {
            for (int i = 0; i < cellAmount(); i++) {
                getCells().get(0).kill();
            }
            setCadre(true);
        }
    }
    
    public void removeRandomPlot(){
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

    public abstract int getGovernance();

}
