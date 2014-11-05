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
import freeWarOnTerror.Troop;
import static freeWarOnTerror.helpers.AppendToString.appendString;
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

    public Country(String name, int id) {
        this.name = name;
        this.id = id;
    }
    @Override
    public void add(Moveable m) {
        if (m instanceof Cell) {
            testCountry();
            getCells().add((Cell) m);
        } else if (m instanceof Plot) {
            getPlots().add((Plot) m);
        } else if (m instanceof Troop) {
            getTroops().add((Troop) m);
        }
    }
    public abstract void resolvePlots();

    public abstract void testCountry();

    public abstract int getGovernance();
    
    public int getRecruit(){
        return getGovernance();
    }

    public int getAlignment() {
        return 0;
    }

    public void setGovernance(int governance) {
    }

    public void setAlignment() {
    }

    public void setGovernanceAndAlignment(int governance, int alignment) {
    }

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
        if (activeCells) {

        }
    }
    
    public void killAllCells(){
        for (Cell c : getCells()){
            c.kill();
        }
        setCadre(true);
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
        return cadre || hasCells();
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

    @Override
    public String toString() {
        String string = appendString(name);
        return string;
    }
    
    public String moveablesString(){
        String string = "";
        if (troopAmount() > 1){
            string += troopAmount() + " troops ";
        }
        else if(troopAmount() == 1){
            string += troopAmount() + " troop ";
        }
        if (cellAmount() > 1){
            string += cellAmount() + " cells ";
        }
        else if (cellAmount() == 1){
            string += cellAmount() + " cell ";
        }
        if (plotAmount() > 1){
            string += plotAmount() + " plots ";
        }
        if (plotAmount() == 1){
            string += plotAmount() + " plot ";
        }
        return appendString(string);
    }
    
    public String eventsToString(){
        String events = "";
        for (Card c : getCardsInPlay()){
            if (c.getCountries().length > 0)
            for (int i : c.getCountries()){
                if (i == id){
                    events += (c.getName() + " ");
                }
            } 
        }
        return events;
    }

}
