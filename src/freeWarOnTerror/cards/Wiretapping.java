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
import freeWarOnTerror.helpers.CardLookup;
import freeWarOnTerror.helpers.CountryLookup;

/**
 *
 * @author Emil
 */
public class Wiretapping extends Card {
    
    public Wiretapping(){
        super(CardLookup.WIRETAPPING);
    }
    
    @Override
    public Boolean getPlayable(){
        return getCountry(CountryLookup.CANADA).hasCells() || getCountry(CountryLookup.UNITEDSTATES).hasCells() || getCountry(CountryLookup.UNITEDKINGDOM).hasCells() || getCountry(CountryLookup.CANADA).getCadre() || getCountry(CountryLookup.UNITEDSTATES).getCadre() || getCountry(CountryLookup.UNITEDKINGDOM).getCadre();
    }
    
    @Override
    public void playEvent(){
        getCountry(CountryLookup.CANADA).killAllCells();
        getCountry(CountryLookup.CANADA).setCadre(false);
        getCountry(CountryLookup.UNITEDSTATES).killAllCells();
        getCountry(CountryLookup.UNITEDSTATES).setCadre(false);
        getCountry(CountryLookup.UNITEDKINGDOM).killAllCells();
        getCountry(CountryLookup.UNITEDKINGDOM).setCadre(false);
        getUS().draw();
    }
}
