/*
 * Copyright (C) 2014 Emil
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package freeWarOnTerror.abClasses;

import freeWarOnTerror.Cell;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.Plot;
import freeWarOnTerror.Troop;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
public abstract class Location {

    private final ArrayList<Cell> cells = new ArrayList<>();
    private final ArrayList<Plot> plots = new ArrayList<>();
    private final ArrayList<Troop> troops = new ArrayList<>();
//--------------------------------GETTERS-------------------------------------------------------

    public ArrayList<Plot> getPlots() {
        return plots;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }
    
    public Cell pickIdleCell(){
        List<Cell> idleList = new ArrayList<>();
        //Checks
        if (cellAmount() == 1){
            cells.get(0).setIdle(false);
            return cells.get(0);
        } else if (cellAmount()== 0){
            return null;
        }
        for (Cell c : getCells()){
            if (c.isIdle()){
                idleList.add(c);
            }
        }
        Cell reCell = inputLoop(idleList);
        reCell.setIdle(false);
        return reCell;
    }
    
    public int troopAmount() {
        return troops.size();
    }

    public Boolean hasTroops() {
        return troops.size() > 0;
    }
    
        public int noCanDeployFrom() {
        if (this instanceof MuslimCountry){
            MuslimCountry country = (MuslimCountry) this;
            if (country.getRegimeChange() > 0){
                return troopAmount()-cellAmount();
            }
        }
        return troopAmount();
    }


    public int cellAmount() {
        return cells.size();
    }

    public Boolean hasCells() {
        return cells.size() > 0;
    }

    public Boolean hasPlots() {
        return plots.size() > 0;
    }

    public int plotAmount() {
        return plots.size();
    }
//--------------------------------SETTERS-------------------------------------------------------

    public void remove(Moveable m) {
        if (m instanceof Cell) {
            cells.remove((Cell) m);
        } else if (m instanceof Plot) {
            plots.remove((Plot) m);
        } else if (m instanceof Troop) {
            troops.remove((Troop) m);
        }
    }

    public void add(Moveable m) {
        if (m instanceof Cell) {
            cells.add((Cell) m);
        } else if (m instanceof Plot) {
            plots.add((Plot) m);
        } else if (m instanceof Troop) {
            troops.add((Troop) m);
        }
    }

    //--------------------------------ABSTRACT METHODS----------------------------------------------
    public abstract String getName();
}
