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
import freeWarOnTerror.Plot;
import freeWarOnTerror.Troop;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;
import java.util.Collections;
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
    public List<Plot> getPlots() {
        Collections.sort(plots);
        return plots;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public Cell pickIdleCell() {
        List<Cell> idleList = new ArrayList<>();
        //Checks
        if (cellAmount() == 1) {
            cells.get(0).setIdle(false);
            return cells.get(0);
        } else if (cellAmount() == 0) {
            return null;
        }
        for (Cell c : getCells()) {
            if (c.isIdle()) {
                idleList.add(c);
            }
        }
        Cell reCell = inputLoop(idleList);
        reCell.setIdle(false);
        return reCell;
    }

    //Troops
    public int troopAmount() {
        return troops.size();
    }

    public Boolean hasTroops() {
        return !troops.isEmpty();
    }

    public int noCanDeployFrom() {
        return troopAmount(); //Overriden in muslimcountry
    }

    //CELLS
    public int cellAmount() {
        return cells.size();
    }

    public Boolean hasCells() {
        return !cells.isEmpty();
    }
    
    public Boolean hasIdleCells(){
        for (Cell c : getCells()){
            if (c.isIdle()){
                return true;
            }
        }
        return false;
    }

    //plots
    public Boolean hasPlots() {
        return !plots.isEmpty();
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
            Cell c = (Cell) m;
            if (cells.contains(c)) {
                return;
            }
            cells.add(c);
        } else if (m instanceof Plot) {
            Plot p = (Plot) m;
            if (plots.contains(p)) {
                return;
            }
            plots.add(p);
        } else if (m instanceof Troop) {
            Troop t = (Troop) m;
            if (troops.contains(t)) {
                return;
            }
            troops.add(t);
        }
    }

    //--------------------------------ABSTRACT METHODS----------------------------------------------
    public abstract String getName();
}