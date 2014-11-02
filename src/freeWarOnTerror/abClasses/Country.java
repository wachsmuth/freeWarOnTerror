/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror.abClasses;

import freeWarOnTerror.Cell;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.Plot;
import freeWarOnTerror.Troop;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
public abstract class Country {

    private final ArrayList<Country> adjacentCountries = new ArrayList<>();
    private final ArrayList<Cell> cells = new ArrayList<>();
    private final List<Plot> plots = new ArrayList<>();
    private final List<Troop> troops = new ArrayList<>();
    private final String name;
    private Boolean needsTesting = true;
    private Boolean cadre = false;
    private Boolean ctr = false;
    private final int id;

    public Country(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public List<Plot> getPlots() {
        return plots;
    }
    
    public boolean hasPlots(){
        return plots.size() > 0;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public int troopAmount() {
        return troops.size();
    }
    
    public Boolean hasTroops(){
        return troops.size() > 0;
    }
    
    public int cellAmount(){
        return cells.size();
    }
    
    public Boolean hasCells(){
        return cells.size() > 0;
    }

    public abstract void resolvePlots();

    public abstract void testCountry();

    public abstract int getGovernance();
    
    public int getAlignment(){ return 0; }
    
    public void setGovernance(){}
    
    public void setAlignment(){}
    
    public void setGovernanceAndAlignment(int governance, int alignment){}

    public abstract Boolean canWarOfIdeas(int ops);
    
    public abstract void warOfIdeas();

    public Boolean needsTesting() {
        return needsTesting;
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

    public void noLongerNeedsTesting() {
        needsTesting = false;
    }

    public void killCell() { //this method not finished
        Boolean activeCells = false;
        Cell anActiveCell = null;
        for (Cell c : cells) {
            if (c.getActive()) {
                activeCells = true;
                anActiveCell = c;
                break;
            }
        }
        if (activeCells) {

        }
    }

    public Boolean canDeployTo() {
        return false;
    }
    
    public boolean canDeployFrom() {
        return false;
    }
    
    public boolean canDisrupt(int ops) {
        return (hasTroops() || getAlignment() == 1 || this instanceof NonMuslimCountry) && hasCells() && ops >= getGovernance();
    }

    public Boolean canRecruit() {
        return cadre || cells.size() > 0;
    }

    public boolean canPlot() { //NOTE: This method works for both minor jihad (if used on only Muslim countries) and plotting.
        return getGovernance() < 4 && hasCells();
    }
    
    public void setCadre(Boolean cadre) {
        this.cadre = cadre;
    }

    public Boolean getCadre() {
        return cadre;
    }

    public Boolean getCTR() {
        return ctr;
    }

    public void setCTR(Boolean ctr) {
        this.ctr = ctr;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getNeedsTesting() {
        return needsTesting;
    }
    

}
