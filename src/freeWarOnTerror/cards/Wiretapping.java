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
package freeWarOnTerror.cards;

import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.getUS;
import freeWarOnTerror.abClasses.Card;
import static freeWarOnTerror.helpers.CONSTANTS.CANADA;
import static freeWarOnTerror.helpers.CONSTANTS.UNITEDKINGDOM;
import static freeWarOnTerror.helpers.CONSTANTS.UNITEDSTATES;
import static freeWarOnTerror.helpers.CONSTANTS.WIRETAPPING;

/**
 *
 * @author Emil
 */
public class Wiretapping extends Card {
    
    public Wiretapping(){
        super("Wiretapping", 2, 2, false, true, WIRETAPPING);
    }
    
    @Override
    public Boolean getPlayable(){
        return getCountry(CANADA).hasCells() || getCountry(UNITEDSTATES).hasCells() || getCountry(UNITEDKINGDOM).hasCells() || getCountry(CANADA).getCadre() || getCountry(UNITEDSTATES).getCadre() || getCountry(UNITEDKINGDOM).getCadre();
    }
    
    @Override
    public void playEvent(){
        getCountry(CANADA).killAllCells();
        getCountry(CANADA).setCadre(false);
        getCountry(UNITEDSTATES).killAllCells();
        getCountry(UNITEDSTATES).setCadre(false);
        getCountry(UNITEDKINGDOM).killAllCells();
        getCountry(UNITEDKINGDOM).setCadre(false);
        getUS().draw();
    }
}
