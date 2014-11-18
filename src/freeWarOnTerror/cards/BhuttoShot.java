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
import freeWarOnTerror.abClasses.Card;
import freeWarOnTerror.helpers.CountryLookup;
import static freeWarOnTerror.helpers.CountryLookup.PAKISTAN;

/**
 *
 * @author Emil
 */
public class BhuttoShot extends Card {
    
    public BhuttoShot(){
        super("Bhutto Shot", 2, 3, true, true, PAKISTAN.ordinal()); //Debug is this right??
    }
    
    @Override
    public Boolean getPlayable(){
        return getCountry(CountryLookup.PAKISTAN).hasCells();
    }
    
    @Override
    public void playEvent(){
    }
}
