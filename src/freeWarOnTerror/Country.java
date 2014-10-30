/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeWarOnTerror;

import java.util.*;

/**
 *
 * @author Emil
 */
public abstract class Country {

    private final ArrayList<Country> adjacentCountries = new ArrayList<>();
    private final ArrayList<Cell> cells = new ArrayList<>();
    private final String name;
    private Boolean needsTesting = true;
    private Boolean cadre = false;

    public Country(String name) {
        this.name = name;
    }

    public abstract void testCountry();

    public abstract int getGovernance();
    
    public abstract Boolean canWarOfIdeas(int ops);

    public Boolean needsTesting() {
        return needsTesting;
    }

    public void addAdjacentCountry(Country c) {
        adjacentCountries.add(c);
    }

    public void removeAdjacentCountry(Country c) {
        adjacentCountries.remove(c);
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

    public void killCell() {
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

    public Boolean canDeploy() {
        return false;
    }
    
}
